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
public class Population {
    
    protected static final Random randomGenerator = new Random();

    private ArrayList<Individual> _pop;
    
    public Population(int numberIndividuals){
        _pop = new ArrayList<Individual>(numberIndividuals);
    }

    public Individual getIndividual(int index) {
        return _pop.get(index);
    }

    public void setIndividual(int index, Individual individual) {
        _pop.add(index, individual);
    }

    public void createRandom() {
        for (Individual individual : _pop) {
            for (Object cro : individual) {
                for (Object gene : cro) {
                    
                }
            }
            
        }
    }
}
