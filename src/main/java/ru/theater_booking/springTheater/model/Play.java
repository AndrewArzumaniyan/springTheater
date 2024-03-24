package ru.theater_booking.springTheater.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "play")
public class Play {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "play_id")
    private Long playId;

    @ManyToOne
    @JoinColumn(name = "theater_id", referencedColumnName = "theater_id")
    private Theater theater;

    @ManyToOne
    @JoinColumn(name = "director_id", referencedColumnName = "director_id")
    private Director director;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "info")
    private String info;

    @Column(name = "genre")
    private String genre;

    @Column(name = "rating")
    private Double rating;
}

