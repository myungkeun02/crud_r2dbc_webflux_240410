package org.myungkeun.crud_r2dbc_webflux_240410.payload.blog;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class BlogRequest {
    private String title;
    private String description;
    private String category;
}
