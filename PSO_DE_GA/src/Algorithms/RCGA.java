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
package Algorithms;

import RealCoded.Problem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author ZULU
 */
public class RCGA {

    protected static Random rnd = new Random();

    public static ArrayList<Problem> createRandom(Problem template, int size) {
        ArrayList<Problem> pop = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Problem newProblem = template.getClone();
            newProblem.fillRandom();
            pop.add(newProblem);
        }
        return pop;
    }

    public static ArrayList<Problem> tournament(ArrayList<Problem> p, int size) {
        ArrayList<Problem> selects = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Problem p1 = p.get(rnd.nextInt(p.size()));
            Problem p2 = p.get(rnd.nextInt(p.size()));
            if (p1.compareTo(p2) > 0) {
                selects.add(p1.getClone());
            } else {
                selects.add(p2.getClone());
            }
        }
        return selects;
    }

    public static ArrayList<Problem> CrossoverAX(ArrayList<Problem> parents, double probability) {
        double MIN = -0.5, MAX = 1.5;
        //new population to crossover individuals
        ArrayList<Problem> newChildren = new ArrayList<>();
        //number of crossovers
        int numberOfRecombinations = (int) (parents.size() * probability) / 2;
        for (int i = 0; i < numberOfRecombinations; i++) {
            //select random parents
            Problem p1 = parents.remove(rnd.nextInt(parents.size()));
            Problem p2 = parents.remove(rnd.nextInt(parents.size()));
            //make the childrens
            Problem c1 = p1.getClone();
            Problem c2 = p2.getClone();
            //change the genes of the childrens using AX crossover operator
            for (int gene = 0; gene < p1.getNumberOfGenes(); gene++) {
                //random [ -0.5 , 1.5 ]
                double alpha = rnd.nextDouble() * (MAX - MIN) + MIN;
                //get original value of genes
                double x1 = p1.getGeneValue(gene);
                double x2 = p2.getGeneValue(gene);
                //perform AX crossover
                c1.setGeneValue(gene, x1 * alpha + x2 * (1 - alpha));
                c2.setGeneValue(gene, x1 * (1 - alpha) + x2 * alpha);
            }
            //add childrens to the new population
            newChildren.add(c1);
            newChildren.add(c2);
        }
        //parents who did not reproduce
        newChildren.addAll(parents);
        //return crossover population
        return newChildren;
    }

    public static ArrayList<Problem> mutationGaussian(ArrayList<Problem> population, double probability) {
        //percentage of interval size
        double SIZE = 0.1;
        for (Problem p : population) {
            //change the genes using Gaussian distribution
            for (int gene = 0; gene < p.getNumberOfGenes(); gene++) {
                //verify the probability of mutatation
                if (rnd.nextDouble() < probability) {
                    //get original value of gene
                    double x = p.getGeneValue(gene);
                    //calulate mutation whith gaussian distribution
                    double mut = Problem.DIM * SIZE * rnd.nextGaussian();
//                    System.out.println("MUT " + mut);
                    //set gene mutation
                    p.setGeneValue(gene, x + mut);
                }
            }
        }
        //return population mutated
        return population;
    }

    protected ArrayList<Problem> replacement(ArrayList<Problem> parents, ArrayList<Problem> children) {
        //size of the original population
        int size = parents.size();
        //join parents and childrens
        parents.addAll(children);
        //select size individuals with tournament
        return tournament(parents, size);
    }

    public Problem solve(int Iterations, int sizeOfPopulations, Problem problem) {
        ArrayList<Problem> parents;
        ArrayList<Problem> children;
        //create random population
        parents = createRandom(problem, sizeOfPopulations);
        for (int i = 0; i < Iterations; i++) {
            //select the same number of parents
            children = tournament(parents, parents.size());
            //execute crossover with 75% of probability
            children = CrossoverAX(children, 0.75);
            //execute mutation with 25% of probability
            children = mutationGaussian(children, 0.25);
            //join parents and childrens
            parents = replacement(parents, children);
            //display best individual
            Collections.sort(parents);
//            System.out.println("Iteration : " + i + " = " + parents.get(parents.size() - 1));
        }
        //Sort Population
        Collections.sort(parents);
        //returns best individual (last)
        return parents.get(parents.size() - 1);
    }
}
