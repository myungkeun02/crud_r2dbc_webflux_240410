package org.myungkeun.crud_r2dbc_webflux_240410.config.security;

import lombok.AllArgsConstructor;
import org.myungkeun.crud_r2dbc_webflux_240410.config.jwt.JwtValidationUtil;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {
    private final JwtValidationUtil jwtValidationUtil;
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        String email = jwtValidationUtil.getEmail(token);
        return Mono.justOrEmpty(jwtValidationUtil.isTokenValid(token, email))
                .flatMap(isValid -> AuthenticationEmailAndToken(email, token));
    }

    private Mono<Authentication> AuthenticationEmailAndToken(String email, String token) {
        return Mono.just(jwtValidationUtil.getClaimsToken(token))
                .map(claims -> {
                    List<String> roleList = claims.get("roles", List.class);
                    return new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            roleList.stream()
                                    .map(SimpleGrantedAuthority::new)
                                    .collect(Collectors.toList())
                    );
                });
    }

}
