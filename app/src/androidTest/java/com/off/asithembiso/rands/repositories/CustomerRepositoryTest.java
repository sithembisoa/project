package com.off.asithembiso.rands.repositories;

import android.test.AndroidTestCase;

import com.off.asithembiso.rands.domain.Customer;
import com.off.asithembiso.rands.repositories.implementation.CustomerRepositoryImpl;
import com.off.asithembiso.rands.repositories.interfaces.CustomerRepository;

/**
 * Created by asithembiso on 2016/10/28.
 */
import junit.framework.Assert;

import java.util.Set;

public class CustomerRepositoryTest extends AndroidTestCase{
    private int id;
    private static final String TAG="CUSTOMER TEST";

    public void testCreateReadUpdateDeleteCustomer() throws Exception {

        CustomerRepository repo = new CustomerRepositoryImpl(this.getContext());
        // CREATE
        Customer createEntity = new Customer.Builder()
                .password("123456789")
                .fullName("Athi Sithembiso")
                .id(1)
                .email("athi@gmail.com")
                .build();

        Customer insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();

        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        Assert.assertEquals(insertedEntity.getPassword(), "123456789");

        //READ ALL
        Set<Customer> tenantSet= repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",tenantSet.size()>0);

        //READ ENTITY
        Customer entity = repo.findById(Long.parseLong(""+id));
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Customer updateEntity = new Customer.Builder()
                .copy(entity)
                .password("Sit@0923")
                .build();
        repo.update(updateEntity);
        Customer newEntity = repo.findById(Long.parseLong(""+id));
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Sit@0923",updateEntity.getPassword());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Customer deletedEntity = repo.findById(Long.parseLong(""+id));
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
