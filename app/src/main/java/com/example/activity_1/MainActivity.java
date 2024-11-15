package com.example.activity_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    DatePicker datePicker;
    Button nextButton;
    EditText nombre,telefono, correo, descripcion;

    private static final int REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        datePicker = findViewById(R.id.editTextDate);
        nextButton = findViewById(R.id.button);
        nombre = findViewById(R.id.editTextText);
        correo = findViewById(R.id.editTextTextEmailAddress);
        telefono = findViewById(R.id.editTextPhone);
        descripcion = findViewById(R.id.editTextTextMultiLine);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth()+1;
                int year = datePicker.getYear();
                String name = nombre.getText().toString();
                String email = correo.getText().toString();
                String phone = telefono.getText().toString();
                String description = descripcion.getText().toString();
                Intent intent = new Intent(MainActivity.this, ConfirmDataActivity.class);

                intent.putExtra("dia", day);
                intent.putExtra("mes", month);
                intent.putExtra("año", year);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("phone", phone);
                intent.putExtra("description", description);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            String name = data.getStringExtra("name");
            int day = data.getIntExtra("dia",12);
            int month = data.getIntExtra("mes",11)-1;
            int year = data.getIntExtra("año",2011);
            String email = data.getStringExtra("correo");
            String phone = data.getStringExtra("phone");
            String desc = data.getStringExtra("description");


            datePicker = findViewById(R.id.editTextDate);
            nombre = findViewById(R.id.editTextText);
            correo = findViewById(R.id.editTextTextEmailAddress);
            telefono = findViewById(R.id.editTextPhone);
            descripcion = findViewById(R.id.editTextTextMultiLine);

            nombre.setText(name);
            datePicker.updateDate(year,month,day);
            correo.setText(email);
            telefono.setText(phone);
            descripcion.setText(desc);

        }
    }
}

