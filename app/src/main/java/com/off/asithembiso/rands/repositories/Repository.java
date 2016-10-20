package com.off.asithembiso.rands.repositories;

import java.util.Set;

/**
 * Created by asithembiso on 2016/09/02.
 */
public interface Repository<E, ID> {

    E findById(ID id);

    E save(E entity);

    E update(E entity);

    E delete(E entity);

    Set<E> findAll();

    int deleteAll();
}
