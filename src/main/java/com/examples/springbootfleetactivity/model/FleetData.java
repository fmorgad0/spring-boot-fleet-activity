package com.examples.springbootfleetactivity.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "fleetData")
public class FleetData {

    @Id
    private ObjectId id;

    @Indexed
    private Long timestamp;

    private Integer lineId;

    private Integer direction;

    private String journeyPatternId;

    private LocalDate timeFrame;

    private Integer vehicleJourneyId;

    private String operator;

    private Integer congestion;

    private BigDecimal lon;

    private BigDecimal lat;

    private Integer delay;

    private Integer blockId;

    private Integer vehicleId;

    private String stopId;

    private Short atStop;

    public FleetData(ObjectId id, Long timestamp, Integer lineId, Integer direction,
                     String journeyPatternId, LocalDate timeFrame, Integer vehicleJourneyId,
                     String operator, Integer congestion, BigDecimal lon, BigDecimal lat,
                     Integer delay, Integer blockId, Integer vehicleId, String stopId, Short atStop) {
        this.id = id;
        this.timestamp = timestamp;
        this.lineId = lineId;
        this.direction = direction;
        this.journeyPatternId = journeyPatternId;
        this.timeFrame = timeFrame;
        this.vehicleJourneyId = vehicleJourneyId;
        this.operator = operator;
        this.congestion = congestion;
        this.lon = lon;
        this.lat = lat;
        this.delay = delay;
        this.blockId = blockId;
        this.vehicleId = vehicleId;
        this.stopId = stopId;
        this.atStop = atStop;
    }
}
