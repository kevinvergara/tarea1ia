package paquete;

import java.util.ArrayList;
import java.util.Scanner;


public class BFS {
    private Cola cola = new Cola();
    public BFS() {
    }
    
    public String [][] bfs(Nodo raiz){
        Matriz matrizClase = new Matriz();
        Operadores operadores = new Operadores();
        Scanner sc = new Scanner(System.in);
        ListaAuxiliar auxi = new ListaAuxiliar();
        
        int i=0;
        
        System.out.print ("El recorrido en Anchura es: ");
        
        Nodo nodoActual = new Nodo();
        if(raiz != null){
            cola.encolar(raiz);            
            while(!cola.vacia()){
                nodoActual = (Nodo)cola.frente();
                cola.desencolar();
                
                int [] vectorAux = null;
                vectorAux = operadores.actualizarPosicion(nodoActual.getMatriz(),(nodoActual.getNivel()+1));
                if(vectorAux != null){
                    String [][] matrizAux= new String[nodoActual.getMatriz().length][nodoActual.getMatriz().length];
                    Nodo nodoAux= new Nodo();
                    i=0;
                    matrizAux = nodoActual.getMatriz();

                    nodoAux.setColores(nodoActual.getColores());
                    nodoAux.setNivel(nodoActual.getNivel()+1);
                    nodoAux.setPosicion(vectorAux);


                    
                    matrizAux[vectorAux[0]][vectorAux[1]]=nodoActual.getColores()[0];
                    nodoAux.setMatriz(matrizAux);
                    //System.out.println(nodoAux.getMatriz()[vectorAux[0]][vectorAux[1]]);
                    //auxi.setNodo((Nodo)nodoAux);
                    cola.encolar(nodoAux);
                    
                    
                    matrizAux[vectorAux[0]][vectorAux[1]]=nodoActual.getColores()[1];
                    nodoAux.setMatriz(matrizAux);
                    //System.out.println(nodoAux.getMatriz()[vectorAux[0]][vectorAux[1]]);
                    //auxi.setNodo((Nodo)nodoAux);
                    cola.encolar(nodoAux);
                            
                    matrizAux[vectorAux[0]][vectorAux[1]]=nodoActual.getColores()[2];
                    
                    nodoAux.setMatriz(matrizAux);
                    //System.out.println(nodoAux.getMatriz()[vectorAux[0]][vectorAux[1]]);
                    //auxi.setNodo((Nodo)nodoAux);
                    cola.encolar(nodoAux);
                    //cola.encolar(auxi.getNodo(1));
                    //cola.encolar(auxi.getNodo(2));
                    
                    System.out.println(cola.tam());
                    for(i=0;i<cola.size();i++){
                        //matrizClase.imprimirMatriz(nodoActual.getNodo(i).getMatriz());
                        //matrizClase.imprimirMatriz(((Nodo)cola.get(i)).getMatriz());
                        System.out.println(((Nodo)cola.get(i)).getMatriz()[vectorAux[0]][vectorAux[1]]);
                    }
                    sc.nextLine();

                }//listo
            }//fin while
        }
        System.out.println(); 
        return null;
    }
}