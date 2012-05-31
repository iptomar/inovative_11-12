/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import RealCoded.Problem;
import RealCoded.Particle;
import java.util.ArrayList;

/**
 *
 * @author manso
 */
public class PSO {
    
    /**
     * Create random population
     * @param template type of individuals int the population
     * @param size size of population
     * @return population with random individuals 
     */
    public static ArrayList<Particle> createRandom(Problem template, int size) {
        ArrayList<Particle> pop = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Particle newProblem = new Particle(template);
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
    public static Problem getBestIndividual(ArrayList<Particle> pop) {
        //best is the first individuals
        Problem best = pop.get(0).myBest;
        //verify the others individuals
        for (int i = 1; i < pop.size(); i++) {
            // if individual i is better than the current best
            if (best.compareTo(pop.get(i).myBest) < 0) {
                best = pop.get(i).myBest;
            }
        }
        //return best individual
        return best;
    }
    
    /**
 * Solve the problem
 * @param Iterations number of iterations
 * @param sizeOfPopulation size of population 
 * @param problem problem to solve
 * @return 
 */
    public Problem solve(int Iterations, int sizeOfPopulation, Problem problem) {
        ArrayList<Particle> population;
        //create random population
        population = createRandom(problem, sizeOfPopulation);
        //get best individual
        Problem best = getBestIndividual(population);
        //execute the iterations
        for (int itera = 0; itera < Iterations; itera++) {
            //performs evolution in the population
            for (Particle p : population) {
                p.evolve(best);
                if( best.compareTo( p.myBest) < 0)
                    best = p.myBest;
            }//next particle
        }//next iteration
        //get the best individual of the population
        return best;
    }
    
}
