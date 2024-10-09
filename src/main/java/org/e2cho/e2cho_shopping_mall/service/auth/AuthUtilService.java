package org.e2cho.e2cho_shopping_mall.service.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.e2cho.e2cho_shopping_mall.constant.ErrorType;
import org.e2cho.e2cho_shopping_mall.dto.auth.infoProvider.google.GoogleUserInfo;
import org.e2cho.e2cho_shopping_mall.dto.auth.infoProvider.kakao.KakaoUserInfo;
import org.e2cho.e2cho_shopping_mall.dto.auth.infoProvider.naver.NaverUserInfo;
import org.e2cho.e2cho_shopping_mall.expection.CustomErrorException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthUtilService {

    public String getKakaoUserSnsId(String accessToken){
        KakaoUserInfo.Response kakaoUserInfo = getKakaoUserInfo(accessToken);
        return String.valueOf(kakaoUserInfo.getId());
    }

    public String getNaverUserSnsId(String accessToken) {
        NaverUserInfo.Response naverUserInfo = getNaverUserInfo(accessToken);
        return naverUserInfo.getResponseDetails().getId();
    }


    public String getGoogleUserSnsId(String accessToken) {
        GoogleUserInfo.Response googleUserInfo = getGoogleUserInfo(accessToken);
        return String.valueOf(googleUserInfo.getSub());
    }


    public KakaoUserInfo.Response getKakaoUserInfo(String accessToken){

        WebClient webClient = WebClient.builder().build();
        String url = "https://kapi.kakao.com/v2/user/me";

        return webClient.get()
                .uri(url)  // Replace with your actual URL
                .header(
                        "Authorization",
                        accessToken
                )
                .retrieve()
                .onStatus(status -> status.value() == 401,
                        this::handleKakao401Error)
                .onStatus(status -> status.value() == 403,
                        this::handleKakao403Error)
                .bodyToMono(KakaoUserInfo.Response.class)
                .block();

    }

    public NaverUserInfo.Response getNaverUserInfo(String accessToken) {

        WebClient webClient = WebClient.builder().build();

        return webClient.get()
                .uri("https://openapi.naver.com/v1/nid/me")  // Replace with your actual URL
                .header(
                        "Authorization",
                        accessToken
                )
                .retrieve()
                .onStatus(status -> status.value() == 401,
                        this::handleNaver401Error)
                .onStatus(status -> status.value() == 403,
                        this::handleNaver403Error)
                .bodyToMono(NaverUserInfo.Response.class)
                .block();

    }

    public GoogleUserInfo.Response getGoogleUserInfo(String accessToken) {
        WebClient webClient = WebClient.builder().build();

        return webClient.get()
                .uri("https://www.googleapis.com/oauth2/v3/userinfo")  // Replace with your actual URL
                .header(
                        "Authorization",
                        accessToken
                )
                .retrieve()
                .onStatus(status -> status.value() == 401,
                        this::handleGoogle401Error)
                .onStatus(status -> status.value() == 403,
                        this::handleGoogle403Error)
                .bodyToMono(GoogleUserInfo.Response.class)
                .block();


    }

    public Mono<? extends Throwable> handleKakao401Error(ClientResponse response) {
        return response.bodyToMono(String.class)
                .flatMap(errorBody -> Mono.error(new CustomErrorException(ErrorType.UnauthorizedKakaoError)));
    }

    public Mono<? extends Throwable> handleKakao403Error(ClientResponse response) {
        return response.bodyToMono(String.class)
                .flatMap(errorBody -> Mono.error(new CustomErrorException(ErrorType.ForbiddenKakaoError)));

    }

    public Mono<? extends Throwable> handleNaver401Error(ClientResponse response) {
        return response.bodyToMono(String.class)
                .doOnNext(errorBody -> log.error("Error Body: {}", errorBody))
                .flatMap(errorBody -> Mono.error(new CustomErrorException(ErrorType.UnauthorizedNaverError)));
    }

    public Mono<? extends Throwable> handleNaver403Error(ClientResponse response) {
        return response.bodyToMono(String.class)
                .flatMap(errorBody -> Mono.error(new CustomErrorException(ErrorType.ForbiddenNaverError)));

    }

    public Mono<? extends Throwable> handleGoogle401Error(ClientResponse response) {
        return response.bodyToMono(String.class)
                .doOnNext(errorBody -> log.error("Error Body: {}", errorBody))
                .flatMap(errorBody -> Mono.error(new CustomErrorException(ErrorType.UnauthorizedGoogleError)));
    }

    public Mono<? extends Throwable> handleGoogle403Error(ClientResponse response) {
        return response.bodyToMono(String.class)
                .flatMap(errorBody -> Mono.error(new CustomErrorException(ErrorType.ForbiddenGoogleError)));

    }
}
