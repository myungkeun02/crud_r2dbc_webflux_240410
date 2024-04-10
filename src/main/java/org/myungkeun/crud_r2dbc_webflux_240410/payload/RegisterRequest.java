package org.myungkeun.crud_r2dbc_webflux_240410.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterRequest extends LoginRequest{
    private String username;
    private String phone;
}
