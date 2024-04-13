package org.myungkeun.crud_r2dbc_webflux_240410.services;

import org.myungkeun.crud_r2dbc_webflux_240410.entities.User;
import org.myungkeun.crud_r2dbc_webflux_240410.payload.UpdateUsernameRequest;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<String> getEmail(String token);

//    Mono<String> getUserInfo(String token);

    Mono<User> updateUsername(String token, String newName);
}
