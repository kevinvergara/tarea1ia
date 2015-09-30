package paquete;


public class BFS {

    public BFS() {
    }
    
    public String [][] bfs(Nodo raiz){
        Matriz matrizClase = new Matriz();
        Operadores operadores = new Operadores();
        Cola cola = new Cola();
        
        System.out.print ("El recorrido en Anchura es: ");
        
        Nodo nodoActual = new Nodo();
        if(raiz != null){
            cola.encolar(raiz);            
            while(!cola.vacia()){
                matrizClase.imprimirMatriz(((Nodo)cola.frente()).getMatriz());
                nodoActual = (Nodo)cola.frente();
                cola.desencolar();
                
                int [] vectorAux = null;
                vectorAux = operadores.actualizarPosicion(nodoActual.getMatriz(),(nodoActual.getNivel()+1));
                
                if(vectorAux != null){
                    for(int i=0 ; i<nodoActual.getColores().length ; i++){
                        String [][] matrizAux=null;
                        matrizAux = operadores.asignarColor(nodoActual.getMatriz(), vectorAux, nodoActual.getColores()[i]);
                        Nodo nodoAux=null;
                        nodoAux= new Nodo(matrizAux,nodoActual.getColores(),vectorAux,(nodoActual.getNivel()+1)); 
                        nodoActual.setNodos(nodoAux);
                        cola.encolar((Nodo)nodoActual.getNodo(i));
                    }
                }//listo
            }//fin while
        }
        System.out.println(); 
        return null;
    }
}