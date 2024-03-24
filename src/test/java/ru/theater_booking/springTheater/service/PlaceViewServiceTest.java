package ru.theater_booking.springTheater.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.theater_booking.springTheater.model.type.PlaceType;
import ru.theater_booking.springTheater.model.view.PlaceView;
import ru.theater_booking.springTheater.repository.PlaceViewRepository;
import ru.theater_booking.springTheater.service.impl.PlaceViewServiceImpl;

import java.math.BigInteger;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PlaceViewServiceTest {
    @InjectMocks
    private PlaceViewServiceImpl service;

    @Mock
    private PlaceViewRepository repository;

    @Test
    void getPlacesByBeginId_shouldReturnPlacesForGivenBeginId() {
        int beginId = 1;
        List<PlaceView> expectedPlaces = List.of(
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

        Mockito.when(repository.getPlacesByBeginId(beginId)).thenReturn(expectedPlaces);

        List<PlaceView> actualPlaces = service.getPlacesByBeginId(beginId);

        Assertions.assertEquals(expectedPlaces, actualPlaces);
    }
}
