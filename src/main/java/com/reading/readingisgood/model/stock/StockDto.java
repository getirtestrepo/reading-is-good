package com.reading.readingisgood.model.stock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
public class StockDto {
    @NotNull(message = "stockName girişi zorunludur!")
    private String stockName;
    @NotNull(message = "stockCode girişi zorunludur!")
    private String stockCode;
    @NotNull(message = "stockCuont girişi zorunludur!")
    private Integer stockCount;
    @NotNull(message = "stockPrice girişi zorunludur!")
    private Long stockPrice;
}
