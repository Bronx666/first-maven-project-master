package com.bronx.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Film.class)
public abstract class Film_ {

	public static volatile SingularAttribute<Film, Integer> duration;
	public static volatile ListAttribute<Film, EventFilm> films;
	public static volatile SingularAttribute<Film, String> name;
	public static volatile SingularAttribute<Film, String> description;
	public static volatile SingularAttribute<Film, Long> id;
	public static volatile SingularAttribute<Film, Float> ticketCost;

	public static final String DURATION = "duration";
	public static final String FILMS = "films";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String TICKET_COST = "ticketCost";

}

