package com.bronx.entity;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EventFilm.class)
public abstract class EventFilm_ {

	public static volatile SingularAttribute<EventFilm, LocalDateTime> date;
	public static volatile SingularAttribute<EventFilm, Integer> freeSeats;
	public static volatile SingularAttribute<EventFilm, Hall> hall;
	public static volatile SingularAttribute<EventFilm, Long> id;
	public static volatile SingularAttribute<EventFilm, Film> film;

	public static final String DATE = "date";
	public static final String FREE_SEATS = "freeSeats";
	public static final String HALL = "hall";
	public static final String ID = "id";
	public static final String FILM = "film";

}

