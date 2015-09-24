
package paquete;

import java.io.IOException;

public class clasePrincipal {

    public static void main(String[] args) throws IOException {        
        int [] aux = new int [2];
        aux[0] = -1;
        aux[1] = -1;
        
        Matriz matrizAuxiliar = new Matriz();
        String [][] matrizInicial = matrizAuxiliar.cargarMatriz();//carga matriz inicial
        
        Nodo raiz = new Nodo(matrizAuxiliar.getColores(),aux,matrizInicial,1);
        
        for (int i=0;i < raiz.getColores().length;i++){
            System.out.println(raiz.getColores()[i]);
        }
        System.out.print("\n \n");
        
        for(int i=0;i < matrizInicial.length ; i++){
            for(int j = 0; j < matrizInicial.length ; j++){
                System.out.print(raiz.getMatriz()[i][j]);
            }
            System.out.println();
        }
    
    
    
    
    
    }
    
}
