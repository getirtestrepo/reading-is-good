package com.reading.readingisgood.repository;

import com.reading.readingisgood.model.entity.OrderEntity;
import com.reading.readingisgood.model.entity.StockEntity;
import com.reading.readingisgood.model.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface StockRepository extends MongoRepository<StockEntity, String> {

    @Query(value = "{'stockCode' : ?0}")
    Optional<StockEntity> findByStockCode(String stockCode);

    @Query(value = "{$and : [{'stockCode' : ?0  },{'stockCount' :  {$gte : ?1} }]}")
    Optional<StockEntity> findAvailableStock(String orderCode, int orderCount);
}