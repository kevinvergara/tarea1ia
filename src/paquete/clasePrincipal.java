
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
        
        while(opc != 3){
            Matriz matrizInicial = new Matriz();
            
            Nodo raiz = new Nodo(matrizInicial.cargarMatriz(),matrizInicial.getColores(),null,0);
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
                int con=0;
                System.out.println("opcion en proceso .|.");
                for(int i=0;i<raiz.getMatriz().length;i++){
                    for(int j=0;j<raiz.getMatriz().length;j++){
                        con++;
                    }
                }
                System.out.println(con);
            }
            
        }

    }
    
    public static String [][] bfs(Nodo raiz){
        Matriz matrizClase = new Matriz();
        Operadores operadores = new Operadores();
        Cola cola= new Cola();
        
        Nodo nodoActual = null;
        System.out.print ("El recorrido en Anchura es: ");
        
        
        if(raiz != null){
            cola.encolar(raiz);
            System.out.println("\nnivel: "+(raiz.getNivel())+"\n");
            matrizClase.imprimirMatriz(raiz.getMatriz());
            
            while(!(cola.vacio())){
                nodoActual = cola.desencolar();
                if (matrizClase.matrizSolucion(nodoActual.getMatriz(), nodoActual.getColores())){
                    System.out.println("\nnivel: "+nodoActual.getNivel()+"\nMatriz Solucion");
                    matrizClase.imprimirMatriz(nodoActual.getMatriz());
                    return nodoActual.getMatriz();//retornar matriz solucion
                }
                
                
                
                System.out.println("\nnivel: "+(nodoActual.getNivel())+"\n");
                matrizClase.imprimirMatriz(nodoActual.getMatriz());
                int [] vectorAux = null;
                vectorAux = operadores.actualizarPosicion(nodoActual.getMatriz().length,(nodoActual.getNivel()+1));
                
                if(vectorAux != null){
                    if(operadores.disponibilidad(nodoActual.getMatriz(), vectorAux)){
                        for(int i=0 ; i<nodoActual.getColores().length ; i++){
                            System.out.println("color: "+nodoActual.getColores()[i]);
                            String [][] matrizAux=null;
                            matrizAux = operadores.asignarColor(nodoActual.getMatriz(), vectorAux, nodoActual.getColores()[i]);
                            Nodo nodoAux = new Nodo(matrizAux,nodoActual.getColores(),vectorAux,(nodoActual.getNivel()+1)); 
                            nodoActual.setNodos(nodoAux);
                            
                        }
                        
                        for(int i=0;i<nodoActual.getColores().length;i++){
                            cola.encolar(nodoActual.getNodo(i));//encolar
                        }
                    }
                }//listo
            }//fin if actualizarPos
                
                
                
                /*if (T.Hizq != null)
                 cola.InsertaFinal (T.Hizq);
                if (T.Hder != null)
                 cola.InsertaFinal (T.Hder);*/
            
        }
        System.out.println(); 
        return null;
    }
    
}