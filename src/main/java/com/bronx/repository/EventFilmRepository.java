package com.bronx.repository;

import com.bronx.entity.dto.EventFilter;
import com.bronx.entity.EventFilm;
import com.bronx.entity.QEventFilm;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EventFilmRepository extends RepositoryBase<Long,EventFilm>{


    public EventFilmRepository(EntityManager entityManager) {
        super(EventFilm.class, entityManager);
    }

    public List<EventFilm> findEventByFilmAndHall(EventFilter eventFilter) {

        var predicate = QPredicate.builder()
                .add(eventFilter.getFilmId(), QEventFilm.eventFilm.film.id::eq)
                .add(eventFilter.getHallId(), QEventFilm.eventFilm.hall.id::eq)
                .buildAnd();

        return new JPAQuery<EventFilm>(getEntityManager())
                .select(QEventFilm.eventFilm)
                .from(QEventFilm.eventFilm)
                .where(predicate)
                .fetch();
    }
}
