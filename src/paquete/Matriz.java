package paquete;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Matriz {
    private int tamaño;
    private int nColores;
    private String [] colores = new String [nColores];

    public Matriz() {
    }
    
    public String [][] cargarMatriz() throws FileNotFoundException, IOException{
        String cadena;
        String archivo = "/home/kvergara/NetBeansProjects/tarea 1 IA/archivos/matriz.txt";
        int i=0;
        
        
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        
        this.tamaño = Integer.parseInt(b.readLine()); //leo el tamaño de la primera linea
        String [][] matriz = new String [tamaño][tamaño];//creo la matriz con el tamaño
        
        this.nColores = Integer.parseInt(b.readLine());
            
        cadena = b.readLine();
        System.out.println(colores.length);
        /*for(i = 0; i < nColores; i++){
            this.colores[i] = "" + cadena.charAt(i);
        }*/
        
        b.readLine();
        
        while((cadena = b.readLine())!=null) {
            System.out.println(cadena);
            
            
        }
        b.close();
        
        
        return matriz;
    } 
    
}
