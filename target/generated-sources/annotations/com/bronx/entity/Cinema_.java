package com.bronx.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cinema.class)
public abstract class Cinema_ {

	public static volatile SingularAttribute<Cinema, String> name;
	public static volatile SingularAttribute<Cinema, String> description;
	public static volatile SingularAttribute<Cinema, Long> id;
	public static volatile ListAttribute<Cinema, Hall> halls;

	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String HALLS = "halls";

}

