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
public class Individual {

    private ArrayList<Chromosome> _genome;

    public Individual() {
        _genome.add(new Chromosome());
        //TODO: Gerar cromossoma aleatório
    }

    public Chromosome getChromosome(int index) {
        return _genome.get(index);
    }

    public void setChromosome(int index, Chromosome cromosome) {
        _genome.add(index, cromosome);
    }
    
    public int sizeChromosome(){
        return _genome.size();
    }
    
    //TODO: metodo fitness [metodo abstracto??]
    public void fitness(){
        //TODO: implmentar este metodo [???]
    }
}
