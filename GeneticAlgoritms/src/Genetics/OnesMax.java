package Genetics;

import java.util.Random;

public class OnesMax extends Individual{

    static final int SIZE_ALLELO_DEFAULT = 10;
    private int _sizeAllelo = SIZE_ALLELO_DEFAULT;
    
    public OnesMax(int sizeAllelo){
        this._sizeAllelo = sizeAllelo;
    }
    
    @Override
    public int fiteness() {
        Integer numberOnes = new Integer(0);
        for (Chromosome chro : this) {
            for (Gene gene : chro) {
                if ((Boolean) gene.getAllele()) {
                    numberOnes++;
                }
            }
        }
        return numberOnes;
    }

}
