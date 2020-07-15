package com.examples.springbootfleetactivity.service;

import com.examples.springbootfleetactivity.model.FleetData;
import com.examples.springbootfleetactivity.utils.TestUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
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

    @AfterEach
    void tearDown() {
        mongoTemplate.dropCollection(FleetData.class);
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

        //lets test for the 30th day
        List<Integer> vehicleIdList = fleetService.getVehicleIdsBetweenTimeFrameForOperator(LocalDate.of(2013,1,30),
                LocalDate.of(2013,1,30),
                "SL");

        Assert.isTrue(vehicleIdList.isEmpty(), "List of vehicleIds should be empty.");

        //There should be vehiclesIds now
        vehicleIdList = fleetService.getVehicleIdsBetweenTimeFrameForOperator(LocalDate.of(2013,1,30),
                LocalDate.of(2013,1,31),
                "SL");

        Assert.isTrue(2 == vehicleIdList.size(), "There should be 2 distinct vehicleIds.");

    }

    @Test
    void getVehicleIdsAtStopBetweenTimeFrame() {

        List<Integer> vehicleIdsFromRequest = new ArrayList<>();
        vehicleIdsFromRequest.add(33305); //this one should not be atStop in the most recent timestamp
        vehicleIdsFromRequest.add(33502);
        vehicleIdsFromRequest.add(43003);

        List<Integer> vehicleIdAtStopList = fleetService.getVehicleIdsAtStopBetweenTimeFrame(LocalDate.of(2013,1,30),
                LocalDate.of(2013,1,31),
                vehicleIdsFromRequest);

        Assert.isTrue(2 == vehicleIdAtStopList.size(), "There should be 2 distinct vehicleIds.");

        Assert.isTrue(!vehicleIdAtStopList.contains(33305), "List should not contain this vehicleId.");

    }

    @Test
    void getVehicleTraceDataBetweenTimeFrameForVehicleId() {

        List<FleetData> fleetDataList = fleetService.getVehicleTraceDataBetweenTimeFrameForVehicleId(LocalDate.of(2013,1,30),
                LocalDate.of(2013,1,31),
                33608);

        Assert.isTrue(2 == fleetDataList.size(), "There should be 2 entries with data.");

    }
}