/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moa2014;

import com.google.common.collect.Multimap;
import java.io.Console;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import static java.util.Spliterators.*;
import java.util.TreeMap;
import java.util.TreeSet;

import com.google.common.collect.TreeMultimap;
import java.util.HashMap;

/**
 *
 * @author Gustavo
 */
public class MOAH22014 {
    
    public static String transformaMatrizString(int[][] matriz){

    int a;
    int b;
    String matrizstring = "";
    for(a=0; a<4; a++){
        for(b=0; b<4;b++){
            int elemento = matriz[a][b];
            matrizstring += String.valueOf(elemento);
            matrizstring += " ";
        }
    }
    return matrizstring;
}

    public static int diferencaMatriz(int[][] matriz){
        int[][] original = {{1, 5, 9, 13},{2, 6, 10, 14},{3, 7, 11, 15},{4, 8, 12, 0}};
        int dif = 0;
        int a;
        int b;
        int N = 4;
        for (a=0; a<4; a++){
            for(b=0; b<4; b++){
                if (original[a][b] != matriz[a][b]){
                    if (matriz[a][b]==0){
                        continue;
                    }
                    else{
                        int targetB = (matriz[a][b] - 1) / N; // expected x-coordinate (row)
                        int targetA = (matriz[a][b] - 1) % N; // expected y-coordinate (col)
                        int db = Math.abs(b - targetB); // x-distance to expected coordinate
                        int da = Math.abs(a - targetA); // y-distance to expected coordinate
                        dif += da + db;
                    }

                }
            }
        }
        System.out.println("Diferenca: "+dif);
        Scanner scanner = new Scanner( System.in );
        String input = scanner.nextLine();
        return dif;

    }
    
    public static int[] localizazero(int[][] matriz){
        int[] retorno = {0, 0};
        for (int x=0; x < 4; x++){
            for (int y=0; y < 4; y++){
                if (matriz[x][y] == 0){
                    retorno[0] = x;
                    retorno[1] = y;
                }
            }
        }
        return retorno;
    }
    
    public static int[][] copyarray(int[][] matriz){
        int[][] matriznova = {{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0}};
        for(int a=0; a<4;a++){
            for(int b = 0; b<4; b++){
                matriznova[a][b] = matriz[a][b];
            }
        }
        return matriznova;
    }
    
    public static void principal(int[][] matrizatual){

    Multimap<Integer, String> open_list = TreeMultimap.create(); 
    HashMap<String, Estado> processados = new HashMap();

    int difmatrizatual = diferencaMatriz(matrizatual);

    String stringmatriz = transformaMatrizString(matrizatual);
    open_list.put(difmatrizatual, stringmatriz);
    Estado estadoatual = new Estado(matrizatual, 0);
    processados.put(stringmatriz, estadoatual);
    
    
    int counter = 1;

    while(!open_list.isEmpty()){
        System.out.println("Arvores processadas: " + counter);
        Iterator iterator = open_list.keySet().iterator();
        /*
        Iterator iterator2 = open_list.keySet().iterator();
        
        while (iterator2.hasNext()) {
          Integer key = (Integer) iterator2.next();
          System.out.println("key : " + key + " value :" + open_list.get(key));
        }
        
        Scanner scanner = new Scanner( System.in );
        String input = scanner.nextLine();
        */
        counter++;
        Integer key = (Integer) iterator.next();
        String matrizatualx1 = open_list.asMap().get(key).iterator().next();
        Estado estadomenor = processados.get(matrizatualx1);
        int altura = estadomenor.getCusto();
        System.out.println("Altura: " + altura);
        //LOCALIZA O ZERO
        int[] zerot = localizazero(estadomenor.getMatriz());
        int x = zerot[0];
        int y = zerot[1];
        int x0 = x-1;
        int x1 = x+1;
        int y0 = y-1;
        int y1 = y+1;
        int difmatrizatualx = diferencaMatriz(estadomenor.getMatriz());
        if (difmatrizatualx== 0){
            System.out.println("Arvores processadas: " + counter);
            System.out.println("Custo: " + estadomenor.getCusto());
            break;
        }
        int[][] matrizatualx = estadomenor.getMatriz();
        if(x0>=0){
            
            int[][] matriz;
            matriz = copyarray(matrizatualx);
            matriz[x][y] = matrizatualx[x0][y];
            matriz[x0][y] = matrizatualx[x][y];
            
            String stringmatriz1 = transformaMatrizString(matriz);
            if (!(processados.containsKey(stringmatriz1))){

                    int diferencamatriz = diferencaMatriz(matriz);
                    int custototal = diferencamatriz + altura + 1;
                    
                    Estado estadonovo = new Estado(matriz, altura+1);
                    open_list.put(custototal, stringmatriz1);
                    
                    
                    processados.put(stringmatriz1, estadonovo);
                
            }
        }
        if(x1<=3){
            int[][] matriz;
            matriz = copyarray(matrizatualx);
            matriz[x][y] = matrizatualx[x1][y];
            matriz[x1][y] = matrizatualx[x][y];
            String stringmatriz2 = transformaMatrizString(matriz);
            
            if (!(processados.containsKey(stringmatriz2))){

                    int diferencamatriz = diferencaMatriz(matriz);
                    int custototal = diferencamatriz + altura + 1;
                    
                    Estado estadonovo = new Estado(matriz, altura+1);
                    open_list.put(custototal, stringmatriz2);
                    
                    
                    processados.put(stringmatriz2, estadonovo);
                
            }
        }
        if(y0>=0){
            int[][] matriz;
            matriz = copyarray(matrizatualx);
            matriz[x][y] = matrizatualx[x][y0];
            matriz[x][y0] = matrizatualx[x][y];
            String stringmatriz3 = transformaMatrizString(matriz);
            
            if (!(processados.containsKey(stringmatriz3))){

                    int diferencamatriz = diferencaMatriz(matriz);
                    int custototal = diferencamatriz + altura + 1;
                    
                    Estado estadonovo = new Estado(matriz, altura+1);
                    open_list.put(custototal, stringmatriz3);
                    
                    processados.put(stringmatriz3, estadonovo);
                
            }
        }
        if(y1<=3){
            int[][] matriz;
            matriz = copyarray(matrizatualx);
            matriz[x][y] = matrizatualx[x][y1];
            matriz[x][y1] = matrizatualx[x][y];

            int custoateaqui = diferencaMatriz(matriz) + altura + 1;
            String stringmatriz4 = transformaMatrizString(matriz);

            if (!(processados.containsKey(stringmatriz4))){

                    int diferencamatriz = diferencaMatriz(matriz);
                    int custototal = diferencamatriz + altura + 1;
                    
                    Estado estadonovo = new Estado(matriz, altura+1);
                    open_list.put(custototal, stringmatriz4);
                    
                    processados.put(stringmatriz4, estadonovo);
                
            }
        }
        open_list.remove(key, matrizatualx1);
    }

}
    
    
    public static void main(String[] args) {
        int[][] caso1 = {{1, 5, 9, 13},
                         {6, 3, 10, 14},
                         {4, 11, 0, 15},
                         {7, 2, 8, 12}};

        int[][] caso2 = {{1, 6, 0, 11},
        {2, 9, 13, 5},
        {3, 7, 14, 10},
        {4, 8, 12, 15}};

        int[][] caso3 = {{9, 8, 0, 14},
        {7, 6, 5, 1},
        {2, 4, 11, 10},
        {3, 12, 13, 15}};

        int[][] caso4 = {{1, 13, 7, 10},
        {2, 0, 12, 11},
        {3, 14, 6, 9},
        {8, 4, 5, 15}};

        int[][] caso5 = {{4, 7, 0, 13},
        {5, 9, 10, 14},
        {6, 11, 3, 1},
        {8, 2, 15, 12}};

    MOAH22014.principal(caso1);
    }
    
}
