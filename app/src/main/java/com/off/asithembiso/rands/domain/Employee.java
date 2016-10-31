package com.off.asithembiso.rands.domain;

/**
 * Created by asithembiso on 2016/10/31.
 */

import java.io.Serializable;

public class Employee implements Serializable{

    private long id;
    private String name;
    private String jobTitle;
    private String surname;
    private double rate;
    private int hours;
    private double salary;

    private Employee (Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.jobTitle = builder.jobTitle;
        this.rate = builder.rate;
        this.hours=builder.hours;
        this.salary=builder.salary;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public double getRate() {
        return rate;
    }

    public int getHours() {
        return hours;
    }

    public double getSalary() {
        return hours*rate;
    }

    public static class Builder {
        private long id;
        private String name;
        private String jobTitle;
        private String surname;
        private double rate;
        private int hours;
        private double salary;

        public Builder id(Long id)  {
            this.id=id;
            return  this;
        }

        public Builder name(String name)  {
            this.name= name;
            return this ;
        }

        public Builder surname(String surnam)  {
            this.surname=surnam;
            return this;
        }
        public Builder jobTitle(String job){
            this.jobTitle=job;
            return this;
        }
        public Builder hoursWorked(int hours){
            this.hours=hours;
            return  this;
        }
        public Builder rate(double rate){
            this.rate=rate;
            return this;
        }
        public Builder salary(){
            this.salary = rate*hours;
            return this;
        }

        public Builder copy(Employee employee)  {
            this.id=employee.id;
            this.name=employee.name;
            this.surname=employee.surname;
            this.jobTitle=employee.jobTitle;
            this.rate=employee.rate;
            this.hours=employee.hours;
            this.salary=employee.salary;
            return this;
        }

        public Employee build()
        {
            return new Employee(this);
        }
    }
}
