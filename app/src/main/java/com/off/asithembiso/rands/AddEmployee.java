package com.off.asithembiso.rands;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.off.asithembiso.rands.domain.Employee;
import com.off.asithembiso.rands.factories.EmployeeFactory;
import com.off.asithembiso.rands.repositories.implementation.EmployeeRepositoryImpl;
import com.off.asithembiso.rands.repositories.interfaces.EmployeeRepository;

public class AddEmployee extends AppCompatActivity {

    private EmployeeRepositoryImpl repo;
    private Bundle bundle;
    private String key;

    private EditText name, hours, lastName, job, rate;
    private TextView salary,idNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        repo = new EmployeeRepositoryImpl(this.getApplicationContext());
        idNumber = (TextView) findViewById(R.id.txtID);
        name = (EditText) findViewById(R.id.txtName);
        lastName = (EditText) findViewById(R.id.txtSurname);
        job = (EditText) findViewById(R.id.txtJob);
        rate = (EditText) findViewById(R.id.txtrate);
        hours = (EditText) findViewById(R.id.txtHours);
        salary = (TextView) findViewById(R.id.txtSalary);

        Intent getIntent= getIntent();
        bundle= getIntent.getExtras();
        key=(String)bundle.get("key");

        if (key.equals("UPDATE")) {
            if (bundle != null){
                Employee employee = (Employee) bundle.get("emp");
                String strRate= Double.toString(employee.getRate());
                idNumber.setText(Long.toString( employee.getId()));
                name.setText(employee.getName().toString());
                lastName.setText(employee.getSurname().toString());
                job.setText(employee.getJobTitle().toString());
                rate.setText(strRate);
                hours.setText(Integer.toString( employee.getHours()));
                salary.setText(Double.toString( employee.getSalary()));
                repo.update(employee);
            }
        }
    }

    public void onBtnSaveClick(View view) {
        try {
            if(bundle==null) {
            }
            else {
                if (key.equals("UPDATE")) {
                    Employee employee =(Employee) bundle.get("emp");
                    Employee update= new Employee.Builder()
                            .copy(employee)
                            .id(Long.parseLong(idNumber.getText().toString()))
                            .name(name.getText().toString())
                            .surname(lastName.getText().toString())
                            .jobTitle(job.getText().toString())
                            .hoursWorked(Integer.parseInt(hours.getText().toString()))
                            .rate(Double.parseDouble(rate.getText().toString()))
                            .salary()
                            .build();

                    repo.update(update);
                    Intent i = new Intent("com.off.asithembiso.rands.DisplayEmployees");
                    startActivity(i);


                } else {
                    String hRate = rate.getText().toString();
                    if (hRate.contains(".")) {

                    } else {
                        hRate = hRate.concat(".0");
                    }
                    Employee employee = new EmployeeFactory().createEmployee(
                            name.getText().toString(), lastName.getText().toString(),
                            job.getText().toString(), Double.parseDouble(hRate),
                            Integer.parseInt(hours.getText().toString()));
                    repo.add(employee);
                    clear();
                }
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getBaseContext(), e.getMessage()+"make sure you entered valid input", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage()+"something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    public void calculateSalary(View view) {
            try {
                Double sal = Integer.parseInt(hours.getText().toString()) * Double.parseDouble(rate.getText().toString());
                salary.setText(Double.toString( sal));

            } catch (NumberFormatException e) {
                Toast.makeText(getBaseContext(), e.getMessage()+"make sure you entered valid input", Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                Toast.makeText(getBaseContext(), e.getMessage()+"", Toast.LENGTH_LONG).show();
        }
    }
    public void clear(){
        idNumber.setText("");
        name.setText("");
        lastName.setText("");
        job.setText("");
        rate.setText("");
        hours.setText("");
        salary.setText("");
    }
    public void home(View view) {
        Intent i = new Intent("com.off.asithembiso.rands.StartMenuActivity");
        startActivity(i);

    }
}

