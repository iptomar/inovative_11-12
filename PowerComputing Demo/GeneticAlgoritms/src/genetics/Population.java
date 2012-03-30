package genetics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ComparatorIndividual;

/**
 * Classe que representa uma população de individuos.
 * Recebe como parãmetros o tamanho da população, o tamanho do genoma,
 * tamanho do genótipo, tamanho dos alelos e o tipo da população(ex:boolean)
 * É composta por um ArrayList de elementos do tipo Individual
 * 
 * @author goncalo
 */

public class Population implements Iterable<Individual> {
    public static final int DEFAULT_AGE_POPULATION = 0;
    public static final int DEFAULT_SIZE_POPULATION = 10;
    public static final int DEFAULT_SIZE_GENOME     = 1;    
    public static final int DEFAULT_SIZE_GENOTYPE   = 1;
    public static final int DEFAULT_SIZE_ALLELO     = 10;    
    public static final Individual DEFAULT_TYPE_POPULATION = new OnesMax();

    //gerador de números aleatórios
    public static final Random RANDOM_GENERATOR = new Random();

    private ArrayList<Individual> _population;
    
    private int _sizePopulation;
    private int _sizeGenotype;
    private int _sizeGenome; 
    private int _sizeAllelo;
    private Individual _typePopulation;
    
    /**
     * Construtor por defeito, que cria uma população com os valores definidos
     * também por defeito.
     * @param typePopulation 
     */
    public Population(Individual typePopulation) {
        this(Population.DEFAULT_SIZE_POPULATION,
             Population.DEFAULT_SIZE_GENOME,
             Population.DEFAULT_SIZE_GENOTYPE,
             Population.DEFAULT_SIZE_ALLELO,
             typePopulation);
    }
    
    /**
     * Criação de uma população com todos os parâmetros e gera a população aleatoriamente
     * @param sizePopulation
     * @param sizeGenome
     * @param sizeGenotype
     * @param sizeAllelo
     * @param typePopulation 
     */
    public Population(int sizePopulation, int sizeGenome, int sizeGenotype, int sizeAllelo, Individual typePopulation){
        this(sizePopulation, sizeGenome, sizeGenotype, sizeAllelo, typePopulation, true);
    }
    
    /**
     * Construtor idêntico ao anterior, mas com a opção de criar uma população aleatória ou não.
     * @param sizePopulation
     * @param sizeGenome
     * @param sizeGenotype
     * @param sizeAllelo
     * @param typePopulation
     * @param initializesPopulation 
     */
    public Population(int sizePopulation, int sizeGenome, int sizeGenotype, int sizeAllelo, Individual typePopulation, boolean initializesPopulation){
        this._sizePopulation    = sizePopulation;
        this._sizeGenome        = sizeGenome;
        this._sizeGenotype      = sizeGenotype;
        this._sizeAllelo        = sizeAllelo;
        this._typePopulation    = typePopulation;
        
        _population             = new ArrayList<Individual>(sizePopulation);
        
        //Se for para iniciar aleatoriamente a população( boolean = true)
        //chama-se a função inicializationPopulation()
        if(initializesPopulation)
            _inicializationPopulation();
    }
    
    /**
     * Inicialização de uma população de forma aleatória.
     */
    private void _inicializationPopulation(){
        for (int __indexIndividual = 0; __indexIndividual < this._sizePopulation; __indexIndividual++) {
            try {
                //é necessário utilizar reflection para criar um individuo, visto que este
                //é uma classe abstracta e seria necessário implementar todos os métodos
                //abstractos, uma vez que neste momento só temos individuos do tipo OnesMax.
                //ao utilizar reflection fica preparado para novas implementações de individuos
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
     * Método que vai buscar individuos individuos aleatoriamente á população.
     * O número de individuos é passado por parâmetro, e tem a opção de retirar o individuo
     * da população ou mantê-lo.
     * @param numberIndividual
     * @param removeIndividualFromPopulation
     * @return 
     */
    public ArrayList<Individual> getArrayIndividualsRandom(int numberIndividual, boolean removeIndividualFromPopulation){
        //array que vai guardas os individuos seleccionados de forma aleatoria
        final ArrayList<Individual> __newArrayIndividual = new ArrayList<Individual>(numberIndividual);
        //contador dos individuos selecionadas
        int __countIndividual = 0;
        //index do individuo
        int __indexIndividual;
        //total de individuos que o método devolve
        int __numberIndividualToReturn = numberIndividual;
        
        // Escolher de forma aleatoria varios individuos        
        while(__countIndividual < __numberIndividualToReturn){
            //escolhe um individuo aleatório da população
            __indexIndividual = Population.RANDOM_GENERATOR.nextInt(this.getSizePopulation() - 1);
            //adiciona uma cópia do individuo seleccionado ao novo array criado anteriormente
            __newArrayIndividual.add(this.getPopulation().get(__indexIndividual).clone());
            //verifica se o individuo é para remover ou não da população original
            if(removeIndividualFromPopulation){
                this._population.remove(this.getPopulation().get(__indexIndividual));
            }
            //incremente o contador
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
            __output.append(__individual.fitness());
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
    
    /**
     * Incrementa uma geração a todos os individuos da população
     */
    public void incrementAgePopulation(){
        for (Individual __individual : this) {
            __individual.incrementAge();
        }
    }
    
    /**
     * Reinicia a idade do individuo.
     * Exemplo:quando se cria um novo individuo(reprodução)
     */
    public void resetAgePopulation(){
        for (Individual __individual : this) {
            __individual.setAgeIndividual(0);
        }
    }

    /**
     * Percorre a população toda e devolve o melhor fitness encontrado
     * @return 
     */
    public int getBestFitness() {
        int __bestFitness = 0;
        for (Individual __individual : this) {
            if(__bestFitness < __individual.fitness())
                __bestFitness = __individual.fitness();
        }
        return __bestFitness;
    }
    
    /**
     * Devolve os melhores individuos de uma população
     * @param sizeHallOfFame - número de individuos a devolver
     * @return 
     */
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
