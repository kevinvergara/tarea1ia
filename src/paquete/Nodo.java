
package paquete;

import java.util.Vector;

public class Nodo {
    private String [] colores;
    private int [] posicion = new int [2];
    
    private Vector nodos = new Vector();
    private String [][] matriz ;
    
    private int nivel;

    public Nodo() {
    }
    
    

    public Nodo(String [][] matriz,String [] colores, int [] posicion, int nivel) {
        if(posicion != null){
            this.posicion=posicion.clone();
        }
        this.matriz = matriz.clone();
        this.colores = colores.clone();
        this.nivel = nivel;
        
    }

    public String[] getColores() {
        return this.colores;
    }
    
    public int size(){
        return nodos.size();
    }
    
    public Nodo getNodo(int index) {
        return (Nodo)this.nodos.get(index);
    }

    public void setNodos(Nodo nodo) {
        this.nodos.addElement(nodo);
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
