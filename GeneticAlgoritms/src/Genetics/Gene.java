/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Genetics;

import java.util.Random;

/**
 *
 * @author diogoantonio
 */
public class Gene<T> {
    
    // T stands for "Type"
    private T _allele;

    public Gene(T valueAllele) {
        this._allele = valueAllele;
    }

    public T getAllele() {
        return _allele;
    }

    public void setAllele(T allele) {
        this._allele = allele;
    }

}
