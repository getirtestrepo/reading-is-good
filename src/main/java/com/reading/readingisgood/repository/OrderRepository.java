package com.reading.readingisgood.repository;

import com.reading.readingisgood.model.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {

    @Query(value = "{'buyer.mobileNumber' : ?0}")
    List<OrderEntity> findByCustomerOrder(Long mobileNumber);
}