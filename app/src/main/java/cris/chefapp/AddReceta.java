package cris.chefapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.LinkedList;
import Menu.Pasos;
import Menu.PlatilloSerializable;
import Menu.Receta;
import interfaz.Interfaz;


public class AddReceta extends AppCompatActivity {

    ListView pasosList;
    String[] pasosStr = new String[15];
    int contador = 0;
    LinkedList<Pasos> listaPasos = new LinkedList<Pasos>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receta);

        //Inicializar la lista vacía para la vista
        for (int i =  0; i < 15; i++){
            pasosStr[i] = "vacio...";
        }
        //Se instancia la vista tipo lista y se crea el adapter
        pasosList = (ListView)findViewById(R.id.listaPasos);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pasosStr);
        pasosList.setAdapter(adapter);

        //Parametro tipo string, paso
        final EditText paso = (EditText)findViewById(R.id.strpaso);

        Button btnagregarPaso = (Button)findViewById(R.id.addpaso);
        Button btnTerminar = (Button)findViewById(R.id.listo);

        btnagregarPaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pasoReceta = paso.getText().toString();
                if(contador < 15) {

                    if(pasoReceta.length()>0){
                        //aquí se debe agregar a la clase el paso y a la lista de pasos
                        pasosStr[contador] = ((contador+1)+"-"+pasoReceta);
                        contador++;
                        paso.setText("");
                        pasosList.setAdapter(adapter);

                        Pasos paso1 = new Pasos();
                        paso1.setPaso(pasoReceta);
                        listaPasos.add(paso1);
                        //esconde el teclado
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(paso.getWindowToken(), 0);
                        //Dialog Message
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddReceta.this);
                        builder.setTitle("¡Listo!");
                        builder.setMessage("Se ha agregado con exito");
                        builder.setPositiveButton("OK", null);
                        builder.create();
                        builder.show();
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddReceta.this);
                        builder.setTitle("¡Atencion!");
                        builder.setMessage("Agregue la información necesaria");
                        builder.setPositiveButton("OK", null);
                        builder.create();
                        builder.show();
                    }


                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddReceta.this);
                    builder.setTitle("¡Atencion!");
                    builder.setMessage("Se ha alcanzado la cantidad maxima de pasos");
                    builder.setPositiveButton("OK", null);
                    builder.create();
                    builder.show();
                }

            }
        });

        btnTerminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // se debe agregar los parametros anteriores para agregarlos a la instancia de la clase platillo
                // Y pasarlos al servidor
                PlatilloSerializable platillo = (PlatilloSerializable)getIntent().getExtras().getSerializable("platillo");
                Receta receta1 = new Receta();
                receta1.setListaPasos(listaPasos);
                platillo.setReceta(receta1);

                //implementar el envío del platillo al server
                Intent intent = new Intent(AddReceta.this, Menu.class);
                Interfaz.addPlatilloMenu(platillo);
                startActivity(intent);
                finish();
            }
        });



    }
}
