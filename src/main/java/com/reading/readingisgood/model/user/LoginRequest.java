package com.reading.readingisgood.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class LoginRequest {
    @NotNull(message = "mobileNumber zorunludur!")
    private Long mobileNumber;
    private String password;
}
