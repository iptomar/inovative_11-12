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

    private ArrayList<Individual> _pop;

    public Individual getIndividual(int index) {
        return _pop.get(index);
    }

    public void setIndividual(int index, Individual individual) {
        _pop.add(index, individual);
    }

    public void createRandom() {
        //TODO: implementar este metodo
    }
}
