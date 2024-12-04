package org.afs.pakinglot.domain.controller;

import org.afs.pakinglot.domain.ParkingLot;
import org.afs.pakinglot.domain.service.ParkingLotService;
simport org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parkingLots")
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @GetMapping
    public List<ParkingLot> getAll() {
        return parkingLotService.getAllParkingLots();
    }
}