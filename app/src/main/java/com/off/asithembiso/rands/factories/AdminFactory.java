package com.off.asithembiso.rands.factories;


import com.off.asithembiso.rands.domain.Admin;

/**
 * Created by asithembiso on 2016/08/26.
 */
public class AdminFactory {

    public static Admin createAdmin(String idNo, String fname, String lname, String pass){
        return new Admin.Builder()
                .idNumber(idNo)
                .firstName(fname)
                .lastName(lname)
                .password(pass)
                .build();
    }
}
