package com.bronx.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCinema is a Querydsl query type for Cinema
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCinema extends EntityPathBase<Cinema> {

    private static final long serialVersionUID = 880674442L;

    public static final QCinema cinema = new QCinema("cinema");

    public final StringPath description = createString("description");

    public final ListPath<Hall, QHall> halls = this.<Hall, QHall>createList("halls", Hall.class, QHall.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QCinema(String variable) {
        super(Cinema.class, forVariable(variable));
    }

    public QCinema(Path<? extends Cinema> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCinema(PathMetadata metadata) {
        super(Cinema.class, metadata);
    }

}

