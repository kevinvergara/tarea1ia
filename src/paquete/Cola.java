package paquete;

import java.util.ArrayList;
import java.util.LinkedList;

public class Cola extends ArrayList{
   /* LinkedList cola = new LinkedList();

    public Cola() {
    }
    
    public void encolar(Nodo a){
        cola.addLast(a);
    }
    
    public Nodo desencolar(){
        return (Nodo)cola.removeFirst();
    }
    
    public boolean vacio(){
        if (cola.size() == 0){
            return true;
        }else{
            return false;
        }
    }
    
    public int size(){
        return cola.size();
    }*/ 
 //se añade un elemento a la cola. Se añade al final de esta.
 public void encolar(Object dato){
  if(dato != null){
   this.add(dato);
  }else{
   System.out.println("Introduzca un dato no nulo");
  }  
 }

 //se elimina el elemento frontal de la cola, es decir, el primer elemento que entró.
 public void desencolar(){
  if(this.size() > 0){
   this.remove(0);
  }
 }
 
 //se devuelve el elemento frontal de la cola, es decir, el primer elemento que entró.
 public Object frente(){
  Object datoAuxiliar = null;
  if(this.size() > 0){
   datoAuxiliar = this.get(0);
  }
  return (Nodo)datoAuxiliar;  
 }
 
 //devuelve cierto si la pila está vacía o falso en caso contrario (empty).
 public boolean vacia(){
  return this.isEmpty();
 }
}
