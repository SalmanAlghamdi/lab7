package com.example.lab7;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText salary, name, id;
    String id_val;
    String name_val;
    int salary_val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);
        salary = (EditText)findViewById(R.id.salaryET) ;
        name = (EditText)findViewById(R.id.nameET) ;
        id = (EditText)findViewById(R.id.idET);
        final Button bttnAdd = (Button)findViewById(R.id.bttnAdd);
        final Button bttnView = (Button)findViewById(R.id.bttnFind);
        final Button bttnDelete = (Button)findViewById(R.id.bttnDELETE);
        bttnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_val = id.getText().toString();
                name_val = name.getText().toString();
                salary_val = Integer.parseInt(salary.getText().toString());
                myDB.AddEmployee(id_val,name_val,salary_val);
                Toast.makeText(MainActivity.this, "Successful Add", Toast.LENGTH_LONG).show();
            }
        });
        bttnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cur = myDB.ViewEmployees();
                StringBuffer buffer = new StringBuffer();
                while (cur.moveToNext()) {
                    buffer.append("id: " + cur.getString(0) + "\n");
                    buffer.append("Name: " + cur.getString(1) + "\n");
                    buffer.append("Salary: " + cur.getString(2) + "\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("All Employees");
                builder.setMessage(buffer.toString());
                builder.show();
                Toast.makeText(MainActivity.this, "Successful View", Toast.LENGTH_LONG).show();
            }
        });
        bttnDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                id_val = id.getText().toString();
                myDB.DeleteEmployees(id_val);
                Toast.makeText(MainActivity.this, "Successful Delete", Toast.LENGTH_LONG).show();
            }
        });
    }
}