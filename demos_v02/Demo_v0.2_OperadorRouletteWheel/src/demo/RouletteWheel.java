/*
 *
 * # #    # #    #  ####  #    #   ##   ##### # #    # ###### 
 * # ##   # ##   # #    # #    #  #  #    #   # #    # #      
 * # # #  # # #  # #    # #    # #    #   #   # #    # #####  
 * # #  # # #  # # #    # #    # ######   #   # #    # #      
 * # #   ## #   ## #    #  #  #  #    #   #   #  #  #  #      
 * # #    # #    #  ####    ##   #    #   #   #   ##   ###### 
 *                                                           
 *
 *                       
 * #    # # #    # #####  
 * ##  ## # ##   # #    # 
 * # ## # # # #  # #    # 
 * #    # # #  # # #    # 
 * #    # # #   ## #    # 
 * #    # # #    # #####  
 *
 * 
 * ======
 * Meta
 * ======

 * project: Life Inspiration
 * version: 0.2
 * status:  stable
 *
 * ======
 * Docs
 * ======
 * 
 * Soon...
 * 
 * 
 */
package demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author diogoantonio
 */
public class RouletteWheel {

    private static final Random RANDOM_GENERATOR = new Random();
    public static int DEFAULT_POPULATION_SIZE = 10;
    public static int DEFAULT_FITNESS_MAX = 20;
    public static int DEFAULT_OFFSPRING_SIZE = 5;
    private ArrayList<Individual> _population;
    private ArrayList<Individual> _offspring;
    private int _totalFitness;
    private int _offspringSize;

    public RouletteWheel(int aPopulationSize, int aFitnessMax, int aOffspringSize) {
        _population = generatePopulation(aPopulationSize, aFitnessMax);
        _offspring = new ArrayList<Individual>();
        _totalFitness = 0;
        _offspringSize = aOffspringSize;
    }

    public RouletteWheel() {
        this(DEFAULT_POPULATION_SIZE, DEFAULT_FITNESS_MAX, DEFAULT_OFFSPRING_SIZE);
    }

    private ArrayList<Individual> generatePopulation(int aPopulationSize, int aFitnessMax) {
        ArrayList<Individual> __newPopulation = new ArrayList<Individual>();
        char __alphabet = 'A';
        for (int i = 0; i < aPopulationSize; i++) {
            __newPopulation.add(new Individual(String.valueOf(__alphabet++), RANDOM_GENERATOR.nextInt(aFitnessMax), 0));
        }
        return __newPopulation;
    }

    private void sumFitness() {
        for (Individual individual : _population) {
            _totalFitness += individual.getFitness();
        }
    }

    private void calculateProbabilities() {

        double __cumulativeProbability = 0;
        sumFitness();
        Collections.sort(_population);
        for (Individual individual : _population) {
            individual.setPercentage(__cumulativeProbability += (individual.getFitness() / (double) _totalFitness));
        }
    }

    private void selection() {
        double __rand;
        calculateProbabilities();
        for (int i = 0; i < _offspringSize; i++) {
            __rand = RANDOM_GENERATOR.nextDouble();
            for (Individual individual : _population) {
                if (__rand < individual.getPercentage()) {
                    _offspring.add(individual);
                    break;
                }
            }

        }
        System.out.println(_population);
        System.out.println(_offspring);

    }

    public static void main(String[] args) {

        RouletteWheel op = new RouletteWheel(6, 8, 4);
        op.selection();

    }
}
