package com.example.crudfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Agregar extends AppCompatActivity {

    EditText nombre, apellido, email, imgurl;

    Button volver, agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        nombre = findViewById(R.id.nombretextedit);
        apellido = findViewById(R.id.apellidotextedit);
        email = findViewById(R.id.emailtextedit);
        imgurl = findViewById(R.id.imgltextedit);

        agregar = findViewById(R.id.btn_agregar);
        volver = findViewById(R.id.btn_volver);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarDatos();
            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void insertarDatos(){
        Map<String,Object> map = new HashMap<>();
        map.put("Nombre", nombre.getText().toString());
        map.put("Apellido", apellido.getText().toString());
        map.put("email", email.getText().toString());
        map.put("imgURL", imgurl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Programación Android")
                .setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Agregar.this, "Insertado de manera correcta", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Agregar.this, "Error al intentar insertarlo", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}