package org.afs.pakinglot.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ParkingManagerTest {

    private ParkingManager parkingManager;

    @BeforeEach
    void setUp() {
        parkingManager = new ParkingManager();
    }

    @Test
    public void should_have_parking_manager_when_initialize() {
        assertNotNull(parkingManager);
        assertEquals(3, parkingManager.getAllParkingLots().size());
    }

    @Test
    public void should_get_all_3_parking_lots_when_get_all_parking_lots_given_valid_parking_manager() {
        List<ParkingLot> parkingLots = parkingManager.getAllParkingLots();
        assertEquals(3, parkingLots.size());
        assertEquals("The Plaza Park", parkingLots.get(0).getName());
        assertEquals("City Mall Garage", parkingLots.get(1).getName());
        assertEquals("Office Tower Parking", parkingLots.get(2).getName());
    }

    @Test
    public void should_get_parked_car_ticket_when_park_given_valid_plate_num_and_parking_boy() {
        Ticket ticket = parkingManager.park("AB-1234", "Standard");
        assertNotNull(ticket);
        assertEquals("AB-1234", ticket.plateNumber());
    }

    @Test
    public void should_get_correct_car_when_fetch_given_valid_plate_num_and_parked_car_plate_num() {
        parkingManager.park("AB-1234", "Standard");
        Car car = parkingManager.fetch("AB-1234");
        assertNotNull(car);
        assertEquals("AB-1234", car.plateNumber());
    }

    @Test
    public void should_throw_IllegalArgumentException_when_park_given_invalid_plate_num() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            parkingManager.park("1234-AB", "Standard");
        });

        String expectedMessage = "Invalid license plate number: 1234-AB";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @ParameterizedTest
    @CsvSource({
            "AB-1234, Standard",
            "CD-5678, Smart",
            "EF-6789, SuperSmart"
    })
    void givenStrategy_whenParkCar_thenParksCar(String plateNumber, String parkingBoyStrategy) {
        // When
        Ticket ticket = parkingManager.park(plateNumber, parkingBoyStrategy);

        // Then
        assertNotNull(ticket);
    }
}