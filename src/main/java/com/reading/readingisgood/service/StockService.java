package com.reading.readingisgood.service;

import com.reading.readingisgood.model.entity.StockEntity;
import com.reading.readingisgood.model.stock.StockDto;
import com.reading.readingisgood.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockService {
    private final StockRepository stockRepository;

    public Integer addStock(StockDto stockRequest) {
        StockEntity entity = StockEntity.builder()
                .stockCode(stockRequest.getStockCode())
                .stockName(stockRequest.getStockName())
                .stockCount(stockRequest.getStockCount())
                .stockPrice((stockRequest.getStockPrice()))
                .build();

        Optional<StockEntity> existingStock = stockRepository.findByStockCode(entity.getStockCode());
        if (existingStock.isPresent()) {
            StockEntity stock = existingStock.get();
            stock.setStockCount(stock.getStockCount() + entity.getStockCount());
            stockRepository.save(stock);
        } else {
            stockRepository.save(entity);
        }
        return 200;
    }
}
