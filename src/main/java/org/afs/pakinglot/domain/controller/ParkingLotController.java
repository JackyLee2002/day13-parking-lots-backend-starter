package org.afs.pakinglot.domain.controller;

import org.afs.pakinglot.domain.Car;
import org.afs.pakinglot.domain.ParkingBoy;
import org.afs.pakinglot.domain.ParkingLot;
import org.afs.pakinglot.domain.Ticket;
import org.afs.pakinglot.domain.service.ParkingLotService;
import org.afs.pakinglot.domain.strategies.AvailableRateStrategy;
import org.afs.pakinglot.domain.strategies.MaxAvailableStrategy;
import org.afs.pakinglot.domain.strategies.SequentiallyStrategy;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    private ParkingBoy StandParkingBoy;
    private ParkingBoy SmartParkingBoy;
    private ParkingBoy SuperSmartParkingBoy;

//    make an array of all possible parking boys option
    private String[] parkingBoys = {"Standard", "Smart", "SuperSmart"};

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
        List<ParkingLot> parkingLots = parkingLotService.getAllParkingLots();
        StandParkingBoy = new ParkingBoy(parkingLots, new SequentiallyStrategy());
        SmartParkingBoy = new ParkingBoy(parkingLots, new MaxAvailableStrategy());
        SuperSmartParkingBoy = new ParkingBoy(parkingLots, new AvailableRateStrategy());
    }

    @GetMapping("/parking-lots")
    public List<ParkingLot> getAll() {
        return parkingLotService.getAllParkingLots();
    }

    @PostMapping("/park/{plateNumber}/{parkingBoy}")
    public Ticket park(@PathVariable String plateNumber, @PathVariable String parkingBoy) {
        if (!Arrays.asList(parkingBoys).contains(parkingBoy)) {
            throw new IllegalArgumentException("Invalid parking boy");
        }
        for (ParkingLot parkingLot : parkingLotService.getAllParkingLots()) {
            for (Ticket t : parkingLot.getTickets()) {
                if (t.plateNumber().equals(plateNumber)) {
                    throw new IllegalArgumentException("Car is already parked");
                }
            }
        }
        switch (parkingBoy) {
            case "Standard":
                return StandParkingBoy.park(new Car(plateNumber));
            case "Smart":
                return SmartParkingBoy.park(new Car(plateNumber));
            case "SuperSmart":
                return SuperSmartParkingBoy.park(new Car(plateNumber));
        }
        return null;
    }

    @PostMapping("/fetch/{plateNumber}")
    public Car fetch(@PathVariable String plateNumber) {
//       first loop over all parking lots to find the ticket within each parking lot's tickets array
        Ticket ticket = null;
        for (ParkingLot parkingLot : parkingLotService.getAllParkingLots()) {
            for (Ticket t : parkingLot.getTickets()) {
                if (t.plateNumber().equals(plateNumber)) {
                    ticket = t;
                    break;
                }
            }
        }
//        if ticket is not found, throw an exception
        if (ticket == null) {
            throw new IllegalArgumentException();
        }
//        if ticket is found, fetch the car from the parking lot
        return StandParkingBoy.fetch(ticket);
    }

}