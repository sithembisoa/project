package com.off.asithembiso.rands.repositories;

import android.test.AndroidTestCase;

import com.off.asithembiso.rands.domain.Customer;
import com.off.asithembiso.rands.repositories.implementation.CustomerRepositoryImpl;
import com.off.asithembiso.rands.repositories.interfaces.CustomerRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by asithembiso on 2016/10/20.
 */

public class CustomerRepositoryTest extends AndroidTestCase {

    private static final String TAG="CUSTOMER TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {

        CustomerRepository repo = new CustomerRepositoryImpl(this.getContext());
        // CREATE

        Customer createEntity = new Customer.Builder()
                .fullName("Athi Sithembiso")
                .email("athi@gmail.com")
                .password("Sit@0923")
                .build();

        Customer insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

       /*//READ ALL
        Set<Customer> customers = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",customers.size()>0);*/

       /* //READ ENTITY
        Customer entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);*/



        /*//UPDATE ENTITY
        Customer updateEntity = new Customer.Builder()
                .copy(createEntity)
                .fullName("Subway Surf")
                .build();
        repo.update(updateEntity);
        Customer newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Subway Surf",newEntity.getFullName());*/

        // DELETE ENTITY
        repo.delete(insertedEntity);
        Customer deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }

}
