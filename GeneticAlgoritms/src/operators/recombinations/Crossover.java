/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.recombinations;

import genetics.Individual;
import genetics.Population;
import java.util.Random;

/**
 *
 * @author Chorinca-Notebook
 */
public class Crossover extends Recombination {

    static final Random random = new Random();
    static final int NUMBER_CUTS_DEFAULT = 1;
    private int _numberCuts = Crossover.NUMBER_CUTS_DEFAULT;

    @Override
    public Population execute(Population population) {
        Population children = null;
        int dimPop = population.getSizePopulation();
        int i = 0, xy = 0, yy = 0, cut = -1;
        int dimGenome = population.getSizeGenome();
        Individual father = null, mother = null, son = null, daughter = null;
        while (i < dimPop) {
            i += 2;
            while ((xy = random.nextInt(dimPop)) == (yy = random.nextInt(dimPop)));
            father = population.getIndividual(xy);
            mother = population.getIndividual(yy);
            cut = random.nextInt(dimGenome - 1) + 1;
            for (int j = 0; j < dimGenome; j++) {
                if (j < cut) {
                    son.setChromosome(j, father.getChromosome(j));
                    daughter.setChromosome(j, mother.getChromosome(j));
                } else {
                    son.setChromosome(j, mother.getChromosome(j));
                    daughter.setChromosome(j, father.getChromosome(j));
                }
            }
            children.addIndividual(daughter);
            children.addIndividual(son);
        }
        return children;
    }
}
