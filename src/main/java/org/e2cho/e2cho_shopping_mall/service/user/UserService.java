package org.e2cho.e2cho_shopping_mall.service.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_shopping_mall.constant.SnsType;
import org.e2cho.e2cho_shopping_mall.domain.user.User;
import org.e2cho.e2cho_shopping_mall.dto.user.UserInfoInquiry;
import org.e2cho.e2cho_shopping_mall.dto.user.UserInfoDelete;
import org.e2cho.e2cho_shopping_mall.dto.user.UserInfoUpdate;
import org.e2cho.e2cho_shopping_mall.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserCommonService userCommonService;

    private final UserRepository userRepository;

    public UserInfoInquiry.Dto getUserInfo(User user){

        userCommonService.validateUser(user);

        return UserInfoInquiry.Dto.fromEntity(user);
    }

    @Transactional
    public void updateUserInfo(User user, UserInfoUpdate.Request request){

        User validatedUser = userCommonService.getValidatedUser(user);

        validatedUser.update(request);
    }

    @Transactional
    public UserInfoDelete.Dto deleteUserInfo(User user){

        userCommonService.validateUser(user);

        Long deletedUserId = user.getId();
        SnsType deletedUserSnsType = user.getSnsType();
        String deletedUserSnsId = user.getSnsId();
        String deletedUserName = user.getName();

        userRepository.delete(user);

        return UserInfoDelete.Dto.fromEntity(deletedUserId, deletedUserSnsType, deletedUserSnsId, deletedUserName);

    }

}
