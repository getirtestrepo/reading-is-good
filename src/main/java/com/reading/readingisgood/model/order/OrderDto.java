package com.reading.readingisgood.model.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
public class OrderDto {
    @NotNull(message = "orderCode girişi zorunludur!")
    private String orderCode;
    @NotNull(message = "orderCount girişi zorunludur!")
    private int orderCount;
}
