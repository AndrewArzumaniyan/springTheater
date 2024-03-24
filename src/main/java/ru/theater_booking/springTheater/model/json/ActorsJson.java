package ru.theater_booking.springTheater.model.json;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ActorsJson implements Serializable {
    private Long actor_id;
    private String info;
    private String role;
    private String full_name;
}
