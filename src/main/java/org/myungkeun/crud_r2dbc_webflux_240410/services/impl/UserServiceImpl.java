package org.myungkeun.crud_r2dbc_webflux_240410.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.myungkeun.crud_r2dbc_webflux_240410.config.jwt.JwtValidationUtil;
import org.myungkeun.crud_r2dbc_webflux_240410.entities.Role;
import org.myungkeun.crud_r2dbc_webflux_240410.entities.User;
import org.myungkeun.crud_r2dbc_webflux_240410.payload.RegisterRequest;
import org.myungkeun.crud_r2dbc_webflux_240410.payload.UpdateUsernameRequest;
import org.myungkeun.crud_r2dbc_webflux_240410.repositories.UserRepository;
import org.myungkeun.crud_r2dbc_webflux_240410.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtValidationUtil jwtValidationUtil;

    @Override
    public Mono<String> getEmail(String token) {
        return Mono.just(jwtValidationUtil.getEmail(token))
                .flatMap(userRepository::findByEmail) // 데이터를 검색
                .filter(Objects::nonNull) // 에러 핸들링
                .map(User::getEmail) // 오브젝트에서 데이터를 가져옴
                .switchIfEmpty(Mono.<String>error(new RuntimeException("error when get emaill - not found email "))); // 공백 에러 핸들링
    }

    @Override
    public Mono<User> updateUsername(String token, String newName) {
        System.out.println(token + newName);
        return Mono.just(jwtValidationUtil.getEmail(token))
                .flatMap(userRepository::findByEmail)
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.<User>error(new RuntimeException("error when getEmail - not found email")))
                .flatMap(user -> {
                    System.out.println("+++++++++++++++" + user);
                    user.setUsername(newName);
                    return userRepository.save(user);
                });
    }
}
