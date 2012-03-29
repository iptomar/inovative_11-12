/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.replacements;

import genetics.Individual;
import genetics.Population;
import java.util.ArrayList;

/**
 *
 * @author Chorinca-Notebook
 */
public class Tournament extends Replacement {

    static final int SIZE_TOURNAMENT_DEFAULT = 2;    

    final private int _sizeTournament;
    
    
    public Tournament(){
        this(Replacement.DIMENDIONS_NEW_POPULATION_DEFAULT, SIZE_TOURNAMENT_DEFAULT);
    }
    
    public Tournament(int dimensionsNewPopulation, int sizeTournament){
        super.dimensionsNewPopulation = dimensionsNewPopulation;
        this._sizeTournament = sizeTournament;
    }
    
    @Override
    public Population execute(Population parents, Population sons) {
        Population __newPopulation = new Population(
                super.dimensionsNewPopulation, 
                parents.getSizeGenome(), 
                parents.getSizeGenotype(), 
                parents.getSizeAllelo(),
                parents.getTypePopulation());
        operators.selections.Tournament __tournamentOnePopulation;
        ArrayList<Individual> __newArrayListIndividuos;

        // Junta as duas populações numa unica população
        __newArrayListIndividuos = new ArrayList<Individual>(parents.getPopulation());
        __newArrayListIndividuos.addAll(sons.getPopulation());
        
        __tournamentOnePopulation = new operators.selections.Tournament(
                super.dimensionsNewPopulation, 
                this._sizeTournament);
        __tournamentOnePopulation.setRemoveIndividualFromPopulation(true);
        
        __newPopulation.setPopulation(__newArrayListIndividuos);
        
        return __tournamentOnePopulation.execute(__newPopulation);
    }
    
}
