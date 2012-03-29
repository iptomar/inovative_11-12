/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import genetics.Individual;
import java.util.Comparator;

/**
 *
 * @author Chorinca-Notebook
 */
public class ComparatorIndividual implements Comparator<Individual> {

    @Override
    public int compare(Individual o1, Individual o2) {
        
        if(o1.fiteness() < o2.fiteness())
            return 1;
        else if(o1.fiteness() >  o2.fiteness())
            return -1;
        else
            return 0;
        
    }
    
}
