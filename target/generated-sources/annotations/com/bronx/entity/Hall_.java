package com.bronx.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Hall.class)
public abstract class Hall_ {

	public static volatile SingularAttribute<Hall, Integer> amountOfSeats;
	public static volatile ListAttribute<Hall, EventFilm> Events;
	public static volatile SingularAttribute<Hall, String> name;
	public static volatile SingularAttribute<Hall, String> description;
	public static volatile SingularAttribute<Hall, Long> id;
	public static volatile SingularAttribute<Hall, Cinema> cinema;

	public static final String AMOUNT_OF_SEATS = "amountOfSeats";
	public static final String EVENTS = "Events";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String CINEMA = "cinema";

}

