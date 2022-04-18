package com.bronx.dao;

public interface IDao<T> {

    void create(T t);

    void delete(T t, int id);

    T read(T t, Long id);

    void update(T t);


}
