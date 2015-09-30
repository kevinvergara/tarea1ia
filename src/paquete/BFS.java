package paquete;


import paquete.Cola;
import paquete.Matriz;
import paquete.Nodo;
import paquete.Operadores;


public class BFS {

    public BFS() {
    }
        
    
    public String [][] bfs(Nodo raiz){
        Matriz matrizClase = new Matriz();
        Operadores operadores = new Operadores();
        Cola cola= new Cola();
        
        
        System.out.print ("El recorrido en Anchura es: ");
        
        Nodo nodoActual = null;
        if(raiz != null){
            cola.encolar(raiz);
            //System.out.println("\nnivel: "+(raiz.getNivel())+"\n");
            //matrizClase.imprimirMatriz(raiz.getMatriz());
            
            while(!(cola.vacio())){
                nodoActual=null;
                nodoActual = cola.desencolar();
                //matrizClase.imprimirMatriz(cola.desencolar().getMatriz());
                
                if (matrizClase.matrizSolucion(nodoActual.getMatriz(), nodoActual.getColores())){
                    System.out.println("\nnivel: "+nodoActual.getNivel()+"\nMatriz Solucion");
                    matrizClase.imprimirMatriz(nodoActual.getMatriz());
                    return nodoActual.getMatriz();//retornar matriz solucion
                }
                
                
                System.out.println("\nnivel: "+(nodoActual.getNivel())+"\n");
                matrizClase.imprimirMatriz(nodoActual.getMatriz());
                int [] vectorAux = null;
                vectorAux = operadores.actualizarPosicion(nodoActual.getMatriz(),(nodoActual.getNivel()+1));
                
                if(vectorAux != null){
                    //if(operadores.disponibilidad(nodoActual.getMatriz(), vectorAux)){
                        for(int i=0 ; i<nodoActual.getColores().length ; i++){
                            //System.out.println("color: "+nodoActual.getColores()[i]);
                            String [][] matrizAux=null;
                            matrizAux = operadores.asignarColor(nodoActual.getMatriz(), vectorAux, nodoActual.getColores()[i]);
                            Nodo nodoAux=null;
                            nodoAux= new Nodo(matrizAux,nodoActual.getColores(),vectorAux,(nodoActual.getNivel()+1)); 
                            nodoActual.setNodos(nodoAux);
                            //System.out.println("\n"+nodoAux.getMatriz()[vectorAux[0]][vectorAux[1]]+"\n");
                            //matrizClase.imprimirMatriz(nodoActual.getNodo(i).getMatriz());
                            cola.encolar((Nodo)nodoActual.getNodo(i));
                        }
                      
                    //}
                }//listo
            }//fin if actualizarPos
        }
        System.out.println(); 
        return null;
    }
    
}
