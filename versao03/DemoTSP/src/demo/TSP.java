/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.io.FileNotFoundException;
import java.io.IOException;
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

    
    public static void main(String[] args) throws FileNotFoundException, IOException {

        int __numberIndividuals;                // numero de individuos
        int __numberIndividualsToSelect;        // numero de individuos a seleccionar para cruzamento
        int __individualSize;                   // numero de cidades
        int __numberOfGenerations;              // numero de gerações ou iterações que o algoritmo vai correr
        double __bestFitness;                      // fitness usaso como criteiro de paragem
        double[][] __costMatrix;                   // matriz com os custos de cada caminho
        ArrayList<int[]> __initialPopulation;
        ArrayList<int[]> __individualsSelectToCrossover;
        ArrayList<int[]> __populationOffspring;
        
        
//        __numberOfGenerations       = 10000;
//        __bestFitness               = 1;
//        __numberIndividuals         = 4;
//        __numberIndividualsToSelect = 4;
//        __individualSize            = 6;
//        __costMatrix                = new double[6][6];
//        
//        __costMatrix[0][0] = 0;
//        __costMatrix[0][1] = 7;
//        __costMatrix[0][2] = 9;
//        __costMatrix[0][3] = Short.MAX_VALUE;
//        __costMatrix[0][4] = Short.MAX_VALUE;
//        __costMatrix[0][5] = 14;
//        
//        __costMatrix[1][0] = 7;
//        __costMatrix[1][1] = 0;
//        __costMatrix[1][2] = 10;
//        __costMatrix[1][3] = 15;
//        __costMatrix[1][4] = Short.MAX_VALUE;
//        __costMatrix[1][5] = Short.MAX_VALUE;
//        
//        __costMatrix[2][0] = 9;
//        __costMatrix[2][1] = 10;
//        __costMatrix[2][2] = 0;
//        __costMatrix[2][3] = 11;
//        __costMatrix[2][4] = Short.MAX_VALUE;
//        __costMatrix[2][5] = 2;
//        
//        __costMatrix[3][0] = Short.MAX_VALUE;
//        __costMatrix[3][1] = 15;
//        __costMatrix[3][2] = 11;
//        __costMatrix[3][3] = 0;
//        __costMatrix[3][4] = 6;
//        __costMatrix[3][5] = Short.MAX_VALUE;
//        
//        __costMatrix[4][0] = Short.MAX_VALUE;
//        __costMatrix[4][1] = Short.MAX_VALUE;
//        __costMatrix[4][2] = Short.MAX_VALUE;
//        __costMatrix[4][3] = 6;
//        __costMatrix[4][4] = 0;
//        __costMatrix[4][5] = 9;
//        
//        __costMatrix[5][0] = 14;
//        __costMatrix[5][1] = Short.MAX_VALUE;
//        __costMatrix[5][2] = 2;
//        __costMatrix[5][3] = Short.MAX_VALUE;
//        __costMatrix[5][4] = 9;
//        __costMatrix[5][5] = 0;
        
        // Inicializar a tabela de custos aleatoria (1º parametro é o maximo do custo)
        // costMatrix = generatoRandomCostMatrix(100, individualSize);
        
        
        __numberOfGenerations       = 100000;
        __bestFitness               = 1;
        __numberIndividuals         = 10;
        __numberIndividualsToSelect = 8;
        __individualSize            = 280;
        
        ReadFile __file = new ReadFile();
        __file.read();
        __costMatrix = __file.convertToCostMatrix();
        
        // Inicializar a população aleatoria
        __initialPopulation = new ArrayList<int[]>(__numberIndividuals);
        for (int __indexIndividual = 0; __indexIndividual < __numberIndividuals; __indexIndividual++) {
            //initialPopulation.add(individualRepairs(generateRandomIndividual(individualSize)));
            int[] __individual          = _generateRandomIndividual(__individualSize);
            int[] __individualRepair    = _individualNormalization(__individual);

            __initialPopulation.add(__individualRepair);
        }

        for (int[] __individual : __initialPopulation) {
            _writeToConsoleIndividual(__individual, __costMatrix);
        }
        System.out.println("");
        
        // corre este ciclo ate atingir o maximo numero de gerações ou encontrar o best procurado num individuo da população
        while((__numberOfGenerations--) > 0 && _bestFitness(__initialPopulation, __costMatrix) >= __bestFitness){
        
            // remover o individio não reparado e volta a colocar ele na população ja reparado
            for (int __indexIndividual = 0; __indexIndividual < __initialPopulation.size(); __indexIndividual++) {
                int[] __individualRepair = _individualNormalization(__initialPopulation.get(__indexIndividual));
                __initialPopulation.remove(__indexIndividual);
                __initialPopulation.add(__individualRepair);
            }
            
            // Selection com SUS (Minimizatin)
            __individualsSelectToCrossover = SUS(__initialPopulation, __numberIndividualsToSelect, __costMatrix);

            // CycleCrossover (para cada dois individuos aplica o operador de cycleCrossover)
            __populationOffspring = new ArrayList<int[]>(__individualsSelectToCrossover.size());
//            for (int __indexIndividual = 0; __indexIndividual < __individualsSelectToCrossover.size(); __indexIndividual = __indexIndividual + 2) {
//                __populationOffspring.addAll(
//                        _cycleCrossover(
//                            __individualsSelectToCrossover.get(__indexIndividual % (__individualsSelectToCrossover.size()-1)), 
//                            __individualsSelectToCrossover.get((__indexIndividual+1) % (__individualsSelectToCrossover.size()-1))));
//            }
            for (int __indexIndividual = 0; __indexIndividual < __individualsSelectToCrossover.size(); __indexIndividual = __indexIndividual + 2) {
                __populationOffspring.addAll(
                        _pmx(
                            __individualsSelectToCrossover.get(__indexIndividual % (__individualsSelectToCrossover.size()-1)), 
                            __individualsSelectToCrossover.get((__indexIndividual+1) % (__individualsSelectToCrossover.size()-1))));
            }

            // Mutation com SwapGene com probabilidade de 1%
            _invertion(__populationOffspring, 0.01);
            //_swapGene(__populationOffspring, 0.01);
            
            // Truncation
            __initialPopulation = _truncation(__initialPopulation, __populationOffspring, __costMatrix, __numberIndividuals);
        
            _writeToConsoleIndividual(_bestIndividual(__initialPopulation, __costMatrix), __costMatrix);
        }
        
        System.out.println("");
        _writeToConsoleMatrix(__costMatrix);
        
        // remover o individio não reparado e volta a colocar ele na população ja reparado
        for (int __indexIndividual = 0; __indexIndividual < __initialPopulation.size(); __indexIndividual++) {
            int[] __individualRepair = _individualNormalization(__initialPopulation.get(__indexIndividual));
            __initialPopulation.remove(__indexIndividual);
            __initialPopulation.add(__individualRepair);
        }
        
        System.out.println("");
        
        for (int[] __individual : __initialPopulation) {
            _writeToConsoleIndividual(__individual, __costMatrix);
        }
        
        System.out.println("");
        
        _writeToConsoleIndividual(_bestIndividual(__initialPopulation, __costMatrix), __costMatrix);
    }

    
    /* Funcões com os operadores usados nesta demo */
    
    private static ArrayList<int[]> _truncation(ArrayList<int[]> progenitors, ArrayList<int[]> descendants, final double[][] costMatrix, int numberOfIndividualsForNewGeneration) {
        ArrayList<int[]> __newGeneration;
        __newGeneration = new ArrayList<int[]>();
        
        __newGeneration.addAll(descendants);
        __newGeneration.addAll(progenitors);
        
        Collections.sort(__newGeneration, new Comparator<int[]>() {

            @Override
            public int compare(int[] individual1, int[] individual2) {
                double __fitnessIndividual1;
                double __fitnessIndividual2;

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
            // calcula a probabilidade
            if(probability > RANDOM_GENERATOR.nextDouble()){
                int[] __points;
                __points = new int[] { 0,0 };
                
                // gera dois pontos distintos
                _generatorTwoPointsDistincts(__points, __individual.length);
                // troca os valores para onde os pontos apontam
                _exchangeValuesFromArrayInTwoPoints(__individual, __points);
            }
        }
    }
    
    private static void _invertion(ArrayList<int[]> population, double probability){
        for (int[] __individual : population) {
            // calculo da probabilidade
            if(probability > RANDOM_GENERATOR.nextDouble()){
                int[] __points;
                __points = new int[] { 0,0 };
                
                // gera dois pontos distintos de corte
                _generatorTwoPointsDistincts(__points, __individual.length);
                // inverte a ordem dos valores dentro dos pontos de corte
                _invertOrderBetweenTwoPointers(__points[0], __individual, __points[1]);
            }
        }
    }
    
    private static ArrayList<int[]> _pmx(int[] father, int[] mother){
        ArrayList<int[]>    __offspringPopulation;
        int                 __dimensionProgenitors;
        int[]               __pointsOfCut;
        int[]               __son;
        int[]               __daughter;
        
        // inicialização dos parametros do pmx
        __offspringPopulation   = new ArrayList<int[]>();
        __dimensionProgenitors  = father.length;
        __son                   = new int[__dimensionProgenitors];
        __daughter              = new int[__dimensionProgenitors];
        __pointsOfCut           = new int[2];
        
        // gerar dois pontos de corte
        _generatorTwoPointsDistinctsSequential(__pointsOfCut, __dimensionProgenitors);
        // aplicar a logica do pmx
        _partiallyMatchedCrossoverLogic(father, mother, __son, __daughter, __pointsOfCut);
        
        // adicionar os dois filhos à população nova
        __offspringPopulation.add(__son);
        __offspringPopulation.add(__daughter);        
        
        return __offspringPopulation;
    }
    
    protected static void _partiallyMatchedCrossoverLogic(int[] father, int[] mother, int[] son, int[] daughter, int[] pointsOfCut){
        int __dimensionProgenitors;
        
        __dimensionProgenitors  = father.length;
        
        // corre os valores todos
        for (int __indexGene = 0; __indexGene < __dimensionProgenitors; __indexGene++) {
            // passa os valores entre os dois pontos para os filhos
            if(__indexGene >= pointsOfCut[0] && __indexGene <= pointsOfCut[1]) {
                son[__indexGene]      = mother[__indexGene];
                daughter[__indexGene] = father[__indexGene];
            } else {
                // procura os valores que faltam e poe nos sitios certos
                son[__indexGene]      = _searchNextGenePMX(father, mother, __indexGene, pointsOfCut);
                daughter[__indexGene] = _searchNextGenePMX(mother, father, __indexGene, pointsOfCut);
            }
        }
    }
    
    protected static int _searchNextGenePMX(int[] progenitorA, int[] progenitorB, int positionGene, int[] pointsOfCut) {
        for (int __indexGene = pointsOfCut[0]; __indexGene <= pointsOfCut[1]; __indexGene++) {
            if(progenitorA[positionGene] == progenitorB[__indexGene])
                // depois de sellecionado um valor temos que verificar se esse valor não existe
                // tambem no individuo ja
                return _searchNextGenePMX(progenitorA, progenitorB, __indexGene, pointsOfCut); // progenitorA[__indexGene];
        }
        
        return progenitorA[positionGene];
    }
    
    protected static void _generatorTwoPointsDistinctsSequential(int[] points, int maxValueExclusive){
        // Soma se 1 ao valor gerado para nunca dar 0 e subtrai-se 3 ao maximo para dar margem para gerar
        // o segundo ponto, e esse mesmo ponto ter margem para não ser o valor do maximo
        points[0] = 1 + RANDOM_GENERATOR.nextInt(maxValueExclusive - 3);
        // vai gerar numeros enquanto os dois valores forem iguais
        do {
            // O valor a ser gerado vai ser o valor que sobra entre o maximo e o primeiro ponto
            // e subtrai-se 1 para deixar pelo menos um ultimo valor livre
            points[1] = points[0] + RANDOM_GENERATOR.nextInt(maxValueExclusive - points[0] - 1);
        } while(points[0] == points[1]);
    }
    
    protected static void _exchangeValuesFromArrayInTwoPoints(int[] individual, int[] points) {  
        // guarda o primeiro valor numa variavel temporaria
        int __temp = individual[points[0] % individual.length];
        // troca o primeiro valor
        individual[points[0] % individual.length] = individual[points[1] % individual.length];
        // troca o valor guardado na variavel temporaria
        individual[points[1] % individual.length] = __temp;
    }
    
    protected static void _generatorTwoPointsDistincts(int[] points, int maxValueExclusive){
        points[0] = RANDOM_GENERATOR.nextInt(maxValueExclusive);
        // vai gerar numeros enquanto os dois valores forem iguais
        do {
            points[1] = RANDOM_GENERATOR.nextInt(maxValueExclusive);
        } while(points[0] == points[1]);
    }
    
    protected static void _invertOrderBetweenTwoPointers(int point1, int[] individual, int point2) {
        int __pointTemp;
        int __distanceBetweenPointers;
        
        __pointTemp                 = point1;
        __distanceBetweenPointers   = 0;
        
        // conta a distancia entre os dois pontos
        while(((__pointTemp++)%individual.length) != point2) __distanceBetweenPointers++;
        
        // corre o array entre os dois pontos e vai trocando os valores ate
        // estarem todos trocados na sua ordem
        for(int pontoMover = 0; pontoMover < __distanceBetweenPointers; pontoMover++)
            for(int indexValue = 0; indexValue < __distanceBetweenPointers-pontoMover; indexValue++){                
                _exchangeValuesFromArrayInTwoPoints(individual, 
                        new int[] { 
                            indexValue + point1,        // ponto actual
                            indexValue + point1 + 1});  // ponto a seguir
            }
    }
    
    private static ArrayList<int[]> _cycleCrossover(int[] father, int[] mother) {
        ArrayList<int[]>    __descendants;
        int[]               __son;
        int[]               __daughter;
        int                 __dimensionProgenitor;
        int                 __pointOfCut;

        // inicialização dos parametros do cycleCrossover
        __descendants           = new ArrayList<int[]>();
        __son                   = new int[father.length];
        __daughter              = new int[mother.length];
        __dimensionProgenitor   = father.length;
        __pointOfCut            = RANDOM_GENERATOR.nextInt(__dimensionProgenitor - 2) + 1;
        
        // troca os genes dos pais com os filhos
        for (int __indexGene = 0; __indexGene < __pointOfCut; __indexGene++) {
            __son[__indexGene]        = father[__indexGene];
            __daughter[__indexGene]   = mother[__indexGene];
        }

        // depois preenche o resto com genes do outro progenitor, diferente do de cima,
        // com os restantes genes que faltam
        for (int __indexGene = __pointOfCut; __indexGene < __dimensionProgenitor; __indexGene++) {
            __son[__indexGene]        = _searchForNextGene(__indexGene, mother, __son);
            __daughter[__indexGene]   = _searchForNextGene(__indexGene, father, __daughter);
        }

        // junta os a população final
        __descendants.add(__son);
        __descendants.add(__daughter);
        
        return __descendants;
    }

    private static int _searchForNextGene(int positionOfCutProgenitor, int[] progenitor, int[] descendant){
        int     __valueProgenitor;
        boolean __valueExiste;
        
        __valueProgenitor = 0;
        
        // corre os valores do progenitor todos
        for (int __projenitorIndex = 0; __projenitorIndex < progenitor.length; __projenitorIndex++) {
            
            // valor do progenitor a partir do ponto de corte
            __valueProgenitor   = progenitor[(__projenitorIndex + positionOfCutProgenitor) % progenitor.length];
            // variavel que mostra se o valor ja existe
            __valueExiste       = false;
            
            for (int __descendantIndex = 0; __descendantIndex < positionOfCutProgenitor; __descendantIndex++) {
                // corre o descendente todos a ver se esse valor ja esta presente nele
                if(__valueProgenitor == descendant[__descendantIndex]){
                    // se sim então indica na variavel e termina o ciclo
                    __valueExiste = true;
                    break;
                }
            }
            
            // se o valor não existir então termina o ciclo
            if(!__valueExiste)
                break; 
        }
        
        // devolve o valor
        return __valueProgenitor;
    }

    
    private static ArrayList<int[]> SUS(ArrayList<int[]> population, int numIndividualsToSelect, double[][] costMatrix) {
        ArrayList<int[]> __selectedIndividuals;
        double      __totalFitness;
        double      __offset;        
        double      __pointerIniticial;
        double[][]  __newCostMatrixMinimization;

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
    
    private static double[][] _calculateNewCostMatrixForMinimization(double[][] costMatrix) {
        double[][]  __newCostMatrixMinimization;
        double      __maxCostInMatrix;
        
        // define o tamanho da matriz
        __newCostMatrixMinimization = new double[costMatrix.length][costMatrix.length];
        // obtem o valor maximo que a matriz original tem
        __maxCostInMatrix           = _maxCostMatrix(costMatrix);
        
        for (int __linhas = 0; __linhas < __newCostMatrixMinimization.length; __linhas++) {
            __newCostMatrixMinimization[__linhas][__linhas] = 0;
            for (int __colunas = __linhas + 1; __colunas < __newCostMatrixMinimization.length; __colunas++) {
                // ao valor maximo subtrai-se os valores dentro da matriz
                __newCostMatrixMinimization[__linhas][__colunas] = __newCostMatrixMinimization[__colunas][__linhas] = (__maxCostInMatrix + 1) - costMatrix[__linhas][__colunas]; 
            }
        }
        
        // devolve a nova matriz invertida nos valores
        return __newCostMatrixMinimization;
    }
    
    private static double _totalFitnessAcumulation(ArrayList<int[]> population, double[][] costMatrix){
        double __totalFitness;
        
        __totalFitness = 0;
        
        for (int[] individuo : population) {
            // incrementa o total fitness
            __totalFitness += _calculateFitness(individuo, costMatrix);
        }
        
        return __totalFitness;
    }
    
    public static int[] _returnIndividualFromPointer(double ponteiro, ArrayList<int[]> population, double[][] costMatrix) {
        double     __totalFitnessAccumulate = 0;
        int[]   __individualSelect = null;
        
        //corre a população para cada individuo
        for (int[] __individuo : population) {
            // incrementa o total fitness
            // recalcula o fitness do individuo para que o menor seja agora o maior e vise-versa
            // porque o problema é de minimização, os individuos com menor fitness são os melhores
            __totalFitnessAccumulate += _calculateFitness(__individuo, costMatrix);
            
            //escolhe o individuo onde o ponteiro aponta 
            if(ponteiro <= __totalFitnessAccumulate) {
                //clone para criar um novo individuo e não ser individuo da pop original
                __individualSelect = __individuo;
                break;
            }
        }
        
        //Caso não existam individuos na população recebida, devolve null
        return __individualSelect;
    }
    
    
    
    /* Funções auxiliares para mostrar resultados na consola */
    
    private static void _writeToConsoleIndividual(int[] individual, double[][] costMatrix) {
        StringBuilder __result = new StringBuilder();
        
        for (int i = 0; i < individual.length; i++) {
            __result.append(individual[i]);
            __result.append(" -> ");
        }
        
        __result.append("0 >>>> ");
        __result.append(_calculateFitness(individual, costMatrix));
        
        System.out.println(__result);
    }
    
    protected static void _writeToConsoleIndividual2(int[] individual) {
        StringBuilder __result = new StringBuilder();
        
        for (int i = 0; i < individual.length; i++) {
            __result.append(individual[i]);
            __result.append(" -> ");
        }
        
        __result.append("0");
        System.out.println(__result);
    }

    private static void _writeToConsoleMatrix(double[][] matrix) {
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
    
    private static double _bestFitness(ArrayList<int[]> population, double[][] matrix) {
        double __bestFitness;

        __bestFitness = _maxFitness(population, matrix);
        
        // procura o melhor fitness na população toda
        for (int[] __individual : population) {
            if(__bestFitness > _calculateFitness(__individual, matrix)){
                __bestFitness = _calculateFitness(__individual, matrix);
            }
        }
        
        return __bestFitness;
    }
    
    private static int[] _bestIndividual(ArrayList<int[]> population, double[][] matrix) {
        double __bestFitness;
        int[] __bestIndividual;
        
        __bestIndividual = null;
        __bestFitness = _maxFitness(population, matrix);
        
        // procura o melhor individuo na população toda
        for (int[] __individual : population) {
            if(__bestFitness >= _calculateFitness(__individual, matrix)){
                __bestFitness = _calculateFitness(__individual, matrix);
                __bestIndividual = __individual;
            }
        }
        
        return __bestIndividual;
    }

    private static double _maxFitness(ArrayList<int[]> population, double[][] costMatrix) {
        double __maxFitness;
        double __individualSelect;
        
        // inicializar a variavel
        __maxFitness = 0;
        
        // procura o fitness mais alto na população
        for (int[] __individual : population) {
            __individualSelect = _calculateFitness(__individual, costMatrix);

            if(__maxFitness < __individualSelect)
                __maxFitness = __individualSelect;
        }
        
        return __maxFitness;
    }
    
    private static double _maxCostMatrix(double[][] costMatrix) {
        double __maxFitness;
        
        __maxFitness = 0;
        
        // procura o valor mais alto na matriz
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
    
    private static int[] _individualNormalization(int[] individual) {
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

    private static double _calculateFitness(int[] individual, double[][] costMatrix) {
        // starting point
        double __fitness = 0;
        for (int i = 1; i <= individual.length; i++) {
            // adds to fitness the cost of traveling between points (in the order expressed in individual)
            __fitness += costMatrix[individual[i - 1]][individual[i % individual.length]];
        }
        return __fitness;
    } 
    
    
    
    /* Funções para gerar Individiuos aleatorios e Matriz de Custos Aleatoria */
    
    protected static int[] _generateRandomIndividual(int size) {
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

    protected static double[][] _generateRandomCostMatrix(int maxCost, int numPoints) {
        double[][] __newCostMatrix = new double[numPoints][numPoints];
        for (int i = 0; i < numPoints; i++) {
            // the cost of a point to itself is 0 (same place)
            __newCostMatrix[i][i] = 0;
            for (int j = i + 1; j < numPoints; j++) {
                // generates a upper triangular matrix, and then mirror to the lower triangular matrix
                __newCostMatrix[i][j] = __newCostMatrix[j][i] = RANDOM_GENERATOR.nextDouble()*maxCost;
            }
        }
        return __newCostMatrix;
    }
}
