/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    
    static ArrayList<int[]> SUS(ArrayList<int[]> population, int numIndividualsToSelect, int[][] costMatrix) {
        ArrayList<int[]> __selectedIndividuals;
        int     __totalFitness;
        double  __offset;        
        double  __pointerIniticial;
        int[][] __newCostMatrixMinimization;

        __selectedIndividuals = new ArrayList<int[]>(numIndividualsToSelect);

        // nova matrix de custo, mas agora com os valores menores como maiores, ou seja,
        // temos um SUS de minimização e não de maximização
        __newCostMatrixMinimization = _calculateNewCostMatrixForMinimization(costMatrix);
        __totalFitness = _totalFitnessAcumulation(population, __newCostMatrixMinimization);
        
        // calcula o offset do SUS
        __offset = ((double)__totalFitness) / ((double)numIndividualsToSelect);
        // calcula um ponto aleatorio entre 0 e o maximo de fitness para ser o ponteiro
        // inicial do SUS
        __pointerIniticial = RANDOM_GENERATOR.nextDouble() * (double)__totalFitness;
        
        // Ciclo ate ter todos os individuos pedidos
        while((numIndividualsToSelect--) > 0) {
            
            __selectedIndividuals.add(
                    _returnIndividualFromPointer(
                        __pointerIniticial,
                        population, 
                        __newCostMatrixMinimization));  
            
            __pointerIniticial = (__pointerIniticial + __offset) % __totalFitness;
        }
        
        return __selectedIndividuals;
    }
    
    private static int[][] _calculateNewCostMatrixForMinimization(int[][] costMatrix) {
        int[][] __newCostMatrixMinimization = new int[costMatrix.length][costMatrix.length];
        int __maxCostInMatrix = _maxCostMatrix(costMatrix);
        
        for (int __linhas = 0; __linhas < __newCostMatrixMinimization.length; __linhas++) {
            __newCostMatrixMinimization[__linhas][__linhas] = 0;
            for (int __colunas = __linhas + 1; __colunas < __newCostMatrixMinimization.length; __colunas++) {
                __newCostMatrixMinimization[__linhas][__colunas] = __newCostMatrixMinimization[__colunas][__linhas] = (__maxCostInMatrix + 1) - costMatrix[__linhas][__colunas]; 
            }
        }
        
        return __newCostMatrixMinimization;
    }
    
    public static int _totalFitnessAcumulation(ArrayList<int[]> population, int[][] costMatrix){
        int __totalFitness;
        
        __totalFitness = 0;
        
        for (int[] individuo : population) {
            // incrementa o total fitness
            __totalFitness += calculateFitness(individuo, costMatrix);
        }
        
        return __totalFitness;
    }
    
    public static int[] _returnIndividualFromPointer(double ponteiro, ArrayList<int[]> population, int[][] costMatrix) {
        int     __totalFitnessAccumulate = 0;
        int[]   __individualSelect = null;
        
        //corre a população para cada individuo
        for (int[] individuo : population) {
            // incrementa o total fitness
            // recalcula o fitness do individuo para que o menor seja agora o maior e vise-versa
            // porque o problema é de minimização, os individuos com menor fitness são os melhores
            __totalFitnessAccumulate += calculateFitness(individuo, costMatrix);
            
            //escolhe o individuo onde o ponteiro aponta 
            if(ponteiro <= (double)__totalFitnessAccumulate) {
                //clone para criar um novo individuo e não ser individuo da pop original
                __individualSelect = individuo;
                break;
            }
        }
        
        //Caso não existam individuos na população recebida, devolve null
        return __individualSelect;
    }
    
    static int _maxFitness(ArrayList<int[]> population, int[][] costMatrix) {
        int __maxFitness;
        int __individualSelect;
        
        // inicializar a variavel
        __maxFitness = 0;
        
        for (int[] __individual : population) {
            __individualSelect = calculateFitness(__individual, costMatrix);

            if(__maxFitness < __individualSelect)
                __maxFitness = __individualSelect;
        }
        
        return __maxFitness;
    }
    
    static int _maxCostMatrix(int[][] costMatrix) {
        int __maxFitness;
        
        __maxFitness = 0;
        
        for (int i = 0; i < costMatrix.length; i++) {
            // não preciso correr a matriz toda porque o triangulo inferior é
            // o espelho do de cima
            for (int j = i; j < costMatrix.length; j++) {
                if(__maxFitness < costMatrix[i][j]){
                    __maxFitness = costMatrix[i][j];
                }
            }
        }
        
        return __maxFitness;
    }
    
    static ArrayList<int[]> cycleCrossover(int[] pai, int[] mae) {
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
            filho[i] = search(i, mae, filho);
            filha[i] = search(i, pai, filha);
        }

        filhos.add(filho);
        filhos.add(filha);
        return filhos;
    }

    static int search(int positionCutProgenitor, int[] progenitor, int[] descendant){
        int __valueProgenitor;
        boolean __valueExiste;
        __valueProgenitor = 0;
        
        for (int __projenitorIndex = 0; __projenitorIndex < progenitor.length; __projenitorIndex++) {
            
            __valueProgenitor = progenitor[(__projenitorIndex + positionCutProgenitor) % progenitor.length];
            __valueExiste = false;
            
            for (int __descendantIndex = 0; __descendantIndex < positionCutProgenitor; __descendantIndex++) {
                if(__valueProgenitor == descendant[__descendantIndex]){
                    __valueExiste = true;
                    break;
                }
            }
            
            if(!__valueExiste)
                break; 
        }
        
        return __valueProgenitor;
    }
    
    // procura(pos, progenitor.gene[], descendente.gene[])
    static int procura(int pos, int[] progenitor, int[] descendente) {
        //p := pos-1
        int p = pos - 1;
        //q := pos
        int q = pos;
        //enquanto (p >= 0)
        while (p >= 0) {
            //se (descendente.gene[p] = progenitor.gene[q]) entao
            if (descendente[p] == progenitor[q]) {
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
        int numIndividuals;        // numero de individuos
        int numIndividualsToSelect;
        int individualSize;        // numero de cidades
        int iterations;
        int bestFitness;
        int[][] costMatrix;

        
        
        iterations = 1000;
        bestFitness = 1;
        numIndividuals = 100;
        numIndividualsToSelect = 70;
        individualSize = 4;
        costMatrix = new int[4][4];
        
        costMatrix[0][0] = 0;
        costMatrix[0][1] = 2;
        costMatrix[0][2] = 5;
        costMatrix[0][3] = 7;
        
        costMatrix[1][0] = 2;
        costMatrix[1][1] = 0;
        costMatrix[1][2] = 8;
        costMatrix[1][3] = 3;
        
        costMatrix[2][0] = 5;
        costMatrix[2][1] = 8;
        costMatrix[2][2] = 0;
        costMatrix[2][3] = 1;
        
        costMatrix[3][0] = 7;
        costMatrix[3][1] = 3;
        costMatrix[3][2] = 1;
        costMatrix[3][3] = 0;
        
        // Inicializar a tabela de custos aleatoria (1º parametro é o maximo do custo)
        // costMatrix = generatoRandomCostMatrix(100, individualSize);
        
        // Inicializar a população aleatoria
        ArrayList<int[]> initialPopulation = new ArrayList<int[]>(numIndividuals);
        for (int i = 0; i < numIndividuals; i++) {
            //initialPopulation.add(individualRepairs(generateRandomIndividual(individualSize)));
            int[] __individual = generateRandomIndividual(individualSize);
            int[] __individualRepair = individualRepairs(__individual);

            initialPopulation.add(__individualRepair);
        }

        while((iterations--) > 0 && _bestFitness(initialPopulation, costMatrix) >= bestFitness){
        
            for (int i = 0; i < initialPopulation.size(); i++) {
                int[] __individualRepair = individualRepairs(initialPopulation.get(i));
                initialPopulation.remove(i);
                initialPopulation.add(__individualRepair);
            }
            
            // Selection com SUS (Minimizatin)
            ArrayList<int[]> __individualsSelects;
            __individualsSelects = SUS(initialPopulation, numIndividualsToSelect, costMatrix);

            // OrderCrossover
            ArrayList<int[]> __individualsSons = new ArrayList<int[]>(__individualsSelects.size());
            for (int i = 0; i < __individualsSelects.size(); i = i + 2) {
                __individualsSons.addAll(
                        cycleCrossover(
                            __individualsSelects.get(i % (__individualsSelects.size()-1)), 
                            __individualsSelects.get((i+1) % (__individualsSelects.size()-1))));
            }

            // Mutation com SwapGene
            _swapGene(__individualsSons, 0.01);
            
            // Truncation
            initialPopulation = _truncation(initialPopulation, __individualsSons, costMatrix, numIndividuals);
        
        }
        
        _writeToConsoleMatrix(costMatrix);
        
        System.out.println("");
        
        for (int[] __individual : initialPopulation) {
            _writeToConsoleIndividual(__individual, costMatrix);
        }
        
        System.out.println("");
        
        _writeToConsoleIndividual(_bestFitnessIndividual(initialPopulation, costMatrix), costMatrix);
    }
    
    static ArrayList<int[]> _truncation(ArrayList<int[]> progenitors, ArrayList<int[]> descendants, final int[][] costMatrix, int numberOfIndividualsForNewGeneration) {
        ArrayList<int[]> __newGeneration;
        __newGeneration = new ArrayList<int[]>();
        
        __newGeneration.addAll(descendants);
        __newGeneration.addAll(progenitors);
        
        Collections.sort(__newGeneration, new Comparator<int[]>() {

            @Override
            public int compare(int[] individual1, int[] individual2) {
                int __fitnessIndividual1;
                int __fitnessIndividual2;

                __fitnessIndividual1 = calculateFitness(individual1, costMatrix);
                __fitnessIndividual2 = calculateFitness(individual2, costMatrix);

                if(__fitnessIndividual1 > __fitnessIndividual2)
                    return 1;
                
                if(__fitnessIndividual1 < __fitnessIndividual2)
                    return -1;
                
                return 0;
            }
            
        });

        return new ArrayList<int[]>(__newGeneration.subList(0, numberOfIndividualsForNewGeneration));
    }
    
    static void _swapGene(ArrayList<int[]> population, double probability){
        for (int[] __individual : population) {
            if(probability > RANDOM_GENERATOR.nextDouble()){
                int ponto1 = RANDOM_GENERATOR.nextInt(__individual.length);
                int ponto2 = RANDOM_GENERATOR.nextInt(__individual.length);

                // operação que garante que o ponto 1 e ponto 2 nunca são iguais
                ponto2 = (ponto1 + ponto2) % __individual.length;

                int __temp = __individual[ponto1];
                __individual[ponto1] = __individual[ponto2];
                __individual[ponto2] = __temp;
            }
        }
    }
    
    static void _writeToConsoleIndividual(int[] individual, int[][] costMatrix) {
        StringBuilder __result = new StringBuilder();
        
        for (int i = 0; i < individual.length; i++) {
            __result.append(individual[i]);
            __result.append(" -> ");
        }
        
        __result.append("0 >>>> ");
        __result.append(calculateFitness(individual, costMatrix));
        
        System.out.println(__result);
    }

    
    static void _writeToConsoleMatrix(int[][] matrix) {
        StringBuilder __result = new StringBuilder();
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                __result.append(matrix[i][j]);
                __result.append(" ");
            }
            __result.append("\n");
        }
        
        System.out.println(__result);
    }

    private static int _bestFitness(ArrayList<int[]> population, int[][] matrix) {
        int __bestFitness;

        __bestFitness = _maxFitness(population, matrix);
        
        for (int[] __individual : population) {
            if(__bestFitness > calculateFitness(__individual, matrix)){
                __bestFitness = calculateFitness(__individual, matrix);
            }
        }
        
        return __bestFitness;
    }
    
    private static int[] _bestFitnessIndividual(ArrayList<int[]> population, int[][] matrix) {
        int __bestFitness;
        int[] __bestIndividual;
        
        __bestIndividual = null;
        __bestFitness = _maxFitness(population, matrix);
        
        for (int[] __individual : population) {
            if(__bestFitness >= calculateFitness(__individual, matrix)){
                __bestFitness = calculateFitness(__individual, matrix);
                __bestIndividual = __individual;
            }
        }
        
        return __bestIndividual;
    }
}
