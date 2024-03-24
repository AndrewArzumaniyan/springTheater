package ru.theater_booking.springTheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.theater_booking.springTheater.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
