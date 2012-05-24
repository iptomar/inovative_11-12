///****************************************************************************/
///****************************************************************************/
///****     Copyright (C) 2012                                             ****/
///****     António Manuel Rodrigues Manso                                 ****/
///****     e-mail: manso@ipt.pt                                           ****/
///****     url   : http://orion.ipt.pt/~manso    manso@ipt.pt             ****/
///****     Instituto Politécnico de Tomar                                 ****/
///****     Escola Superior de Tecnologia de Tomar                         ****/
///****                                                                    ****/
///****************************************************************************/
///****     This software was build with the purpose of learning.          ****/
///****     Its use is free and is not provided any guarantee              ****/
///****     or support.                                                    ****/
///****     If you met bugs, please, report them to the author             ****/
///****                                                                    ****/
///****************************************************************************/
///****************************************************************************/
package Algorithms;

import RealCoded.Problem;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ZULU
 */
public class DE {

    //dimension of change [0,1]
    static double F = 0.5;
    //probability of crossover [0,1]
    static double CR = 0.9;
    static Random random = new Random();

    /**
     * Create random population
     * @param template type of individuals int the population
     * @param size size of population
     * @return population with random individuals 
     */
    public static ArrayList<Problem> createRandom(Problem template, int size) {
        ArrayList<Problem> pop = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Problem newProblem = template.getClone();
            newProblem.fillRandom();
            pop.add(newProblem);
        }
        return pop;
    }

    /**
     * get best individual in the population
     *
     * @param pop population
     * @return best individual
     */
    public static Problem getBestIndividual(ArrayList<Problem> pop) {
        //best is the first individuals
        Problem best = pop.get(0);
        //verify the others individuals
        for (int i = 1; i < pop.size(); i++) {
            // if individual i is better than the current best
            if (best.compareTo(pop.get(i)) < 0) {
                best = pop.get(i);
            }
        }
        //return best individual
        return best;
    }

    /**
     * Execute Differential evolution
     *
     * @param F Dimension of chchange
     * @param Cr Probability of crossover
     * @param x Individual do evolve
     * @param best Best individual in the population
     * @param p0 Random individual
     * @param p1 Random individual
     */
    public static void differentialEvolution(double F, double Cr, double[] x, double[] best, double[] p0, double[] p1) {
        //for all genes
        for (int i = 0; i < x.length; i++) {
            //change gene ?
            if ((random.nextDouble() < Cr)) {
                //change the gene
                x[i] = best[i] + F * (p0[i] - p1[i]);
            }
        }
    }
/**
 * Solve the problem
 * @param Iterations number of iterations
 * @param sizeOfPopulation size of population 
 * @param problem problem to solve
 * @return 
 */
    public Problem solve(int Iterations, int sizeOfPopulation, Problem problem) {
        ArrayList<Problem> population;
        //create random population
        population = createRandom(problem, sizeOfPopulation);
        //get best individual
        Problem best = getBestIndividual(population);
        //execute the iterations
        for (int itera = 0; itera < Iterations; itera++) {
            //pereforms Differential evolution in the population
            for (int current = 0; current < population.size(); current++) {
                //get current individual
                Problem pivot = population.get(current);
                //generate two random differents indexes 
                int index1, index2;
                do {
                    index1 = random.nextInt(population.size());
                    index2 = random.nextInt(population.size());
                } while (index1 != current && index1 != index2 && index2 != current);
                //get two random individuals
                Problem rnd1 = population.get(index1);
                Problem rnd2 = population.get(index2);
                //execute differential evolution in pivot
                differentialEvolution(F, CR, pivot.value, best.value, rnd1.value, rnd2.value);
                //evaluate new individual
                pivot.evaluate();
                //update best
                if (pivot.compareTo(best) > 0) {
                    best = pivot.getClone();
//                    System.out.println("Iteration " + itera + " " + best);
                }
            }//next individual
        }//next iteration
        //get the best individual of the population
        return best;
    }
}
