package ru.theater_booking.springTheater.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.theater_booking.springTheater.model.BeginPlace;
import ru.theater_booking.springTheater.repository.BeginPlaceRepository;
import ru.theater_booking.springTheater.service.impl.BeginPlaceServiceImpl;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BeginPlaceServiceTest {

    @InjectMocks
    private BeginPlaceServiceImpl service;

    @Mock
    private BeginPlaceRepository repository;

    @Test
    void getBeginPlaceById_existingId_shouldReturnBeginPlace() {
        Long beginPlaceId = 1L;
        BeginPlace beginPlace = new BeginPlace();
        beginPlace.setBeginPlaceId(beginPlaceId);

        Mockito.when(repository.findById(beginPlaceId)).thenReturn(Optional.of(beginPlace));

        Optional<BeginPlace> result = service.getBeginPlaceById(beginPlaceId);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(beginPlace, result.get());
    }

    @Test
    void getBeginPlaceById_nonExistingId_shouldReturnEmptyOptional() {
        Long nonExistingId = 99L;

        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        Optional<BeginPlace> result = service.getBeginPlaceById(nonExistingId);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void updateBeginPlaceStatus_existingId_shouldUpdateAndReturnBeginPlace() {
        Long beginPlaceId = 1L;
        BeginPlace existingBeginPlace = new BeginPlace();
        existingBeginPlace.setBeginPlaceId(beginPlaceId);
        existingBeginPlace.setStatus(false);

        BeginPlace updatedBeginPlace = new BeginPlace();
        updatedBeginPlace.setBeginPlaceId(beginPlaceId);
        updatedBeginPlace.setStatus(true);

        Mockito.when(repository.findById(beginPlaceId)).thenReturn(Optional.of(existingBeginPlace));
        Mockito.when(repository.save(updatedBeginPlace)).thenReturn(updatedBeginPlace);

        Optional<BeginPlace> result = service.updateBeginPlaceStatus(beginPlaceId, true);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(updatedBeginPlace, result.get());
    }

    @Test
    void updateBeginPlaceStatus_nonExistingId_shouldReturnEmptyOptional() {
        Long nonExistingId = 99L;
        boolean newStatus = true;

        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        Optional<BeginPlace> result = service.updateBeginPlaceStatus(nonExistingId, newStatus);
        Assertions.assertTrue(result.isEmpty());
    }
}
