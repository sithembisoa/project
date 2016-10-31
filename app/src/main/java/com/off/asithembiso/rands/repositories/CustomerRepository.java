package com.off.asithembiso.rands.repositories;
/**
 * Created by asithembiso on 2016/09/02.
 */
public interface CustomerRepository<E, ID> {

    String findUser(String emil);

    E save(E entity);

    E update(E entity);

    E delete(E entity);
}
