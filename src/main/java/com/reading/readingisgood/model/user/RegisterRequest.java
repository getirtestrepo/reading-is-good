package com.reading.readingisgood.model.user;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Builder
@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String address;
    private Integer age;
    private String password;
    @NotNull(message = "mobileNumber girişi zorunludur!")
    private Long mobileNumber;
}