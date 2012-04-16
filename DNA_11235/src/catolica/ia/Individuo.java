package catolica.ia;  
  
import java.util.ArrayList;  
import java.util.Collection;  
  
import catolica.ia.Cromossomo;  
  
/** 
 * Esta classe tem o objetivo de prover a abstração necessaria para o funcionamento 
 * de um individuo. 
 * 
 * @author Daniel Gatis Carrazzoni 
 */  
public class Individuo implements Comparable {  
  
   private static final int TAMANHO_DO_DNA = 1;  
  
     
   private Cromossomo[] dna;  
  
   private boolean mutante;  
     
   /** 
    * construtor. 
    * @param dna array de cromossomos. 
    */  
   public Individuo(Cromossomo[] dna) {  
      this.dna = dna;  
   }  
     
   /** 
    * construtor simples. 
    * 
    */  
   public Individuo() {  
      this.dna = new Cromossomo[TAMANHO_DO_DNA];  
  
      for (int i = 0; i < dna.length; i++) {  
         this.dna[i] = new Cromossomo();  
      }  
   }  
     
   /** 
    * faz o cruzamento entre individuos e depois faz a mutacao no filho. 
    * @param individuo individuo para cruzar. 
    * @param taxaDeMutacao taxa de mutacao. 
    * @return filho gerado do cruzamento. 
    */  
   public Individuo cruzar(Individuo individuo, double taxaDeMutacao) {  
  
      boolean mutante = false;  
  
      Collection<Cromossomo> novoDna = new ArrayList<Cromossomo>();  
  
      for (int i = 0; i < dna.length; i++) {  
         Cromossomo novoCromossomo = getDna()[i].crossover(individuo  
               .getDna()[i]);  
         if (novoCromossomo.mutation(taxaDeMutacao)) {  
            mutante = true;  
         }  
         novoDna.add(novoCromossomo);  
      }  
  
      Cromossomo[] temp = new Cromossomo[TAMANHO_DO_DNA];  
  
      Individuo filho = new Individuo(novoDna.toArray(temp));  
      filho.setMutante(mutante);  
  
      return filho;  
   }  
     
   /** 
    *  retorna o fenotipo custo do individuo. 
    * @return custo. 
    */  
   public double getCusto() {  
      int limiteSuperior = 20;  
      int limiteInferior = 1;  
        
      return limiteInferior + ((limiteSuperior-limiteInferior)/255.0) *dna[0].getFenotipo(0, 8);  
   }  
  
   /** 
    *  retorna o fenotipo beneficio do individuo. 
    * @return beneficio. 
    */  
   public double getBeneficio() {  
      int limiteSuperior = 100;  
      int limiteInferior = 1;  
        
        
      return limiteInferior + ((limiteSuperior-limiteInferior)/255.0) *dna[0].getFenotipo(8, 16);  
   }  
  
   /** 
    *  retorna o dna do indivduo. 
    * @return array de cromossomos. 
    */  
   public Cromossomo[] getDna() {  
      return dna;  
   }  
  
   /** 
    *  @see #toString() 
    */  
   public String toString() {  
      StringBuffer saida = new StringBuffer("Individuo: \n");  
  
      for (int i = 0; i < dna.length; i++)  
         saida.append(dna[i].toString() + "\n");  
  
      saida.append("mutante: " + isMutante() + "\n");  
      saida.append("custo: " + getCusto() + "\n");  
      saida.append("beneficio: " + getBeneficio() + "\n");  
      saida.append("fitness: " + getFitness() + "\n");  
  
      return saida.toString();  
   }  
     
   /** 
    *  retorna o fitness do individuo. 
    *  maximiza o beneficio e reduz o custo. 
    * @return fitness 
    */  
   public double getFitness() {  
      return 1.00 / ((getCusto() / getBeneficio()) + getCusto());  
   }  
  
   /** 
    *  compara dois individuos 
    * @param inidviduo 
    * @return 0 se for igual, 1 se for maior, -1 se for menor 
    */  
   public int compareTo(Object inidviduo) {  
      Individuo that = (Individuo) inidviduo;  
  
      double esse = this.getFitness();  
      double aquele = that.getFitness();  
  
      return new Double(esse).compareTo(new Double(aquele));  
   }  
  
   /** 
    * retorna se o individuo sofreu mutacao. 
    * @return true caso tenha sofrido mutacao. 
    */  
   public boolean isMutante() {  
      return mutante;  
   }  
  
   /** 
    *  seta o individuo mutante. 
    * @param mutante true ou false. 
    */  
   public void setMutante(boolean mutante) {  
      this.mutante = mutante;  
   }  
  
   /** 
    *  @see #equals(Object) 
    */  
   public boolean equals(Object individuo) {  
      if (individuo == this) {  
         return true;  
      }  
      if (!(individuo instanceof Individuo)) {  
         return false;  
      }  
      Individuo that = (Individuo) individuo;  
  
      return this.getDna().equals(that.getDna());  
   }  
}