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
//criar un novo randel
    static final Random random = new Random();
    //meter o numero da variavel (numero de curtes por defeito )igualar a 1
    static final int NUMBER_CUTS_DEFAULT = 1;
    private int _numberCuts = Crossover.NUMBER_CUTS_DEFAULT;

    @Override
    
    public Population execute(Population population) {
        //meter a população e igualar a null
        Population children = null;
        //criar a dimensão da população (dimPop)
        int dimPop = population.getSizePopulation();
        int i = 0, xy = 0, yy = 0, cut = -1;
         //criar a dimensão do Genorme  (dimGenome) que vai ter o mesmo valor do dimPop
        int dimGenome = population.getSizeGenome();
       // criar os indivíduos (pai , mãe , filho e filha) e igualar a null
        Individual father = null, mother = null, son = null, daughter = null;
        
        while (i < dimPop) {
            i += 2;
            //trocra de chromosome
            while ((xy = random.nextInt(dimPop)) == (yy = random.nextInt(dimPop)));
            father = population.getIndividual(xy);
            mother = population.getIndividual(yy);
            //cut =divisão o cromossoma 
            cut = random.nextInt(dimGenome - 1) + 1;
            //dicidir o cromossoma 
            for (int j = 0; j < dimGenome; j++) {
                if (j < cut) {
                    //o pai da metade o sei cromossoma ao filho
                    son.setChromosome(j, father.getChromosome(j));
                    //o mãe da metade o sei cromossoma ao filha
                    daughter.setChromosome(j, mother.getChromosome(j));
                } else {
                    //o pai da metade o sei cromossoma ao filha
                    son.setChromosome(j, mother.getChromosome(j));
                     //o mãe da metade o sei cromossoma ao filha
                    daughter.setChromosome(j, father.getChromosome(j));
                }
            }
            children.addIndividual(daughter);
            children.addIndividual(son);
        }
        return children;
    }
}
