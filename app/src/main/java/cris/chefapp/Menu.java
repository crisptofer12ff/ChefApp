package cris.chefapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import interfaz.Interfaz;

import static interfaz.Interfaz.contenedor;


public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        TextView cheffName = (TextView)findViewById(R.id.cheffName);
        cheffName.setText(Interfaz.Cheff);
        Button btnRegistrarInv = (Button)findViewById(R.id.setInventary);
        btnRegistrarInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, RegistrarInv.class);
                startActivity(intent);
            }
        });

        Button btnRegistrarMenu = (Button)findViewById(R.id.setMenu);
        btnRegistrarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, InformacionPlatillo.class);
                startActivity(intent);
            }
        });

        Button btnVerOrdenes = (Button)findViewById(R.id.viewOrders);
        btnVerOrdenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!contenedor.getRestaurante().getColaOrdenes().equals(null)) {
                    Intent intent = new Intent(Menu.this, Ordenes.class);
                    startActivity(intent);
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
                    builder.setTitle("¡Atencion!");
                    builder.setMessage("No hay ordenes disponibles para preparar");
                    builder.setPositiveButton("OK", null);
                    builder.create();
                    builder.show();
                }
            }
        });
        Button btnCerrar = (Button)findViewById(R.id.cerrar2);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
