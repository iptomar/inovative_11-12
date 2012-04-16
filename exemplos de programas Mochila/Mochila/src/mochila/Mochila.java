/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mochila;

/**
 *
 * @author mendes
 */
public class Mochila {


 
    private int W; //capacidade da mochila
    private int j; //itens
    private int[] w = {2,1,5,6,8,10,3,5,4,1}; //tamanho dos itens
    private int[] v = {0,100,200,300,400,500,600,700,800,900}; //valor dos itens
    private int[] x; //mochila        
 
    public Mochila(){
 
    }    
    public Mochila(int capacidade, int itens){
        this.W = capacidade;
        this.j = itens;        
    }
    /**
     * Retorna um vetor com o tamanho de cada item disponível
     * @return
     */
    public int[] getw() {
        return w;
    }
    /**
     * Pega o valor definido como tamanho da mochila
     * @return
     */
    public int getW() {
        return W;
    }
    /**
     * Atribui o valor como tamanho da mochila
     * @param W
     */
    public void setW(int W) {
        this.W = W;
    }
    /**
     * Pega o valor da quantidade de itens
     * @return
     */
    public int getJ() {
        return j;
    }
    /**
     * Atribui quantidade de itens
     * @param j
     */
    public void setJ(int j) {
        this.j = j;
    }
}
