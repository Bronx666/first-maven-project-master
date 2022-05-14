package com.bronx.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFilm is a Querydsl query type for Film
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFilm extends EntityPathBase<Film> {

    private static final long serialVersionUID = 41229149L;

    public static final QFilm film = new QFilm("film");

    public final StringPath description = createString("description");

    public final NumberPath<Integer> duration = createNumber("duration", Integer.class);

    public final ListPath<EventFilm, QEventFilm> films = this.<EventFilm, QEventFilm>createList("films", EventFilm.class, QEventFilm.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Float> ticketCost = createNumber("ticketCost", Float.class);

    public QFilm(String variable) {
        super(Film.class, forVariable(variable));
    }

    public QFilm(Path<? extends Film> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFilm(PathMetadata metadata) {
        super(Film.class, metadata);
    }

}

