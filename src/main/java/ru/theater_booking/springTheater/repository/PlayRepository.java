package ru.theater_booking.springTheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.theater_booking.springTheater.model.Play;

public interface PlayRepository extends JpaRepository<Play, Long> {
}
