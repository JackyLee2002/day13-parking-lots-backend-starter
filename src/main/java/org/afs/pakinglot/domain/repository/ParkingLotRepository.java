package org.afs.pakinglot.domain.repository;

import org.afs.pakinglot.domain.ParkingLot;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ParkingLotRepository {
    private final List<ParkingLot> parkingLots = new ArrayList<>();

    //    on initialize, create 3 parkinglots with id,1,2,3, names= The Plaza Park, City Mall Garage, and Office Tower Parking, with size 9,12,9
    public ParkingLotRepository() {
        parkingLots.add(new ParkingLot(1, "The Plaza Park", 9));
        parkingLots.add(new ParkingLot(2, "City Mall Garage", 12));
        parkingLots.add(new ParkingLot(3, "Office Tower Parking", 9));
    }

    public List<ParkingLot> getAll() {
        return parkingLots;
    }
}


