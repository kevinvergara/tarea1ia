
package paquete;

import java.util.ArrayList;

public class ListaAuxiliar {
    private ArrayList nodos = new ArrayList();
    
    public void setNodo(Nodo nodo){
        this.nodos.add(nodo);
    }
    
    public Nodo getNodo(int index){
        return (Nodo)this.nodos.get(index);
    }
    
    public int tamaño(){
        return this.nodos.size();
    }
    
    public void borrar(){
        this.nodos.clear();
    }
}
