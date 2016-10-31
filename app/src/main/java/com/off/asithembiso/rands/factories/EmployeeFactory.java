package com.off.asithembiso.rands.factories;

import com.off.asithembiso.rands.domain.Employee;

/**
 * Created by asithembiso on 2016/10/31.
 */

public class EmployeeFactory {

    public static Employee createEmployee(String name, String surname, String job, double rate, int hours) {

        Employee employee = new Employee.Builder()
                .name(name)
                .surname(surname)
                .jobTitle(job)
                .rate(rate)
                .hoursWorked(hours)
                .salary()
                .build();
        return employee;
    }


}

