package com.bronx.repository;

import com.bronx.entity.EventFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventFilmRepository extends JpaRepository<EventFilm, Long> {
}
