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
    public int [] actualizarPosicion(int dimension,int nivel){
        int cont=0;
        int [] vector = new int [2];
        
        if((dimension*dimension) == nivel) return null;
        
        for(int i=0;i<dimension;i++){
            for(int j=0;j<dimension;j++){
                cont++;
                if(dimension == cont){
                    vector[0]=i;
                    vector[1]=j;
                }
            }
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
}
