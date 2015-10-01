package paquete;

public class Operadores {
    
    public Operadores(){
    }
    
    public String [][] asignarColor(String [][] matriz, int [] posicion, String color){
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz.length;j++){
                if(posicion[0]==i && posicion[1]==j){
                    matriz[i][j] = color;    
                }
            }
        }
        return matriz;
    }
    public int [] actualizarPosicion(String [][] matriz){
        int [] vector = new int [2];
        vector[0]=-1;
        vector[1]=-1;
        
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz.length;j++){
                if(matriz[i][j].equals(" ")){
                    vector[0]=i;
                    vector[1]=j;
                }
            }
        }
        
        if(vector[0]==-1 & vector[1]==-1){ 
            System.out.println("nulllllll");
            return null;
        }
        return vector;
    }
    public boolean disponibilidad(String [][] matriz, int [] posicion){
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz.length;j++){
                if(posicion[0]==i && posicion[1]==j){
                    if(matriz[i][j].equals(" ")){
                        return true;
                    }else{
                        return false;
                    }
                }
            }
        }
        return false;
    }
    
    public String [][] matrizModificada(String [][] matri,int [] vector,String color){
        String [][] matriAux = new String [matri.length][matri.length];
        
        for(int i=0;i<matri.length;i++){
            for(int j=0;j<matri.length;j++){
                
                if(vector[0]==i && vector[1]==j){
                    matriAux[i][j]=color;
                }else{
                    matriAux[i][j]=matri[i][j];
                }
            }
        }
        
        return matriAux;
    }
}
