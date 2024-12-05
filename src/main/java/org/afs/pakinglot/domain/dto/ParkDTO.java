package org.afs.pakinglot.domain.dto;


public class ParkDTO {
    private String plateNumber;
    private String parkingBoyStrategy;

    public ParkDTO(String plateNumber, String parkingBoyStrategy) {
        this.plateNumber = plateNumber;
        this.parkingBoyStrategy = parkingBoyStrategy;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getParkingBoyStrategy() {
        return parkingBoyStrategy;
    }
}
