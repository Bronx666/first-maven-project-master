package com.bronx.entity.dto;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EventFilter {
    Long filmId;
    Long HallId;
}
