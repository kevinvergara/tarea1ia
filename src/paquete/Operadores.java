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
    public int [] actualizarPosicion(int dimension,int [] posicion){
        if(posicion[0] == (dimension-1) && posicion[1] == (dimension-1) ){//ultima posicion
            return null;
        }
        //------
        if(posicion[1] != (dimension-1)){//
            posicion[1]++;
            System.out.println("pos: ("+posicion[0]+" , "+posicion[1]+")");
            return posicion;
        }
        //-----
        if(posicion[1] == (dimension-1)){
            posicion[0]++;
            posicion[1]=0;
            System.out.println("pos: ("+posicion[0]+" , "+posicion[1]+")");
            return posicion;
        }
        
        return null;
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
