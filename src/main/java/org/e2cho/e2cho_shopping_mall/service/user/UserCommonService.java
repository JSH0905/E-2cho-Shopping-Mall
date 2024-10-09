package org.e2cho.e2cho_shopping_mall.service.user;

import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_shopping_mall.constant.ErrorType;
import org.e2cho.e2cho_shopping_mall.domain.user.User;
import org.e2cho.e2cho_shopping_mall.expection.CustomErrorException;
import org.e2cho.e2cho_shopping_mall.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommonService {

    private final UserRepository userRepository;

    public void validateUser(User user){
        if (user == null) {
            throw new CustomErrorException(ErrorType.UserNotFoundError);
        }
    }

    public User getValidatedUser(User user){

        return userRepository.findBySnsId(user.getSnsId())
                .orElseThrow(() -> new CustomErrorException(ErrorType.UserNotFoundError));

    }
}
