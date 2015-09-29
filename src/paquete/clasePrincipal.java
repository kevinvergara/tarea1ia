
package paquete;

import java.io.IOException;
import java.util.Scanner;


public class clasePrincipal {

    public static void main(String[] args) throws IOException {        
        //anchura cola
        //profundidad pila
        Scanner sc = new Scanner(System.in);
        int opc=0;
        //-------------
        int [] vectorInicial = new int[2];
        vectorInicial[0]=0;
        vectorInicial[0]=0;
        //-------------
        
        while(opc != 3){
            Matriz matrizInicial = new Matriz();     
            Nodo raiz = new Nodo(matrizInicial.getColores(),vectorInicial,matrizInicial.cargarMatriz(),0);
            
            //carga matriz inicial
            System.out.println("----menu----");
            System.out.println("1.-bfs (anchura)");
            System.out.println("2.-dfs (profundidad)");
            System.out.println("3.-Salir");
            opc = sc.nextInt();
            
            if(opc == 1){
                //anchura
                matrizInicial.imprimirMatriz(bfs(raiz));
            }else if(opc == 2){
            
            }
            
        }

    }
    
    public static String [][] bfs(Nodo raiz){
        int cont=0;
        Matriz matrizClase = new Matriz();
        Operadores operadores = new Operadores();
        Cola cola= new Cola();
        
        Nodo nodoActual = null;
        System.out.print ("El recorrido en Anchura es: ");
        
        if(raiz != null){
            cola.encolar(raiz);
            
            while(!(cola.vacio())){
                nodoActual = cola.desencolar();
                
                if (matrizClase.matrizSolucion(nodoActual.getMatriz(), nodoActual.getColores())){
                    System.out.println("\nnivel: "+nodoActual.getNivel()+"\nMatriz Solucion");
                    matrizClase.imprimirMatriz(nodoActual.getMatriz());
                    return nodoActual.getMatriz();//retornar matriz solucion
                }
                
                
                
                System.out.println("\nnivel: "+nodoActual.getNivel()+"\n");
                matrizClase.imprimirMatriz(nodoActual.getMatriz());
               
                if(operadores.actualizarPosicion(nodoActual.getMatriz().length,nodoActual.getUltimaAnterior()) != null){
                    
                    for(int i=0 ; i<nodoActual.getColores().length ; i++){
                        String [][] matrizAux = operadores.asignarColor(nodoActual.getMatriz(), operadores.actualizarPosicion(nodoActual.getMatriz().length,nodoActual.getUltimaAnterior()), nodoActual.getColores()[i]);
                        Nodo nodoAux = new Nodo(nodoActual.getColores(),operadores.actualizarPosicion(nodoActual.getMatriz().length,nodoActual.getUltimaAnterior()),matrizAux,(nodoActual.getNivel()+1)); 
                        nodoActual.setNodos(nodoAux);
                    }

                }//fin if actualizarPos
                
                for(int i=0 ; i<nodoActual.getColores().length ; i++){
                    cola.encolar(nodoActual.getNodo(i));
                }//listo
                
                /*if (T.Hizq != null)
                 cola.InsertaFinal (T.Hizq);
                if (T.Hder != null)
                 cola.InsertaFinal (T.Hder);*/
            }
        }
        System.out.println(); 
        return null;
    }
    
}
