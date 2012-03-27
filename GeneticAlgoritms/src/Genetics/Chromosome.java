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
public class Chromosome implements Iterable<Gene> {

    private static final int DEFAULT_SIZE_CHROMOSOME = 10;
    private ArrayList<Gene> _genotype;

    public Chromosome() {
        this(DEFAULT_SIZE_CHROMOSOME);
    }

    public Chromosome(int sizeChromosome) {
        this._genotype = new ArrayList<Gene>(sizeChromosome);
    }
    
    public Gene getGene(int index){
        return _genotype.get(index);
    }
    
    public void setGene(int index, Gene gene){
        _genotype.add(index, gene);
    }

    @Override
    public Iterator<Gene> iterator() {
        return this._genotype.iterator();
    }
    
}
