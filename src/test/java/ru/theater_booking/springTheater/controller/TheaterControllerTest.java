package ru.theater_booking.springTheater.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.theater_booking.springTheater.model.Theater;
import ru.theater_booking.springTheater.service.TheaterService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TheaterController.class)
class TheaterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TheaterService theaterService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findAllTheater_shouldReturnAllTheaters() throws Exception {
        List<Theater> theaters = Arrays.asList(
                Theater.builder().theaterId(1L).name("Theater 1").build(),
                Theater.builder().theaterId(2L).name("Theater 2").build()
        );

        when(theaterService.findAllTheaters()).thenReturn(theaters);

        mockMvc.perform(get("/api/v1/theater/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].theaterId").value(theaters.get(0).getTheaterId()))
                .andExpect(jsonPath("$[0].name").value(theaters.get(0).getName()))
                .andExpect(jsonPath("$[1].theaterId").value(theaters.get(1).getTheaterId()))
                .andExpect(jsonPath("$[1].name").value(theaters.get(1).getName()));
    }

    @Test
    void findAllTheater_noTheatersAvailable_shouldReturnNoContent() throws Exception {
        when(theaterService.findAllTheaters()).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/theater/all"))
                .andExpect(status().isNoContent());
    }

    @Test
    void findTheaterById_existingId_shouldReturnTheater() throws Exception {
        Long theaterId = 1L;
        Theater theater = Theater.builder().theaterId(theaterId).name("Theater 1").build();

        when(theaterService.findTheaterById(theaterId)).thenReturn(Optional.of(theater));

        mockMvc.perform(get("/api/v1/theater/{theaterId}", theaterId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.theaterId").value(theater.getTheaterId()))
                .andExpect(jsonPath("$.name").value(theater.getName()));
    }

    @Test
    void findTheaterById_nonExistingId_shouldReturnNotFound() throws Exception {
        Long nonExistingId = 99L;

        when(theaterService.findTheaterById(nonExistingId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/theater/{theaterId}", nonExistingId))
                .andExpect(status().isNotFound());
    }

    @Test
    void createTheater_shouldCreateAndReturnTheater() throws Exception {
        Theater theater = Theater.builder().name("New Theater").build();
        Theater savedTheater = Theater.builder().theaterId(1L).name("New Theater").build();

        when(theaterService.saveTheater(theater)).thenReturn(savedTheater);

        mockMvc.perform(post("/api/v1/theater")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(theater)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.theaterId").value(savedTheater.getTheaterId()))
                .andExpect(jsonPath("$.name").value(savedTheater.getName()));
    }

    @Test
    void updateTheater_shouldUpdateAndReturnTheater() throws Exception {
        Theater theater = Theater.builder().theaterId(1L).name("Updated Theater").build();

        when(theaterService.updateTheater(theater)).thenReturn(theater);

        mockMvc.perform(put("/api/v1/theater")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(theater)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.theaterId").value(theater.getTheaterId()))
                .andExpect(jsonPath("$.name").value(theater.getName()));
    }

    @Test
    void deleteTheater_existingId_shouldDeleteAndReturnOk() throws Exception {
        Long theaterId = 1L;

        mockMvc.perform(delete("/api/v1/theater/{theaterId}", theaterId))
                .andExpect(status().isOk());

        verify(theaterService, times(1)).deleteTheater(theaterId);
    }
}