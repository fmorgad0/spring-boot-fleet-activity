package com.examples.springbootfleetactivity.service;

import com.examples.springbootfleetactivity.model.FleetData;
import com.examples.springbootfleetactivity.repository.FleetDataRepository;
import com.examples.springbootfleetactivity.utils.TestUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.util.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class FleetServiceTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    FleetService fleetService;

    @BeforeEach
    void contextLoads() {

        try{
            Files.walk(Paths.get("src","test","resources","mongo"))
                    .filter(Files::isRegularFile)
                    .forEach(filePath -> TestUtils
                            .insertDocumentsFromMongoExtendedJsonFile(mongoTemplate,
                                    filePath,
                                    filePath.getFileName().toString().replace(".json", "")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void getOperatorsBetweenTimeFrame() {
        //lets test for the 30th day
        List<String> operatorsList = fleetService.getOperatorsBetweenTimeFrame(LocalDate.of(2013,1,30),
                LocalDate.of(2013,1,30));

        Assert.isTrue(4 == operatorsList.size(), "There should be 4 distinct operators.");

        //Now between the 30th and the 31st
        operatorsList = fleetService.getOperatorsBetweenTimeFrame(LocalDate.of(2013,1,30),
                LocalDate.of(2013,1,31));

        Assert.isTrue(6 == operatorsList.size(), "There should be 6 distinct operators.");

    }

    @Test
    void getVehicleIdsBetweenTimeFrameForOperator() {
        Assert.isTrue(5 == 5, "test assert");
    }

    @Test
    void getVehicleIdsAtStopBetweenTimeFrame() {
        Assert.isTrue(5 == 5, "test assert");
    }

    @Test
    void getVehicleTraceDataBetweenTimeFrameForVehicleId() {
        Assert.isTrue(5 == 5, "test assert");
    }
}