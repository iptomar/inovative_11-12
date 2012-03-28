/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.recombinations;

import Genetics.Population;

/**
 *
 * @author Chorinca-Notebook
 */
public class Crossover extends Recombination {

    static final int NUMBER_CUTS_DEFAULT = 2;
    
    private int _numberCuts = Crossover.NUMBER_CUTS_DEFAULT;
    
    @Override
    public Population execute(Population population) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
