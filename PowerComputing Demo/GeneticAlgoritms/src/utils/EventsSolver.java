/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import genetics.Population;

/**
 *
 * @author Chorinca-Notebook
 */
public interface EventsSolver {
    
    public void EventStartSolver();    
    public void EventIteraction(int iteractionNumber, Population currentPopulation);
    public void EventFinishSolver(Population lastPopulation);

}
