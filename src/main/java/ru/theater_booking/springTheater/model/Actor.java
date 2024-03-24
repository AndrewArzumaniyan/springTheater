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
@Table(name = "actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Long actorId;

    @ManyToOne
    @JoinColumn(name = "play_id", referencedColumnName = "play_id")
    private Play play;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "info")
    private String info;

    @Column(name = "role")
    private String role;
}
