package com.afd.mate.domain.mongo;

import com.afd.mate.domain.service.StockPositionRepository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveMongoStockPositionRepository
        extends StockPositionRepository, ReactiveMongoRepository<StockPositionDocument, ObjectId> {
}
