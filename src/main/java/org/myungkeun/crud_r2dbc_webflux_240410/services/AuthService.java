package org.myungkeun.crud_r2dbc_webflux_240410.services;

import org.myungkeun.crud_r2dbc_webflux_240410.entities.User;
import org.myungkeun.crud_r2dbc_webflux_240410.payload.LoginRequest;
import org.myungkeun.crud_r2dbc_webflux_240410.payload.RegisterRequest;
import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<User> register(RegisterRequest request);
    Mono<String> login(LoginRequest request);
}
