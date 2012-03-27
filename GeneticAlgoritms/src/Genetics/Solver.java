/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Genetics;

import java.util.ArrayList;

/**
 *
 * @author diogoantonio
 */
public class Solver {
    
    ArrayList<Population> populacoes;
    
    public Solver() {
        
        populacoes.add(new Population(100, 1, 1, new OnesMax(10)));
        
    }
    
}
