/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import genetics.OnesMax;
import genetics.Solver;

/**
 *
 * @author Chorinca-Notebook
 */
public class main {
    
    public static void main(String[] args) {
        
        Solver __newSolver = new Solver(1000, 100, new OnesMax(), 1000, 80);
        __newSolver.run();
        
    }
    
}
