/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.selections;

import genetics.Individual;
import genetics.Population;
import java.util.ArrayList;

/**
 *
 * @author Chorinca-Notebook
 */
public class Tournament extends Selection {

    static final int SIZE_TOURNAMENT_DEFAULT = 2;   
    static final boolean  DEFAULT_REMOVE_INDIVIDUAL_FROM_POPULATION = false;
    
    final private int _sizeTournament;
    
    private boolean _removeIndividualFromPopulation = DEFAULT_REMOVE_INDIVIDUAL_FROM_POPULATION;
    
    public Tournament(){
        this(Selection.DIMENDIONS_NEW_POPULATION_DEFAULT, Tournament.SIZE_TOURNAMENT_DEFAULT);
    }
    
    public Tournament(int dimensionsNewPopulation, int sizeTournament){
        super._dimensionsNewPopulation = dimensionsNewPopulation;
        this._sizeTournament = sizeTournament;
    }
    
    @Override
    public Population execute(Population population) {
        // nova população a ser criada com os individuos mais aptos
        final Population _newPopulation = 
                new Population(
                    super._dimensionsNewPopulation, 
                    population.getSizeGenome(), 
                    population.getSizeGenotype(),
                    population.getSizeAllelo(),
                    population.getTypePopulation(), 
                    false);
        
        // variavel que vai guardar o individuo mais apto
        Individual __bestIndividualTournament;
        // variavel que vai guardar o candidato a individuo mais apto
        Individual __individualsToFightForBest;
        
        // Criar nova população
        for (int __numberIndividualsInTheNewPopulation = 0; __numberIndividualsInTheNewPopulation < super._dimensionsNewPopulation; __numberIndividualsInTheNewPopulation++) {
            // Selecionar de forma aleatoria os individuos da população de pais
            ArrayList<Individual> __individualToEnterInTheTournament = population.getArrayIndividualsRandom(this._sizeTournament, this._removeIndividualFromPopulation);
            
            // Selecionar o primeiro individuo, e por defeito é logo o bestIndividuo
            __bestIndividualTournament = __individualToEnterInTheTournament.get(0);
            // remover esse individuo da lista de candidatos
            __individualToEnterInTheTournament.remove(__bestIndividualTournament);
            
            // Corre a lista de candidatos ate não existir mais candidatos
            while(__individualToEnterInTheTournament.size() > 0) {
                
                // Seleciona o primeiro individuo dos candidatos a best
                __individualsToFightForBest = __individualToEnterInTheTournament.get(0);
                
                // Comparar os individuos com base no fiteness
                if(__bestIndividualTournament.fitness() >= __individualsToFightForBest.fitness()){
                    // se o individuo continuar a ser o melhor entao remove se o candidato a best
                    __individualToEnterInTheTournament.remove(__individualsToFightForBest);
                    // e volta a por esse individuo na população
                    population.addIndividual(__individualsToFightForBest);
                } else {
                    // devolve a população o individuo que perdeu, neste caso era o que ate agora era considerado
                    // o melhor candidato
                    population.addIndividual(__bestIndividualTournament);
                    // se o candidato a best tiver mais fiteness então passa a ser o best e remove se da lista
                    // de candidatos
                    __bestIndividualTournament = __individualsToFightForBest;
                    __individualToEnterInTheTournament.remove(__bestIndividualTournament);
                }
            }

            // por fim quando não existir mais candidatos adicionamos o best à nova população
            _newPopulation.addIndividual(__bestIndividualTournament.clone());
        }
        
        // devolve a nova população com os individuos mais aptos
        return _newPopulation;
    }

    /**
     * @return the _removeIndividualFromPopulation
     */
    public boolean isRemoveIndividualFromPopulation() {
        return _removeIndividualFromPopulation;
    }

    /**
     * @param removeIndividualFromPopulation the _removeIndividualFromPopulation to set
     */
    public void setRemoveIndividualFromPopulation(boolean removeIndividualFromPopulation) {
        this._removeIndividualFromPopulation = removeIndividualFromPopulation;
    }
}
