package com.dev.personalFinanceTracker.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountRequestDto {

    @NotBlank(message = "Name is required")
    private String name;
}
