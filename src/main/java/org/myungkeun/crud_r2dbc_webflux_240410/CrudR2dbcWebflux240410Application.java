package org.myungkeun.crud_r2dbc_webflux_240410;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@EnableR2dbcRepositories
public class CrudR2dbcWebflux240410Application {

    public static void main(String[] args) {
        SpringApplication.run(CrudR2dbcWebflux240410Application.class, args);
    }

}
