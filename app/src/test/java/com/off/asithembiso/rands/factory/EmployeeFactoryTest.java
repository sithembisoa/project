package com.off.asithembiso.rands.factory;

/**
 * Created by asithembiso on 2016/10/31.
 */
import com.off.asithembiso.rands.domain.Employee;
import com.off.asithembiso.rands.factories.EmployeeFactory;

import junit.framework.Assert;
import org.junit.Test;

public class EmployeeFactoryTest {

    public void testTenant() throws Exception {
        Employee employee = EmployeeFactory.createEmployee("Athi", "Sithembiso", "Dj", 150.50, 35);

        Assert.assertEquals(acc.getAccNo(),"24567");
        Assert.assertFalse(3568.5 > acc.getBalance());
        Assert.assertTrue((3568.5+water.calculateBill()+park.calculateBill()+elec.calculateBill())==acc.getBalance());
    }

    @Test
    public void testUpdate() throws Exception {

    }

}
