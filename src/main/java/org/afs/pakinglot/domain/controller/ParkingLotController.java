package org.afs.pakinglot.domain.controller;

import org.afs.pakinglot.domain.Car;
import org.afs.pakinglot.domain.ParkingLot;
import org.afs.pakinglot.domain.ParkingManager;
import org.afs.pakinglot.domain.Ticket;
import org.afs.pakinglot.domain.dto.FetchDTO;
import org.afs.pakinglot.domain.dto.ParkDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ParkingLotController {
    private final ParkingManager parkingManager = new ParkingManager();

    @GetMapping("/parking-lots")
    public List<ParkingLot> getAllParkingLots() {
        return parkingManager.getAllParkingLots();
    }

    @PostMapping("/park")
    public Ticket park(@RequestBody ParkDTO parkDTO) {
        return parkingManager.park(parkDTO.getPlateNumber(), parkDTO.getParkingBoyStrategy());
    }

    @PostMapping("/fetch")
    public Car fetch(@RequestBody FetchDTO fetchDTO) {
        return parkingManager.fetch(fetchDTO.getPlateNumber());
    }
}