package org.e2cho.e2cho_shopping_mall.controller.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_shopping_mall.domain.user.User;
import org.e2cho.e2cho_shopping_mall.dto.user.UserInfoInquiry;
import org.e2cho.e2cho_shopping_mall.dto.user.UserInfoDelete;
import org.e2cho.e2cho_shopping_mall.dto.user.UserInfoUpdate;
import org.e2cho.e2cho_shopping_mall.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/userInfo")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<UserInfoInquiry.Response> getUserInfo(@AuthenticationPrincipal User user) {

        UserInfoInquiry.Dto dto = userService.getUserInfo(user);

        return new ResponseEntity<>(UserInfoInquiry.Response.fromDto(dto), HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<UserInfoUpdate.Response> updateUserInfo(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody UserInfoUpdate.Request request
            ) {

         userService.updateUserInfo(user, request);

        return new ResponseEntity<>(UserInfoUpdate.Response.createNewResponse(), HttpStatus.CREATED);
    }

    @DeleteMapping()
    public ResponseEntity<UserInfoDelete.Response> deleteUserInfo(@AuthenticationPrincipal User user) {

        UserInfoDelete.Dto dto = userService.deleteUserInfo(user);

        return new ResponseEntity<>(UserInfoDelete.Response.fromDto(dto), HttpStatus.CREATED);
    }

}
