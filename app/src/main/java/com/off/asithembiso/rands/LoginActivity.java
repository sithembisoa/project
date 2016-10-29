package com.off.asithembiso.rands;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.off.asithembiso.rands.repositories.implementation.CustomerRepositoryImpl;
import com.off.asithembiso.rands.repositories.interfaces.CustomerRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity {

    private TextView registerLink;
    private CustomerRepository repo = new CustomerRepositoryImpl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerLink = (TextView) findViewById(R.id.registerLink);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.off.asithembiso.rands.RegisterActivity");
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
                finish();
                startActivity(intent);
            }
        });
    }


    public void onLoginClick(View view){

        if (view.getId() == R.id.btnLog){
            EditText email = (EditText)findViewById(R.id.email_txt);
            EditText password = (EditText)findViewById(R.id.password);

            String email_str = email.getText().toString();
            String password_str = password.getText().toString();

            String pass = repo.findUser(email_str);

            if (pass.equals(password_str)) {
                Intent intent = new Intent(LoginActivity.this, StartMenuActivity.class);
                intent.putExtra("email", email_str);
                startActivity(intent);
            }else{
                Toast.makeText(LoginActivity.this,"Email and Password Don't match", Toast.LENGTH_LONG).show();

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
}
