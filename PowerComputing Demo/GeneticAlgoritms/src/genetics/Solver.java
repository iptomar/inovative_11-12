/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;
import operators.Genetic;
import operators.Operator;
import operators.mutation.Flipbit;
import operators.recombinations.Crossover2;
import operators.replacements.Replacement;
import operators.selections.Tournament;

/**
 *
 * @author diogoantonio
 */
public class Solver extends Thread{
    
    private Population _parentsPopulation;
    private Population _sonsPopulation;
    
    private ArrayList<Operator> _operators;
    private int _sizePopulation;
    private int _sizeGenotype = 1;
    private int _sizeGenome = 1; 
    private int _sizeAllelo;
    private Individual _typeIndividual;
    private StopCriterion _stopCriterion;
    private int _numberIteractions;
    
    /**
     * Objecto do CyclicBarrier que permite criar uma barreira de sincronismo nas threads
     * Mais info em http://docs.oracle.com/javase/1.5.0/docs/api/java/util/concurrent/CyclicBarrier.html
     */
    private CyclicBarrier _barrier;
    /**
     * Variável que contem a interação onde se vai fazer a paragem
     * Esta variável é comparada com a _numberIteractions
     */
    private int _barrierInteration;
    /**
     * Variável que indica o número de interações para cada paragem, 
     *  ou seja é o Intervalo entre paragens.
     * (Não necessário, pode ser outro critério de paragem)
     */
    private int _originalBarrierInteration;
    /**
     * Variável que indica quantas paragem já foram realizadas
     * (Não necessário apenas serve para testes e informação)
     */
    private int _stopCount;
    public Solver() {
        this(100, 20, new OnesMax(), 100, 18);
    }    
    
    public Solver(CyclicBarrier barrier, int barrierInteration) {
        this(1000, 100, new OnesMax(), 1000, 99);
        
        this._barrier = barrier;
        this._barrierInteration = barrierInteration;
        this._originalBarrierInteration = barrierInteration;
        this._stopCount = 1;
    }
    
    public Solver(int sizePopulation, int sizeAllelo, Individual typeIndividual, int iteractions, int bestfiteness) {
        this._sizePopulation = sizePopulation;
        this._sizeAllelo = sizeAllelo;
        this._typeIndividual = typeIndividual;
        this._stopCriterion = new StopCriterion(iteractions, bestfiteness);
    }

    @Override
    public void run() {
        System.out.println("Inicio da Thread");
        solverRun();
    }
    
    
    
    public void solverRun(){
        
        this._operators = new ArrayList<Operator>(4);
        this._operators.add(new Tournament(8, 2));        
        this._operators.add(new Crossover2());
        this._operators.add(new Flipbit(0.01));
        this._operators.add(new operators.replacements.Tournament(this._sizePopulation, 2));

        this._numberIteractions = 0;
        this._parentsPopulation = new Population(this._sizePopulation, this._sizeGenome, this._sizeGenotype, this._sizeAllelo, this._typeIndividual);
        
        while((this._numberIteractions < this._stopCriterion.getNumberIteractions()) && 
              (this._parentsPopulation.getBestFiteness() < this._stopCriterion.getGoodFiteness())) {
        
            this._parentsPopulation.incrementAgePopulation();
            this._sonsPopulation = ((Genetic)this._operators.get(0)).execute(this._parentsPopulation);

            for (int __indexOperators = 1; __indexOperators < this._operators.size(); __indexOperators++) {

                if(this._operators.get(__indexOperators) instanceof Genetic) {
                    _sonsPopulation = ((Genetic)this._operators.get(__indexOperators)).execute(_sonsPopulation);
                } else {
                    _parentsPopulation = ((Replacement)this._operators.get(__indexOperators)).execute(this._parentsPopulation, this._sonsPopulation);
                }

            }
        
            
            this._numberIteractions++;
            //Verificação da condição de paragem por iterações
            if(this._numberIteractions==this._barrierInteration){
                try {
                    System.out.println(this.getName() +"- Paragem em "+_numberIteractions);
                    //Inicrementar contador de paragens
                    this._stopCount++;
                    //Colocar a proxima paragem
                    _barrierInteration += _originalBarrierInteration;
                    //Colocar a Thread na espera da barreira
                    this._barrier.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Solver.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BrokenBarrierException ex) {
                    Logger.getLogger(Solver.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        System.out.println("Hall of Fame");
        System.out.println(this._parentsPopulation.getHallOfFame(5).toString());
    
        System.out.println("");
        
        System.out.println("Population");
        System.out.println(this._parentsPopulation.toString());
    }
}
