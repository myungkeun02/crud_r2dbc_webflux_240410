package org.myungkeun.crud_r2dbc_webflux_240410.services.impl;

import lombok.AllArgsConstructor;
import org.myungkeun.crud_r2dbc_webflux_240410.config.security.UserPasswordEncoder;
import org.myungkeun.crud_r2dbc_webflux_240410.config.jwt.JwtTokenUtil;
import org.myungkeun.crud_r2dbc_webflux_240410.entities.Role;
import org.myungkeun.crud_r2dbc_webflux_240410.entities.User;
import org.myungkeun.crud_r2dbc_webflux_240410.payload.LoginRequest;
import org.myungkeun.crud_r2dbc_webflux_240410.payload.RegisterRequest;
import org.myungkeun.crud_r2dbc_webflux_240410.repositories.UserRepository;
import org.myungkeun.crud_r2dbc_webflux_240410.services.AuthService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor

public class AuthServiceImpl implements AuthService {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final UserPasswordEncoder userPasswordEncoder;
    @Override
    public Mono<User> register(RegisterRequest request) {
        return userRepository.findByEmail(request.getEmail())
                .filter(Objects::nonNull)
                .flatMap(user -> Mono.<User>error(new RuntimeException("Email already registered")))
                .switchIfEmpty(saveNewUser(request));
    }

    // 저장하는 로직

    private Mono<User> saveNewUser(RegisterRequest request) {
        User newUser = User.builder()
                .email(request.getEmail())
                .phone(request.getPhone())
                .username(request.getUsername())
                .password(userPasswordEncoder.encode(request.getPassword()))
                .roles(Role.ROLE_USER.name())
                .enabled(Boolean.TRUE)
                .createdBy(request.getUsername())
                .createdAt(LocalDateTime.now())
                .build();
        return userRepository.save(newUser);
    }

    @Override
    public Mono<String> login(LoginRequest request) {
        return userRepository.findByEmail(request.getEmail())
                .filter(user -> userPasswordEncoder.matches(request.getPassword(), user.getPassword()))
                .map(jwtTokenUtil::generateAccessToken)
                .switchIfEmpty(Mono.<String>error(new RuntimeException("Login failed - not found email or wrong password")));
    }
}
