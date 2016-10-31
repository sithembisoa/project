package com.off.asithembiso.rands;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.off.asithembiso.rands.domain.Employee;
import com.off.asithembiso.rands.repositories.implementation.EmployeeRepositoryImpl;
import com.off.asithembiso.rands.repositories.interfaces.EmployeeRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class DisplayEmployees extends AppCompatActivity {

    private EmployeeRepository repo;

    private ArrayList<String> employees;
    private ArrayAdapter arrayAdapter;
    GridView employeeGridView;
    EditText search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_employees);
        search=(EditText) findViewById(R.id.txtSearch);
        repo = new EmployeeRepositoryImpl(this);
        loadGrid();
    }

   public void loadGrid(){
      /* Set<Employee> objEmployees= repo.findAll();
       Iterator<Employee> employeeIterator = objEmployees.iterator();
       employees = new ArrayList<>();

       while (employeeIterator.hasNext()){
           employees.add(""+employeeIterator.next().getId());
           employees.add(""+employeeIterator.next().getName());
           employees.add(""+employeeIterator.next().getJobTitle());
           employees.add(""+employeeIterator.next().getSalary());
       }

       arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, employees);
       employeeGridView = (GridView) findViewById(R.id.gridView);
       employeeGridView.setAdapter(arrayAdapter);*/
    }

    public void updateEmployee(View view) {

        Intent intent = new Intent(getApplicationContext(), AddEmployee.class);
        try {
            String id = search.getText().toString();
            Long idLong = Long.parseLong(id);
            Employee employee = repo.findById(idLong);
            if(employee==null){
                Toast.makeText(getBaseContext(), "not found", Toast.LENGTH_LONG).show();
            }
            else {
                intent.putExtra("emp", employee);
                intent.putExtra("key", "UPDATE");
                startActivity(intent);
            }
        } catch (Exception e){
            Toast.makeText(getBaseContext(), "not found"+ e.getMessage(), Toast.LENGTH_LONG).show();

        }

    }

    public void addEmployee(View view) {
        Intent intent = new Intent("com.off.asithembiso.rands.AddEmployee");
        intent.putExtra("key","ADD");
        startActivity(intent);
    }

    public void search(View view) {
        String id=search.getText().toString();
        try {
            Long idLong = Long.parseLong(id);
            Employee employee = repo.findById(idLong);
            if (employee == null) {
                Toast.makeText(getBaseContext(), "not found", Toast.LENGTH_LONG).show();
            } else {

            }
        } catch(Exception e){
            Toast.makeText(getBaseContext(), "make sure you entered right format", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteEmployee(View view) {
       try {
            String id = search.getText().toString();
            Long idLong = Long.parseLong(id);
            Employee employee = repo.findById(idLong);
            repo.remove(employee);
            loadGrid();
        }  catch(Exception e) {
            Toast.makeText(getBaseContext(), "not found" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}


