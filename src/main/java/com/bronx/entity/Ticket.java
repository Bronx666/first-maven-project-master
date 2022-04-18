package com.bronx.entity;

import javax.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Ticket {

    @Id
    private int id;
    @ManyToOne//or OneToMany??
    private User user;
    @ManyToOne//or oneToMany??
    private EventFilm eventFilm;
    private int ticketNumber;

}
