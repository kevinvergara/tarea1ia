package paquete;

import java.io.IOException;


public class BFS {
    private final Cola cola = new Cola();
    public BFS() {
    }
    
    public String [][] bfs(Nodo raiz) throws IOException{
        Matriz matrizClase = new Matriz();
        Operadores operadores = new Operadores();
        int contador=1;
        
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
            
                System.out.println("-----------------------------");
                System.out.println("nivel: "+nodoActual.getNivel());
                System.out.println("estado: "+contador);
                matrizClase.imprimirMatriz(nodoActual.getMatriz());
                System.out.println("-----------------------------");

                if(vectorAux != null){
                    for(int i=0;i<nodoActual.getColores().length;i++){
                        
                        nodoActual.setNodos(new Nodo(operadores.matrizModificada(nodoActual.getMatriz(), vectorAux, nodoActual.getColores()[i]),nodoActual.getColores(),vectorAux,nodoActual.getNivel()+1));
                        cola.encolar(new Nodo(operadores.matrizModificada(nodoActual.getMatriz(), vectorAux, nodoActual.getColores()[i]),nodoActual.getColores(),vectorAux,nodoActual.getNivel()+1)); 

                    }
                }//listo
                contador++;
            }//fin while
        }
        System.out.println(); 
        return null;
    }
}
