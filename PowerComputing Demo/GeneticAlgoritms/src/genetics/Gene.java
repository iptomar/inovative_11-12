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
     * Construtor que recebe um objecto Gene<T> como parametro, e cria uma cópia.
     * É necessário quando se aplica os operadores, para não ficar com a referência
     * á população inicial.
     * @param newGene 
     */
    public Gene(Gene<T> newGene){
        // Fazer nova copia do gene, quando o allelo é do tipo boolean
        if(newGene.getAllele() instanceof Boolean[]){
            //cast ao allelo que é passado como parametro através do objecto boolean[]
            //e atribui o seu tamanha á variável size_allelo
            int __sizeAllelo = ((Boolean[])newGene.getAllele()).length;
            //criação do novo allelo com o tamanho anterior
            Boolean[] __newAllelo = new Boolean[__sizeAllelo];
            
            //inicializa cada valor do allelo com base no gene que é passado como parametro
            //bit a bit atribui o valor que do allelo passado como parâmetro ao novo allelo criado anteriormente
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
    
    /**
     * Método que devolve o valor guardado dentro do gene
     * @return 
     */
    @Override
    public String toString() {
        //Optimização que ao criar uma nova string, é reservado automaticamente
        //um espaço em memória que fica disponível para adicionar novos caracteres futuramente.
        //Como já tem espaço reservado em memória, ao adicionar novos dados já não é preciso reservar espaço novamente
        StringBuilder __output = new StringBuilder();
        
        // se for uma instancia/allelo do tipo Boolean[] entao mostramos o valor da seguinte forma
        if(this._allele instanceof Boolean[]){
            Boolean[] __allelo = (Boolean[])this._allele;
            for (int __indexAlleloValue = 0; __indexAlleloValue < __allelo.length; __indexAlleloValue++) {
                __output.append(__allelo[__indexAlleloValue] ? "1" : "0");
            }
        } else {
            //caso seja de um tipo ainda não implementado mostra a seguinte mensagem
            __output.append("Não esta definido ainda!!!");
        }
        
        return __output.toString();
    }

}
