package com.bronx.entity;
import javax.persistence.*;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private LevelAccess levelAccess;
//    Under development
//    @OneToMany(mappedBy = "User")
//    private List<Ticket> listOfTickets;
}
