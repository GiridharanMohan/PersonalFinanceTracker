package com.dev.personalFinanceTracker.model.dto;


import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto<T> {
    private boolean success;

    private String message;

    @Nullable
    private T data;
}
