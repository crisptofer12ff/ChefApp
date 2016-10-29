package Comunicacion;

import estructurasDatos.ListaDoble;
import Menu.Pasos;

/**
 * Created by ggg on 28/10/2016.
 */

public class DatosRecibidos {

    public static String Cheff;
    public static ListaDoble<Pasos> receta;

    public static void getPasos(){

    }

    public static String getCheffName(){
    return "hola";
    }

    public static void setListaPasos(ListaDoble<Pasos> lista){
        receta = lista;
    }


}
