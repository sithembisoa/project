package com.off.asithembiso.rands;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private TextView loginLink;

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

        final EditText email = (EditText)findViewById(R.id.email_txt);
        final EditText password = (EditText)findViewById(R.id.password);
        final EditText name = (EditText)findViewById(R.id.name_txt);


        Button validate = (Button)findViewById(R.id.btnReg);


        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.length()<8){
                    name.setError("Enter Full Name");
                    name.requestFocus();
                }else if(!validateEmail(email.getText().toString())){
                    email.setError("Invalid Email");
                    email.requestFocus();
                }else if(!validatePassword(password.getText().toString())){
                    password.setError("Password must contain 8 characters");
                    password.requestFocus();
                }else{

                    Toast.makeText(RegisterActivity.this,"Registration Success",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent("com.off.asithembiso.rands.LoginActivity");
                    intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
                    startActivity(intent);
                }
            }
        });
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
