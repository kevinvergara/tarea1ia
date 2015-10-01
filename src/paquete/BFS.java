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
                    
        
        int i=0,contador=1;
        
        System.out.print ("El recorrido en Anchura es: \n");
        
        Nodo nodoActual = new Nodo();
        if(raiz != null){
            cola.encolar(raiz);            
            while(!cola.vacia()){
                nodoActual = (Nodo)cola.frente();
                cola.desencolar();
                
                //ver si es solucion
                if(matrizClase.matrizSolucion(nodoActual.getMatriz(), nodoActual.getColores())){
                    System.out.println("solucion encontrada en el nivel: "+nodoActual.getNivel()+"\n");
                    
                    return nodoActual.getMatriz();
                }
                int [] vectorAux = null;
                vectorAux = operadores.actualizarPosicion(nodoActual.getMatriz());
                
                if(contador<21){
                    System.out.println("-----------------------------");
                    System.out.println("nivel: "+nodoActual.getNivel());
                    System.out.println("estado: "+contador);
                    matrizClase.imprimirMatriz(nodoActual.getMatriz());
                    System.out.println("-----------------------------");
                }
                if(vectorAux != null){
                    if(operadores.disponibilidad(nodoActual.getMatriz(), vectorAux)){
                        //System.out.println("wepa: ");
                        for(i=0;i<nodoActual.getColores().length;i++){

                            cola.encolar(new Nodo(operadores.matrizModificada(nodoActual.getMatriz(), vectorAux, nodoActual.getColores()[i]),nodoActual.getColores(),vectorAux,nodoActual.getNivel()+1)); 

                        }
                    }
                }//listo
                contador++;
            }//fin while
        }
        System.out.println(); 
        return null;
    }
}