package estructurasDatos;

import java.util.NoSuchElementException;


public class ListaDoble<T> {

	public NodoDoble<T> inicio;
	private NodoDoble<T> ultimo;
	public int size;
	
	public ListaDoble() {
		//size = 0;
	}
	public int size() { 
		return size; 
	}	
	public boolean isEmpty() { 
		return size == 0; 
	}
	
	public void add(T dato) {
		
		NodoDoble<T> temporal = new NodoDoble<T>(dato);
		if(inicio != null){
			ultimo.setSiguiente(temporal);
			temporal.setAnterior(ultimo);
			ultimo = temporal;
		}
		else{
			inicio = ultimo = temporal;
		}
		size++;
	}

	public T deleteFirst() {
		if (size == 0) throw new NoSuchElementException();
		NodoDoble<T> temporal = inicio;
		inicio = inicio.getSiguiente();
		inicio.setAnterior(null);
		size--;
		return temporal.getDato();
	}
}
	
	
	
