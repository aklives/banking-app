package com.ex.data;

import java.util.Collection;

public interface Repository<ID, E> {
    E findByCustId(ID id);
    E findById(int id);
    Collection<E> findAll(int id);
    ID save(E obj);
    void delete(E obj);
}
