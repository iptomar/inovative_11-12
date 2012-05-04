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

    
    
    public static void main(String[] args) {

        int __numberIndividuals;                // numero de individuos
        int __numberIndividualsToSelect;        // numero de individuos a seleccionar para cruzamento
        int __individualSize;                   // numero de cidades
        int __numberOfGenerations;              // numero de gerações ou iterações que o algoritmo vai correr
        int __bestFitness;                      // fitness usaso como criteiro de paragem
        int[][] __costMatrix;                   // matriz com os custos de cada caminho
        ArrayList<int[]> __initialPopulation;
        ArrayList<int[]> __individualsSelectToCrossover;
        ArrayList<int[]> __populationOffspring;
        
        
        __numberOfGenerations       = 1000;
        __bestFitness               = 1;
        __numberIndividuals         = 10;
        __numberIndividualsToSelect = 8;
        __individualSize            = 4;
        __costMatrix                = new int[4][4];
        
        __costMatrix[0][0] = 0;
        __costMatrix[0][1] = 2;
        __costMatrix[0][2] = 5;
        __costMatrix[0][3] = 7;
        
        __costMatrix[1][0] = 2;
        __costMatrix[1][1] = 0;
        __costMatrix[1][2] = 8;
        __costMatrix[1][3] = 3;
        
        __costMatrix[2][0] = 5;
        __costMatrix[2][1] = 8;
        __costMatrix[2][2] = 0;
        __costMatrix[2][3] = 1;
        
        __costMatrix[3][0] = 7;
        __costMatrix[3][1] = 3;
        __costMatrix[3][2] = 1;
        __costMatrix[3][3] = 0;
        
        // Inicializar a tabela de custos aleatoria (1º parametro é o maximo do custo)
        // costMatrix = generatoRandomCostMatrix(100, individualSize);
        
        // Inicializar a população aleatoria
        __initialPopulation = new ArrayList<int[]>(__numberIndividuals);
        for (int __indexIndividual = 0; __indexIndividual < __numberIndividuals; __indexIndividual++) {
            //initialPopulation.add(individualRepairs(generateRandomIndividual(individualSize)));
            int[] __individual          = _generateRandomIndividual(__individualSize);
            int[] __individualRepair    = _individualRepairs(__individual);

            __initialPopulation.add(__individualRepair);
        }

        // corre este ciclo ate atingir o maximo numero de gerações ou encontrar o best procurado num individuo da população
        while((__numberOfGenerations--) > 0 && _bestFitness(__initialPopulation, __costMatrix) >= __bestFitness){
        
            // remover o individio não reparado e volta a colocar ele na população ja reparado
            for (int __indexIndividual = 0; __indexIndividual < __initialPopulation.size(); __indexIndividual++) {
                int[] __individualRepair = _individualRepairs(__initialPopulation.get(__indexIndividual));
                __initialPopulation.remove(__indexIndividual);
                __initialPopulation.add(__individualRepair);
            }
            
            // Selection com SUS (Minimizatin)
            __individualsSelectToCrossover = SUS(__initialPopulation, __numberIndividualsToSelect, __costMatrix);

            // CycleCrossover (para cada dois individuos aplica o operador de cycleCrossover)
            __populationOffspring = new ArrayList<int[]>(__individualsSelectToCrossover.size());
            for (int __indexIndividual = 0; __indexIndividual < __individualsSelectToCrossover.size(); __indexIndividual = __indexIndividual + 2) {
                __populationOffspring.addAll(
                        _cycleCrossover(
                            __individualsSelectToCrossover.get(__indexIndividual % (__individualsSelectToCrossover.size()-1)), 
                            __individualsSelectToCrossover.get((__indexIndividual+1) % (__individualsSelectToCrossover.size()-1))));
            }

            // Mutation com SwapGene com probabilidade de 1%
            _invertion(__populationOffspring, 1);
            
            // Truncation
            __initialPopulation = _truncation(__initialPopulation, __populationOffspring, __costMatrix, __numberIndividuals);
        
        }
        
        _writeToConsoleMatrix(__costMatrix);
        
        System.out.println("");
        
        for (int[] __individual : __initialPopulation) {
            _writeToConsoleIndividual(__individual, __costMatrix);
        }
        
        System.out.println("");
        
        _writeToConsoleIndividual(_bestIndividual(__initialPopulation, __costMatrix), __costMatrix);
    }

    
    /* Funcões com os operadores usados nesta demo */
    
    private static ArrayList<int[]> _truncation(ArrayList<int[]> progenitors, ArrayList<int[]> descendants, final int[][] costMatrix, int numberOfIndividualsForNewGeneration) {
        ArrayList<int[]> __newGeneration;
        __newGeneration = new ArrayList<int[]>();
        
        __newGeneration.addAll(descendants);
        __newGeneration.addAll(progenitors);
        
        Collections.sort(__newGeneration, new Comparator<int[]>() {

            @Override
            public int compare(int[] individual1, int[] individual2) {
                int __fitnessIndividual1;
                int __fitnessIndividual2;

                __fitnessIndividual1 = _calculateFitness(individual1, costMatrix);
                __fitnessIndividual2 = _calculateFitness(individual2, costMatrix);

                if(__fitnessIndividual1 > __fitnessIndividual2)
                    return 1;
                
                if(__fitnessIndividual1 < __fitnessIndividual2)
                    return -1;
                
                return 0;
            }
            
        });

        return new ArrayList<int[]>(__newGeneration.subList(0, numberOfIndividualsForNewGeneration));
    }
    
    private static void _swapGene(ArrayList<int[]> population, double probability){
        for (int[] __individual : population) {
            if(probability > RANDOM_GENERATOR.nextDouble()){
                int[] __points = new int[] { 0,0 };
                
                _generatorTwoPointsDistincts(__points, __individual.length);
                _exchangeValuesFromArrayInTwoPoints(__individual, __points);
            }
        }
    }
    
    private static void _invertion(ArrayList<int[]> population, double probability){
        for (int[] __individual : population) {
            if(probability > RANDOM_GENERATOR.nextDouble()){
                int[] __points = new int[] { 0,0 };
                
                _generatorTwoPointsDistincts(__points, __individual.length);
                
                _invertOrderBetweenTwoPointers(__points[0], __individual, __points[1]);
            }
        }
    }
    
    protected static void _exchangeValuesFromArrayInTwoPoints(int[] __individual, int[] points) {       
        int __temp = __individual[points[0] % __individual.length];
        __individual[points[0] % __individual.length] = __individual[points[1] % __individual.length];
        __individual[points[1] % __individual.length] = __temp;
    }
    
    protected static void _generatorTwoPointsDistincts(int[] points, int maxValueExclusive){
        points[0] = RANDOM_GENERATOR.nextInt(maxValueExclusive);
        // vai gerar numeros enquanto os dois valores forem iguais
        do {
            points[1] = RANDOM_GENERATOR.nextInt(maxValueExclusive);
        } while(points[0] == points[1]);
    }
    
    protected static void _invertOrderBetweenTwoPointers(int ponto1, int[] __individual, int ponto2) {
        int ponto1Temp;
        int distanceBetweenPointers;
        
        ponto1Temp              = ponto1;
        distanceBetweenPointers = 0;
        
        while(((ponto1Temp++)%__individual.length) != ponto2) distanceBetweenPointers++;
        
        for(int pontoMover = 0; pontoMover < distanceBetweenPointers; pontoMover++)
            for(int indexValue = 0; indexValue < distanceBetweenPointers-pontoMover; indexValue++){                
                _exchangeValuesFromArrayInTwoPoints(__individual, 
                        new int[] { 
                            indexValue + ponto1,        // ponto actual
                            indexValue + ponto1 + 1});  // ponto a seguir
            }
    }
    
    private static ArrayList<int[]> _cycleCrossover(int[] father, int[] mother) {
        ArrayList<int[]> filhos = new ArrayList<int[]>();
        int[] filho = new int[father.length];
        int[] filha = new int[mother.length];

        int dim = father.length;

        int corte = RANDOM_GENERATOR.nextInt(dim - 2) + 1;
        
        for (int i = 0; i < corte; i++) {
            filho[i] = father[i];
            filha[i] = mother[i];
        }

        for (int i = corte; i < dim; i++) {
            filho[i] = _searchForNextGene(i, mother, filho);
            filha[i] = _searchForNextGene(i, father, filha);
        }

        filhos.add(filho);
        filhos.add(filha);
        return filhos;
    }

    private static int _searchForNextGene(int positionOfCutProgenitor, int[] progenitor, int[] descendant){
        int __valueProgenitor;
        boolean __valueExiste;
        __valueProgenitor = 0;
        
        for (int __projenitorIndex = 0; __projenitorIndex < progenitor.length; __projenitorIndex++) {
            
            __valueProgenitor = progenitor[(__projenitorIndex + positionOfCutProgenitor) % progenitor.length];
            __valueExiste = false;
            
            for (int __descendantIndex = 0; __descendantIndex < positionOfCutProgenitor; __descendantIndex++) {
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

    
    private static ArrayList<int[]> SUS(ArrayList<int[]> population, int numIndividualsToSelect, int[][] costMatrix) {
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
    
    private static int _totalFitnessAcumulation(ArrayList<int[]> population, int[][] costMatrix){
        int __totalFitness;
        
        __totalFitness = 0;
        
        for (int[] individuo : population) {
            // incrementa o total fitness
            __totalFitness += _calculateFitness(individuo, costMatrix);
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
            __totalFitnessAccumulate += _calculateFitness(individuo, costMatrix);
            
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
    
    
    
    /* Funções auxiliares para mostrar resultados na consola */
    
    private static void _writeToConsoleIndividual(int[] individual, int[][] costMatrix) {
        StringBuilder __result = new StringBuilder();
        
        for (int i = 0; i < individual.length; i++) {
            __result.append(individual[i]);
            __result.append(" -> ");
        }
        
        __result.append("0 >>>> ");
        __result.append(_calculateFitness(individual, costMatrix));
        
        System.out.println(__result);
    }
    
    private static void _writeToConsoleIndividual2(int[] individual) {
        StringBuilder __result = new StringBuilder();
        
        for (int i = 0; i < individual.length; i++) {
            __result.append(individual[i]);
            __result.append(" -> ");
        }
        
        __result.append("0");
        System.out.println(__result);
    }

    private static void _writeToConsoleMatrix(int[][] matrix) {
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

    
    
    /* Funções para seleccionar Fitness e Melhor Individuo */
    
    private static int _bestFitness(ArrayList<int[]> population, int[][] matrix) {
        int __bestFitness;

        __bestFitness = _maxFitness(population, matrix);
        
        for (int[] __individual : population) {
            if(__bestFitness > _calculateFitness(__individual, matrix)){
                __bestFitness = _calculateFitness(__individual, matrix);
            }
        }
        
        return __bestFitness;
    }
    
    private static int[] _bestIndividual(ArrayList<int[]> population, int[][] matrix) {
        int __bestFitness;
        int[] __bestIndividual;
        
        __bestIndividual = null;
        __bestFitness = _maxFitness(population, matrix);
        
        for (int[] __individual : population) {
            if(__bestFitness >= _calculateFitness(__individual, matrix)){
                __bestFitness = _calculateFitness(__individual, matrix);
                __bestIndividual = __individual;
            }
        }
        
        return __bestIndividual;
    }

    private static int _maxFitness(ArrayList<int[]> population, int[][] costMatrix) {
        int __maxFitness;
        int __individualSelect;
        
        // inicializar a variavel
        __maxFitness = 0;
        
        for (int[] __individual : population) {
            __individualSelect = _calculateFitness(__individual, costMatrix);

            if(__maxFitness < __individualSelect)
                __maxFitness = __individualSelect;
        }
        
        return __maxFitness;
    }
    
    private static int _maxCostMatrix(int[][] costMatrix) {
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
    
    private static int[] _individualRepairs(int[] individual) {
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

    private static int _calculateFitness(int[] individual, int[][] costMatrix) {
        // starting point
        int __fitness = 0;
        for (int i = 1; i <= individual.length; i++) {
            // adds to fitness the cost of traveling between points (in the order expressed in individual)
            __fitness += costMatrix[individual[i - 1]][individual[i % individual.length]];
        }
        return __fitness;
    } 
    
    
    
    /* Funções para gerar Individiuos aleatorios e Matriz de Custos Aleatoria */
    
    static int[] _generateRandomIndividual(int size) {
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

    static int[][] _generateRandomCostMatrix(int maxCost, int numPoints) {
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
}
