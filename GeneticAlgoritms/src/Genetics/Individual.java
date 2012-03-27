/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Genetics;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author diogoantonio
 */
public abstract class Individual<T> implements Iterable<Chromosome> {
    
    private static final int DEFAULT_SIZE_GENOME = 1;

    private ArrayList<
            > _genome;

    public Individual(){
        this(DEFAULT_SIZE_GENOME);
    }
    
    public Individual(int numberChromosomes) {
        _genome = new ArrayList<Chromosome>(numberChromosomes);
    }

    public Chromosome getChromosome(int index) {
        return _genome.get(index);
    }

    public void setChromosome(int index, Chromosome cromosome) {
        _genome.add(index, cromosome);
    }
    
    public abstract T fiteness();
    
    @Override
    public Iterator<Chromosome> iterator() {
        return this._genome.iterator();
    }
    
    
}
