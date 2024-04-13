package org.myungkeun.crud_r2dbc_webflux_240410.payload;

import lombok.Data;

@Data

public class UpdateUsernameRequest {
    private String newUsername;
}
