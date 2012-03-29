package genetics;

import java.util.ArrayList;
import java.util.Iterator;

public class Chromosome implements Iterable<Gene> {

    private ArrayList<Gene> _genotype;
    private Individual _individual;

    public Chromosome(Individual typeIndividual) {
        this._individual    = typeIndividual;
        this._genotype      = new ArrayList<Gene>(typeIndividual.getSizeGenotype());
        
        this._inicializationGenotype();
    }
    
    public Chromosome(Chromosome newChromosome) {
        this._genotype = new ArrayList<Gene>(newChromosome.getGenotype().size());
        for (Gene __gene : newChromosome) {
            this._genotype.add(new Gene(__gene));
        }
        this._individual = newChromosome.getIndividual();
    }
    
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
    
    @Override
    public String toString() {
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
