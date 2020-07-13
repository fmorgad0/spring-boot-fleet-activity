package com.examples.springbootfleetactivity.controller;

import com.examples.springbootfleetactivity.model.FleetData;
import com.examples.springbootfleetactivity.service.FleetService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/fleet")
public class FleetController {

    private FleetService fleetDataService;

    public FleetController(FleetService fleetDataService) {
        this.fleetDataService = fleetDataService;
    }

    @GetMapping("/getRunningOperators")
    public ResponseEntity<List<String>> getOperatorsBetweenTimeFrame(@RequestParam(value = "startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startTime,
                                                                     @RequestParam(value = "endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endTime) {
        List<String> fleetOperatorsList = fleetDataService.getOperatorsBetweenTimeFrame(startTime, endTime);

        return ResponseEntity.ok().body(fleetOperatorsList);

    }

    @GetMapping("/getVehicleIds")
    public ResponseEntity<List<Integer>> getVehicleIdsBetweenTimeFrameForOperator(@RequestParam(value = "startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startTime,
                                                                  @RequestParam(value = "endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endTime,
                                                                  @RequestParam(value = "operator") String operator) {
        List<Integer> vehicleIdsList = fleetDataService.getVehicleIdsBetweenTimeFrameForOperator(startTime, endTime, operator);

        return ResponseEntity.ok().body(vehicleIdsList);

    }

    @GetMapping("/getVehicleIdsAtStop")
    public ResponseEntity<List<Integer>> getVehicleIdsAtStopBetweenTimeFrame(@RequestParam(value = "startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startTime,
                                                                                  @RequestParam(value = "endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endTime,
                                                                                  @RequestParam(value = "vehicleIds") List<Integer> vehicleIds) {
        List<Integer> vehicleIdsList = fleetDataService.getVehicleIdsAtStopBetweenTimeFrame(startTime, endTime, vehicleIds);

        return ResponseEntity.ok().body(vehicleIdsList);

    }

    @GetMapping("/getVehicleTracingData")
    public ResponseEntity<List<FleetData>> getVehicleTracingData(@RequestParam(value = "startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startTime,
                                                                             @RequestParam(value = "endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endTime,
                                                                             @RequestParam(value = "vehicleId") Integer vehicleId) {
        List<FleetData> vehicleIdsList = fleetDataService.getVehicleTraceDataBetweenTimeFrameForVehicleId(startTime, endTime, vehicleId);

        return ResponseEntity.ok().body(vehicleIdsList);

    }

}
