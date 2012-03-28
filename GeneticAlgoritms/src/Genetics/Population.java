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
        for (int __indexIndividual = 0; __indexIndividual < this.getNumberIndividuals(); __indexIndividual++) {
            if(this.getTypeIndividual() instanceof OnesMax)
                this._population.add((OnesMax)this.getTypeIndividual());
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

    /**
     * @return the _numberIndividuals
     */
    public int getNumberIndividuals() {
        return _numberIndividuals;
    }

    /**
     * @param numberIndividuals the _numberIndividuals to set
     */
    public void setNumberIndividuals(int numberIndividuals) {
        this._numberIndividuals = numberIndividuals;
    }

    /**
     * @return the _numberGenes
     */
    public int getNumberGenes() {
        return _numberGenes;
    }

    /**
     * @param numberGenes the _numberGenes to set
     */
    public void setNumberGenes(int numberGenes) {
        this._numberGenes = numberGenes;
    }

    /**
     * @return the _numberChromosomes
     */
    public int getNumberChromosomes() {
        return _numberChromosomes;
    }

    /**
     * @param numberChromosomes the _numberChromosomes to set
     */
    public void setNumberChromosomes(int numberChromosomes) {
        this._numberChromosomes = numberChromosomes;
    }

    /**
     * @return the _typeIndividual
     */
    public Object getTypeIndividual() {
        return _typeIndividual;
    }

    /**
     * @param typeIndividual the _typeIndividual to set
     */
    public void setTypeIndividual(Object typeIndividual) {
        this._typeIndividual = typeIndividual;
    }
    
    public void addIndividual(Individual individual){
        this._population.add(individual);
    }
    
    public ArrayList<Individual> getArrayIndividualRandom(int numberIndividual, boolean removeIndividualFromPopulation){
        final ArrayList<Individual> __newArrayIndividual = new ArrayList<Individual>(numberIndividual);
        int __numberIndividual = this._numberIndividuals;
        int __indexIndividual;
        
        // Escolher de forma aleatoria varios individuos
        for (int __numberIndividualCount = 0; __numberIndividualCount < numberIndividual; __numberIndividualCount++) {
            // Selecciona de forma aleatoria o index de um individuo
            __indexIndividual = Population.randomGenerator.nextInt(__numberIndividual - 1);
            __newArrayIndividual.add(this._population.get(__indexIndividual));
            
            // caso se queira remover o individuo da população assim que é selecionado
            if(removeIndividualFromPopulation){
                __numberIndividual--;
                this._population.remove(__indexIndividual);
            }
        }        
        
        return __newArrayIndividual;
    }
}
