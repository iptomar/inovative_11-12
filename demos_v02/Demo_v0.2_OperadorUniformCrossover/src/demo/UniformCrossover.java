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

import java.util.Arrays;
import java.util.Random;

/**
 * Demo of the crossover operator UniformCrossover.
 * Bits are randomly copied from the first or from the second parent. 
 * @author diogoantonio
 */
public class UniformCrossover {

    public static final Random RANDOM_GENERATOR = new Random();
    static int DEFAULT_INDIVIDUAL_SIZE = 10;
    // we need 2 individuals for crossover
    private int[] _parentA;
    private int[] _parentB;
    // after crossover
    private int[] _offspringA;
    private int[] _offspringB;
    // for each one in the mask, there will exchange of bits between father and mother
    private int[] _mask;

    /**
     * 
     * @param individualSize size of the parents and offspring
     */
    public UniformCrossover(int individualSize) {
        _parentA = generateIndividual(individualSize);
        _parentB = generateIndividual(individualSize);
        _offspringA = new int[individualSize];
        _offspringB = new int[individualSize];
        _mask = generateMask(individualSize, 50);
    }

    /**
     * 
     * @param parentA
     * @param parentB
     * @param maskProbability probability to be zeros in the mask
     */
    public UniformCrossover(int[] parentA, int[] parentB, int maskProbability) {
        _parentA = parentA;
        _parentB = parentB;
        _mask = generateMask(parentA.length, maskProbability);
    }

    /**
     * 
     * @param individualSize size of the parents and offspring
     * @param maskProbability probability to be zeros in the mask
     */
    public UniformCrossover(int individualSize, int maskProbability) {
        _parentA = generateIndividual(individualSize);
        _parentB = generateIndividual(individualSize);
        _offspringA = new int[individualSize];
        _offspringB = new int[individualSize];
        _mask = generateMask(individualSize, maskProbability);
    }

    /**
     * 
     */
    public UniformCrossover() {
        this(DEFAULT_INDIVIDUAL_SIZE);
    }

    /**
     * Generates a random mask.
     * @param size size of the generated individual
     * @param onesProbability defines the probability of genes "1". If oneProbability = 50, same probability for "0" and "1" genes. If oneProbability = 75, "1" genes probability is 75 and "0" genes probability = 25.
     * @return 
     */
    private int[] generateMask(int size, int onesProbability) {
        int[] __newMask = new int[size];
        for (int i = 0; i < size; i++) {
            __newMask[i] = ((RANDOM_GENERATOR.nextInt(100) < onesProbability) ? 1 : 0);
        }
        return __newMask;
    }

    /**
     * Generates a new random Individual.
     * @param size size of the generated individual
     * @return 
     */
    private int[] generateIndividual(int size) {
        int[] __newIndividual = new int[size];
        for (int i = 0; i < size; i++) {
            __newIndividual[i] = RANDOM_GENERATOR.nextInt(2);
        }
        return __newIndividual;
    }

    /**
     * Do crossover. When a "1" if found in the mask, the gene of parent A is copied to offspring B and vice versa. When a "0" if found in the mask, the gene of parent A is copied to offspring A and and the same for parent B.
     */
    public void crossover() {
        for (int i = 0; i < _parentA.length; i++) {
            if (getMask()[i] == 1) {
                _offspringA[i] = _parentB[i];
                _offspringB[i] = _parentA[i];
            } else {
                _offspringA[i] = _parentA[i];
                _offspringB[i] = _parentB[i];
            }

        }
    }
    
    /**
     * Do crossover. When a "1" if found in the mask, the gene of parent A is copied to offspring B and vice versa. When a "0" if found in the mask, the gene of parent A is copied to offspring A and and the same for parent B. The parenthood, the mask and the offspring are printed to the console.
     */
    public void crossoverAndPrintOnConsole() {
        crossover();
        StringBuilder __output = new StringBuilder(30);
        __output.append("Parent A    = ");
        __output.append(Arrays.toString(getParentA()));
        __output.append("\r\n");
        __output.append("Parent B    = ");
        __output.append(Arrays.toString(getParentB()));
        __output.append("\r\n");
        __output.append("Mask        = ");
        __output.append(Arrays.toString(getMask()));
        __output.append("\r\n");
        __output.append("Offspring A = ");
        __output.append(Arrays.toString(getOffspringA()));
        __output.append("\r\n");
        __output.append("Offspring B = ");
        __output.append(Arrays.toString(getOffspringB()));
        System.out.println(__output);
    }

    /**
     * @return the _parentA
     */
    public int[] getParentA() {
        return _parentA;
    }

    /**
     * @param parentA the _parentA to set
     */
    public void setParentA(int[] parentA) {
        this._parentA = parentA;
    }

    /**
     * @return the _parentB
     */
    public int[] getParentB() {
        return _parentB;
    }

    /**
     * @param parentB the _parentB to set
     */
    public void setParentB(int[] parentB) {
        this._parentB = parentB;
    }

    /**
     * @return the _offspringA
     */
    public int[] getOffspringA() {
        return _offspringA;
    }

    /**
     * @return the _offspringB
     */
    public int[] getOffspringB() {
        return _offspringB;
    }

    /**
     * @return the _mask
     */
    public int[] getMask() {
        return _mask;
    }

    /**
     * @param mask the _mask to set
     */
    public void setMask(int[] mask) {
        this._mask = mask;
    }

    public static void main(String[] args) {
        UniformCrossover op = new UniformCrossover();
        op.crossoverAndPrintOnConsole();
    }
}
