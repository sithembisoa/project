package com.off.asithembiso.rands.domain;

/**
 * Created by asithembiso on 2016/08/26.
 */


import java.io.Serializable;

/**
 * Created by asithembiso on 2016/08/26.
 */

public class Admin implements Serializable {

    private Long id;
    private String idNumber;
    private String adminName;
    private String adminLastName;
    private String password;

    public Admin(){}

    public Admin(Builder builder) {
        this.id =builder.id;
        this.idNumber =builder.idNumber;
        this.adminName=builder.adminName;
        this.adminLastName=builder.adminLastName;
    }

    public Long getId() {
        return id;
    }
    public String getadminId() {
        return idNumber;
    }
    public String getadminName() {
        return adminName;
    }
    public String getadminLastName() {
        return adminLastName;
    }
    public String getadminPass() {
        return password;
    }

    public static class Builder {
        private Long id;
        private String idNumber;
        private String adminName;
        private String adminLastName;
        private String password;

        public Builder id(Long id) {
            this.id=id;
            return  this;
        }
        public Builder password(String password) {
            this.password=password;
            return  this;
        }

        public Builder idNumber(String idNumber) {
            this.idNumber= idNumber;
            return this ;
        }
        public Builder firstName(String firstName) {
            this.adminName=firstName;
            return this;
        }
        public Builder lastName(String lastName) {
            this.adminLastName=lastName;
            return this;
        }
        public Builder copy(Admin adminValue) {
            this.id=adminValue.id;
            this.idNumber=adminValue.idNumber;
            this.adminName=adminValue.adminName;
            this.adminLastName=adminValue.adminLastName;
            this.password = adminValue.password;
            return this;
        }

        public Admin build()
        {
            return new Admin(this);
        }

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        return id == admin.id;

    }
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}

