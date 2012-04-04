/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import genetics.OnesMax;
import genetics.Population;
import genetics.Solver;
import genetics.SolverThreads;
import java.util.concurrent.CyclicBarrier;
import utils.EventsSolver;

/**
 *
 * @author Chorinca-Notebook
 */
public class main {
    
    public static void main(String[] args) {
//        // Atribuição do número de Cores a ser utilizados
//        // No futuro utilizar a API do java para saber quantos cores estão disponiveis
//        int numCores = 2;
//        // Intervalo entre paragens. Neste caso é de 50 a 50 iterações
//        int stopIteration = 50;
//        // Criar o obejcto do tipo CyclicBarrier. Neste caso não existe nenhum construtor especial
//        // Mais info ver -> http://docs.oracle.com/javase/1.5.0/docs/api/java/util/concurrent/CyclicBarrier.html
//        CyclicBarrier barrier = new CyclicBarrier(numCores, new CodeBarrier());
//        
//        // Inicialização de um Array de Threads. 
//        // Neste caso o objecto utilizado é em solver que foi estendido para thread
//        SolverThreads[] solvers = new SolverThreads[numCores];
//        
//        // Inicialização de cada Solver(Thread) e seu inicio...
//        for (int i = 0; i < numCores; i++) {
//            solvers[i] = new SolverThreads(barrier, stopIteration);
//            solvers[i].start();
//        } 
//        
//        //Ver execução para perceber o que é feito :)
        
        Solver __newSolver = new Solver(100, 100, new OnesMax(), 1000, 80, new EventsSolver() {

            @Override
            public void EventStartSolver() {
                System.out.println("Solver Start");
            }

            @Override
            public void EventIteraction(int iteractionNumber, Population currentPopulation) {
                System.out.println("Iteração numero " + iteractionNumber);
            }

            @Override
            public void EventFinishSolver(Population lastPopulation) {
                System.out.println("Solver terminado");
                System.out.println(lastPopulation.getHallOfFame(5).toString());
            }
        });
        __newSolver.run();
        
    }
    
}
