package ru.theater_booking.springTheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.theater_booking.springTheater.model.Theater;

public interface TheaterRepository extends JpaRepository<Theater, Long> {

}
