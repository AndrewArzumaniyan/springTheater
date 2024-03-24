package ru.theater_booking.springTheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.theater_booking.springTheater.model.Scene;

public interface SceneRepository extends JpaRepository<Scene, Long> {
}
