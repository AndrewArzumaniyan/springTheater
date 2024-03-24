package ru.theater_booking.springTheater.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.theater_booking.springTheater.model.Theater;
import ru.theater_booking.springTheater.repository.TheaterRepository;
import ru.theater_booking.springTheater.service.impl.TheaterServiceImpl;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TheaterServiceTest {
    @InjectMocks
    private TheaterServiceImpl service;

    @Mock
    private TheaterRepository repository;

    @Test
    void findAllTheaters_withData_getData() {
        Theater theater1 = Theater.builder()
                .name("Theater name 1")
                .info("Theater name 1 was build in 1980's")
                .city("Moscow")
                .street("Street 1")
                .house("1")
                .lat(55.7522F)
                .lng(37.6156F)
                .build();
        Theater theater2 = Theater.builder()
                .name("Theater name 2")
                .info("Theater name 2 was build in 1980's")
                .city("Moscow")
                .street("Street 2")
                .house("2")
                .lat(55.7522F)
                .lng(37.6156F)
                .build();
        Mockito.when(repository.findAll()).thenReturn(List.of(theater1, theater2));

        List<Theater> expected = List.of(theater1, theater2);
        List<Theater> theaters = service.findAllTheaters();
        Assertions.assertIterableEquals(expected, theaters);
    }

    @Test
    void findAllTheaters_withEmptyData_getEmptyList() {
        Mockito.when(repository.findAll()).thenReturn(List.of());

        List<Theater> theaters = service.findAllTheaters();
        Assertions.assertIterableEquals(List.of(), theaters);
    }

    @Test
    void saveTheater_shouldReturnSavedTheater() {
        Theater theater = Theater.builder()
                .name("New Theater")
                .info("New theater information")
                .city("New York")
                .street("Broadway")
                .house("123")
                .lat(40.7128F)
                .lng(-74.0060F)
                .build();

        Mockito.when(repository.save(theater)).thenReturn(theater);

        Theater savedTheater = service.saveTheater(theater);
        Assertions.assertEquals(theater, savedTheater);
    }

    @Test
    void findTheaterById_existingId_shouldReturnTheater() {
        long theaterId = 1L;
        Theater theater = Theater.builder()
                .theaterId(theaterId)
                .name("Existing Theater")
                .build();

        Mockito.when(repository.findById(theaterId)).thenReturn(Optional.of(theater));

        Optional<Theater> foundTheater = service.findTheaterById(theaterId);
        Assertions.assertTrue(foundTheater.isPresent());
        Assertions.assertEquals(theater, foundTheater.get());
    }

    @Test
    void findTheaterById_nonExistingId_shouldReturnEmptyOptional() {
        long nonExistingTheaterId = 99L;

        Mockito.when(repository.findById(nonExistingTheaterId)).thenReturn(Optional.empty());

        Optional<Theater> foundTheater = service.findTheaterById(nonExistingTheaterId);
        Assertions.assertTrue(foundTheater.isEmpty());
    }

    @Test
    void updateTheater_existingTheater_shouldUpdateAndReturnTheater() {
        long theaterId = 1L;
        Theater existingTheater = Theater.builder()
                .theaterId(theaterId)
                .name("Existing Theater")
                .info("Old information")
                .city("Old City")
                .build();

        Theater updatedTheater = Theater.builder()
                .theaterId(theaterId)
                .name("Updated Theater")
                .info("New information")
                .city("New City")
                .build();

        Mockito.when(repository.findById(theaterId)).thenReturn(Optional.of(existingTheater));
        Mockito.when(repository.save(Mockito.any(Theater.class))).thenReturn(updatedTheater);

        Theater result = service.updateTheater(updatedTheater);
        Assertions.assertEquals(updatedTheater, result);
    }

    @Test
    void deleteTheater_existingId_shouldDeleteTheater() {
        long theaterId = 1L;

        Mockito.doNothing().when(repository).deleteById(theaterId);

        service.deleteTheater(theaterId);

        Mockito.verify(repository, Mockito.times(1)).deleteById(theaterId);
    }
}
