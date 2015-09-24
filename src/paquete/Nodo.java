
package paquete;

public class Nodo {
    private String [] colores;
    private int [] ultimaAnterior = new int [2];
    
    private Nodo [] nodos;
    private String [][] matriz ;
    
    private int nivel;
    

    public Nodo(String [] colores, int [] ultimaPos,String [][] matriz, int nivel) {
        this.ultimaAnterior=ultimaPos.clone();
        this.matriz = matriz.clone();
        this.colores = colores.clone();
        this.nivel = nivel + 1;
    }

    public String[] getColores() {
        return colores;
    }

    public Nodo getNodos(int index) {
        return nodos [index];
    }

    public String[][] getMatriz() {
        return matriz;
    }

    public int getNivel() {
        return nivel;
    }
    
    
    
    
}
