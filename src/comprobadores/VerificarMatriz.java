package comprobadores;
/*
    Esta clase es para verificar si tanto como el jugado o el oponente ha ganado
 */


import java.io.*;
import java.util.Arrays;

/**
 *
 * @author Grupo10
 * Esta Clase Verifica la representacion matricial de las tablas jugadoras, ya sea del oponente o del jugador.
 * 
 */
public class VerificarMatriz {

    /**
     *Verifica si se ha cumplido la alineacion Fila-Columna, Superior e Izquierda respectivamente
     * @param  M
     * M es una matriz 4x4 , que posicion de la matriz esta de acuerdo al tablero del juego
     */


    public  static boolean comprobar( int M[][] )  {

        int sumaFilas[];
        int sumaColumnas[];
        
        sumaFilas = new int[4];
        sumaColumnas = new int[4];

        for (int i=0; i<4; i++)
            for (int k=0; k<4; k++){

                sumaFilas[i] = sumaFilas[i] + M[i][k];

            }
        for (int k=0; k<4; k++)
            for (int i=0; i<4; i++)
                sumaColumnas[k] = sumaColumnas[k] + M[i][k];
        boolean valor= esArray(4, sumaFilas, sumaColumnas);

        return valor;

    }

    /**
     *Verifica si en el array, tanto las filas como columnas suman el mismo valor
     * @param numero
     * @param sumaFilas
     * @param sumaColumnas
     */
    public static boolean esArray(int numero,int sumaFilas[],int sumaColumnas[]){
        boolean retorno=false;
        boolean x= check(sumaFilas, numero);
        boolean y= check(sumaColumnas, numero);
        if(x){
            retorno=true;
            return  retorno;
        }
        return  retorno;
    }

    /**
     *
     * @param  arr
     * @param toCheckValue
     */
    private static boolean check(int[] arr, int toCheckValue) {

        boolean test = false;
        for (int element : arr) {
            if (element == toCheckValue) {
                test = true;
                break;
            }
        }
        return test;

    }

    /**
     *Verifica si se ha cumplido la alineacion Diagonal Derecha
     * @param M, la matriz para verifificar
     */
 
    public  static boolean diagonalCom(int M[][]) {
         
        boolean valor;
        int sumaDiagonales= M[0][0]+M[1][1]+M[2][2]+M[3][3];
        /*
        for (int k=0; k<4; k++)
            for (int i=0; i<4; i++) {
                if (i==k) {
                    sumaDiagonales += M[k][i];
                }
            }
        */
        if (sumaDiagonales==4) {
            valor=true;
        }else {
            valor=false;
        }

        return valor;
    }

    /**
     *Verifica si se ha cumplido la alineacion Esquinas
     * @param M, la matriz para verifificar
     */

    public static boolean esquinasCom(int M[][]) {
         
        boolean valor;
        int sumaEsquinas=M[0][0]+M[3][3]+M[0][3]+M[3][0] ;
        /*
        for (int k=0; k<4; k=k+3)
            for (int i=0; i<4; i=i+3) {
                sumaEsquinas += M[k][i];
            }
        */    
        if (sumaEsquinas==4) {
            valor=true;
        }else {
            valor=false;
        }

        return valor;
    }
    /**
     *Verifica si se ha cumplido la alineacion Valores del medio
     * @param M, la matriz para verifificar
     */


    public static boolean medioCards(int M[][]){
        int sumaMedio=M[1][1]+M[1][2]+M[2][1]+M[2][2] ;
        
        boolean valor;
        if (sumaMedio==4) {
            valor=true;
        }else {
            valor=false;
        }

        return valor;


    }
    /**
     *Verifica si se ha cumplido la alineacion Diagonal Izquierda
     * @param M, la matriz para verifificar
     */


    public static boolean sumarTrazaIzquierda(int M[][]){
    	
        int sumaMedio=M[0][3]+M[1][2]+M[2][1]+M[3][0] ;
        boolean valor;
        if (sumaMedio==4) {
            valor=true;
        }else {
            valor=false;
        }

        return valor;

    }
}
