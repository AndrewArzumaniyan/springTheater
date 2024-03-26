package ru.theater_booking.springTheater.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.theater_booking.springTheater.model.type.PlaceType;
import ru.theater_booking.springTheater.model.view.PlaceView;
import ru.theater_booking.springTheater.service.PlaceViewService;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlaceViewController.class)
class PlaceViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlaceViewService placeViewService;

    @Test
    void findAllPlacesByBeginId_existingBeginId_shouldReturnPlaceViews() throws Exception {
        int beginId = 1;
        List<PlaceView> placeViews = Arrays.asList(
                PlaceView.builder()
                        .id(BigInteger.ONE)
                        .price(100.0)
                        .placeType(PlaceType.партер)
                        .build(),
                PlaceView.builder()
                        .id(BigInteger.valueOf(2))
                        .price(150.0)
                        .placeType(PlaceType.бельэтаж)
                        .build()
        );

        when(placeViewService.getPlacesByBeginId(beginId)).thenReturn(placeViews);

        mockMvc.perform(get("/api/v1/place/all/begin/{begin_id_param}", beginId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(placeViews.get(0).getId()))
                .andExpect(jsonPath("$[0].price").value(placeViews.get(0).getPrice()))
                .andExpect(jsonPath("$[0].placeType").value(placeViews.get(0).getPlaceType().toString()))
                .andExpect(jsonPath("$[1].id").value(placeViews.get(1).getId()))
                .andExpect(jsonPath("$[1].price").value(placeViews.get(1).getPrice()))
                .andExpect(jsonPath("$[1].placeType").value(placeViews.get(1).getPlaceType().toString()));
    }

    @Test
    void findAllPlacesByBeginId_nonExistingBeginId_shouldReturnNoContent() throws Exception {
        int nonExistingBeginId = 99;

        when(placeViewService.getPlacesByBeginId(nonExistingBeginId)).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/place/all/begin/{begin_id_param}", nonExistingBeginId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}