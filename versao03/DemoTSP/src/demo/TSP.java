/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author diogoantonio
 */
public class TSP {

    static final Random RANDOM_GENERATOR = new Random();

    static String individualToString(int[] individual) {
        StringBuilder __individualString = new StringBuilder();
        for (int i = 0; i < individual.length; i++) {
            __individualString.append(individual[i]).append("\t");
        }
        return __individualString.toString();
    }

    static String costMatrixToString(int[][] costMatrix) {
        StringBuilder __costMatrixString = new StringBuilder();
        for (int i = 0; i < costMatrix.length; i++) {
            for (int j = 0; j < costMatrix.length; j++) {
                __costMatrixString.append(costMatrix[i][j]).append(" ");

            }
            __costMatrixString.append("\n");
        }
        return __costMatrixString.toString();
    }

    static int[] generateRandomIndividual(int size) {
        int[] __newIndividual = new int[size];
        // fill individual width indexes between 0 and size
        for (int i = 0; i < __newIndividual.length; i++) {
            __newIndividual[i] = i;
        }
        int max = __newIndividual.length - 1;
        while (max > 0) {
            int index = RANDOM_GENERATOR.nextInt(max);
            int aux = __newIndividual[index];
            __newIndividual[index] = __newIndividual[max];
            __newIndividual[max] = aux;
            max--;
        }
        return __newIndividual;
    }

    static int[][] generatoRandomCostMatrix(int maxCost, int numPoints) {
        int[][] __newCostMatrix = new int[numPoints][numPoints];
        for (int i = 0; i < numPoints; i++) {
            // the cost of a point to itself is 0 (same place)
            __newCostMatrix[i][i] = 0;
            for (int j = i + 1; j < numPoints; j++) {
                // generates a upper triangular matrix, and then mirror to the lower triangular matrix
                __newCostMatrix[i][j] = __newCostMatrix[j][i] = RANDOM_GENERATOR.nextInt(maxCost);
            }
        }
        return __newCostMatrix;
    }

    static int[] individualRepairs(int[] individual) {
        int[] __newIndividual = new int[individual.length];
        int __zeroIndex = 0;
        // search for position of 0
        for (int i = 0; i < individual.length; i++) {
            if (individual[i] == 0) {
                __zeroIndex = i;
                break;
            }
        }
        for (int i = 0; i < individual.length; i++) {
            // rotating vector from the zero position
            // example: [2,4,0,3] -> [2,4|0,3] -> [0,3,2,4]
            __newIndividual[i] = individual[(i + __zeroIndex) % individual.length];
        }
        return __newIndividual;
    }

    static int calculateFitness(int[] individual, int[][] costMatrix) {
        // starting point
        int __fitness = 0;
        for (int i = 1; i <= individual.length; i++) {
            // adds to fitness the cost of traveling between points (in the order expressed in individual)
            __fitness += costMatrix[individual[i - 1]][individual[i % individual.length]];
        }
        return __fitness;
    }

    static ArrayList<int[]> selection(ArrayList<int[]> pop, int numIndividuals, int[][] costMatrix) {
        ArrayList<int[]> __selectedIndividuals = new ArrayList<int[]>();
        for (int i = 0; i < numIndividuals; i++) {
            int index1 = RANDOM_GENERATOR.nextInt(pop.size());
            int index2 = RANDOM_GENERATOR.nextInt(pop.size());
            int fitness1 = calculateFitness(pop.get(index1), costMatrix);
            int fitness2 = calculateFitness(pop.get(index2), costMatrix);
            if (fitness1 < fitness2) {
                __selectedIndividuals.add(pop.get(index1));
            } else {
                __selectedIndividuals.add(pop.get(index2));
            }

        }
        return __selectedIndividuals;
    }

//    static void orderCrossover(int[] t1, int[] t2) {
//        int[] s1 = new int[t1.length];
//        int[] s2 = new int[t2.length];
//        System.arraycopy(t1, 0, s1, 0, s1.length);
//        System.arraycopy(t2, 0, s2, 0, s2.length);
//
//        int cut = RANDOM_GENERATOR.nextInt(t1.length - 1) + 1;
//        //fazer o primeiro individuos
//        int index1 = cut;
//        int index2 = cut;
//        while (index1 < t1.length) {
//            //verificR SE O GEne ja  existe
//            if (getIndexOf(s1, s2[index2]) > index1) {
//                t1[index1] = s2[index2];
//                index1++;
//            }
//            index2 = (index2 + 1) % t2.length;
//        }
//        index1 = cut;
//        index2 = cut;
//        while (index2 < t2.length) {
//            //verificR SE O GEne ja  existe
//            if (getIndexOf(s2, s1[index2]) > index2) {
//                t2[index1] = s1[index2];
//                index1++;
//            }
//            index2 = (index2 + 1) % t2.length;
//        }
//
//
//    }
    static ArrayList<int[]> orderCrossover(int[] pai, int[] mae) {
        ArrayList<int[]> filhos = new ArrayList<int[]>();
        int[] filho = new int[pai.length];
        int[] filha = new int[mae.length];
//dim := tamanho(pai.genes[])
        int dim = pai.length;
//corte := aleatorio([1;dim-1])
        int corte = RANDOM_GENERATOR.nextInt(dim - 2) + 1;
//para i := 0 atÈ i < corte 
//	filho.gene[i] := pai.gene[i]
//	filha.gene[i] := mae.gene[i]
//	i := i+1

        for (int i = 0; i < corte; i++) {
            filho[i] = pai[i];
            filha[i] = mae[i];
        }

//para J := corte atÈ j < dim
//	filho.gene[j] := procura(j, mae.gene[], filho.gene[])
//	filha.gene[j] := procura(j, pai.gene[], filha.gene[])
//	j := j+1
//
//sai(Individuo filho, Individuo filha)
        for (int i = corte; i < dim; i++) {
            pai[i] = procura(i, pai, filho);
            mae[i] = procura(i, pai, filha);
        }
        filhos.add(filho);
        filhos.add(filha);
        return filhos;

    }

//procura(pos, progenitor.gene[], descendente.gene[])
    static int procura(int pos, int[] progenitor, int[] descendente) {
        //p := pos-1
        int p = pos - 1;
        //q := pos
        int q = pos;
        //enquanto (p >= 0)
        while (p >= 0) {
            //se (descendente.gene[p] = progenitor.gene[q]) entao
            if (descendente[p] == progenitor[p]) {
                //q := q+1
                q++;
                //q := q%tamanho(progenitor.gene[])
                q = q % progenitor.length;
                //p := pos
                p = pos;
            }
            //p := p-1
            p--;
        }

        //retorna progenitor.gene[q]
        return progenitor[q];
    }



    public static void main(String[] args) {

        /**
         * CONFIG
         */
        int numIndividuals = 10;
        int individualSize = 10;
        int numSelectedIndividuals = 5;
        int maxCostPerIndividual = 100000;
        //*********************

        int[][] costMatrix = generatoRandomCostMatrix(maxCostPerIndividual, numIndividuals);

        ArrayList<int[]> initialPopulation = new ArrayList<int[]>();

        for (int i = 0; i < numIndividuals; i++) {
            initialPopulation.add(individualRepairs(generateRandomIndividual(individualSize)));
        }



    }
}
