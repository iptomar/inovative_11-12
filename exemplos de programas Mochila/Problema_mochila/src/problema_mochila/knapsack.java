/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package problema_mochila;
import java.util.Random;
/**
 *
 * @author mendes
 */
/**
*
*
* Compilation:  javac Knapsack.java
*  Execution:    java Knapsack N P W
*
*  Generates an instance of the 0/1 knapsack problem with N items,
*  profits between 0 and P-1, weights between 0 and W-1,
*  and solves it in O(NW) using dynamic programming.
*
*  %  java Knapsack 6 1000 2000
*  item    profit  weight  take
*  1       874     580     true
*  2       620     1616    false
*  3       345     1906    false
*  4       369     1942    false
*  5       360     50      true
*  6       470     294     true
*
*************************************************************************/
public class knapsack {
   
    private static final Random rnd = new Random();
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);   // number of items
        int P = Integer.parseInt(args[1]);   // maximum profit
        int W = Integer.parseInt(args[2]);   // maximum weight
        int[] profit = new int[N+1];
        int[] weight = new int[N+1];
 
        // generate random instance, items 1..N
        for (int n = 1; n <= N; n++) {
            profit[n] = rnd.nextInt(P);
            weight[n] = rnd.nextInt(W);
        }
 
        // opt[n][w] = max profit of packing items 1..n with weight limit w
        // sol[n][w] = does opt solution to pack items 1..n with weight limit w include item n?
        int[][] opt = new int[N+1][W+1];
        boolean[][] sol = new boolean[N+1][W+1];
 
        for (int n = 1; n <= N; n++) {
            for (int w = 1; w <= W; w++) {
 
                // don't take item n
                int option1 = opt[n-1][w];
 
                // take item n
                int option2 = Integer.MIN_VALUE;
                if (weight[n] <= w) option2 = profit[n] + opt[n-1][w-weight[n]];
 
                 // select better of two options
                opt[n][w] = Math.max(option1, option2);
                sol[n][w] = option2 > option1;
            }
        }
 
        // determine which items to take
        boolean[] take = new boolean[N+1];
        for (int n = N, w = W; n > 0; n--) {
            if (sol[n][w]) { take[n] = true;  w = w - weight[n]; }
            else           { take[n] = false;                    }
        }
 
        // print results
        System.out.println("item" + "\t" + "profit" + "\t" + "weight" + "\t" + "take");
        for (int n = 1; n <= N; n++)
            System.out.println(n + "\t" + profit[n] + "\t" + weight[n] + "\t" + take[n]);
        System.out.println(); 
    } 
}
