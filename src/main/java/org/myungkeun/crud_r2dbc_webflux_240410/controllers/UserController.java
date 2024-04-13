package org.myungkeun.crud_r2dbc_webflux_240410.controllers;

import lombok.AllArgsConstructor;
import org.myungkeun.crud_r2dbc_webflux_240410.config.filter.ReactiveRequestContextHolder;
import org.myungkeun.crud_r2dbc_webflux_240410.payload.BaseResponse;
import org.myungkeun.crud_r2dbc_webflux_240410.payload.UpdateUsernameRequest;
import org.myungkeun.crud_r2dbc_webflux_240410.payload.UserResponse;
import org.myungkeun.crud_r2dbc_webflux_240410.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @GetMapping
    public Mono<ResponseEntity<BaseResponse<UserResponse>>> getUserByToken() {
        return ReactiveRequestContextHolder.getTokenAuth()
                .flatMap(userService::getEmail)
                .map(userEmail -> ResponseEntity.ok(BaseResponse.<UserResponse>builder()
                        .code(200)
                        .message("get user success")
                        .data(new UserResponse(userEmail))
                        .build()))
                .onErrorResume(throwable -> Mono.just(ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .body(BaseResponse.<UserResponse>builder()
                                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .message(throwable.getMessage())
                                .data(null)
                                .build())));
    }

    @PutMapping
    public Mono<ResponseEntity<BaseResponse<UserResponse>>> updateUsername(
            @RequestBody UpdateUsernameRequest request
    ) {
        return ReactiveRequestContextHolder.getTokenAuth()
                .flatMap(token -> userService.updateUsername(token, request.getNewUsername()))
                .map(user -> ResponseEntity.ok(BaseResponse.<UserResponse>builder()
                        .code(200)
                        .message("success")
                        .data(new UserResponse(user.getUsername()))
                        .build()))
                .onErrorResume(throwable -> Mono.just(ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .body(BaseResponse.<UserResponse>builder()
                                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .message(throwable.getMessage())
                                .data(null)
                                .build())));
    }
}
