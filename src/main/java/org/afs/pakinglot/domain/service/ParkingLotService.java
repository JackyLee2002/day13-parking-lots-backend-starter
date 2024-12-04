package org.afs.pakinglot.domain.service;

import org.afs.pakinglot.domain.ParkingLot;
import org.afs.pakinglot.domain.repository.ParkingLotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;

    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    public List<ParkingLot> getAllParkingLots() {
        return parkingLotRepository.getAll();
    }
}