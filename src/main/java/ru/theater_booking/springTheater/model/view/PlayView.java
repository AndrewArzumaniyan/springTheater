package ru.theater_booking.springTheater.model.view;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.theater_booking.springTheater.model.json.ActorsJson;
import ru.theater_booking.springTheater.model.json.BeginsJson;

import java.time.Duration;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "play_view") // Название вашего представления в базе данных
public class PlayView {
    @Id
    @Column(name = "play_id")
    private Long playId;

    @Column(name = "theater_id")
    private Long theaterId;

    @Column(name = "theater_name")
    private String theaterName;

    @Column(name = "director_name")
    private String directorName;

    @Column(name = "director_info")
    private String directorInfo;

    @Column(name = "play_name")
    private String playName;

    @Column(name = "play_info")
    private String playInfo;

    @Column(name = "play_genre")
    private String playGenre;

    @Column(name = "play_rating")
    private double playRating;

    @JdbcTypeCode(SqlTypes.INTERVAL_SECOND)
    @Column(name = "duration")
    private Duration duration;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "actors")
    private List<ActorsJson> actors;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "begins")
    private List<BeginsJson> begins;
}
