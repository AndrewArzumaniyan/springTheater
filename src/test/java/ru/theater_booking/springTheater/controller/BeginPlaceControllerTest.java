package ru.theater_booking.springTheater.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.theater_booking.springTheater.controller.BeginPlaceController;
import ru.theater_booking.springTheater.model.BeginPlace;
import ru.theater_booking.springTheater.service.BeginPlaceService;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeginPlaceController.class)
class BeginPlaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeginPlaceService beginPlaceService;

    @Test
    void findBeginPlaceById_existingId_shouldReturnBeginPlace() throws Exception {
        Long beginPlaceId = 1L;
        BeginPlace beginPlace = BeginPlace.builder()
                .beginPlaceId(beginPlaceId)
                .status(true)
                .build();

        when(beginPlaceService.getBeginPlaceById(beginPlaceId)).thenReturn(Optional.of(beginPlace));

        mockMvc.perform(get("/api/v1/begin-place/{beginPlaceId}", beginPlaceId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.beginPlaceId").value(beginPlaceId))
                .andExpect(jsonPath("$.status").value(true));
    }

    @Test
    void findBeginPlaceById_nonExistingId_shouldReturnNotFound() throws Exception {
        Long nonExistingId = 99L;

        when(beginPlaceService.getBeginPlaceById(nonExistingId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/begin-place/{beginPlaceId}", nonExistingId))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateBeginPlaceStatus_existingId_shouldUpdateAndReturnBeginPlace() throws Exception {
        Long beginPlaceId = 1L;
        boolean newStatus = true;
        BeginPlace updatedBeginPlace = BeginPlace.builder()
                .beginPlaceId(beginPlaceId)
                .status(newStatus)
                .build();

        when(beginPlaceService.updateBeginPlaceStatus(beginPlaceId, newStatus)).thenReturn(Optional.of(updatedBeginPlace));

        mockMvc.perform(put("/api/v1/begin-place/{beginPlaceId}", beginPlaceId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(newStatus)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.beginPlaceId").value(beginPlaceId))
                .andExpect(jsonPath("$.status").value(newStatus));
    }

    @Test
    void updateBeginPlaceStatus_nonExistingId_shouldReturnNotFound() throws Exception {
        Long nonExistingId = 99L;
        boolean newStatus = true;

        when(beginPlaceService.updateBeginPlaceStatus(nonExistingId, newStatus)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/v1/begin-place/{beginPlaceId}", nonExistingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(newStatus)))
                .andExpect(status().isNotFound());
    }
}