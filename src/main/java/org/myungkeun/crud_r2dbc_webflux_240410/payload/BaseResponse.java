package org.myungkeun.crud_r2dbc_webflux_240410.payload;

import lombok.*;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public class BaseResponse<T> {
    private int code;
    private String message;
    private T data;
    private List<String> errors;
}
