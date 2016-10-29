package com.off.asithembiso.rands.factories;


import com.off.asithembiso.rands.domain.Customer;

/**
 * Created by asithembiso on 2016/08/26.
 */
public class CustomerFactory {

    public static Customer createCustomer(String fullName, String email, String pass){
        return new Customer.Builder()
                .fullName(fullName)
                .email(email)
                .password(pass)
                .build();
    }
}