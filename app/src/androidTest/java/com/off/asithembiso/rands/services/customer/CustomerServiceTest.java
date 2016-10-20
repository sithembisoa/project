package com.off.asithembiso.rands.services.customer;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.off.asithembiso.rands.conf.util.App;
import com.off.asithembiso.rands.domain.Customer;
import com.off.asithembiso.rands.repositories.implementation.CustomerRepositoryImpl;
import com.off.asithembiso.rands.repositories.interfaces.CustomerRepository;
import com.off.asithembiso.rands.services.customer.impl.CustomerServiceImpl;

import junit.framework.Assert;

/**
 * Created by asithembiso on 2016/10/20.
 */

public class CustomerServiceTest extends AndroidTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testAddCustomerService() throws Exception {

        CustomerRepository repo = new CustomerRepositoryImpl(this.getContext());
        Long id;
        Intent intent = new Intent(App.getAppContext(),CustomerServiceImpl.class);

        CustomerService customerService = new CustomerServiceImpl();


        Customer createEntity = new Customer.Builder()
                .fullName("Athi Sithembiso")
                .email("athi@gmail.com")
                .password("Sit@0923")
                .build();

        customerService.addCustomer(App.getAppContext(),createEntity);
        App.getAppContext().startService(intent);
        id = createEntity.getId();

        Assert.assertNotNull("CREATE",createEntity);
        Assert.assertEquals(createEntity.getEmail(),"athi@gmail.com");
    }
}
