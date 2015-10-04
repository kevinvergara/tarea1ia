
package paquete;

import java.util.Stack;

public class DFS {
    private Stack pila = new Stack();
    
    public String [][] dfs(Nodo raiz){
        int contador=1;
        
        Matriz matrizClase = new Matriz();
        Operadores operadores = new Operadores();
        Nodo nodoActual = new Nodo();
        
        if(raiz!=null){
            pila.push(null);
            pila.push((Nodo)raiz);
            
            while(!pila.empty()){//mientras la pila no este vacia
                nodoActual = (Nodo)pila.pop();
                
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
                    for(int i=0;i<nodoActual.getColores().length;i++){
                        pila.push((Nodo)new Nodo(operadores.matrizModificada(nodoActual.getMatriz(), vectorAux, nodoActual.getColores()[i]),nodoActual.getColores(),vectorAux,nodoActual.getNivel()+1)); 
                    }
                }//listo
                contador++;

            }
        }
    return null;
    }
}
