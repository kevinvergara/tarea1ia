package paquete;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class BFS {
    private Cola cola = new Cola();
    public BFS() {
    }
    
    public String [][] bfs(Nodo raiz) throws IOException{
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
                System.out.println("\n");
                matrizClase.imprimirMatriz(nodoActual.getMatriz());
                System.out.println("\n");
                cola.desencolar();
                
                int [] vectorAux = null;
                vectorAux = operadores.actualizarPosicion(nodoActual.getMatriz(),(nodoActual.getNivel()+1));
                if(vectorAux != null){
                    String [][] matrizAux1= new String[nodoActual.getMatriz().length][nodoActual.getMatriz().length];
                    String [][] matrizAux2= new String[nodoActual.getMatriz().length][nodoActual.getMatriz().length];
                    String [][] matrizAux3= new String[nodoActual.getMatriz().length][nodoActual.getMatriz().length];
                    
                    Nodo nodoAux1= new Nodo();
                    Nodo nodoAux2=new Nodo();
                    Nodo nodoAux3 = new Nodo();
                   
                    Matriz matrizInicial = new Matriz();
                    
                    matrizAux1 = matrizInicial.cargarMatriz();
                    matrizAux2 = matrizInicial.cargarMatriz();
                    matrizAux3 = matrizInicial.cargarMatriz();

                    nodoAux1.setColores(nodoActual.getColores());
                    nodoAux1.setNivel(nodoActual.getNivel()+1);
                    nodoAux1.setPosicion(vectorAux);

                    nodoAux2.setColores(nodoActual.getColores());
                    nodoAux2.setNivel(nodoActual.getNivel()+1);
                    nodoAux2.setPosicion(vectorAux);
                    
                    nodoAux3.setColores(nodoActual.getColores());
                    nodoAux3.setNivel(nodoActual.getNivel()+1);
                    nodoAux3.setPosicion(vectorAux);
                    
                    matrizAux1[vectorAux[0]][vectorAux[1]]=nodoActual.getColores()[0];
                    nodoAux1.setMatriz(matrizAux1);
                    //System.out.println(nodoAux.getMatriz()[vectorAux[0]][vectorAux[1]]);
                    //auxi.setNodo((Nodo)nodoAux);
                  
                    
                    matrizAux2[vectorAux[0]][vectorAux[1]]=nodoActual.getColores()[1];
                    nodoAux2.setMatriz(matrizAux3);
                    //System.out.println(nodoAux.getMatriz()[vectorAux[0]][vectorAux[1]]);
                    //auxi.setNodo((Nodo)nodoAux);
                          
                    matrizAux3[vectorAux[0]][vectorAux[1]]=nodoActual.getColores()[2];
                    
                    nodoAux3.setMatriz(matrizAux3);
                    //System.out.println(nodoAux.getMatriz()[vectorAux[0]][vectorAux[1]]);
                    //auxi.setNodo((Nodo)nodoAux);
                   
                    //cola.encolar(auxi.getNodo(1));
                    //cola.encolar(auxi.getNodo(2));
                    cola.encolar(nodoAux1); 
                    cola.encolar(nodoAux2);
                    cola.encolar(nodoAux3);
                    
                       

                }//listo
            }//fin while
        }
        System.out.println(); 
        return null;
    }
}