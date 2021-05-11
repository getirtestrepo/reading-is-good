package com.reading.readingisgood.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CustomerDetailDto extends ApiResponse {
    private String customerName;
    private String customerLastName;
    private int age;
    private String address;
    private long mobileNumber;

}
