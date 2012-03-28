/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.selections;

import Genetics.Individual;
import Genetics.Population;
import java.util.ArrayList;

/**
 *
 * @author Chorinca-Notebook
 */
public class Tournament extends Selection {

    static final int SIZE_TOURNAMENT_DEFAULT = 2;    
    static final int DIMENDIONS_NEW_POPULATION_DEFAULT = 10;

    final private int _sizeTournament;
    final private int _dimensionsNewPopulation;
    
    public Tournament(){
        this(Tournament.DIMENDIONS_NEW_POPULATION_DEFAULT, Tournament.SIZE_TOURNAMENT_DEFAULT);
    }
    
    public Tournament(int dimensionsNewPopulation, int sizeTournament){
        this._dimensionsNewPopulation = dimensionsNewPopulation;
        this._sizeTournament = sizeTournament;
    }
    
    @Override
    public Population execute(Population population) {
        // nova população a ser criada com os individuos mais aptos
        final Population _newPopulation = 
                new Population(
                    this._dimensionsNewPopulation, 
                    population.getNumberChromosomes(), 
                    population.getNumberGenes(), 
                    population.getTypeIndividual());
        
        // variavel que vai guardar o individuo mais apto
        Individual __bestIndividualTournament;
        // variavel que vai guardar o candidato a individuo mais apto
        Individual __individualsToFightForBest;
        
        // Criar nova população
        for (int __numberIndividualsInTheNewPopulation = 0; __numberIndividualsInTheNewPopulation < this._dimensionsNewPopulation; __numberIndividualsInTheNewPopulation++) {
            // Selecionar de forma aleatoria os individuos da população de pais
            ArrayList<Individual> __individualToEnterInTheTournament = population.getArrayIndividualRandom(this._sizeTournament, false);
            
            // Selecionar o primeiro individuo, e por defeito é logo o bestIndividuo
            __bestIndividualTournament = __individualToEnterInTheTournament.get(0);
            // remover esse individuo da lista de candidatos
            __individualToEnterInTheTournament.remove(__bestIndividualTournament);
            
            // Corre a lista de candidatos ate não existir mais candidatos
            while(__individualToEnterInTheTournament.size() > 0) {
                
                // Seleciona o primeiro individuo dos candidatos a best
                __individualsToFightForBest = __individualToEnterInTheTournament.get(0);
                
                // Comparar os individuos com base no fiteness
                if(__bestIndividualTournament.fiteness() >= __individualsToFightForBest.fiteness()){
                    // se o individuo continuar a ser o melhor entao remove se o candidato a best
                    __individualToEnterInTheTournament.remove(__individualsToFightForBest);
                } else {
                    // se o candidato a best tiver mais fiteness então passa a ser o best e remove se da lista
                    // de candidatos
                    __bestIndividualTournament = __individualsToFightForBest;
                    __individualToEnterInTheTournament.remove(__bestIndividualTournament);
                }
            }
            
            // por fim quando não existir mais candidatos adicionamos o best à nova população
            _newPopulation.addIndividual(__bestIndividualTournament);
        }
        
        // devolve a nova população com os individuos mais aptos
        return _newPopulation;
    }
}
