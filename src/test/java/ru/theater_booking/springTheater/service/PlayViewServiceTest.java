package ru.theater_booking.springTheater.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.theater_booking.springTheater.model.view.PlayView;
import ru.theater_booking.springTheater.repository.PlayViewRepository;
import ru.theater_booking.springTheater.service.impl.PlayViewServiceImpl;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class PlayViewServiceTest {

    @InjectMocks
    private PlayViewServiceImpl service;

    @Mock
    private PlayViewRepository repository;

    @Test
    void findAllPlays_shouldReturnAllPlays() {
        List<PlayView> expectedPlays = List.of(
                PlayView.builder()
                        .playId(1L)
                        .theaterName("Theater 1")
                        .playName("Play 1")
                        .build(),
                PlayView.builder()
                        .playId(2L)
                        .theaterName("Theater 2")
                        .playName("Play 2")
                        .build()
        );

        Mockito.when(repository.findAll()).thenReturn(expectedPlays);

        List<PlayView> actualPlays = service.findAllPlays();

        Assertions.assertEquals(expectedPlays, actualPlays);
    }

    @Test
    void getPlaysByYearAndMonth_shouldReturnPlaysForGivenYearAndMonth() {
        int year = 2023;
        int month = 5;
        List<PlayView> expectedPlays = List.of(
                PlayView.builder()
                        .playId(1L)
                        .theaterName("Theater 1")
                        .playName("Play 1")
                        .build(),
                PlayView.builder()
                        .playId(3L)
                        .theaterName("Theater 2")
                        .playName("Play 3")
                        .build()
        );

        Mockito.when(repository.getPlaysByYearAndMonth(year, month)).thenReturn(expectedPlays);

        List<PlayView> actualPlays = service.getPlaysByYearAndMonth(year, month);

        Assertions.assertEquals(expectedPlays, actualPlays);
    }

    @Test
    void findPlaysInTheater_shouldReturnPlaysForGivenTheaterId() {
        long theaterId = 1L;
        List<PlayView> expectedPlays = List.of(
                PlayView.builder()
                        .playId(1L)
                        .theaterId(theaterId)
                        .theaterName("Theater 1")
                        .playName("Play 1")
                        .build(),
                PlayView.builder()
                        .playId(4L)
                        .theaterId(theaterId)
                        .theaterName("Theater 1")
                        .playName("Play 4")
                        .build()
        );

        Mockito.when(repository.findByTheaterId(theaterId)).thenReturn(expectedPlays);

        List<PlayView> actualPlays = service.findPlaysInTheater(theaterId);

        Assertions.assertEquals(expectedPlays, actualPlays);
    }
}