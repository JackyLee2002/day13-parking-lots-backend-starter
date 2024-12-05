package org.afs.pakinglot.domain;

import org.afs.pakinglot.domain.strategies.AvailableRateStrategy;
import org.afs.pakinglot.domain.strategies.MaxAvailableStrategy;
import org.afs.pakinglot.domain.strategies.SequentiallyStrategy;

import java.util.List;
import java.util.regex.Pattern;

public class ParkingManager {
    private static final Pattern LICENSE_PLATE_REGEX_PATTERN = Pattern.compile("^[A-Z]{2}-\\d{4}$");

    private final List<ParkingLot> parkingLots = List.of(
            new ParkingLot(1, "The Plaza Park", 9),
            new ParkingLot(2, "City Mall Garage", 12),
            new ParkingLot(3, "Office Tower Parking", 9)
    );

    private final ParkingBoy StandardParkingBoy = new ParkingBoy(parkingLots, new SequentiallyStrategy());
    private final ParkingBoy SmartParkingBoy = new ParkingBoy(parkingLots, new MaxAvailableStrategy());
    private final ParkingBoy SuperSmartParkingBoy = new ParkingBoy(parkingLots, new AvailableRateStrategy());

    public List<ParkingLot> getAllParkingLots() {
        return parkingLots;
    }

    public Ticket park(String plateNumber, String parkingBoyString) {
        validatePlateNumber(plateNumber);
        ParkingBoy parkingBoy = getParkingBoyByStrategy(parkingBoyString);
        Car car = new Car(plateNumber);
        return parkingBoy.park(car);
    }

    public Car fetch(String plateNumber) {
        validatePlateNumber(plateNumber);
        for (ParkingLot parkingLot : parkingLots) {
            for (Ticket ticket : parkingLot.getTickets()) {
                if (ticket.plateNumber().equals(plateNumber)) {
                    return parkingLot.fetch(ticket);
                }
            }
        }
        throw new IllegalArgumentException("Car with plate number " + plateNumber + " not found");
    }

    public void validatePlateNumber(String plateNumber) {
        if (plateNumber == null || !LICENSE_PLATE_REGEX_PATTERN.matcher(plateNumber).matches()) {
            throw new IllegalArgumentException("Invalid license plate number: " + plateNumber);
        }
    }

    private ParkingBoy getParkingBoyByStrategy(String parkingBoy) {
        switch (parkingBoy) {
            case "Standard":
                return StandardParkingBoy;
            case "Smart":
                return SmartParkingBoy;
            case "SuperSmart":
                return SuperSmartParkingBoy;
            default:
                throw new IllegalArgumentException("Invalid parking strategy ID");
        }
    }
}