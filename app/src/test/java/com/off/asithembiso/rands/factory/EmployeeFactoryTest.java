package com.off.asithembiso.rands.factory;

/**
 * Created by asithembiso on 2016/10/31.
 */
import com.off.asithembiso.rands.domain.Employee;
import com.off.asithembiso.rands.factories.EmployeeFactory;

import junit.framework.Assert;
import org.junit.Test;

public class EmployeeFactoryTest {

    public void testEmployee() throws Exception {

        Employee employee = EmployeeFactory.createEmployee("Athi", "Sithembiso", "Dj", 150.50, 35);

        Assert.assertEquals(employee.getJobTitle(), "Dj");
        Assert.assertFalse(3568.5 > employee.getSalary());
    }

    @Test
    public void testUpdate() throws Exception {

    }

}
