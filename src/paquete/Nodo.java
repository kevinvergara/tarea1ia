
package paquete;

public class Nodo {
    private int nColores;
    private int color;
    private int [] ultimaAnterior = new int [2];
    private Nodo [] nodos = new Nodo[nColores];
    
    private int tamañoMatriz;
    private String [][] matriz = new String [tamañoMatriz][tamañoMatriz];
    

    public Nodo(int nColores, int [] ultimaPos,int color,String [][] matriz,int tamaño) {
        this.nColores = nColores;
        this.color = color;
        this.tamañoMatriz=tamaño;
        for(int i=0;i < 2;i++){
            this.ultimaAnterior[i]=ultimaPos[i];
        }
        this.matriz=matriz.clone();
    }
    
    
    
    
}
