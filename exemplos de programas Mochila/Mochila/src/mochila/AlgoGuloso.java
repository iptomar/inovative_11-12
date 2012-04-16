/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mochila;

/**
 *
 * @author mendes
 */
public class AlgoGuloso {

    public static void main(String[] args) {
        Mochila mochila = new Mochila(10, 9);
        int W = mochila.getW();
        int j = mochila.getJ();
        int[] w = mochila.getw();
        int[] x = new int[W];
 
        while (j >= 1 && w[j] <= W) {
            x[j] = 1; //insere o item na mochila
            W = W - w[j]; //decrementa a capacidade
            j--; //decrementa itens
            System.out.println("Peso do item "+j+": "+w[j]);
            System.out.println("Espaço disponivel: "+W);
 
            //invariante
            if (j >= 1) {
                x[j] = W / w[j]; //avalia se o item cabe na mochila
                for (int k = j - 1; k < j && k > 0; k--) {
                    x[k] = 0;//os espaços não preênchidos recebem 0
                }
            }
        }
        int total = 0;
        for (int m : x) {
            total += m;
        }
        System.out.println("Total de itens: "+total);
    }

}
