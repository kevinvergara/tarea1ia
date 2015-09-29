package paquete;

public class Operadores {
    
    public Operadores(){
    }
    
    public String [][] asignarColor(String [][] matriz, int [] posicion, String color){
        
        for(int i=0;i<matriz.length;i++){
            for(int j=0;i<matriz.length;i++){
                if(i == posicion[0] && j == posicion[1]){
                    matriz[i][j] = color;
                }
            }
        }
        return matriz;
    }
    public int [] actualizarPosicion(int dimension,int [] posicion){
        if(posicion[0] == (dimension-1) && posicion[1] == (dimension-1) ){//ultima posicion
            return null;
        }
        //------
        if(posicion[1] != (dimension-1)){//
            posicion[1]++;
        }
        
        
        return null;
    }
}
