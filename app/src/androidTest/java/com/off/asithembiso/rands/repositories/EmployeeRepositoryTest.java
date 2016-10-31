package com.off.asithembiso.rands.repositories;

import android.database.Cursor;
import android.test.AndroidTestCase;

import com.off.asithembiso.rands.domain.Employee;
import com.off.asithembiso.rands.repositories.implementation.EmployeeRepositoryImpl;
import com.off.asithembiso.rands.repositories.interfaces.EmployeeRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by asithembiso on 2016/10/31.
 */

public class EmployeeRepositoryTest extends AndroidTestCase {

    private Long id;
    private static final String TAG ="EMPLOYEE TEST";

    public void testCreateReadUpdateDeleteEmployee() throws Exception {

        EmployeeRepositoryImpl repo = new EmployeeRepositoryImpl(this.getContext());
        // CREATE
        Employee createEntity = new Employee.Builder()
                .name("Athi")
                .surname("Sithembiso")
                .jobTitle("DJ")
                .rate(150)
                .hoursWorked(40)
                .salary()
                .build();

        Employee insertedEntity = repo.add(createEntity);
        id=insertedEntity.getId();

       Assert.assertNotNull(TAG+" CREATE",insertedEntity);


        //READ ALL
        Set<Employee> employeeSet= repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",employeeSet.size()>0);

        //READ ENTITY
        Employee entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        Employee updateEntity = new Employee.Builder()
                .copy(entity)
                .name("Thabo")
                .build();
        repo.update(updateEntity);
        Employee newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Thabo",newEntity.getName());

        /*// DELETE ENTITY
        repo.delete(updateEntity);
        TenantManagement deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);*/

    }
}
