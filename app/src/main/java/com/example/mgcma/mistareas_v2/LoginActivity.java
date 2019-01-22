package com.example.mgcma.mistareas_v2;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
    }

    public void crearCuenta(View view){
        Toast toast = Toast.makeText(this,"Funcionalidad no disponible",Toast.LENGTH_LONG);
        toast.show();
    }

    public void login (View view){
        EditText usuario = (EditText) findViewById(R.id.editText);
        EditText pass = (EditText) findViewById(R.id.editText2);

        if(usuario.getText().toString().equalsIgnoreCase("maria") && pass.getText().toString().equalsIgnoreCase("12345")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this,"Usuario/contraseña inválido",Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
