package com.bronx.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHall is a Querydsl query type for Hall
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHall extends EntityPathBase<Hall> {

    private static final long serialVersionUID = 41281042L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHall hall = new QHall("hall");

    public final NumberPath<Integer> amountOfSeats = createNumber("amountOfSeats", Integer.class);

    public final QCinema cinema;

    public final StringPath description = createString("description");

    public final ListPath<EventFilm, QEventFilm> Events = this.<EventFilm, QEventFilm>createList("Events", EventFilm.class, QEventFilm.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QHall(String variable) {
        this(Hall.class, forVariable(variable), INITS);
    }

    public QHall(Path<? extends Hall> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHall(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHall(PathMetadata metadata, PathInits inits) {
        this(Hall.class, metadata, inits);
    }

    public QHall(Class<? extends Hall> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cinema = inits.isInitialized("cinema") ? new QCinema(forProperty("cinema")) : null;
    }

}

