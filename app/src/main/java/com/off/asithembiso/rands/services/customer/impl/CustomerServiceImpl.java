package com.off.asithembiso.rands.services.customer.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.off.asithembiso.rands.conf.util.App;
import com.off.asithembiso.rands.domain.Customer;
import com.off.asithembiso.rands.repositories.implementation.CustomerRepositoryImpl;
import com.off.asithembiso.rands.repositories.interfaces.CustomerRepository;
import com.off.asithembiso.rands.services.customer.CustomerService;

/**
 * Created by asithembiso on 2016/10/20.
 */

public class CustomerServiceImpl extends IntentService implements CustomerService {

    private final CustomerRepository repository;
    private static  CustomerServiceImpl service = null;


    private static final String ACTION_ADD ="package com.off.asithembiso.rands.services.customer.impl.action.ADD";
    private static final String ACTION_UPDATE="package ccom.off.asithembiso.rands.services.customer.impl.action.action.Update";

    private static final String EXTRA_ADD = "package com.off.asithembiso.rands.services.customer.impl.action.Impl.extra.ADD";


    public CustomerServiceImpl() {
        super("CustomerServiceImpl");
        repository = new CustomerRepositoryImpl(App.getAppContext());
    }

    private static CustomerServiceImpl getInstance(){
        if(service == null)
            service = new CustomerServiceImpl();
        return service;
    }



    @Override
    public void addCustomer(Context context, Customer customer) {
        Intent intent = new Intent(context,CustomerServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD,customer);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        Customer customerResources = (Customer) intent.getSerializableExtra(EXTRA_ADD);
        Customer customer = new Customer.Builder()
                .fullName(customerResources.getFullName())
                .id(customerResources.getId())
                .password(customerResources.getPassword())
                .email(customerResources.getEmail())
                .build();
        repository.save(customer);
        Toast.makeText(getApplicationContext(),"Tenant has been added",Toast.LENGTH_LONG).show();

    }

}
