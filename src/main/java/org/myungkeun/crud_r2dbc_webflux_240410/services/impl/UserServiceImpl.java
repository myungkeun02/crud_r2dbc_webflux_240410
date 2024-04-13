package org.myungkeun.crud_r2dbc_webflux_240410.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.myungkeun.crud_r2dbc_webflux_240410.config.jwt.JwtValidationUtil;
import org.myungkeun.crud_r2dbc_webflux_240410.entities.User;
import org.myungkeun.crud_r2dbc_webflux_240410.repositories.UserRepository;
import org.myungkeun.crud_r2dbc_webflux_240410.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtValidationUtil jwtValidationUtil;

    @Override
    public Mono<String> getEmail(String token) {
        return Mono.just(jwtValidationUtil.getEmail(token))
                .flatMap(userRepository::findByEmail)
                .filter(Objects::nonNull)
                .map(User::getEmail)
                .switchIfEmpty(Mono.<String>error(new RuntimeException("error when get emaill - not found email ")));
    }
}
