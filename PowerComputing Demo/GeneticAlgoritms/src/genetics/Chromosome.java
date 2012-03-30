package genetics;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * Classe que representa um cromossoma. Neste momento existe apenas um cromossoma,
 * mas está preparada para no futuro se fazer a implementação de mais.
 * Recebe como parâmetro a variável typeIndividual, que representa o tipo do individuo(ex:boolean)
 * É composta por um ArrayList de elementos do tipo Gene, com o nome genotype.
 * Implementa a interface Iterable, que permite que o cromossoma consiga directamente
 * devolver o array genotype, que permite com um ciclo for aceder directamente a 
 * cada gene.
 * @author goncalo
 */
public class Chromosome implements Iterable<Gene> {

    private ArrayList<Gene> _genotype;
    //é necessário ter um individuo, porque é este que guarda a variável que contém o tipo do gene
    private Individual _individual;

    /**
     * Construtor por defeito que passa o tipo de individuo
     * @param typeIndividual 
     */
    public Chromosome(Individual typeIndividual) {
        this._individual    = typeIndividual;
        this._genotype      = new ArrayList<Gene>(typeIndividual.getSizeGenotype());
        
        this._inicializationGenotype();
    }
    /**
     * Construtor que faz a cópia de um novo cromossoma
     * @param newChromosome 
     */
    public Chromosome(Chromosome newChromosome) {
        this._genotype = new ArrayList<Gene>(newChromosome.getGenotype().size());
        //for each que através do iterador permite aceder a todos os genes.
        //não é necessário utilizar o ciclo for com os sets e gets
        for (Gene __gene : newChromosome) {
            this._genotype.add(new Gene(__gene));
        }
        this._individual = newChromosome.getIndividual();
    }
    
    /**
     * Inicializa os genes e passa como parametro qual o tipo e os valores que o gene vai ter
     */
    private void _inicializationGenotype() {
        for (int __indexGene = 0; __indexGene < this.getIndividual().getSizeGenotype(); __indexGene++) {
             this.getGenotype().add(new Gene(getIndividual().inicializationAllelo()));
        }
    }
    
    public Gene getGene(int index){
        return getGenotype().get(index);
    }
    
    public void setGene(Gene gene){
        getGenotype().add(gene);
    }

    @Override
    public Iterator<Gene> iterator() {
        return this.getGenotype().iterator();
    }
    
    /**
     * Método que devolve o valor guardado dentro do cromossoma
     * @return 
     */
    @Override
    public String toString() {
        //Optimização que ao criar uma nova string, é reservado automaticamente
        //um espaço em memória que fica disponível para adicionar novos caracteres futuramente.
        //Como já tem espaço reservado em memória, ao adicionar novos dados já não é preciso reservar espaço novamente
        StringBuilder __output = new StringBuilder();
        
        for (Gene __gene : this) {
            __output.append(__gene.toString());
        }
        
        return __output.toString();
    }

    /**
     * @return the _genotype
     */
    public ArrayList<Gene> getGenotype() {
        return _genotype;
    }

    /**
     * @param genotype the _genotype to set
     */
    public void setGenotype(ArrayList<Gene> genotype) {
        this._genotype = genotype;
    }

    /**
     * @return the _individual
     */
    public Individual getIndividual() {
        return _individual;
    }

    /**
     * @param individual the _individual to set
     */
    public void setIndividual(Individual individual) {
        this._individual = individual;
    }
}
