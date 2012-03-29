/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import genetics.OnesMax;
import genetics.Solver;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author Chorinca-Notebook
 */
public class main {
    
    public static void main(String[] args) {
        // Atribuição do número de Cores a ser utilizados
        // No futuro utilizar a API do java para saber quantos cores estão disponiveis
        int numCores = 2;
        // Intervalo entre paragens. Neste caso é de 50 a 50 iterações
        int stopIteration = 50;
        // Criar o obejcto do tipo CyclicBarrier. Neste caso não existe nenhum construtor especial
        // Mais info ver -> http://docs.oracle.com/javase/1.5.0/docs/api/java/util/concurrent/CyclicBarrier.html
        CyclicBarrier barrier = new CyclicBarrier(numCores, new CodeBarrier());
        
        // Inicialização de um Array de Threads. 
        // Neste caso o objecto utilizado é em solver que foi estendido para thread
        Solver[] solvers = new Solver[numCores];
        
        // Inicialização de cada Solver(Thread) e seu inicio...
        for (int i = 0; i < numCores; i++) {
            solvers[i] = new Solver(barrier, stopIteration);
            solvers[i].start();
        } 
        
        //Ver execução para perceber o que é feito :)
        
        //Solver __newSolver = new Solver(1000, 100, new OnesMax(), 1000, 80);
        //__newSolver.run();
        
    }
    
}
