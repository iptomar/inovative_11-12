/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.mutation;

import java.util.Random;
import operators.Genetic;
    
/**
 *
 * @author Chorinca-Notebook
 */
public abstract class Mutation extends Genetic{
    protected double probability=0.02D;
    protected Random random = new Random();
}
