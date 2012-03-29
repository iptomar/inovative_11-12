package genetics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ComparatorIndividual;

public class Population implements Iterable<Individual> {
    
    public static final int DEFAULT_AGE_POPULATION = 0;
    public static final int DEFAULT_SIZE_POPULATION = 10;
    public static final int DEFAULT_SIZE_GENOME     = 1;    
    public static final int DEFAULT_SIZE_GENOTYPE   = 1;
    public static final int DEFAULT_SIZE_ALLELO     = 10;    
    public static final Individual DEFAULT_TYPE_POPULATION = new OnesMax();

    
    public static final Random RANDOM_GENERATOR = new Random();

    private ArrayList<Individual> _population;
    
    private int _sizePopulation;
    private int _sizeGenotype;
    private int _sizeGenome; 
    private int _sizeAllelo;
    private Individual _typePopulation;
    
    public Population(Individual typePopulation) {
        this(Population.DEFAULT_SIZE_POPULATION,
             Population.DEFAULT_SIZE_GENOME,
             Population.DEFAULT_SIZE_GENOTYPE,
             Population.DEFAULT_SIZE_ALLELO,
             typePopulation);
    }
    
    public Population(int sizePopulation, int sizeGenome, int sizeGenotype, int sizeAllelo, Individual typePopulation){
        this(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, typePopulation, true);
    }
    
    public Population(int sizePopulation, int sizeGenome, int sizeGenotype, int sizeAllelo, Individual typePopulation, boolean initializesPopulation){
        this._sizePopulation    = sizePopulation;
        this._sizeGenome        = sizeGenome;
        this._sizeGenotype      = sizeGenotype;
        this._sizeAllelo        = sizeAllelo;
        this._typePopulation    = typePopulation;
        
        _population             = new ArrayList<Individual>(sizePopulation);
        
        if(initializesPopulation)
            _inicializationPopulation();
    }
    
    private void _inicializationPopulation(){
        for (int __indexIndividual = 0; __indexIndividual < this._sizePopulation; __indexIndividual++) {
            try {
                
                Individual __newIndividual = (Individual)_typePopulation.getClass().newInstance();
                
                __newIndividual.setSizeGenome(_sizeGenome);
                __newIndividual.setSizeGenotype(_sizeGenotype);
                __newIndividual.setSizeAllelo(_sizeAllelo);
                
                __newIndividual.inicializationGenome();
                
                this._population.add(__newIndividual);
                
            } catch (InstantiationException ex) {
                Logger.getLogger(Population.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Population.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    public Individual getIndividual(int index) {
        return getPopulation().get(index);
    }

    public void setIndividual(int index, Individual individual) {
        getPopulation().add(index, individual);
    }
    
    /**
     * @return the _numberIndividuals
     */
    public int getSizePopulation() {
        return _population.size();
    }

    /**
     * @param sizePopulation the _numberIndividuals to set
     */
    public void setSizePopulation(int sizePopulation) {
        this._sizePopulation = sizePopulation;
    }

    
    /**
     * @return the _typeIndividual
     */
    public Individual getTypePopulation() {
        return _typePopulation;
    }

    /**
     * @param typePopulation the _typeIndividual to set
     */
    public void setTypePopulation(Individual typePopulation) {
        this._typePopulation = typePopulation;
    }
    
    public void addIndividual(Individual individual){
        this._population.add(individual);
    }
    
    /***
     * 
     * @param numberIndividual
     * @param removeIndividualFromPopulation
     * @return 
     */
    public ArrayList<Individual> getArrayIndividualsRandom(int numberIndividual, boolean removeIndividualFromPopulation){
        final ArrayList<Individual> __newArrayIndividual = new ArrayList<Individual>(numberIndividual);
        int __countIndividual = 0;
        int __indexIndividual;
        int __numberIndividualToReturn = numberIndividual;
        
        // Escolher de forma aleatoria varios individuos        
        while(__countIndividual < __numberIndividualToReturn){
            
            __indexIndividual = Population.RANDOM_GENERATOR.nextInt(this.getSizePopulation() - 1);
            __newArrayIndividual.add(this.getPopulation().get(__indexIndividual).clone());
            
            if(removeIndividualFromPopulation){
                this._population.remove(this.getPopulation().get(__indexIndividual));
            }
            
            __countIndividual++;
        }
        
        return __newArrayIndividual;
    }

    /**
     * @return the _population
     */
    public ArrayList<Individual> getPopulation() {
        return _population;
    }

    /**
     * @param population the _population to set
     */
    public void setPopulation(ArrayList<Individual> population) {
        this._population = population;
    }

    @Override
    public Iterator<Individual> iterator() {
        return this._population.iterator();
    }

    /**
     * @return the _sizeAllelo
     */
    public int getSizeAllelo() {
        return _sizeAllelo;
    }

    /**
     * @param sizeAllelo the _sizeAllelo to set
     */
    public void setSizeAllelo(int sizeAllelo) {
        this._sizeAllelo = sizeAllelo;
    }
    
    @Override
    public String toString() {
        StringBuilder __output = new StringBuilder();
        int __countIndividuals = 1;
        
        for (Individual __individual : this) {
            __output.append(__countIndividuals);
            __output.append(" - ");
            __output.append(__individual.toString());
            __output.append(" - ");
            __output.append(__individual.fiteness());
            __output.append(" - ");
            __output.append(__individual.getAgeIndividual());
            __output.append("\n");
            __countIndividuals++;
        }

        return __output.toString();
    }

    /**
     * @return the _sizeGenotype
     */
    public int getSizeGenotype() {
        return _sizeGenotype;
    }

    /**
     * @param sizeGenotype the _sizeGenotype to set
     */
    public void setSizeGenotype(int sizeGenotype) {
        this._sizeGenotype = sizeGenotype;
    }

    /**
     * @return the _sizeGenome
     */
    public int getSizeGenome() {
        return _sizeGenome;
    }

    /**
     * @param sizeGenome the _sizeGenome to set
     */
    public void setSizeGenome(int sizeGenome) {
        this._sizeGenome = sizeGenome;
    }
    
    public void incrementAgePopulation(){
        for (Individual __individual : this) {
            __individual.incrementAge();
        }
    }
    
    public void resetAgePopulation(){
        for (Individual __individual : this) {
            __individual.setAgeIndividual(0);
        }
    }

    public int getBestFiteness() {
        int __bestFiteness = 0;
        for (Individual __individual : this) {
            if(__bestFiteness < __individual.fiteness())
                __bestFiteness = __individual.fiteness();
        }
        return __bestFiteness;
    }
    
    public Population getHallOfFame(int sizeHallOfFame) {
        final Population __newPopulation = 
                new Population(
                    sizeHallOfFame, 
                    this._sizeGenome, 
                    this._sizeGenotype,
                    this._sizeAllelo,
                    this._typePopulation, 
                    false);
        
        Collections.sort(this._population, new ComparatorIndividual());
        for (Individual __individualHallOfFame : this._population.subList(0, 5)) {
            __newPopulation.addIndividual(__individualHallOfFame);    
        }

        return __newPopulation;
    }
}
