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
public class Gene {

    protected Random randomGenerator = new Random();
    // T stands for "Type"
    Object _allele;

    public Gene(Object valueAllele) {
        this._allele = valueAllele;
    }

    public Object getAllele() {
        return _allele;
    }

    public void setAllele(Object allele) {
        this._allele = allele;
    }

    public void generateRandomAllele() {
        //TODO: implementar este 
        if(_allele instanceof Integer){
           _allele = randomGenerator.nextInt();
        } else if(_allele instanceof Double) {
           _allele = randomGenerator.nextDouble();
        } else if(_allele instanceof Float){
            _allele = randomGenerator.nextFloat();
        }else if(_allele instanceof Boolean){
            _allele = randomGenerator.nextBoolean();
        }
        
    }
    public static void main(String[] args) {
        Gene g = new Gene( new Integer(3));
        g.generateRandomAllele();
        System.out.println(g._allele);
    }
    
}
