
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
            
            Nodo raiz = new Nodo(matrizInicial.cargarMatriz(),matrizInicial.getColores(),null,1);
            //carga matriz inicial
            System.out.println("----menu----");
            System.out.println("1.-bfs (anchura)");
            System.out.println("2.-dfs (profundidad)");
            System.out.println("3.-Salir");
            opc = sc.nextInt();
            
            if(opc == 1){
                //anchura
                BFS anchura = new BFS();
                matrizInicial.imprimirMatriz(anchura.bfs(raiz));
            }else if(opc == 2){
                int con=0;
                System.out.println("opcion en proceso .|.");
            }
            
        }

    }
    
}