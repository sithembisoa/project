package com.off.asithembiso.rands.factory;

/**
 * Created by asithembiso on 2016/10/31.
 */
import com.off.asithembiso.rands.domain.Customer;
import com.off.asithembiso.rands.factories.CustomerFactory;

import junit.framework.Assert;
import org.junit.Test;

public class CustomerFactoryTest {
    @Test
    public void testTenant() throws Exception {
        Customer customer =  CustomerFactory.createCustomer("Athi Sithembiso", "athi@gmail.com", "12345");

        Assert.assertEquals(customer.getPassword(),"12345");
        Assert.assertFalse(customer.getEmail()=="athi@hotmail.com");
        Assert.assertTrue(customer.getFullName() == "Athi Sithembiso");
    }

    @Test
    public void testUpdate() throws Exception {
        Customer customer =  CustomerFactory.createCustomer("Athi Sithembiso", "athi@gmail.com", "12345");
        Customer updateCustomer = new Customer.Builder()
                .copy(customer)
                .fullName("Malibongwe Mali")
                .password("4321")
                .build();
        Assert.assertEquals(updateCustomer.getEmail(), customer.getEmail());
        Assert.assertFalse(updateCustomer.getFullName() == customer.getFullName());
        Assert.assertTrue(updateCustomer.getPassword()=="4321");
    }
}
