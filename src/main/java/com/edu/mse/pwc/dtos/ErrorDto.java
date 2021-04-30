package com.edu.mse.pwc.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {

    private String message;

}
