package org.e2cho.e2cho_shopping_mall.service.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.e2cho.e2cho_shopping_mall.constant.ErrorType;
import org.e2cho.e2cho_shopping_mall.constant.SnsType;
import org.e2cho.e2cho_shopping_mall.domain.user.User;
import org.e2cho.e2cho_shopping_mall.dto.auth.login.Login;
import org.e2cho.e2cho_shopping_mall.expection.CustomErrorException;
import org.e2cho.e2cho_shopping_mall.provider.JwtTokenProvider;
import org.e2cho.e2cho_shopping_mall.repository.UserRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class LoginService {

    private final AuthUtilService authUtilService;

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final StringRedisTemplate redisTemplate;

    public Login.Dto login(Login.Request request){

        String snsId = "";

        if(request.getSnsType() == SnsType.Kakao){
            snsId = authUtilService.getKakaoUserSnsId(request.getAccessToken());
        }

        if(request.getSnsType() == SnsType.Naver){
            snsId = authUtilService.getNaverUserSnsId(request.getAccessToken());
        }

        if(request.getSnsType() == SnsType.Google){
            snsId = authUtilService.getGoogleUserSnsId(request.getAccessToken());
        }

        User validatedUser = userRepository.findBySnsId(snsId)
                .orElseThrow(() -> new CustomErrorException(ErrorType.UserNotFoundError));


        String accessToken = jwtTokenProvider.generateAccessToken(validatedUser.getSnsId());
        String refreshToken = jwtTokenProvider.generateRefreshToken();

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(refreshToken, accessToken);

        return Login.Dto.of(accessToken, refreshToken);

    }

}



