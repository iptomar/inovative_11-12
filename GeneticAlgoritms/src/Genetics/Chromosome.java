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
public class Chromosome {

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
    
    public void generateRandomChromosome(){
        //TODO: implmentar este metodo
    }
    
    public int numberOfOnes(){
        //TODO: implementar esta classe
        return 0;
    }
    
}
