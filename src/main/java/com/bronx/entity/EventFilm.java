package com.bronx.entity;

import javax.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class EventFilm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private Hall hall;
    @ManyToOne(optional = false)
    private Film film;
    private LocalDateTime date;
    private int freeSeats;
}
