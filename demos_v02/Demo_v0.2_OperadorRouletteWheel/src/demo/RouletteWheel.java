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
 *
 */
package demo;

import java.util.ArrayList;
import java.util.Arrays;
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
        for (Individual individual : getPopulation()) {
            _totalFitness += individual.getFitness();
        }
    }

    private void calculateProbabilities() {

        double __cumulativeProbability = 0;
        sumFitness();
        Collections.sort(getPopulation());
        for (Individual individual : getPopulation()) {
            individual.setPercentage(__cumulativeProbability += (individual.getFitness() / (double) getTotalFitness()));
        }
    }

    private void selection() {
        double __rand;
        calculateProbabilities();
        for (int i = 0; i < getOffspringSize(); i++) {
            __rand = RANDOM_GENERATOR.nextDouble();
            for (Individual individual : getPopulation()) {
                if (__rand < individual.getPercentage()) {
                    getOffspring().add(individual);
                    break;
                }
            }

        }
    }

    private void selectionAndPrintToConsole() {
        selection();
        StringBuilder __output = new StringBuilder(30);
        __output.append("Population      = ");
        __output.append(getPopulation());
        __output.append("\r\n");
        __output.append("Total fitness   = ");
        __output.append(getTotalFitness());
        __output.append("\r\n");
        __output.append("Offspring       = ");
        __output.append(getOffspring());
        System.out.println(__output);
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

    /**
     * @return the _offspring
     */
    public ArrayList<Individual> getOffspring() {
        return _offspring;
    }

    /**
     * @return the _totalFitness
     */
    public int getTotalFitness() {
        return _totalFitness;
    }

    /**
     * @return the _offspringSize
     */
    public int getOffspringSize() {
        return _offspringSize;
    }

    public static void main(String[] args) {

        RouletteWheel op = new RouletteWheel(6, 8, 4);
        op.selectionAndPrintToConsole();

    }
}
