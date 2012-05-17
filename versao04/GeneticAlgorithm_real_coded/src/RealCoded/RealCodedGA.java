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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author ZULU
 */
public class RealCodedGA {

    protected static Random rnd = new Random();
    protected ArrayList<RealCodedProblem> parents;
    protected ArrayList<RealCodedProblem> children;

   public static  ArrayList<RealCodedProblem> createRandom(RealCodedProblem template, int size) {
        ArrayList<RealCodedProblem> pop = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            RealCodedProblem newProblem = template.getClone();
            newProblem.fillRandom();
            pop.add(newProblem);
        }
        return pop;
    }

    public static  ArrayList<RealCodedProblem> tournament(ArrayList<RealCodedProblem> p, int size) {
        ArrayList<RealCodedProblem> selects = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            RealCodedProblem p1 = p.get(rnd.nextInt(p.size()));
            RealCodedProblem p2 = p.get(rnd.nextInt(p.size()));
            if (p1.compareTo(p2) > 0) {
                selects.add(p1.getClone());
            } else {
                selects.add(p2.getClone());
            }
        }
        return selects;
    }

    public static ArrayList<RealCodedProblem> CrossoverAX(ArrayList<RealCodedProblem> parents, double probability) {
        double MIN = -0.5, MAX = 1.5;
        //new population to crossover individuals
        ArrayList<RealCodedProblem> newChildren = new ArrayList<>();
        //number of crossovers
        int numberOfRecombinations = (int) (parents.size() * probability) / 2;
        for (int i = 0; i < numberOfRecombinations; i++) {
            //select random parents
            RealCodedProblem p1 = parents.remove(rnd.nextInt(parents.size()));
            RealCodedProblem p2 = parents.remove(rnd.nextInt(parents.size()));
            //make the childrens
            RealCodedProblem c1 = p1.getClone();
            RealCodedProblem c2 = p2.getClone();
            //change the genes of the childrens using AX crossover operator
            for (int j = 0; j < p1.getNumberOfGenes(); j++) {
                //random [ -0.5 , 1.5 ]
                double alpha = rnd.nextDouble() * (MAX - MIN) + MIN;
                //get original value of genes
                double gene1 = p1.getGeneValue(j);
                double gene2 = p2.getGeneValue(j);
                //perform AX crossover
                c1.setGeneValue(j, gene1 * alpha + gene2 * (1 - alpha));
                c2.setGeneValue(j, gene1 * (1 - alpha) + gene2 * alpha);
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

    public static ArrayList<RealCodedProblem> mutationGaussian(ArrayList<RealCodedProblem> population, double probability) {
        double SIZE = 0.01;
        for (RealCodedProblem p : population) {
            //change the genes of the childrens using AX crossover operator
            for (int j = 0; j < p.getNumberOfGenes(); j++) {
                //verify the probability of mutatation
                if (rnd.nextDouble() < probability) {
                    //get original value of gene
                    double g = p.getGeneValue(j);
                    //calulate mutation whith gaussian distribution
                    double mut = RealCodedProblem.DIM * SIZE * rnd.nextGaussian();
                    //set gene mutation
                    p.setGeneValue(j, g + mut);
                }
            }
        }
        //return population mutated
        return population;
    }

   protected ArrayList<RealCodedProblem> replacement(ArrayList<RealCodedProblem> parents, ArrayList<RealCodedProblem> children) {
        //size of the original population
        int size = parents.size();
        //join parents and childrens
        parents.addAll(children);
        //select size individuals with tournament
        return tournament(parents, size);
    }

    public RealCodedProblem solve(int Iterations, int sizeOfPopulations, RealCodedProblem problem) {
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
            System.out.println("Iteration : " + i + " = " + parents.get(parents.size() - 1) );
        }
        //Sort Population
        Collections.sort(parents);
        //returns best individual (last)
        return parents.get(parents.size() - 1);
    }
   

}
