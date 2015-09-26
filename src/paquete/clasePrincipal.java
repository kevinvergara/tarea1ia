
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
            Nodo raiz = new Nodo(matrizInicial.getColores(),vectorInicial,matrizInicial.cargarMatriz(),-1);
            
            //carga matriz inicial
            System.out.println("----menu----");
            System.out.println("1.-bfs (anchura)");
            System.out.println("2.-dfs (profundidad)");
            System.out.println("3.-Salir");
            opc = sc.nextInt();
            
            if(opc == 1){
                bfs(raiz);//anchura
            }else if(opc == 2){
            
            }
            
        }

    }
    
    public static boolean bfs(Nodo nodo){
        Matriz matrizClase = new Matriz();
        
        if(matrizClase.matrizSolucion(nodo.getMatriz(), nodo.getColores())){
            return true;
        }
        
        matrizClase.imprimirMatriz(nodo.getMatriz());
        
        
       return true;
    }
    
}
