/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.mutation;

import Genetics.Chromosome;
import Genetics.Gene;
import Genetics.Individual;
import Genetics.Population;
/**
 *
 * @author Chorinca-Notebook
 */
public class Flipbit extends Mutation {

    @Override
    public Population execute(Population population) {
        
        //Population childs = new Population(population.getNumberIndividuals(), population.getNumberChromosomes(), population.getNumberGenes(), population.getTypeIndividual()); 
        for (int i = 0; i < population.getNumberIndividuals(); i++) {
            Individual ind_mutation = population.getIndividual(i);
            doFlipbit(ind_mutation,population);
        }
        
        
        
        return population;
    }
    
 public void doFlipbit(Individual ind_mutation,Population population){
     boolean mutation = false;
     Chromosome cromossome = ind_mutation.getChromosome(1);
     Gene gene = cromossome.getGene(1);
     Boolean[] allelo = (Boolean[]) gene.getAllele();
     //double bit = ; //este bit é o tamanho do vector de booleans do allelo, para percorrer todos os allelos
     for (int i = 0; i < bit; i++) {
         if(random.nextDouble() < probability){
             mutation = true;
            
             boolean a = allelo[0];
             
             
             if(allelo.equals(0)){
                 gene.setAllele(1);
             }
             else
                 gene.setAllele(0);
         }
             
             
         
     }
 }
    
}
