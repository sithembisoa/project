
package com.off.asithembiso.rands.domain;

import java.io.Serializable;

/**
 * Created by asithembiso on 2016/08/25.
 */

public class Customer implements Serializable {

    private Long id;
    private String fullName;
    private String email;
    private String password;

    public Customer(){}

    public Customer(Builder builder) {
        email = builder.email;
        fullName = builder.fullName;
        id = builder.id;
    }

    public String getFullName() {return fullName;}

    public String getEmail() { return email;}

    public Long getId(){return id;}

    public String getPassword(){return this.password;}

    public static class Builder {

        private String fullName;
        private String email;
        private Long id;
        private String password;

        public Builder (String email) {
            this.email = email;
        }

        public Builder(){}

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder password(String string){
            this.password = string;
            return this;
        }

        public Builder email(String email){this.email = email;return this;}
        public Builder id(Long id){this.id = id; return this;}

        public Builder copy(Customer Customer){

            this.email = Customer.getEmail();
            this.fullName = Customer.getFullName();
            this.password = Customer.password;
            this.id=Customer.id;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return id == customer.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

}


