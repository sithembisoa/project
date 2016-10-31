package com.off.asithembiso.rands.factory;

/**
 * Created by asithembiso on 2016/10/31.
 */
import junit.framework.Assert;
import org.junit.Test;

public class EmployeeFactoryTest {

    public void testTenant() throws Exception {
        Employee acc = AccountFactory.getAccount("24567", 3568.5, water, elec, park);

        Assert.assertEquals(acc.getAccNo(),"24567");
        Assert.assertFalse(3568.5 > acc.getBalance());
        Assert.assertTrue((3568.5+water.calculateBill()+park.calculateBill()+elec.calculateBill())==acc.getBalance());
    }

    @Test
    public void testUpdate() throws Exception {

    }

}
