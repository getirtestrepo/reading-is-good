package com.reading.readingisgood.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Stock")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockEntity {
    @Id
    private String id;
    private String stockName;
    private String stockCode;
    private Long stockPrice;
    private Integer stockCount;

}
