package com.reading.readingisgood.repository;

import com.reading.readingisgood.model.entity.StockEntity;
import com.reading.readingisgood.model.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {

    @Query(value = "{'mobileNumber' : ?0}")
    Optional<UserEntity> findByMobileNumber(Long mobileNumber);

}