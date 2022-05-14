package com.bronx.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Ticket.class)
public abstract class Ticket_ {

	public static volatile SingularAttribute<Ticket, Integer> ticketNumber;
	public static volatile SingularAttribute<Ticket, EventFilm> eventFilm;
	public static volatile SingularAttribute<Ticket, Long> id;
	public static volatile SingularAttribute<Ticket, User> user;

	public static final String TICKET_NUMBER = "ticketNumber";
	public static final String EVENT_FILM = "eventFilm";
	public static final String ID = "id";
	public static final String USER = "user";

}

