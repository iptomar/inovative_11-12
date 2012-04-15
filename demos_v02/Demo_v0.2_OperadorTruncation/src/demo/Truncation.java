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

import java.util.Arrays;
import java.util.Random;

/**
 * Demo of the replacement operator Truncation.
 * 
 * @author diogoantonio
 */
public class Truncation {

    public static final Random RANDOM_GENERATOR = new Random();
    static int DEFAULT_POPULATION_SIZE = 10;
    static int DEFAULT_FITNESS_MAX = 100;
    // array that represents a population, each element is an individual and the value of each index is the fitness of the individual
    private int[] _populationA;
    private int[] _populationB;
    // reunion of the _populationA and _populationB, both populations together
    private int[] _populationReunion;
    // array that represents the offspring, wich is a new generation of _populationA and _populationB, composed with fittest individuals found
    private int[] _offspring;

    
    public Truncation(int size, int fitnessMax) {
        _populationA = generatePopulation(size, fitnessMax);
        _populationB = generatePopulation(size, fitnessMax);
        _populationReunion = new int[_populationA.length + _populationB.length];
        _offspring = new int[_populationA.length];
    }

    public Truncation(int[] _populationA, int[] _populationB) {
        this._populationA = _populationA;
        this._populationB = _populationB;
        _populationReunion = new int[_populationA.length + _populationB.length];
        _offspring = new int[_populationA.length];
    }
   
    public Truncation() {
        this(DEFAULT_POPULATION_SIZE,DEFAULT_FITNESS_MAX);
    }

    private int[] generatePopulation(int size, int fitnessMax) {
        int[] __newPopulation = new int[size];
        for (int i = 0; i < size; i++) {
            __newPopulation[i] = RANDOM_GENERATOR.nextInt(fitnessMax);
        }
        return __newPopulation;
    }

    private void invertArray(int[] array){
        int[] aux = array.clone();
        int index = 0;
        for (int x : aux) {
            array[index] = aux[(aux.length-1)-index];
            index++;
        }
    }
    
    public void replace() {
        // auxiliar variable, point to each __populationReunion index sequentially
        int index = 0;
        // put each individual (represented by x) of _populationA in __populationReunion
        for (int x : getPopulationA()) {
            _populationReunion[index] = x;
            index++;
        }
        // put each individual (represented by x) of _populationB in __populationReunion
        for (int x : getPopulationB()) {
            _populationReunion[index] = x;
            index++;
        }
        // order individuals in ascending fitness order, using quicksort (n*log(n))
        Arrays.sort(_populationReunion);
        // invert individuals fitness order (descending order)
        invertArray(_populationReunion);
        // select first individuals in _populationReunion (wich are the fittest individuals)
        _offspring = Arrays.copyOfRange(get_populationReunion(), 0, getPopulationA().length);
    }

    public void replaceAndPrintToConsole() {
        replace();
        StringBuilder __output = new StringBuilder(30);
        __output.append("Population A           = ");
        __output.append(Arrays.toString(getPopulationA()));
        __output.append("\r\n");
        __output.append("Population B           = ");
        __output.append(Arrays.toString(getPopulationB()));
        __output.append("\r\n");
        __output.append("Reunion of populations = ");
        __output.append(Arrays.toString(get_populationReunion()));
        __output.append("\r\n");
        __output.append("Offspring              = ");
        __output.append(Arrays.toString(getOffspring()));
        System.out.println(__output);
    }

    /**
     * @return the _populationA
     */
    public int[] getPopulationA() {
        return _populationA;
    }

    /**
     * @param populationA the _populationA to set
     */
    public void setPopulationA(int[] populationA) {
        this._populationA = populationA;
    }

    /**
     * @return the _populationB
     */
    public int[] getPopulationB() {
        return _populationB;
    }

    /**
     * @param populationB the _populationB to set
     */
    public void setPopulationB(int[] populationB) {
        this._populationB = populationB;
    }

    /**
     * @return the _offspring
     */
    public int[] getOffspring() {
        return _offspring;
    }

    /**
     * @param offspring the _offspring to set
     */
    private void setOffspring(int[] offspring) {
        this._offspring = offspring;
    }

    /**
     * @return the __populationReunion
     */
    public int[] get_populationReunion() {
        return _populationReunion;
    }
    
    
    public static void main(String[] args) {
        Truncation op = new Truncation();
        op.replaceAndPrintToConsole();
    }
}
