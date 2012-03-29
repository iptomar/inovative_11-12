package genetics;
/**
 * Classe que representa um gene. Tem um objecto T do tipo allele, que é o valor
 * guardado dentro do gene.
 * @author goncalo
 * @param <T> 
 */
public class Gene<T> {

    private T _allele;
    
    public Gene(T valueAllelo) {
        this._allele = valueAllelo;
    }
    
    /**
     * Criação de um novo gene.
     * É criado um array de booleans com o tamanho dos allelos
     * @param newGene 
     */
    public Gene(Gene<T> newGene){
        // Fazer nova copia do gene
        if(newGene.getAllele() instanceof Boolean[]){
            int __sizeAllelo = ((Boolean[])newGene.getAllele()).length;
            Boolean[] __newAllelo = new Boolean[__sizeAllelo];
            
            for (int __indexAlleloValue = 0; __indexAlleloValue < __sizeAllelo; __indexAlleloValue++) {
                __newAllelo[__indexAlleloValue] = ((Boolean[])newGene.getAllele())[__indexAlleloValue];
            }
            
            this._allele = (T)__newAllelo;
        }else{
            this._allele = newGene.getAllele();
        }
    }

    public T getAllele() {
        return _allele;
    }

    public void setAllele(T allele) {
        this._allele = allele;
    }
    
    @Override
    public String toString() {
        StringBuilder __output = new StringBuilder();
        
        // se for uma instancia/allelo do tipo Boolean[] entao mostramos o valor da seguinte forma
        if(this._allele instanceof Boolean[]){
            Boolean[] __allelo = (Boolean[])this._allele;
            for (int __indexAlleloValue = 0; __indexAlleloValue < __allelo.length; __indexAlleloValue++) {
                __output.append(__allelo[__indexAlleloValue] ? "1" : "0");
            }
        } else {
            __output.append("Não esta definido ainda!!!");
        }
        
        return __output.toString();
    }

}
