package catolica.ia; 
  
/** 
 * Esta classe tem o objetivo de prover a abstração necessaria para o funcionamento 
 * de um cromosso. 
 * 
 * @author Daniel Gatis Carrazzoni 
 */  
public class Cromossomo {  
   private static final int TAMANHO_DO_CROMOSSOMO = 16;  
  
   private Gene[] genes;  
     
     
    /** 
     *  construtor simples. 
     * 
     */  
   public Cromossomo() {  
      genes = new Gene[TAMANHO_DO_CROMOSSOMO];  
      initCromossomo();  
   }  
  
   /** 
    *  inicializa o cromossomo de uma forma aleatoria. 
    * 
    */  
   private void initCromossomo() {  
      for (int i = 0; i < genes.length; i++) {  
         genes[i] = new Gene();  
      }  
   }  
  
   /** 
    *  faz o cruzamento entre cromossomos. 
    *  seguindo a regra: 
    *  posicao par : gene do pai 
    *  posicao impar: gene da mae 
    *   
    *  exemplo 
    *   
    *   pai : 01011100 
    *   mae : 11100010 
    *   
    * filho : 01001000 
    *  
    *  
    *   
    * @param cromossomo cromossomo para cruzar. 
    * @return cromossomo filho. 
    */  
   public Cromossomo crossover(Cromossomo cromossomo) {  
      Cromossomo novoCromossomo = new Cromossomo();  
  
      for (int i = 0; i < genes.length; i++) {  
         Gene novoGene;  
         if (i % 2 == 0) {  
            novoGene = new Gene(this.getGenes()[i]  
                  .getValor());  
         } else {  
            novoGene = new Gene(cromossomo.getGenes()[i]  
                  .getValor());  
         }  
         novoCromossomo.getGenes()[i] = novoGene;  
      }  
      return novoCromossomo;  
   }  
  
   /** 
    * causa a mutacao para cada gene cromossomo. 
    *  
    * @param porcentagem taxa de mutacao. 
    * @return caso o cromossomo tenha sofrido mutacao retorna true. 
    */  
   public boolean mutation(double porcentagem) {  
      boolean retorno = false;  
        
      for (int i = 0; i < genes.length; i++) {  
         if (genes[i].mutation(porcentagem)) {  
            retorno = true;  
         }  
      }  
      return retorno;  
   }  
     
   /** 
    *  retorna uma caracteristica baseado no cromossomo. 
    *   
    * @param geneInicial gene inicial. 
    * @param geneFinal gene final. 
    * @return valor da caracteristica. 
    */  
   public int getFenotipo(int geneInicial, int geneFinal) {  
      int valor = 0;  
  
      int delta = geneFinal - geneInicial;  
  
      for (int i = 0; i < delta; i++)  
         valor += genes[i + geneInicial].getValor()  
               * Math.pow(2, delta - i);  
  
      return valor / 2;  
   }  
  
   /** 
    *  pega o array de genes do cromossomo. 
    * @return um array de genes. 
    */  
   public Gene[] getGenes() {  
      return genes;  
   }  
  
   /** 
    *  seta um array de genes do cromossomo. 
    * @param genes array de genes. 
    */  
   public void setGenes(Gene[] genes) {  
      this.genes = genes;  
   }  
  
   /** 
    *  @see #toString() 
    */  
   public String toString() {  
      StringBuffer saida = new StringBuffer("Cromossomo: [ ");  
  
      for (int i = 0; i < genes.length; i++) {  
         if (i == TAMANHO_DO_CROMOSSOMO / 2) {  
            saida.append("| ");  
         }  
         saida.append(genes[i].toString());  
         saida.append(" ");  
      }  
      saida.append("]");  
      return saida.toString();  
   }  
  
   /** 
    *  @see #equals(Object) 
    */  
   public boolean equals(Object cromossomo) {  
      if (cromossomo == this) {  
         return true;  
      }  
      if (!(cromossomo instanceof Cromossomo)) {  
         return false;  
      }  
      Cromossomo that = (Cromossomo) cromossomo;  
  
      for (int i = 0; i < genes.length; i++) {  
         if (!this.getGenes()[i].equals(that.getGenes()[i])){  
            return false;  
         }  
      }  
      return true;  
   }  
  
}