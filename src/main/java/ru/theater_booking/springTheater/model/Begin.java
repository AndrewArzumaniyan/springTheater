package ru.theater_booking.springTheater.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "begin")
public class Begin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "begin_id")
    private Long beginId;

    @ManyToOne
    @JoinColumn(name = "play_id", referencedColumnName = "play_id")
    private Play play;

    @ManyToOne
    @JoinColumn(name = "scene_id", referencedColumnName = "scene_id")
    private Scene scene;

    @Column(name = "begin_time")
    private Timestamp beginTime;
}

