package Genetics;

import java.util.Random;

public class Gene<T> {
    
    static final Random random = new Random();
    
    private T _allele;

    public Gene(T valueAllele, int sizeAllelo) {
        this._allele = valueAllele;
    
        if(valueAllele instanceof Boolean[]){
            Boolean[] __allelo = new Boolean[sizeAllelo];
                
            // gerar de forma aleatoria os valores em binario para o allelo
            for (int __indexAllelo = 0; __indexAllelo < sizeAllelo; __indexAllelo++) {
                __allelo[__indexAllelo] = random.nextBoolean();
            }
                
            _allele = (T)__allelo;
        }
    }

    public T getAllele() {
        return _allele;
    }

    public void setAllele(T allele) {
        this._allele = allele;
    }

}
