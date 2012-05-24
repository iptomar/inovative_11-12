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
public abstract class Problem implements Comparable<Problem> {

    protected abstract double calculateFunction();
    
    protected static Random rnd = new Random();
    public static double MIN = -1;
    public static double MAX = 1;
    public static double DIM = MAX - MIN;
    
    protected double functionValue;
    public double[] value;

    public Problem(int dim) {
        value = new double[dim];
        fillRandom();
        evaluate();
    }

    public  void evaluate() {
        functionValue = calculateFunction();
    }

    public final void fillRandom() {
        for (int i = 0; i < value.length; i++) {
            value[i] = MIN + rnd.nextDouble() * DIM;
        }
        evaluate();
    }

    public int getNumberOfGenes() {
        return value.length;
    }

    public double getGeneValue(int index) {
        return value[index];
    }

    public void setGeneValue(int index, double value) {
        if (value < MIN) {
            value = MIN;
        }
        if (value > MAX) {
            value = MAX;
        }
        this.value[index] = value;
        evaluate();
    }

    @Override
    public int compareTo(Problem o) {
        if (functionValue > o.functionValue) {
            return 1;
        }
        if (functionValue < o.functionValue) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder( this.getClass().getSimpleName() +"(");
        str.append(Arrays.toString(value));
        str.append(")=").append(functionValue);
        return str.toString();
    }

    //deep clone using reflexion - need default constructor of of the objects
    public Problem getClone() {
        try {
            //get default constructor
            Constructor co = this.getClass().getConstructor();
            //make tmp object with default constructor
            Object[] objectParam = null; //no parameters
            Problem tmp = (Problem) co.newInstance(objectParam);
            //deep copy of genes
            System.arraycopy(value, 0, tmp.value, 0, value.length);
            //copy fitness
            tmp.functionValue = functionValue;
            return tmp;
        } catch (Exception ex) {
            Logger.getLogger(Problem.class.getName()).log(Level.SEVERE, null, ex);
        }
        //something wrong appens
        return null;
    }
}
