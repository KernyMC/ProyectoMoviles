package com.example.pryapigoogle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    //Declarar los objetos gráficos
    EditText txt_usuario,txt_clave;
    Button btn_login;

    private String user ="Admin",clave1 ="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //mapeo de objetos

        txt_usuario = findViewById(R.id.et_usuario);
        txt_clave = findViewById(R.id.et_contrasena);
        btn_login = findViewById(R.id.bt_login);

        //método onclick
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = txt_usuario.getText().toString();
                String clave = txt_clave.getText().toString();

                if (usuario.length() != 0) {
                    if (usuario.equals(user)) {
                        // Verificación contraseña
                        if (clave.equals(clave1)) {
                            Toast.makeText(Login.this, "Datos correctos", Toast.LENGTH_SHORT).show();
                            // Conexión a otra actividad
                            Intent conec = new Intent(Login.this, RestaurantsActivity.class);
                            conec.putExtra("usuario", user); // Agregar esta línea

                            startActivity(conec);
                        } else {
                            Toast.makeText(Login.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Login.this, "Usuario incorrecta", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Datos incompletos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}