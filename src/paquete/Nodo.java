
package paquete;

import java.util.ArrayList;

public class Nodo {
    private String [] colores;
    private int [] posicion = new int [2];
    
    private ArrayList nodos = new ArrayList();
    private String [][] matriz ;
    
    private int nivel;
    

    public Nodo(String [][] matriz,String [] colores, int [] posicion, int nivel) {
        this.posicion=posicion.clone();
        this.matriz = matriz.clone();
        this.colores = colores.clone();
        this.nivel = nivel;
        
    }

    public String[] getColores() {
        return this.colores;
    }

    public Nodo getNodo(int index) {
        return (Nodo)nodos.get(index);
    }

    public void setNodos(Nodo nodo) {
        nodos.add(nodo);
    }

    public String[][] getMatriz() {
        return matriz;
    }

    public int getNivel() {
        return nivel;
    }

    public int[] getUltimaAnterior() {
        return posicion;
    }
    
    
}
