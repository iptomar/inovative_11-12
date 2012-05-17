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

package RealCoded;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ZULU
 */
public abstract class RealCodedProblem implements Comparable<RealCodedProblem> {

    protected abstract double calculateFitness();
    protected static Random rnd = new Random();
    public static double MIN = -1;
    public static double MAX = 1;
    public static double DIM = MAX - MIN;
    double fitness;
    double[] gene;

    public RealCodedProblem(int dim) {
        gene = new double[dim];
        fillRandom();
        evaluate();
    }

    public final void evaluate() {
        fitness = calculateFitness();
    }

    public final void fillRandom() {
        for (int i = 0; i < gene.length; i++) {
            gene[i] = MIN + rnd.nextDouble() * DIM;
        }
        evaluate();
    }

    public int getNumberOfGenes() {
        return gene.length;
    }

    public double[] getGenes() {
        return gene;
    }

    public double getGeneValue(int index) {
        return gene[index];
    }

    public void setGeneValue(int index, double value) {
        if (value < MIN) {
            value = MIN;
        }
        if (value > MAX) {
            value = MAX;
        }
        gene[index] = value;
        evaluate();
    }

    @Override
    public int compareTo(RealCodedProblem o) {
        if (fitness > o.fitness) {
            return 1;
        }
        if (fitness < o.fitness) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder( this.getClass().getSimpleName() +"(");
        str.append(Arrays.toString(gene));
        str.append(")=").append(fitness);
        return str.toString();
    }

    //deep clone using reflexion - need default constructor of of the objects
    public RealCodedProblem getClone() {
        try {
            //get default constructor
            Constructor co = this.getClass().getConstructor();
            //make tmp object with default constructor
            Object[] objectParam = null; //no parameters
            RealCodedProblem tmp = (RealCodedProblem) co.newInstance(objectParam);
            //deep copy of genes
            System.arraycopy(gene, 0, tmp.gene, 0, gene.length);
            //copy fitness
            tmp.fitness = fitness;
            return tmp;
        } catch (Exception ex) {
            Logger.getLogger(RealCodedProblem.class.getName()).log(Level.SEVERE, null, ex);
        }
        //something wrong appens
        return null;
    }
}
