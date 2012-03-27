package Genetics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public abstract class Individual implements Iterable<Chromosome> {
    
    private static final int DEFAULT_SIZE_GENOME = 1;

    protected ArrayList<Chromosome> _genome;
    private int _numberGenes;
    protected int _numberChromosomes = DEFAULT_SIZE_GENOME; 
    
    public Individual(){
        this(DEFAULT_SIZE_GENOME);
    }
    
    public Individual(int numberChromosomes) {
        this._numberChromosomes = numberChromosomes; 
        _genome = new ArrayList<Chromosome>(numberChromosomes);
        
        this._inicializationChromosomes();
    }

    private void _inicializationChromosomes() {
        for (int __indexChromosome = 0; __indexChromosome < this._numberChromosomes; __indexChromosome++) {
            this._genome.set(__indexChromosome, new Chromosome());
        }
    }
    
    public Chromosome getChromosome(int index) {
        return _genome.get(index);
    }

    public void setChromosome(int index, Chromosome cromosome) {
        _genome.add(index, cromosome);
    }
    
    public abstract int fiteness();
    
    @Override
    public Iterator<Chromosome> iterator() {
        return this._genome.iterator();
    }
    
    
}
