package genetics;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe que representa um individuo.
 * Recebe como parâmetro o tamanho do genoma, tamanho do genótipo e tamanho dos alelos.
 * É composta por um ArrayList com elementos do tipo Chromossome, chamados genome.
 * @author goncalo
 */

public abstract class Individual implements Iterable<Chromosome> {
    
    private final ArrayList<Chromosome> _genome;
    private int _sizeGenotype;
    private int _sizeGenome; 
    private int _sizeAllelo;
    private int _ageIndividual = Population.DEFAULT_AGE_POPULATION;
    
    public Individual(){
        this(Population.DEFAULT_SIZE_GENOME, 
             Population.DEFAULT_SIZE_GENOTYPE, 
             Population.DEFAULT_SIZE_ALLELO);
    }
    
    public Individual(int sizeGenome, int sizeGenotype, int sizeAllelo) {
        this._sizeGenome        = sizeGenome; 
        this._sizeGenotype      = sizeGenotype;
        this._sizeAllelo        = sizeAllelo;
        this._genome            = new ArrayList<Chromosome>(sizeGenome);
    }
    
    public Individual(Individual newIndividual) {
        this._sizeGenome        = newIndividual.getSizeGenome(); 
        this._sizeGenotype      = newIndividual.getSizeGenotype();
        this._sizeAllelo        = newIndividual._sizeAllelo;
        this._genome            = new ArrayList<Chromosome>(this._sizeGenome);
        this._ageIndividual     = newIndividual.getAgeIndividual();
        
        // Cria chromosomes novos
        for (Chromosome __chromosome : newIndividual.getGenome()) {
            this._genome.add(new Chromosome(__chromosome));
        }
    }

    public void inicializationGenome() {
        for (int __indexChromosome = 0; __indexChromosome < this.getSizeGenome(); __indexChromosome++) {
            this.getGenome().add(new Chromosome(this));
        }
    }
    
    public Chromosome getChromosome(int index) {
        return getGenome().get(index);
    }

    public void setChromosome(int index, Chromosome cromosome) {
        getGenome().add(index, cromosome);
    }
    
    @Override
    public Iterator<Chromosome> iterator() {
        return this.getGenome().iterator();
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

    /**
     * @return the _genome
     */
    public ArrayList<Chromosome> getGenome() {
        return _genome;
    }
    
    @Override
    public String toString() {
        final StringBuilder __output = new StringBuilder();

        for (Chromosome __chromosome : this) {
            __output.append(__chromosome.toString());
        }

        return __output.toString();
    }
    
    public abstract int fiteness();
    public abstract Boolean[] inicializationAllelo();
    
    @Override
    public abstract Individual clone();

    /**
     * @return the _ageIndividual
     */
    public int getAgeIndividual() {
        return _ageIndividual;
    }

    /**
     * @param ageIndividual the _ageIndividual to set
     */
    public void setAgeIndividual(int ageIndividual) {
        this._ageIndividual = ageIndividual;
    }
    
    public void incrementAge(){
        this._ageIndividual++;
    }
}
