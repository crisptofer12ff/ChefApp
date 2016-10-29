package cris.chefapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import ingredientes.Carnes;
import ingredientes.Frutas;
import ingredientes.Granos;
import ingredientes.IngredienteSolo;
import ingredientes.Lacteos;
import ingredientes.Vegetales;
import interfaz.Interfaz;


public class RegistrarInv extends AppCompatActivity {

    Spinner categoriaActual;
    EditText nombreIngrediente;
    EditText cantidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_inv);
        String[] categorias = {"Vegetal", "Carne", "Grano", "Lacteo", "Fruta"};
        categoriaActual = (Spinner)findViewById(R.id.categoriaSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, categorias);
        categoriaActual.setAdapter(adapter);
        nombreIngrediente = (EditText)findViewById(R.id.nombreIngrediente);
        cantidad = (EditText)findViewById(R.id.cantidadIngrediente);


        Button btnAgregar = (Button)findViewById(R.id.agregarIngrediente);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreIngrediente.getText().toString();
                String categoria = categoriaActual.getSelectedItem().toString();
                String cantidades = cantidad.getText().toString();

                if(nombre.length()!=0 && cantidad.length() != 0){
                    switch (categoria){
                        case "Vegetal":
                            Vegetales vegetal = new Vegetales();
                            vegetal.setName(nombre);
                            IngredienteSolo vegetal1 = new IngredienteSolo();
                            vegetal1.setVegetal(vegetal);
                            Interfaz.setIngredientes(vegetal1);
                            break;

                        case "Carne":
                            Carnes carne1 = new Carnes();
                            carne1.setName(nombre);
                            IngredienteSolo carne = new IngredienteSolo();
                            carne.setCarne(carne1);
                            Interfaz.setIngredientes(carne);
                            break;

                        case "Grano":
                            Granos grano1 = new Granos();
                            grano1.setName(nombre);
                            IngredienteSolo grano = new IngredienteSolo();
                            grano.setGrano(grano1);
                            Interfaz.setIngredientes(grano);
                            break;

                        case "Lacteo":
                            Lacteos lacteo1 = new Lacteos();
                            lacteo1.setName(nombre);
                            IngredienteSolo lacteo = new IngredienteSolo();
                            lacteo.setLacteo(lacteo1);
                            Interfaz.setIngredientes(lacteo);
                            break;
                        case "Fruta":
                            Frutas fruta1 = new Frutas();
                            fruta1.setName(nombre);
                            IngredienteSolo fruta = new IngredienteSolo();
                            fruta.setFruta(fruta1);
                            Interfaz.setIngredientes(fruta);
                            break;
                        default:
                            break;
                    }
                    cantidad.setText("");
                    nombreIngrediente.setText("");
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegistrarInv.this);
                    builder.setTitle("¡Listo!");
                    builder.setMessage("Se agregó correctamente");
                    builder.setPositiveButton("OK",null);
                    builder.create();
                    builder.show();
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegistrarInv.this);
                    builder.setTitle("¡Atención!");
                    builder.setMessage("Por favor complete la información");
                    builder.setPositiveButton("OK",null);
                    builder.create();
                    builder.show();
                }
            }
        });



    }
}
