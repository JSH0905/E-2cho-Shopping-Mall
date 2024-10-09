package org.e2cho.e2cho_shopping_mall.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.e2cho.e2cho_shopping_mall.constant.ErrorType;
import org.e2cho.e2cho_shopping_mall.constant.TokenType;
import org.e2cho.e2cho_shopping_mall.expection.CustomErrorException;
import org.e2cho.e2cho_shopping_mall.expection.ErrorResponseDto;
import org.e2cho.e2cho_shopping_mall.provider.JwtTokenProvider;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {
        String accessToken = jwtTokenProvider.resolveToken(request.getHeader("Authorization"));

        if (accessToken == null) {
            chain.doFilter(request, response);
            return;
        }

        try {
            jwtTokenProvider.validateToken(TokenType.ACCESS_TOKEN, accessToken);
            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } catch (Exception e) {
            if (request.getRequestURI().equals("/api/auth/login") && request.getMethod().equals(HttpMethod.POST.name())) {
                chain.doFilter(request, response);
            }

            if (request.getRequestURI().equals("/api/auth/refreshToken") && request.getMethod().equals(HttpMethod.POST.name())) {
                chain.doFilter(request, response);
            }

            if (request.getRequestURI().equals("/api/auth/logout") && request.getMethod().equals(HttpMethod.POST.name())) {
                chain.doFilter(request, response);
            }

            jwtExceptionHandler(response, e);
        }
    }

    // 토큰에 대한 오류가 발생했을 때, 커스터마이징해서 Exception 처리 값을 클라이언트에게 알려준다.
    public void jwtExceptionHandler(HttpServletResponse response, Exception e) {
        log.info("Exception Info:" + e.getMessage());
        ErrorType errorType = ErrorType.InternalServerError;
        if (e instanceof CustomErrorException) {
            errorType = ((CustomErrorException) e).getErrorType();
        }

        response.setStatus(errorType.getHttpStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String json = new ObjectMapper().writeValueAsString(ErrorResponseDto.of(errorType.name(), errorType.getMessage()));
            response.getWriter().write(json);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }


}
