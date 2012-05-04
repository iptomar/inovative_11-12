/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.recombinations;

import genetics.Individual;
import genetics.Population;
import operators.Genetic;

/**
 *
 * @author Chorinca-Notebook
 */
public class Crossover2 extends Recombination {

    @Override
    public Population execute(Population population) {
        final Population __newPopulation = 
                new Population(
                    population.getSizePopulation(), 
                    population.getSizeGenome(), 
                    population.getSizeGenotype(),
                    population.getSizeAllelo(),
                    population.getTypePopulation(), 
                    false);
        
        // o ciclo anda de 2 em 2 para trazer sempre dois pais
        for (int __indexParents = 0; __indexParents < population.getSizePopulation(); __indexParents = __indexParents + 2) {
           //pointOfCutAllelo gerador aleatório da população
            int __pointOfCutAllelo = Genetic.RANDOM_GENERATOR.nextInt(__newPopulation.getSizeAllelo() - 1) + 1;

            Boolean[] __father = (Boolean[])population.getIndividual(__indexParents).getGenome().get(0).getGene(0).getAllele();  
            Boolean[] __mother = (Boolean[])population.getIndividual(__indexParents + 1).getGenome().get(0).getGene(0).getAllele();

            Boolean[] __son = new Boolean[__newPopulation.getSizeAllelo()];
            Boolean[] __daughter = new Boolean[__newPopulation.getSizeAllelo()];
            //
            for (int __indexAlleloValuesFather = 0; __indexAlleloValuesFather < __newPopulation.getSizeAllelo(); __indexAlleloValuesFather++) {
                if(__indexAlleloValuesFather < __pointOfCutAllelo - 1)
                    __son[__indexAlleloValuesFather] = __father[__indexAlleloValuesFather];
                else
                    __daughter[__indexAlleloValuesFather] = __father[__indexAlleloValuesFather];
            }

            for (int __indexAlleloValuesMother = 0; __indexAlleloValuesMother < __newPopulation.getSizeAllelo(); __indexAlleloValuesMother++) {
                if(__indexAlleloValuesMother < __pointOfCutAllelo - 1)
                    __daughter[__indexAlleloValuesMother] = __mother[__indexAlleloValuesMother];
                else
                    __son[__indexAlleloValuesMother] = __mother[__indexAlleloValuesMother];
            }

            Individual __sonIndividual = population.getIndividual(0).clone();
            // utiliza as caracteristicas do pai mas muda os valores do allelo
            __sonIndividual.getChromosome(0).getGene(0).setAllele((Object)__son);

            Individual __daughterIndividual = population.getIndividual(0).clone();
            // utiliza as caracteristicas do pai mas muda os valores do allelo
            __daughterIndividual.getChromosome(0).getGene(0).setAllele((Object)__daughter);

            __newPopulation.addIndividual(__sonIndividual);
            __newPopulation.addIndividual(__daughterIndividual);
        
        }
        
        return __newPopulation;
    }
    
}
