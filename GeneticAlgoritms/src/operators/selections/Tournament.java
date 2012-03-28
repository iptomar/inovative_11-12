/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators.selections;

import genetics.Population;

/**
 *
 * @author Chorinca-Notebook
 */
public class Tournament extends Selection {

    static final int SIZE_TOURNAMENT_DEFAULT = 2;
    
    private int _sizeTournament = Tournament.SIZE_TOURNAMENT_DEFAULT;
    
    public Tournament(){
        
    }
    
    @Override
    public Population execute(Population population) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
