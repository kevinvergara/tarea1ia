package paquete;

public class Operadores {
    
    public Operadores(){
    }
    
    public int [] actualizarPosicion(String [][] matriz){// se usa
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
            return null;
        }
        return vector;
    }

    public String [][] matrizModificada(String [][] matri,int [] vector,String color){// se usa
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
