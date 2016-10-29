package cris.chefapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import Comunicacion.DatosRecibidos;
import estructurasDatos.*;
import Menu.Pasos;

public class Ordenes extends AppCompatActivity {

    ListaDoble<Pasos> listaPasos = DatosRecibidos.receta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordenes);

        final String[] pasos = new String[15];
        for (int i =  0; i < 15; i++){
            pasos[i] = "vacio...";
        }
        NodoDoble<Pasos> temp = listaPasos.inicio;
        for(int i = 0; i < listaPasos.size; i++, temp = temp.getSiguiente()){
            pasos[i] = temp.getDato().getPaso();
        }
        final ListView listViewPasos = (ListView)findViewById(R.id.pasosReceta);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, pasos);
        listViewPasos.setAdapter(adapter);

        Button terminarPaso = (Button)findViewById(R.id.terminarPaso);
        terminarPaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listaPasos.size == 1) {

                    // Lanza mensaje de terminado
                    AlertDialog.Builder builder = new AlertDialog.Builder(Ordenes.this);
                    builder.setTitle("¡Listo!");
                    builder.setMessage("Todos los Pasos se han completado");
                    builder.setPositiveButton("OK",null);
                    builder.create();
                    builder.show();
                    Intent intent = new Intent(Ordenes.this, Menu.class);
                    startActivity(intent);
                }
                else {

                    listaPasos.deleteFirst();
                    for (int i =  0; i < 15; i++){
                        pasos[i] = "vacio...";
                    }
                    NodoDoble<Pasos> temp = listaPasos.inicio;
                    for(int i = 0; i < listaPasos.size; i++, temp = temp.getSiguiente()){
                        if(listaPasos.isEmpty()){
                            break;
                        }
                        else{
                            pasos[i] = temp.getDato().getPaso();
                        }
                    }
                    listViewPasos.setAdapter(adapter);
                }

            }

        });


    }
}
