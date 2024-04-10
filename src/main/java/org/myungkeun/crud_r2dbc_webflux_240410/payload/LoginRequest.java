package org.myungkeun.crud_r2dbc_webflux_240410.payload;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data

public class LoginRequest {
    private String email;
    private String password;
}
