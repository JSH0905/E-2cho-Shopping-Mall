package org.e2cho.e2cho_shopping_mall.controller.auth;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_shopping_mall.dto.auth.login.Login;
import org.e2cho.e2cho_shopping_mall.dto.auth.logout.Logout;
import org.e2cho.e2cho_shopping_mall.dto.auth.signup.Signup;
import org.e2cho.e2cho_shopping_mall.dto.auth.token.ReIssueToken;
import org.e2cho.e2cho_shopping_mall.service.auth.LoginService;
import org.e2cho.e2cho_shopping_mall.service.auth.LogoutService;
import org.e2cho.e2cho_shopping_mall.service.auth.ReIssueTokenService;
import org.e2cho.e2cho_shopping_mall.service.auth.SignupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final SignupService signupService;
    private final LoginService loginService;
    private final LogoutService logoutService;
    private final ReIssueTokenService reIssueTokenService;

    @PostMapping("/signup")
    public ResponseEntity<Signup.Response> signup(@Valid @RequestBody Signup.Request request){

        Signup.Dto dto = signupService.signup(request);

        return new ResponseEntity<>(Signup.Response.fromDto(dto), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<Login.Response> login(@Valid @RequestBody Login.Request request) {

        Login.Dto dto = loginService.login(request);

        return new ResponseEntity<>(Login.Response.fromDto(dto), HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<Logout.Response> logout(
            @RequestHeader("Authorization-refresh") String refreshToken
    ) {
        logoutService.logout(refreshToken);

        return new ResponseEntity<>(Logout.Response.success(), HttpStatus.CREATED);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<ReIssueToken.Response> reIssueToken(
            @RequestHeader("Authorization") String accessToken,
            @RequestHeader("Authorization-refresh") String refreshToken
    ){
        ReIssueToken.Dto dto = reIssueTokenService.reIssueToken(accessToken, refreshToken);

        return new ResponseEntity<>(ReIssueToken.Response.fromDto(dto), HttpStatus.CREATED);
    }

}
