package com.examples.springbootfleetactivity.service;

import com.examples.springbootfleetactivity.model.FleetData;
import com.examples.springbootfleetactivity.repository.FleetDataRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FleetDataService {

    private FleetDataRepository fleetDataRepository;
    private MongoTemplate mongoTemplate;

    public FleetDataService(FleetDataRepository fleetDataRepository,
                            MongoTemplate mongoTemplate) {
        this.fleetDataRepository = fleetDataRepository;
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Given a time frame [start-time, end-time],
     * returns the list of running operators
     * @param endTime
     * @param endTime
     * @return List<String>
     */
    public List<String> getOperatorsBetweenTimeFrame(LocalDate startTime, LocalDate endTime) {

        Query query = new Query(Criteria
                .where("timeFrame")
                    .gte(startTime)
                    .lte(endTime));

        return mongoTemplate.findDistinct(query, "operator", FleetData.class, String.class);

    }

    /**
     * Given a time frame [start-time, end-time] and an operator,
     * returns the list of vehicle IDs
     * @param startTime
     * @param endTime
     * @param operator
     * @return List<Integer>
     */
    public List<Integer> getVehicleIdsBetweenTimeFrameForOperator(LocalDate startTime, LocalDate endTime, String operator) {

        Query query = new Query(Criteria
                .where("timeFrame")
                    .gte(startTime)
                    .lte(endTime)
                .and("operator").is(operator));

        return mongoTemplate.findDistinct(query, "vehicleId", FleetData.class, Integer.class);

    }

    /**
     * Given a time frame [start-time, end-time] and a fleet,
     * returns the list of vehicle IDs at a stop
     * @param startTime
     * @param endTime
     * @param vehicleIds
     * @return List<Integer>
     */
    public List<Integer> getVehicleIdsAtStopBetweenTimeFrame(LocalDate startTime, LocalDate endTime, List<Integer> vehicleIds) {

        List<Integer> vehicleIdsAtStop = new ArrayList<>();

        for (Integer vehicleId : vehicleIds) {
            Query query = new Query(Criteria
                    .where("timeFrame")
                    .gte(startTime)
                    .lte(endTime)
                    .and("vehicleId").is(vehicleId))
                    .limit(1)
                    .with(Sort.by(Sort.Direction.DESC, "timestamp"));

            Short vehicleAtStop = mongoTemplate.findOne(query, FleetData.class).getAtStop();

            if (vehicleAtStop != null && vehicleAtStop == 1) {
                vehicleIdsAtStop.add(vehicleId);
            }

        }

        return vehicleIdsAtStop;

    }

    /**
     * Given a time frame [start-time, end-time] and a vehicle,
     * returns its tracing data, orderer by timestamp
     * @param startTime
     * @param endTime
     * @param vehicleId
     * @return List<FleetData>
     */
    public List<FleetData> getVehicleTraceDataBetweenTimeFrameForVehicleId(LocalDate startTime, LocalDate endTime, Integer vehicleId) {

        Query query = new Query(Criteria
                .where("timeFrame")
                .gte(startTime)
                .lte(endTime)
                .and("vehicleId").is(vehicleId));

        return mongoTemplate.find(query, FleetData.class);

    }

}
