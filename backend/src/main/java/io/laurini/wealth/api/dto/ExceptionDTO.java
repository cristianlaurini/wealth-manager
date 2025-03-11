package io.laurini.wealth.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionDTO {

    private Integer code;
    private String exception;
    private String description;

}
