package com.bronx.entity;

import javax.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
//Under development
//    @OneToMany(mappedBy = "Cinema")
//    private List<Hall> listOfHalls;
}
