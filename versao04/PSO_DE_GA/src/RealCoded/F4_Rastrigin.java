/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RealCoded;

import java.util.StringTokenizer;

/**
 *
 * @author manso
 */
public class F4_Rastrigin extends Problem {
    //define domain of Genes
    static {
        MIN = -5;
        MAX = 5;
    }
    static int DIMENSION = 2;

    public F4_Rastrigin() {
        super(DIMENSION);
    }

    @Override
    protected double calculateFunction() {
        double xi;
        double sum = 0;
        for (int i = 0; i < value.length; i++) {
            xi = value[i];
            sum = sum + (Math.pow(xi, 2) - 10 * Math.cos(2 * Math.PI * xi) + 10);
        }
        return sum;
    }
    
     @Override
    public int compareTo(Problem o) {
        if (functionValue > o.functionValue) {
            return -1;
        }
        if (functionValue < o.functionValue) {
            return 1;
        }
        return 0;
    }
}
