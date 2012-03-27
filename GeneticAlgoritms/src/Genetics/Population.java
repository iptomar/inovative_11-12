package Genetics;

import java.util.ArrayList;
import java.util.Random;

public class Population {
    
    protected static final Random randomGenerator = new Random();

    private ArrayList<Individual> _population;
    
    private int _numberIndividuals;
    private int _numberGenes;
    private int _numberChromosomes;
    private Object _typeIndividual;
    
    public Population(int numberIndividuals, int numberChromosomes, int numberGenes, Object typeIndividual){
        this._numberIndividuals = numberIndividuals;
        this._numberChromosomes = numberChromosomes;
        this._numberGenes = numberGenes;
        this._typeIndividual = typeIndividual;
        
        _population = new ArrayList<Individual>(numberIndividuals);
    }
    
    private void inicializationPopulation(){
        for (int __indexIndividual = 0; __indexIndividual < this._numberIndividuals; __indexIndividual++) {
            if(this._typeIndividual instanceof OnesMax)
                this._population.add((OnesMax)this._typeIndividual);
        }
    }

    
    public Individual getIndividual(int index) {
        return _population.get(index);
    }

    public void setIndividual(int index, Individual individual) {
        _population.add(index, individual);
    }
    
    @Override
    public String toString(){
        StringBuilder value = new StringBuilder();
        
        for (Individual individual : _population) {
            for (Object chromosome : individual) {
                for (Gene gene : (Chromosome)chromosome) {
                    value.append(gene.toString());
                }
            }
        }
        
        return value.toString();
    }
    
}
