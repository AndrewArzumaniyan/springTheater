package ru.theater_booking.springTheater.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.theater_booking.springTheater.model.view.PlayView;
import ru.theater_booking.springTheater.service.PlayViewService;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlayViewController.class)
class PlayViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayViewService playViewService;

    @Test
    void findAllPlays_shouldReturnAllPlays() throws Exception {
        List<PlayView> playViews = Arrays.asList(
                PlayView.builder()
                        .playId(1L)
                        .playName("Play 1")
                        .build(),
                PlayView.builder()
                        .playId(2L)
                        .playName("Play 2")
                        .build()
        );

        when(playViewService.findAllPlays()).thenReturn(playViews);

        mockMvc.perform(get("/api/v1/play/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].playId").value(playViews.get(0).getPlayId()))
                .andExpect(jsonPath("$[0].playName").value(playViews.get(0).getPlayName()))
                .andExpect(jsonPath("$[1].playId").value(playViews.get(1).getPlayId()))
                .andExpect(jsonPath("$[1].playName").value(playViews.get(1).getPlayName()));
    }

    @Test
    void findAllPlays_noPlaysAvailable_shouldReturnNoContent() throws Exception {
        when(playViewService.findAllPlays()).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/play/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void findAllPlaysPerDate_existingDateAndPlays_shouldReturnPlays() throws Exception {
        int year = 2023;
        int month = 4;
        String date = year + "-" + String.format("%02d", month);

        List<PlayView> playViews = Arrays.asList(
                PlayView.builder()
                        .playId(1L)
                        .playName("Play 1")
                        .duration(Duration.ofHours(2))
                        .build(),
                PlayView.builder()
                        .playId(2L)
                        .playName("Play 2")
                        .duration(Duration.ofHours(3))
                        .build()
        );

        when(playViewService.getPlaysByYearAndMonth(year, month)).thenReturn(playViews);

        mockMvc.perform(get("/api/v1/play/all/date/{date}", date)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findAllPlaysPerDate_noPlaysAvailableForDate_shouldReturnNoContent() throws Exception {
        int year = 2023;
        int month = 4;
        String date = year + "-" + String.format("%02d", month);

        when(playViewService.getPlaysByYearAndMonth(year, month)).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/play/all/date/{date}", date)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}