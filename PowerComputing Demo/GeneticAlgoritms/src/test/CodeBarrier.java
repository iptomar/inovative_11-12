/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Bruno Oliveira nº 11127 IPT-ESTT
 */
public class CodeBarrier implements Runnable {

    /**
     * Objecto que implementa a interface Runnable
     * Este objecto tem o codigo que é executado quando 
     * todas as threads cheagam a barreira.
     * Neste exemplo apenas é mostrado um texto mas pode existir algo préviamente partilhado
     * entre as threas através do construtor deste obejecto. (Java na sua forma simples :) )
     * (Mais info consultar Power Computing - Bruno Oliveira)
     */
    
    @Override
    public void run() {
         System.out.println("CodeBarrier CODE");
    }
    
}
