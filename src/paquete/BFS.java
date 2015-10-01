package paquete;

import java.io.IOException;
import java.util.Scanner;


public class BFS {
    private Cola cola = new Cola();
    public BFS() {
    }
    
    public String [][] bfs(Nodo raiz) throws IOException{
        Matriz matrizClase = new Matriz();
        Operadores operadores = new Operadores();
        Scanner sc = new Scanner(System.in);
                    
        
        int i=0,contador=0;
        
        System.out.print ("El recorrido en Anchura es: ");
        
        Nodo nodoActual = new Nodo();
        if(raiz != null){
            cola.encolar(raiz);            
            while(!cola.vacia()){
                nodoActual = (Nodo)cola.frente();
                System.out.println("\n");
                System.out.println("nivel: "+nodoActual.getNivel()+"\n");
                matrizClase.imprimirMatriz(nodoActual.getMatriz());
                System.out.println("\n");
                cola.desencolar();
                
                int [] vectorAux = null;
                vectorAux = operadores.actualizarPosicion(nodoActual.getMatriz(),(nodoActual.getNivel()+1));
                
                if(vectorAux != null){
                    
                    if(operadores.disponibilidad(nodoActual.getMatriz(), vectorAux)){
                        System.out.println("wepa: ");
                        for(i=0;i<nodoActual.getColores().length;i++){

                            cola.encolar(new Nodo(operadores.matrizModificada(nodoActual.getMatriz(), vectorAux, nodoActual.getColores()[i]),nodoActual.getColores(),vectorAux,nodoActual.getNivel()+1)); 

                        }
                    }
                }//listo
            }//fin while
        }
        System.out.println(); 
        return null;
    }
}