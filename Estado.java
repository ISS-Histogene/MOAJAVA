/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moa2014;

/**
 *
 * @author Gustavo
 */
public class Estado {
    private int[][] matriz = new int[4][4];
    private int custo;

    public Estado(int[][] matriznova, int custo) {
        this.matriz = matriznova;
        this.custo = custo;
    }

    public int getCusto() {
        return custo;
    }

    public int[][] getMatriz() {
        return matriz;
    }
    
}