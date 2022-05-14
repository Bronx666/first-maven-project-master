package com.bronx.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEventFilm is a Querydsl query type for EventFilm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventFilm extends EntityPathBase<EventFilm> {

    private static final long serialVersionUID = 944675077L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventFilm eventFilm = new QEventFilm("eventFilm");

    public final DateTimePath<java.time.LocalDateTime> date = createDateTime("date", java.time.LocalDateTime.class);

    public final QFilm film;

    public final NumberPath<Integer> freeSeats = createNumber("freeSeats", Integer.class);

    public final QHall hall;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QEventFilm(String variable) {
        this(EventFilm.class, forVariable(variable), INITS);
    }

    public QEventFilm(Path<? extends EventFilm> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEventFilm(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEventFilm(PathMetadata metadata, PathInits inits) {
        this(EventFilm.class, metadata, inits);
    }

    public QEventFilm(Class<? extends EventFilm> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.film = inits.isInitialized("film") ? new QFilm(forProperty("film")) : null;
        this.hall = inits.isInitialized("hall") ? new QHall(forProperty("hall"), inits.get("hall")) : null;
    }

}

