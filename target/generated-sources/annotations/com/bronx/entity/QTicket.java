package com.bronx.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTicket is a Querydsl query type for Ticket
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTicket extends EntityPathBase<Ticket> {

    private static final long serialVersionUID = 1367047845L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTicket ticket = new QTicket("ticket");

    public final QEventFilm eventFilm;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> ticketNumber = createNumber("ticketNumber", Integer.class);

    public final QUser user;

    public QTicket(String variable) {
        this(Ticket.class, forVariable(variable), INITS);
    }

    public QTicket(Path<? extends Ticket> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTicket(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTicket(PathMetadata metadata, PathInits inits) {
        this(Ticket.class, metadata, inits);
    }

    public QTicket(Class<? extends Ticket> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.eventFilm = inits.isInitialized("eventFilm") ? new QEventFilm(forProperty("eventFilm"), inits.get("eventFilm")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

