package org.afs.pakinglot.domain.dto;

public class FetchDTO {
    private String plateNumber;

    public FetchDTO(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

}
