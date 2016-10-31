package cris.chefapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import Menu.PlatilloSerializable;

public class InformacionPlatillo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_platillo);

        final EditText nom = (EditText)findViewById(R.id.nombrePlatillo);
        final EditText nutri = (EditText)findViewById(R.id.informacionNutricional);
        final EditText price = (EditText)findViewById(R.id.precio);
        final EditText time = (EditText)findViewById(R.id.tiempoPreparacion);
        final EditText cantIng = (EditText)findViewById(R.id.cantidadDeIng);
        final EditText info = (EditText)findViewById(R.id.datoscomplementarios);

        Button btnSiguiente = (Button)findViewById(R.id.next2);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = nom.getText().toString();
                String nutricionalInfor = nutri.getText().toString();
                String precio = price.getText().toString();
                String tiempo = time.getText().toString();
                String cant = cantIng.getText().toString();
                String datosComp = info.getText().toString();

                if(nutricionalInfor.isEmpty() && precio.isEmpty() && tiempo.isEmpty() && nombre.isEmpty() && cant.isEmpty() && datosComp.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(InformacionPlatillo.this);
                    builder.setTitle("¡Atencion!");
                    builder.setMessage("Agregue la información necesaria");
                    builder.setPositiveButton("OK", null);
                    builder.create();
                    builder.show();
                }
                else {


                    // agregar los parametros a la clase para mandarla a la siguiente pantalla
                    PlatilloSerializable platillo = new PlatilloSerializable();
                    platillo.setNombre(nombre);
                    platillo.setInformacionNutricional(nutricionalInfor);
                    int precio1 = Integer.parseInt(precio);
                    platillo.setPrecio(precio1);
                    int tiempo1 = Integer.parseInt(tiempo);
                    platillo.setTiempoDePreparacion(tiempo1);
                    int cant1 = Integer.parseInt(cant);
                    platillo.setCantidadDeIngredientes(cant1);
                    platillo.setDatoExtra(datosComp);
                    Intent intent = new Intent(InformacionPlatillo.this, AddIngredientes.class);
                    intent.putExtra("platillo", platillo);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
