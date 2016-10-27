package cris.chefapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InformacionPlatillo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_platillo);

        final EditText nutri = (EditText)findViewById(R.id.informacionNutricional);
        final EditText price = (EditText)findViewById(R.id.precio);
        final EditText time = (EditText)findViewById(R.id.tiempoPreparacion);
        final EditText info = (EditText)findViewById(R.id.datoscomplementarios);

        Button btnSiguiente = (Button)findViewById(R.id.next2);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nutricionalInfor = nutri.getText().toString();
                String precio = price.getText().toString();
                String tiempo = time.getText().toString();
                String datosComp = info.getText().toString();

                if(nutricionalInfor.isEmpty() && precio.isEmpty() && tiempo.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(InformacionPlatillo.this);
                    builder.setTitle("¡Atencion!");
                    builder.setMessage("Agregue la información necesaria");
                    builder.setPositiveButton("OK", null);
                    builder.create();
                    builder.show();
                }
                else {


                    // agregar los parametros a la clase para mandarla a la siguiente pantalla
                    Intent intent = new Intent(InformacionPlatillo.this, AddReceta.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
