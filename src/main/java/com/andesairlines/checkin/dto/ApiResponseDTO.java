package com.andesairlines.checkin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseDTO<T> {

    private int code;
    private T data;
    private String errors;

    
    public ApiResponseDTO(int code, T data) {
        this.code = code;
        this.data = data;
    }

    
    public ApiResponseDTO(int code, String errors) {
        this.code = code;
        this.errors = errors;
    }
}
