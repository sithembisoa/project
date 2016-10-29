package com.off.asithembiso.rands;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.off.asithembiso.rands.domain.Customer;
import com.off.asithembiso.rands.repositories.implementation.CustomerRepositoryImpl;
import com.off.asithembiso.rands.repositories.interfaces.CustomerRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private TextView loginLink;
    private CustomerRepository repo = new CustomerRepositoryImpl(this);
    private Customer insertEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        loginLink = (TextView) findViewById(R.id.loginLink);

        loginLink.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.off.asithembiso.rands.LoginActivity");
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
                startActivity(intent);
            }
        });
    }

    public void onSignUpClick(View view){

        if (view.getId() == R.id.btnReg){
            EditText email = (EditText)findViewById(R.id.email_txt);
            EditText password = (EditText)findViewById(R.id.password);
            EditText passwordRetype = (EditText)findViewById(R.id.passwordRetype);
            EditText name = (EditText)findViewById(R.id.name_txt);

            String email_str = email.getText().toString();
            String password_str = password.getText().toString() ;
            String passwordRetype_str = passwordRetype.getText().toString();
            String name_str = name.getText().toString();

            if (!password_str.equals(passwordRetype_str)){
                Toast.makeText(RegisterActivity.this,"Passwords Don't match", Toast.LENGTH_LONG).show();
                password.requestFocus();
            }else {
                Customer customer = new Customer.Builder()
                        .email(email_str)
                        .password(password_str)
                        .fullName(name_str)
                        .build();

                insertEntity =  repo.save(customer);

                Toast.makeText(RegisterActivity.this,"Registration Success",Toast.LENGTH_LONG).show();
                Intent intent = new Intent("com.off.asithembiso.rands.LoginActivity");
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
                startActivity(intent);

            }
        }
    }

    private boolean validateEmail(String email) {
        String emailPattern ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private boolean validatePassword(String password) {
        if(password!=null && password.length()>5){
            return true;
        }else{
            return false;
        }
    }

}
