package cris.chefapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.LinkedList;

import interfaz.Interfaz;

public class ValidarCheff extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate);

        final EditText count = (EditText)findViewById(R.id.cuenta);
        EditText pass = (EditText)findViewById(R.id.cuenta);
        final String cuenta = count.getText().toString();
        final String contrasenia = pass.getText().toString();

        final LinkedList<String> parametros = new LinkedList<String>();
        parametros.add(cuenta);
        parametros.add(contrasenia);

        Button btningresar = (Button)findViewById(R.id.ingresar);
        Button btnregistrar = (Button)findViewById(R.id.registrarNuevo);

        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(parametros.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ValidarCheff.this);
                    builder.setTitle("¡Atencion!");
                    builder.setMessage("Por favor ingrese sus datos de inicio");
                    builder.setPositiveButton("OK", null);
                    builder.create();
                    builder.show();
                }
                else {


                    if(Interfaz.searchCheffCuenta(cuenta, contrasenia) == true){
                        Interfaz.Cheff = count.getText().toString();
                        Intent intent = new Intent(ValidarCheff.this, Menu.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ValidarCheff.this);
                        builder.setTitle("¡Atencion!");
                        builder.setMessage("Usuario o contraseña incorrecto");
                        builder.setPositiveButton("OK", null);
                        builder.create();
                        builder.show();
                    }
                }
            }
        });

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ValidarCheff.this, Register.class);
                startActivity(intent2);
                finish();
            }
        });
    }
}
