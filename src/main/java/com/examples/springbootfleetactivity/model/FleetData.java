package com.examples.springbootfleetactivity.model;


import lombok.AllArgsConstructor;
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
@AllArgsConstructor
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

}
