/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.mutation;

import operators.Genetic;
    
/**
 *
 * @author Chorinca-Notebook
 */
public abstract class Mutation extends Genetic {    
    static final double PROBABILITY_BY_DEFAULT = 0.2;
    protected double probability;
}
