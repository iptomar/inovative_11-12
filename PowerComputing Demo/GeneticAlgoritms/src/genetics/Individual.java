package genetics;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe que representa um individuo.
 * Recebe como parâmetro o tamanho do genoma, tamanho do genótipo e tamanho dos alelos.
 * É composta por um ArrayList com elementos do tipo Chromossome, chamados genome.
 * Implementa a interface Iterable, que permite que o cromossoma consiga directamente
 * devolver o array genome, que permite com um ciclo for aceder directamente a 
 * cada cromossoma.
 * @author goncalo
 */

public abstract class Individual<T> implements Iterable<Chromosome> {
    
    private final ArrayList<Chromosome> _genome;
    private int _sizeGenotype;
    private int _sizeGenome; 
    private int _sizeAllelo;
    private int _ageIndividual = Population.DEFAULT_AGE_POPULATION;
    
    /**
     * Vai buscar os valores definidos por defeito na classe Population.
     * É o construtor por defeito.
     */
    public Individual(){
        this(Population.DEFAULT_SIZE_GENOME, 
             Population.DEFAULT_SIZE_GENOTYPE, 
             Population.DEFAULT_SIZE_ALLELO);
    }
    
    /**
     * Construtor que inicializa o individuo com os valores passados por parametro.
     * 
     * @param sizeGenome - tamanho do genoma
     * @param sizeGenotype - tamanho do genotipo
     * @param sizeAllelo - tamanho do allelo
     */
    public Individual(int sizeGenome, int sizeGenotype, int sizeAllelo) {
        this._sizeGenome        = sizeGenome; 
        this._sizeGenotype      = sizeGenotype;
        this._sizeAllelo        = sizeAllelo;
        this._genome            = new ArrayList<Chromosome>(sizeGenome);
    }
    
    /**
     * Construtor que faz uma cópia do individuo
     * @param newIndividual 
     */
    public Individual(Individual newIndividual) {
        this._sizeGenome        = newIndividual.getSizeGenome(); 
        this._sizeGenotype      = newIndividual.getSizeGenotype();
        this._sizeAllelo        = newIndividual._sizeAllelo;
        this._genome            = new ArrayList<Chromosome>(this._sizeGenome);
        this._ageIndividual     = newIndividual.getAgeIndividual();
        
        // Cria chromosomes novos
        for (Object __chromosome : newIndividual.getGenome()) {
            this._genome.add(new Chromosome((Chromosome)__chromosome));
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
    
    //método abstracto que implementa o fitness
    public abstract int fitness();
    //método abstracto que implementa a inicialização dos allelos para este individuo
    public abstract T inicializationAllelo();
    
    //método abstracto que implementa a cópia de um individuo
    @Override
    public abstract Individual clone();

    /**
     * Data a idade do individuo.
     * Por cada iteração incrementa um valor ao individuo.
     * Permite saber a geração do individuo.
     * @return the _ageIndividual
     */
    public int getAgeIndividual() {
        return _ageIndividual;
    }

    /**
     * Atribui uma idade, passada por parametro, ao individuo.
     * @param ageIndividual the _ageIndividual to set
     */
    public void setAgeIndividual(int ageIndividual) {
        this._ageIndividual = ageIndividual;
    }
    
    /**
     * Incrementa a idade do individuo
     */
    public void incrementAge(){
        this._ageIndividual++;
    }
}
