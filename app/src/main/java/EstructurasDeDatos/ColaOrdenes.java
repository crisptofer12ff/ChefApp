package EstructurasDeDatos;

import Algoritmos.Ordenador;
import Menu.Orden;

public class ColaOrdenes {

    private NodoDoble<Orden> primero;
    private int size;

    public ColaOrdenes() {
        primero = null;
        size = 0;
    }

    public void agregarACola(Orden orden) {
        NodoDoble<Orden> nuevaOrden = new NodoDoble<Orden>(orden);

        if (primero == null) {
            primero = nuevaOrden;
        }
        else{
            primero.setAnterior(nuevaOrden);
            primero = this.primero.getAnterior();
        }
        Ordenador.ordenarOrdenes(this);
        for(int i = 0; i < this.size(); i++){
            System.out.println(this.get(i).getDato().getCliente().getCategoria());
        }
        size++;
    }

    public Orden sacarDeCola() {
        if (primero == null){
            System.out.println("Esta pidiendo un nulo, revise");
            return null;
        }
        Orden orden = primero.getDato();
        primero = primero.getSiguiente();
        size--;
        return orden;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }


    public NodoDoble<Orden> get(int posicion){
        NodoDoble<Orden> temp = this.primero;

        for (int i = 0; i < posicion; i++){
            temp = temp.getSiguiente();
        }
        return temp;

    }

}

