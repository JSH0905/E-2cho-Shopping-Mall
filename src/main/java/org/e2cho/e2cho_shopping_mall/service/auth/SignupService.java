package org.e2cho.e2cho_shopping_mall.service.auth;

import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_shopping_mall.constant.ErrorType;
import org.e2cho.e2cho_shopping_mall.constant.SnsType;
import org.e2cho.e2cho_shopping_mall.domain.user.User;
import org.e2cho.e2cho_shopping_mall.dto.auth.provider.google.GoogleUserInfo;
import org.e2cho.e2cho_shopping_mall.dto.auth.provider.kakao.KakaoUserInfo;
import org.e2cho.e2cho_shopping_mall.dto.auth.provider.naver.NaverUserInfo;
import org.e2cho.e2cho_shopping_mall.dto.auth.signup.Signup;
import org.e2cho.e2cho_shopping_mall.expection.CustomErrorException;
import org.e2cho.e2cho_shopping_mall.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final AuthUtilService authUtilService;

    private final UserRepository userRepository;

    public Signup.Dto signup(Signup.Request request) {

        String snsId = null;
        SnsType snsType = null;
        String name = null;
        String email = null;
        String profileImage = null;

        if(request.getSnsType() == SnsType.Kakao){
            KakaoUserInfo.Response kakaoUserInfo = authUtilService.getKakaoUserInfo(request.getAccessToken());
            snsId = String.valueOf(kakaoUserInfo.getId());
            snsType = SnsType.Kakao;
            name = kakaoUserInfo.getProperties().getNickname();
            email = kakaoUserInfo.getKakaoAccount().getEmail();
            profileImage = kakaoUserInfo.getProperties().getProfileImage();
        }

        if(request.getSnsType() == SnsType.Naver){
            NaverUserInfo.Response naverUserInfo = authUtilService.getNaverUserInfo(request.getAccessToken());
            snsId = naverUserInfo.getResponseDetails().getId();
            snsType = SnsType.Naver;
            name = naverUserInfo.getResponseDetails().getName();
            email = naverUserInfo.getResponseDetails().getEmail();
            profileImage = naverUserInfo.getResponseDetails().getProfileImage();
        }

        if(request.getSnsType() == SnsType.Google){
            GoogleUserInfo.Response googleUserInfo = authUtilService.getGoogleUserInfo(request.getAccessToken());
            snsId = String.valueOf(googleUserInfo.getSub());
            snsType = SnsType.Google;
            name = googleUserInfo.getName();
            email = googleUserInfo.getEmail();
            profileImage = googleUserInfo.getPicture();
        }

        Optional<User> userOptional = userRepository.findBySnsId(snsId);
        if(userOptional.isPresent()){
            throw new CustomErrorException(ErrorType.AlreadyExistUserError);
        }

        User newUser = userRepository.save(User.of(snsId, snsType, name, email, profileImage));
        return Signup.Dto.fromEntity(newUser);
    }
}
