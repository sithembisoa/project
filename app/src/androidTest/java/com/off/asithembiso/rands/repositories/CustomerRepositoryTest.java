package com.off.asithembiso.rands.repositories;

import android.test.AndroidTestCase;

import com.off.asithembiso.rands.domain.Customer;
import com.off.asithembiso.rands.repositories.implementation.CustomerRepositoryImpl;
import com.off.asithembiso.rands.repositories.interfaces.CustomerRepository;

/**
 * Created by asithembiso on 2016/10/28.
 */
import junit.framework.Assert;

public class CustomerRepositoryTest extends AndroidTestCase{
    private int id;
    private static final String TAG="CUSTOMER TEST";

    public void testCreateReadUpdateDelete() throws Exception {

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

        //Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        Assert.assertEquals(insertedEntity.getPassword(), "123456789");

        /*//READ ALL
        Set<TenantManagement> tenantSet= repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",tenantSet.size()>0);

        //READ ENTITY
        TenantManagement entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        createEntity.RemoveTenant("Malusi Gigs");

        TenantManagement updateEntity = new TenantManagement.Builder()
                .copy(entity)
                .list(createEntity.getTenantList())
                .build();
        repo.update(updateEntity);
        TenantManagement newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",0,updateEntity.getSize());

        // DELETE ENTITY
        repo.delete(updateEntity);
        TenantManagement deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);*/

    }

}
