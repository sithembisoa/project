package com.off.asithembiso.rands.repositories;

import java.util.Set;

/**
 * Created by asithembiso on 2016/10/31.
 */

public interface Repository<E, ID> {
    E findById(ID id);

    E add(E entity);

    E update(E entity);

    E remove(E entity);

    Set<E> findAll();

    int removeAll();

}
