package com.off.asithembiso.rands;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
    }

    public void onEmployeesClick (View view){
        Intent i = new Intent(getApplicationContext(), DisplayEmployees.class);
        startActivity(i);
    }

    public void onExitClick(View view){
        finish();
    }

}
