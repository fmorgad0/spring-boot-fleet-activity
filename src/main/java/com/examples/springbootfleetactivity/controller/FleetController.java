package com.examples.springbootfleetactivity.controller;

import com.examples.springbootfleetactivity.model.FleetData;
import com.examples.springbootfleetactivity.repository.FleetDataRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fleet")
public class FleetController {

    public FleetDataRepository fleetDataRepository;

    public FleetController(FleetDataRepository fleetDataRepository) {
        this.fleetDataRepository = fleetDataRepository;
    }

    @GetMapping("/getByJourneyPatternId/{id}")
    public ResponseEntity<List<FleetData>> getDataByJourneyPatternId(@PathVariable(value = "id") int lineId) {
        List<FleetData> fleetDataList = fleetDataRepository.findAllByLineId(lineId);

        return ResponseEntity.ok().body(fleetDataList);

    }

}
