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
            
        cadena = b.readLine();//leer todos los colores
        
        colores = getColores(cadena).clone();//crear el vector con los colores
        
        b.readLine();//leer lineas inutiles
        i=0;
        int j=0,x=0;
        while((cadena = b.readLine())!=null) {
            for(j=0 ; j< (tamaño*2) ; j++){
                if(j%2 == 0){//leer matriz
                    matriz[i][x] = "" + cadena.charAt(j);
                    x++;
                }
            }
            x=0;
            i++;
        }
        b.close();
        
        
        return matriz;
    } 

    public boolean matrizSolucion(String [][] matriz,String [] colors){
        //le enviamos la matriz y los colores
        //si retorna true solucion, si es false no es solucion
        int [] nColors = new int [colors.length];
        for(int i = 0;i<colors.length;i++){//inicilizar el vector contador
            nColors[i]=0;
        }
        //-------------------
      /*  for(int i=0 ; i<matriz.length ; i++){//primero ver que no tenga espacios vacios
            for(int j=0 ; j<matriz.length ; j++){
                if(matriz[i][j].equals(" ")){
                    return false;
                }
            }
        }*/
        //-------------------
        /*for(int i=0 ; i<matriz.length ; i++){//verificar que qe todos los colores tengan mas qe 1
            for(int j=0 ; j<matriz.length ; j++){    
                for(int x = 0 ; x<colors.length ; x++){
                    if(matriz[i][j].equals(colors[x])){
                        nColors[x]++;
                    }
                }  
            }
        }
        for(int x = 0 ; x<colors.length ; x++){
            if(nColors[x]!=2){
                return false;
            }
        } */
        //---------------------
        //ver qe cada color tengo uno de si mismo a su lado
        for(int i = 0;i<colors.length;i++){//inicilizar el vector contador
            if(!contadorColorLado(i,colors,matriz)) return false;
        }
        //---------------------
        
        return true;
    }
    
    public boolean contadorColorLado(int index,String [] color,String [][] matriz){
        
        for(int i=0 ; i<matriz.length ; i++){//primero ver que no tenga espacios vacios
            for(int j=0 ; j<matriz.length ; j++){
                if(matriz[i][j].equals(Integer.toString(index)) || matriz[i][j].equals(color[index])){
                //arriba
                    
                
                
                
                }
            }
        }
        
        
        
        return true;
    }
    
    
    public String [] getColores(String color) {
        String [] vector = new String [nColores];
        //crear el vector desde el vector de colores
        for(int i = 0; i < nColores; i++){
            
            vector[i] = "" + color.charAt(i);
        }
        return vector;
    }
    
    public String [] getColores(){
        return colores;
    }
    
}
