package org.afs.pakinglot.domain.controller;

import org.afs.pakinglot.domain.ParkingLot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class ParkingLotControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        List<ParkingLot> parkingLots = Arrays.asList(
                new ParkingLot(1, "The Plaza Park", 9),
                new ParkingLot(2, "City Mall Garage", 12),
                new ParkingLot(3, "Office Tower Parking", 9)
        );
    }

    @Test
    public void givenParkingLots_whenGetAll_thenReturnJsonArray() throws Exception {
        // given
        // when
        mockMvc.perform(get("/parking-lots"))
                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("The Plaza Park"))
                .andExpect(jsonPath("$[0].capacity").value(9))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("City Mall Garage"))
                .andExpect(jsonPath("$[1].capacity").value(12))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[2].name").value("Office Tower Parking"))
                .andExpect(jsonPath("$[2].capacity").value(9));
    }

    @Test
    public void givenValidPlateNumberAndParkingBoy_whenPark_thenReturnOk() throws Exception {
        // given
        // when
        mockMvc.perform(post("/park/AB-2234/Standard"))
                // then
                .andExpect(status().isOk());
    }

    @Test
    public void givenInvalidParkingBoy_whenPark_thenReturnBadRequest() throws Exception {
        // given
        // when
        mockMvc.perform(post("/park/AB-2234/InvalidBoy"))
                // then
                .andExpect(status().isBadRequest());

    }



}