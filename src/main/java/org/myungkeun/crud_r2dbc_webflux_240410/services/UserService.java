package org.myungkeun.crud_r2dbc_webflux_240410.services;

import org.myungkeun.crud_r2dbc_webflux_240410.entities.User;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<String> getEmail(String token);

}
