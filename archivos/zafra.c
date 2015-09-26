/*TAREA INTELIGENCIA ARTIFICIAL 
INTEGRANTES : GABRIEL PIZARRO HERRERA
			  FELIPE CONTRERAS LOBOS  */   


#include <stdio.h>                  
#include <stdlib.h>    
#include <conio2.h>                                        //se incluyen las librerias
#define filas 4
#define columnas 4                                         
#define OK 1                                               //se define las dimensiones de la matriz
#define NO 0
#define ncubos 3		                                   // se especifica la cantidad de cubos en la etapa





typedef struct posicion{
	int x;                                            //en esta estructura se guardan las coordenadas de un cubo
	int y;
}posicion;

typedef struct cubo{
	int color;
	char direccion;                                   //la estructura cubo guarda los datos del cubo
	char estado;
	posicion p;
}cubo;

typedef struct casilla{
	int ocupado;                                           //casilla representa una posicion en la matriz y guarda los objetivos
	int color_objetivo;                                    //cambios de direccion y si el espacio esta ocupado
	char c_direccion;	
}casilla;

typedef struct estado{
	struct estado *next;                                  //guarda los estados con un arreglo de cubos 
	cubo cubos[ncubos];
}estado;


int mover_bloque(int num_bloque,cubo *vector_cubos,casilla matriz[filas][columnas]);
int empujar(int num_bloque,cubo *vector_cubos,casilla matriz[filas][columnas]);
int comprobar_estado(cubo cubos[ncubos],casilla matriz[filas][columnas]);
void push (estado **c,cubo cubos[ncubos]);
void dfs(cubo cubos[ncubos],casilla matriz[filas][columnas]);                       //declaracion de funciones
void bfs(cubo cubos[ncubos],casilla matriz[filas][columnas]);
void encolar (estado **pri, estado **ult, cubo cubos[ncubos]);
void imprime_paso(casilla matriz[filas][columnas],cubo cubos[ncubos]);
void copiar_cubos(cubo *auxiliar,cubo cubos[filas][columnas]);
void largo_lista(estado *h);
estado * desencolar(estado **H,estado **T);
estado *pop(estado **c);


//esta funcion se utiliza para saber la cantidad de elementos en las pilas y colas
void largo_lista(estado *h){
	estado *aux=NULL;
	aux=h;
	int contador=0;
	                                                          
	if(h!=NULL){
	while(aux->next!=NULL){
		aux=aux->next;
		contador++;
	}
	printf("\nCantidad de estados en la lista : %d\n",contador+1);
	}
	else printf("\nCantidad de estados en la lista : %d\n",contador);
	
}


//esta funcion crea una copia del vector de cubos; es fundamental para el funcionamiento del codigo
void copiar_cubos(cubo *auxiliar,cubo *cubos){
	int j;
	                                                
	for(j=0;j<ncubos;j++){
		auxiliar[j].direccion=cubos[j].direccion;
		auxiliar[j].estado=cubos[j].estado;
		auxiliar[j].p.x=cubos[j].p.x;
		auxiliar[j].p.y=cubos[j].p.y;
	}
}

//esta funcion se invoca en los recorridos para comprobar si el estado actual corresponde a la solución del juego
//si es solucion se retorna 1, de lo contrario se retorna 0
int comprobar_estado(cubo cubos[ncubos],casilla matriz[filas][columnas]){
	int aux=0,i;
	
	for(i=0;i<ncubos;i++){
		if(cubos[i].estado==1){
			aux++;
		}
	}
		
	if(aux==ncubos){
		printf("-----------------------\n");
		printf("\n\nEstado final alcanzado:\n\n");
			
		for(i=0;i<ncubos;i++){
			printf("Cubo %d : x=%d , y=%d\n",i+1,cubos[i].p.x,cubos[i].p.y);
			matriz[cubos[i].p.x][cubos[i].p.y].ocupado=1;
		}
		
		imprime_paso(matriz,cubos);
		printf("\n\nETAPA SUPERADA\n\n");	
		//system("pause");
		return 1;
	}
	return 0;
}

//funcion push de la pila, la cual se construye con estructuras estado
void push(estado **c,cubo cubos[ncubos]){  
	 int i;
	 estado *aux2=NULL;
     aux2=(estado*)malloc(sizeof(estado));
     
     for(i=0;i<ncubos;i++){
     	aux2->cubos[i]=cubos[i];
     }
     
     if(!*c){
	 	(*c)=aux2; aux2->next=NULL;
		 }
     else{ 
	 	aux2->next=(*c);
        *c=aux2;
    }
}
     
//funcion pop de la pila, retorna una variable tipo estado
estado *pop(estado **c){	
	estado *aux=NULL;
    aux=(*c);
    
    if(!*c)  return (NULL);   
	else{
     *c=aux->next;
     return(aux);
    }
}

//esta funcion permite imprimir el estado del juego en cada operación realizada, recibiendo como parametro el vector de cubos y la matriz modificada
void imprime_paso(casilla matriz[filas][columnas],cubo cubos[ncubos]){	
	int i,j,k;
	casilla aux[filas][columnas];
	
	for(i=0;i<filas;i++){
		for(j=0;j<columnas;j++){
			aux[i][j]=matriz[i][j];
		}
	}							
	
	for(i=0;i<ncubos;i++){
		aux[cubos[i].p.x][cubos[i].p.y].ocupado=1;
    }
	printf("\n\n");
	
	//en las siguientes lineas se utilizan caracteres ASCII y la  funcion textcolor de la libreria conio2.h
	for(i=0;i<filas;i++){
		for(j=0;j<columnas;j++){
			if(aux[i][j].ocupado==1){
				for(k=0;k<ncubos;k++){
					if(cubos[k].p.x==i && cubos[k].p.y==j){					
						if(cubos[k].color==1) textcolor(MAGENTA);
						if(cubos[k].color==2) textcolor(YELLOW);
						if(cubos[k].color==3) textcolor(GREEN);
						if(cubos[k].color==4) textcolor(LIGHTRED);
						break;						
					}
				}
				if(cubos[k].direccion=='U') cprintf("%c   ",30);			//se imprimen los cubos representados por flechas que indican su dirección y color
				if(cubos[k].direccion=='D') cprintf("%c   ",31);			
				if(cubos[k].direccion=='L') cprintf("%c   ",17);
				if(cubos[k].direccion=='R') cprintf("%c   ",16);
				textcolor(WHITE);
			}
			else{
				if(matriz[i][j].color_objetivo!=0){
					if(matriz[i][j].color_objetivo==1) textcolor(MAGENTA);
					if(matriz[i][j].color_objetivo==2) textcolor(YELLOW);	//los objetivos se imprimen con su color correspondiente
					if(matriz[i][j].color_objetivo==3) textcolor(GREEN);
					if(matriz[i][j].color_objetivo==4) textcolor(LIGHTRED);
					cprintf("o   ");
					textcolor(WHITE);
				}
				else{
					if(matriz[i][j].c_direccion!=0){
						if (matriz[i][j].c_direccion=='D') printf("%c   ",25);
						if(matriz[i][j].c_direccion=='U') printf("%c   ",24);	//se imprimen las flechas de cambio de dirección existentes en el mapa
						if(matriz[i][j].c_direccion=='L') printf("%c   ",27);
						if(matriz[i][j].c_direccion=='R') printf("%c   ",26);
					}
					else{
					printf("X   "); // en espacios vacios imprime X
					}
				}
			}		
		}
	printf("\n\n\n");
	}	
	textcolor(WHITE);
}

//esta funcion guarda en la cola variables del tipo estado
void encolar(estado **pri, estado **ult,cubo cubos[ncubos]){
	
	int i;          
	estado *nodo;
	nodo=(estado*)malloc(sizeof(estado));
	
	for(i=0;i<ncubos;i++){
		nodo->cubos[i]=cubos[i];
	}

	if(!*pri) {	
		*pri=nodo;
		*ult=nodo;
	}	          
	else{		
		(*ult)->next=nodo;
		*ult=nodo;
		(*ult)->next=NULL;
	}
}

//esta funcion retira un estado de la cola para ser analizado como posible solución y en caso que no sea generar sus respectivos estados hijos
estado* desencolar(estado **H, estado **T){
    estado *a = NULL;
    estado *aux;
    
    if (*H){
    	aux =*H;
        if(*H==*T){
        	*H=NULL;
           	*T=NULL;
           	return aux;
        }
        *H = (*H)->next;
        a = aux;   
    }
    return a;  	
}

//Este es el recorrido DFS que permite encontrar la solución al juego mediante una búsqueda en profundidad
void dfs(cubo cubos[ncubos],casilla matriz[filas][columnas]){	
	int i,j,k,aux,loop=0,final,contador=0;
	estado *c=NULL,*estado_auxiliar=NULL;
	cubo *auxiliar=NULL;
	push(&c,cubos);			//guarda el primer estado ( raiz ) 
	
	printf("\n\nINICIO DEL JUEGO\n\n");
	while(loop!=8){		//en este paso entra a un bucle que solo se detiene en caso de encontrar solución o que la pila sea vaciada y no haya solución
		
		final=comprobar_estado(cubos,matriz);
		if(final==1){ 
        largo_lista(c);
        printf("\n\nNumero de estados generados : %d\n",contador);
        printf("\n\n");
        system("pause");
        return;
        }
		if(c==NULL){
			printf("\n\nNo hay solucion\n\n");	//si la cola no tiene elementos no hay solución al mapa ingresado
			system("pause");
			return;
		}
			
		auxiliar=(cubo*)malloc(sizeof(cubo)*ncubos);
		
		copiar_cubos(auxiliar,cubos);   // se guarda el vector de cubos del estado padre
	
		printf("\n\nEstado:\n\n");
			
		for(j=0;j<ncubos;j++){
			printf("Cubo %d : x=%d , y=%d\n",j+1,cubos[j].p.x,cubos[j].p.y); // imprime las coordenadas de los cubos en cada estado
		}
		imprime_paso(matriz,cubos); // imprime la representacion de la matriz
		largo_lista(c);
			
		for(i=ncubos-1;i>=0;i--){			//en este for se va generando los hijos de derecha izquierda y se va almacenando en la pila
			k=mover_bloque(i,cubos,matriz);			// de esta manera se podrá seguir la busqueda con el "hijo" más a la izquierda del estado utilizando la funcion pop
			if(k==0) k=empujar(i,cubos,matriz);			
			if(k==1){                           //solo si hay movimiento se guarda el estado
            contador++;
            push(&c,cubos);
            }								
			copiar_cubos(cubos,auxiliar); // se recupera el vector de cubos del estado padre y asi podra generar los otros cubos
		}
		estado_auxiliar=pop(&c); // se obtiene el ultimo elemento de la pila para continuar con la busqueda en profundidad
				
		for(i=0;i<ncubos;i++){
			cubos[i]=estado_auxiliar->cubos[i];		//se actualiza el vector de cubos con los datos del nuevo estado
		}
		printf("\n-----------------------\n");				
	}
}


//en esta funcion se hace el recorrido en anchura para encontrar la solucion al juego
void bfs(cubo cubos[ncubos],casilla matriz[filas][columnas]){
	int i,j,k=5,aux,final,validar=0,contador=0;
	estado *pri=NULL,*ult=NULL,*aux_estado=NULL;
	cubo *auxiliar=NULL;	
	
	while(k!=8){			//entra en un bucle que solo se interrumpe al encontrar solucion, o determinar que el juego no tiene solucion
		final=comprobar_estado(cubos,matriz);
		if(final==1){ 
        largo_lista(pri); 
        printf("\n\nNumero de estados generados : %d\n",contador);
        printf("\n\n");
        system("pause");
        return;
        }
		
		if(pri==NULL && validar>0){
			printf("\n\nNo hay solucion\n\n");
			system("pause");
			return;
		}
														//se sigue un procedimiento similar al dfs con respecto al respaldo del vector de cubos
		auxiliar=(cubo*)malloc(sizeof(cubo)*ncubos);
			
		copiar_cubos(auxiliar,cubos);
		
		printf("\n\nEstado:\n\n");
			
		for(j=0;j<ncubos;j++){
			printf("Cubo %d : x=%d , y=%d\n",j+1,cubos[j].p.x,cubos[j].p.y);
		}
		imprime_paso(matriz,cubos);
		largo_lista(pri);
		
		
		for(i=0;i<ncubos;i++){
			k=mover_bloque(i,cubos,matriz);					//va guardando los estados hijos en la cola si es que es posible realizar un movimiento			
			if(k==0)	k=empujar(i,cubos,matriz);							
			if(k==1){
            contador++;	
            encolar(&pri,&ult,cubos);
            }								
			copiar_cubos(cubos,auxiliar);
		}	
					
		aux_estado=desencolar(&pri,&ult);   // recupera el siguiente estado almacenado para continuar la busqueda
		
		for(i=0;i<ncubos;i++){
			cubos[i]=aux_estado->cubos[i];		
		}
		printf("\n\n-----------------------\n");
		validar++;			
	}	
}

//esta funcion realiza los movimientos,siempre y cuando sean posibles, del cubo que se quiera mover
//la funcion retornara 1 en caso de que el movimiento se realice, de lo contrario retorna 0
int mover_bloque(int num_cubo,cubo *vector_cubos,casilla matriz[filas][columnas]){
		
	int i,j,d,x,y; 
	casilla aux[filas][columnas];				//se utiliza una matriz auxiliar para modificarla
	char status;
	
	for(i=0;i<filas;i++){
		for(j=0;j<columnas;j++){
			aux[i][j]=matriz[i][j];
		}
	}
	
	for(i=0;i<ncubos;i++){
		aux[vector_cubos[i].p.x][vector_cubos[i].p.y].ocupado=1;
		if(i==num_cubo){										//se añaden a la matriz la presencia de cubos en sus respectivas coordenadas
			x=vector_cubos[i].p.x;
			y=vector_cubos[i].p.y;									
		}
	}
		
	char direccion=vector_cubos[num_cubo].direccion;
	
	if (direccion=='U'){   // se analiza el caso en que el movimiento sea hacia arriba, validando todos los casos y realizando el movimiento solo si es posible
		
		if(x>0){
						
			if(aux[x-1][y].ocupado==0){
				
				if(vector_cubos[num_cubo].color==aux[x-1][y].color_objetivo){
					status=OK;
				}
				else{
					status=NO;
				}
				
				vector_cubos[num_cubo].p.x=x-1;	
				vector_cubos[num_cubo].estado=status;	
				aux[x-1][y].ocupado=1;
				aux[x][y].ocupado=0;	
				
				if(aux[x-1][y].c_direccion!=0){
					vector_cubos[num_cubo].direccion=aux[x-1][y].c_direccion;
				}
				printf("\n\nMovimiento del cubo %d: \n",num_cubo+1);
				imprime_paso(aux,vector_cubos);
				return 1;
			}
			else return 0;
		}
		else return 0;
	}		
	
	if (direccion=='D'){  // se realiza el movimiento hacia abajo
		
		if(x<filas-1){
			
			if(aux[x+1][y].ocupado==0){
				
				if(vector_cubos[num_cubo].color==aux[x+1][y].color_objetivo){
					status=OK;					
				}				
				else	status=NO;					
									
				
				
				vector_cubos[num_cubo].p.x=x+1;
				vector_cubos[num_cubo].estado=status;
				aux[x+1][y].ocupado=1;
				aux[x][y].ocupado=0;	
			
						
				if(aux[x+1][y].c_direccion!=0){
					vector_cubos[num_cubo].direccion=aux[x+1][y].c_direccion;
				}
				printf("\n\nMovimiento del cubo %d: \n",num_cubo+1);
				imprime_paso(aux,vector_cubos);
				return 1;
			}
			else	return 0;		
		}	
		else return 0;	
	}
	
	if (direccion=='L'){ //el caso en que el cubo se quiera mover hacia la izquierda
		if(y>0){
			
			if(aux[x][y-1].ocupado==0){
				
				if(vector_cubos[num_cubo].color==aux[x][y-1].color_objetivo){
					status=OK;
				}
				else{
					status=NO;
				}
				
				
			  	vector_cubos[num_cubo].p.y=y-1;
			  	vector_cubos[num_cubo].estado=status;
			  	aux[x][y-1].ocupado=1;
				aux[x][y].ocupado=0;	
				
				if(aux[x][y-1].c_direccion!=0){
					vector_cubos[num_cubo].direccion=aux[x][y-1].c_direccion;
				}
				
				printf("\n\nMovimiento del cubo %d: \n",num_cubo+1);
				imprime_paso(aux,vector_cubos);
				return 1;
			}
			else	return 0;			
		}	
		else return 0;	
	}
	
	if (direccion=='R'){   // el caso en que el cubo se quiera mover hacia la derecha
		if(y<columnas-1){
			
			if(aux[x][y+1].ocupado==0){
				
				if(vector_cubos[num_cubo].color==aux[x][y+1].color_objetivo){				
					status=OK;		
				}
				else	status=NO;
				
				
				
				vector_cubos[num_cubo].p.y=y+1;
				vector_cubos[num_cubo].estado=status;
				aux[x][y+1].ocupado=1;
				aux[x][y].ocupado=0;	
				
				if(aux[x][y+1].c_direccion!=0){
					vector_cubos[num_cubo].direccion=aux[x][y+1].c_direccion;		
				}
				printf("\n\nMovimiento del cubo %d: \n",num_cubo+1);
				imprime_paso(aux,vector_cubos);
				return 1;
			}
			else return 0;
		}	
		else return 0;			
	}
}

//esta funcion es invocada en caso de que la funcion mover retorne un 0, ya que debe verificar si es que hay que empujar uno o más cubos para moverse
//en caso de poder empujar, realizara la operacion y retornará un 1, de lo contrario retornará 0
int empujar(int num_cubo,cubo *vector_cubos,casilla matriz[filas][columnas]){	
	int i,j,n,x,y,nc=num_cubo; 
	casilla aux[filas][columnas];
	char status;
		
	for(i=0;i<filas;i++){
		for(j=0;j<columnas;j++){  // 		tambien se trabaja con una matriz auxiliar
		aux[i][j]=matriz[i][j];
		}
	}
	
	for(i=0;i<ncubos;i++){
		aux[vector_cubos[i].p.x][vector_cubos[i].p.y].ocupado=1;
		if(i==num_cubo){						//se añaden a la matriz la presencia de cubos en sus respectivas coordenadas
			x=vector_cubos[i].p.x;
			y=vector_cubos[i].p.y;								
		}
	}

	x=vector_cubos[num_cubo].p.x;
	y=vector_cubos[num_cubo].p.y;
			
	char direccion=vector_cubos[num_cubo].direccion;
		
	if(direccion=='U'){ //en caso de que se quiera empujar hacia arriba
		n=0;
		int resta=x;
		
		while(aux[resta-1][y].ocupado==1 && resta -1 >= 0){
			n++;
			resta--;
		}
	
		if(x-n==0){
			printf("\n\nMovimiento del cubo %d: \n",nc+1);
			printf("\nNo se puede empujar");
			return 0;
		}	
		else{
				
			for(i=n;i>=0;i--){		//este for es muy importante ya que permitira la modificacion de la posicion de cada cubo involucrado en la operacion
				x=x-i;					//este proceso es similar para empujar en todas las demas direcciones
				
				for(j=0;j<ncubos;j++){
					if(vector_cubos[j].p.x==x && vector_cubos[j].p.y==y){
						num_cubo=j;
					}		
				}
					
				if(vector_cubos[num_cubo].color==aux[x-1][y].color_objetivo){
					status=OK;
				}
				else status=NO;
							
				vector_cubos[num_cubo].p.x=x-1;	
				vector_cubos[num_cubo].estado=status;
				aux[x-1][y].ocupado=1;
				aux[x][y].ocupado=0;	
									
				if(aux[x-1][y].c_direccion!=0){
					vector_cubos[num_cubo].direccion=aux[x-1][y].c_direccion;
				}
				x=x+i;
			}
			
			printf("\n\nMovimiento del cubo %d: \n",nc+1);
			imprime_paso(aux,vector_cubos);
			return 1;
		}
	}
	
	if(direccion=='D'){	//en el caso en que se empuja hacia abajo
		n=0;
		int aumenta=x;
		
		while(aux[aumenta+1][y].ocupado==1 && aumenta+1<filas) {
			n++;
			aumenta++;
		}
	 
		if(x+n==filas-1){
			printf("\n\nMovimiento del cubo %d: \n",nc+1);
			printf("\nNo se puede empujar");
			return 0;
		}
		else{
		
			for(i=n;i>=0;i--){
				x=x+i;
						
				for(j=0;j<ncubos;j++){
					if(vector_cubos[j].p.x==x && vector_cubos[j].p.y==y){
						num_cubo=j;
					}
				}
						
				if(vector_cubos[num_cubo].color==aux[x+1][y].color_objetivo){
					status=OK;				
				}				
				else status=NO;
								
				vector_cubos[num_cubo].p.x=x+1;	
				vector_cubos[num_cubo].estado=status;
				aux[x+1][y].ocupado=1;
				aux[x][y].ocupado=0;	
												
				if(aux[x+1][y].c_direccion!=0){
					vector_cubos[num_cubo].direccion=aux[x+1][y].c_direccion;
				}			
				x=x-i;
			}
			
			printf("\n\nMovimiento del cubo %d: \n",nc+1);
			imprime_paso(aux,vector_cubos);
			return 1;
		}
	}
	
	if(direccion=='L'){ 			// en el caso en que se empuja hacia la izquierda
		n=0;
		
		while(aux[x][y-n].ocupado==1 && y-n >= 0){
			n++;
		}
		n=n-1;
		
		if(y-n==0){
			printf("\n\nMovimiento del cubo %d: \n",nc+1);
			printf("\nNo se puede empujar");
			return 0;
		}
		else{

			for(i=n;i>=0;i--){
				y=y-i;
				
				if(y>0){
			
					if(aux[x][y-1].ocupado==0){
						
						for(j=0;j<ncubos;j++){
							if(vector_cubos[j].p.x==x && vector_cubos[j].p.y==y){
								num_cubo=j;
							}
						}
						
						if(vector_cubos[num_cubo].color==aux[x][y-1].color_objetivo){
							status=OK;
						}
						else status=NO;
										
						vector_cubos[num_cubo].p.y=y-1;	
						vector_cubos[num_cubo].estado=status;
						aux[x][y-1].ocupado=1;
						aux[x][y].ocupado=0;	
						
						if(aux[x][y-1].c_direccion!=0){
							vector_cubos[num_cubo].direccion=aux[x][y-1].c_direccion;
						}
					}			
				}		
				y=y+i;
			}
			printf("\n\nMovimiento del cubo %d: \n",nc+1);
			imprime_paso(aux,vector_cubos);
			return 1;
		}
	}
	
	if(direccion=='R'){					//en el caso en que se empuja hacia la derecha
		n=0;
		
		while(aux[x][y+n].ocupado==1 && y+n<columnas){
			n++;
		}
		n=n-1;
		
		if(y+n==columnas-1){
			printf("\n\nMovimiento del cubo %d: \n",nc+1);
			printf("\n\nNo se puede empujar\n\n");
			return 0;
		}
		else{
			
			for(i=n;i>=0;i--){
				y=y+i;
				if(y<columnas-1){

					if(aux[x][y+1].ocupado==0){
						
						for(j=0;j<ncubos;j++){
							if(vector_cubos[j].p.x==x && vector_cubos[j].p.y==y){
							num_cubo=j;
							}
						}
					
						if(vector_cubos[num_cubo].color==aux[x][y+1].color_objetivo){
							status=OK;
						}
						else status=NO;
						
						vector_cubos[num_cubo].p.y=y+1;	
						vector_cubos[num_cubo].estado=status;
						aux[x][y+1].ocupado=1;
						aux[x][y].ocupado=0;	
						
						if(aux[x][y+1].c_direccion!=0){
							vector_cubos[num_cubo].direccion=aux[x][y+1].c_direccion;
						}
					}			
				}		
				y=y-i;		
			}
			printf("\n\nMovimiento del cubo %d: \n",nc+1);
			imprime_paso(aux,vector_cubos);
			return 1;
		}
	}
}

//desde aqui se construye la matriz inicial y el vector de cubos inicial del juego y se invocan los recorridos
main(){					
       textbackground(BLUE );   
      
	int i,j,opc;
	casilla matriz[filas][columnas];
	cubo vector_cubos[ncubos];
	
	while(opc!=3){			//este bucle permite desplegar el menu que muestra la etapa a resolver y da a elejir qué recorrido realizar
	system("cls");
	
	for(i=0;i<filas;i++){                        //en cada bucle se restauraran los datos iniciales 
		for(j=0;j<columnas;j++){
			matriz[i][j].color_objetivo=0;
			matriz[i][j].c_direccion=0;
			matriz[i][j].ocupado=0;
		}
	}
	
	
printf("Nivel : Medio\n\n");			//se ingresa los datos de la etapa
										// OBS : los datos del mapa se deben modificar manualmente para cada etapa
										// se adjuntaran archivos txt con otras etapas listas para realizar y probar
	vector_cubos[0].color=1;
	vector_cubos[0].direccion='R';
	vector_cubos[0].estado=NO;
	vector_cubos[0].p.x=1;
	vector_cubos[0].p.y=0;	
	
	vector_cubos[1].color=2;
	vector_cubos[1].direccion='D';
	vector_cubos[1].estado=NO;
	vector_cubos[1].p.x=0;
	vector_cubos[1].p.y=1;	
	
	vector_cubos[2].color=3;
	vector_cubos[2].direccion='D';
	vector_cubos[2].estado=NO;
	vector_cubos[2].p.x=1;
	vector_cubos[2].p.y=2;	
		
	
	matriz[3][3].color_objetivo=1;	
	matriz[1][1].color_objetivo=2;
	matriz[2][2].color_objetivo=3;
    
    imprime_paso(matriz,vector_cubos);
	printf("\n\n");

    printf("Especifique el tipo de busqueda:\n\n1)BFS\n2)DFS\n3)Salir\n\n");
	scanf("%d",&opc);
	
	if(opc==1) bfs(vector_cubos,matriz);
	if(opc==2) dfs(vector_cubos,matriz);
	}	
}