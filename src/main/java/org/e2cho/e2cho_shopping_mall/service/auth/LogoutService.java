package org.e2cho.e2cho_shopping_mall.service.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.e2cho.e2cho_shopping_mall.constant.ErrorType;
import org.e2cho.e2cho_shopping_mall.expection.CustomErrorException;
import org.e2cho.e2cho_shopping_mall.provider.JwtTokenProvider;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogoutService {

    private final RedisTemplate<String, String> redisTemplate;
    private final JwtTokenProvider jwtTokenProvider;

    public void logout(String refreshToken){

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        String resolvedRefreshToken = jwtTokenProvider.resolveToken(refreshToken);

        if (resolvedRefreshToken == null){
            throw new CustomErrorException(ErrorType.NotValidRefreshTokenError);
        }

        String savedAccessToken = valueOperations.get(resolvedRefreshToken);

        if (savedAccessToken == null){
            throw new CustomErrorException(ErrorType.NotValidRefreshTokenError);
        }

        valueOperations.getAndDelete(resolvedRefreshToken);
    }
}
