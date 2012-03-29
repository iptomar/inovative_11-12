package operators;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import genetics.Population;
import java.util.Random;

/**
 *
 * @author Chorinca-Notebook
 */
public abstract class Genetic extends Operator {
    
    static final public Random RANDOM_GENERATOR = new Random();
    
    public abstract Population execute(Population population);
    
}
