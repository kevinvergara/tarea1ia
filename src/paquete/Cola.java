package paquete;

import java.util.LinkedList;

public class Cola {
    LinkedList cola = new LinkedList();

    public Cola() {
    }
    
    public void encolar(Nodo a){
        cola.addFirst(a);
    }
    
    public Nodo desencolar(){
        return (Nodo)cola.removeLast();
    }
}
