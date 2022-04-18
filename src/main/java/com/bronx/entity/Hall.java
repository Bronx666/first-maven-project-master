package com.bronx.entity;

import javax.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int amountOfSeats;
    @ManyToOne
    private Cinema cinema;
    //Under development
//    @OneToMany(mappedBy = "Hall")
//    private List<EventFilm> listOfEventFilms;
}
