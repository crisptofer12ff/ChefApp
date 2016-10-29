package cris.chefapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import interfaz.Interfaz;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText ip1 = (EditText)findViewById(R.id.ip);
        EditText puerto1 = (EditText)findViewById(R.id.puerto);
        Button btnconect = (Button)findViewById(R.id.enviarParametros);

        final String ip = ip1.getText().toString();
        final String puerto = puerto1.getText().toString();

        btnconect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ip.isEmpty() && puerto.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("¡Atencion!");
                    builder.setMessage("Por favor ingrese los parametros solicitados");
                    builder.setPositiveButton("OK", null);
                    builder.create();
                    builder.show();
                }
                else {
                    Interfaz.setURL(ip, puerto);
                    Intent intent = new Intent(MainActivity.this, ValidarCheff.class);
                    startActivity(intent);
                }
            }
        });
        ImageButton btnsalir = (ImageButton)findViewById(R.id.salir);

        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
