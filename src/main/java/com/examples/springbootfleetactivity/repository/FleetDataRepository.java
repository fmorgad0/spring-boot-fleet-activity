package com.examples.springbootfleetactivity.repository;

import com.examples.springbootfleetactivity.model.FleetData;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FleetDataRepository extends MongoRepository<FleetData, ObjectId> {

    List<FleetData> findAllByLineId(int journeyPatternId);

}
