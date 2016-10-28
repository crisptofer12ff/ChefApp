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
import android.widget.Spinner;

import Menu.PlatilloSerializable;
import estructurasDatos.ListaDoble;
import ingredientes.Carnes;
import ingredientes.Frutas;
import ingredientes.Granos;
import ingredientes.IngredienteSolo;
import ingredientes.Lacteos;
import ingredientes.Vegetales;

public class AddIngredientes extends AppCompatActivity {

    ListView listViewIng;
    ListaDoble<IngredienteSolo> listaIngredientes;
    int contador;
    String[] ingredientes = new String[25];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_platillo);

        // Se agregan las categorias al spinner
        String[] categorias = {"Vegetal", "Carne", "Grano", "Lacteo", "Fruta"};
        final Spinner categoriaActual = (Spinner)findViewById(R.id.category);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, categorias);
        categoriaActual.setAdapter(adapter);

        //Informacion del ingrediente
        final EditText nombreIngrediente = (EditText)findViewById(R.id.nomIngrediente);
        final EditText cantidad = (EditText)findViewById(R.id.cantIngrediente);

        //inicializa un array vacío para poder ver los ingredientes agregados
        for (int i = 0; i<25; i++){
            ingredientes[i] = "vacio.....";
        }
        listViewIng = (ListView)findViewById(R.id.listaIngredientes);
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ingredientes);
        listViewIng.setAdapter(adapter1);

        Button btnAgregar = (Button)findViewById(R.id.addIngredient);
        Button bntSiguiente = (Button)findViewById(R.id.next);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre0 = nombreIngrediente.getText().toString();
                String categoria = categoriaActual.getSelectedItem().toString();
                String cantidades = cantidad.getText().toString();

                if(contador < 25){
                    if(nombre0.length()!=0 && cantidad.length() != 0){
                        String nombre = nombre0+"     "+cantidades;
                        switch (categoria){
                            case "Vegetal":
                                Vegetales vegetal = new Vegetales();
                                vegetal.setName(nombre);
                                IngredienteSolo ing =new IngredienteSolo();
                                ing.setVegetal(vegetal);
                                listaIngredientes.add(ing);
                                break;
                            case "Carne":
                                Carnes carne = new Carnes();
                                carne.setName(nombre);
                                IngredienteSolo ing1 =new IngredienteSolo();
                                ing1.setCarne(carne);
                                listaIngredientes.add(ing1);
                                break;
                            case "Grano":
                                Granos grano = new Granos();
                                grano.setName(nombre);
                                IngredienteSolo ing2 =new IngredienteSolo();
                                ing2.setGrano(grano);
                                listaIngredientes.add(ing2);
                                break;
                            case "Lacteo":
                                Lacteos lacteo = new Lacteos();
                                lacteo.setName(nombre);
                                IngredienteSolo ing3 =new IngredienteSolo();
                                ing3.setLacteo(lacteo);
                                listaIngredientes.add(ing3);
                                break;
                            case "Fruta":
                                Frutas fruta = new Frutas();
                                fruta.setName(nombre);
                                IngredienteSolo ing4 =new IngredienteSolo();
                                ing4.setFruta(fruta);
                                listaIngredientes.add(ing4);
                                break;
                            default:
                                break;
                        }
                        ingredientes[contador] = nombre;
                        contador++;
                        cantidad.setText("");
                        nombreIngrediente.setText("");
                        listViewIng.setAdapter(adapter1);

                        //esconde el teclado
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(cantidad.getWindowToken(), 0);

                        AlertDialog.Builder builder = new AlertDialog.Builder(AddIngredientes.this);
                        builder.setTitle("¡Listo!");
                        builder.setMessage("Se agregó correctamente");
                        builder.setPositiveButton("OK",null);
                        builder.create();
                        builder.show();
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddIngredientes.this);
                        builder.setTitle("¡Atención!");
                        builder.setMessage("Por favor complete la información");
                        builder.setPositiveButton("OK", null);
                        builder.create();
                        builder.show();
                    }
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddIngredientes.this);
                    builder.setTitle("¡Atención!");
                    builder.setMessage("¡Se alcanzó la cantidad maxima de ingredientes para el platillo!");
                    builder.setPositiveButton("OK", null);
                    builder.create();
                    builder.show();
                }

            }
        });
        bntSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se debe pasar los parametros recuperados a la siguente pantalla
                PlatilloSerializable platillo = (PlatilloSerializable)getIntent().getExtras().getSerializable("platillo");
                platillo.setListaIngredientes(listaIngredientes);
                Intent intent = new Intent(AddIngredientes.this, AddReceta.class);
                intent.putExtra("platillo", platillo);
                startActivity(intent);
                finish();
            }
        });



    }
}
