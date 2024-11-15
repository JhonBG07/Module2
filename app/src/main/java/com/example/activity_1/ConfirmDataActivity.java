package com.example.activity_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConfirmDataActivity extends AppCompatActivity {

    Button edit;
    TextView nombre,fecha,telefono,correo,descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirm_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edit = findViewById(R.id.editButton);
        nombre = findViewById(R.id.nameView);
        fecha = findViewById(R.id.dateView);
        correo = findViewById(R.id.emailView);
        telefono = findViewById(R.id.phoneView);
        descripcion = findViewById(R.id.descriptionView);

        int day = getIntent().getIntExtra("dia",12);
        int month = getIntent().getIntExtra("mes",11);
        int year = getIntent().getIntExtra("año",2011);
        String dateString = day+"/"+month+"/"+year;
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");
        String desc = getIntent().getStringExtra("description");

        nombre.setText(name);
        fecha.setText("Fecha de Nacimiento: "+dateString);
        correo.setText("Email: "+email);
        telefono.setText("Telefono:" +phone);
        descripcion.setText("Descripción: "+desc);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("dia", day);
                intent.putExtra("mes", month);
                intent.putExtra("año", year);
                intent.putExtra("name", name);
                intent.putExtra("correo", email);
                intent.putExtra("phone", phone);
                intent.putExtra("description", desc);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }
}