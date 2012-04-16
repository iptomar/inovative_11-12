package catolica.ia;
  
import java.util.ArrayList;  
import java.util.Collection;  
import java.util.Iterator;  
  
/** 
 * Esta classe tem o objetivo de prover a abstração necessaria para o 
 * funcionamento de uma populacao de individuos. 
 * 
 * @author Daniel Gatis Carrazzoni 
 *  
 */


/**
 *
 * @author mendes
 */
public class Populacao {
    
    
       private Collection<Individuo> individuos;  
  
   private int tamanho;  
  
   /** 
    * construtor. 
    *  
    * @param tamanho 
    *            tamanho da populacao. 
    */  
   public Populacao(int tamanho) {  
      this.tamanho = tamanho;  
  
      individuos = new ArrayList<Individuo>(tamanho);  
      initPopulacao(tamanho);  
   }  
     
   /** 
    * inicializa populacao. 
    *  
    * @param tamanho 
    *            tamanho da populacao. 
    */  
   private void initPopulacao(int tamanho) {  
      for (int i = 0; i < tamanho; i++) {  
         individuos.add(new Individuo());  
      }  
   }  
  
   /** 
    * cria uma nova geração da população. 
    *  
    * @param taxaDeCruzamento 
    *            taxa de cruzamento. 
    * @param taxaDeMutacao 
    *            taxa de mutacao. 
    */  
   public void novaGeracao(double taxaDeCruzamento, double taxaDeMutacao) {  
      int iteracoes = (int)(taxaDeCruzamento * tamanho);  
        
      for (int i = 0; i < iteracoes; i++) {  
         addIndividuo(getCasal().cruzar(taxaDeMutacao));  
      }  
   }  
  
   /** 
    * adiciona um individuo na população. caso o individuo a ser adicionado 
    * seja um mutante ele é colocado no lugar do individuo de menor fitness da 
    * populacao. caso a individuo não seja mutante só adiciona se o fitness do 
    * individuo seja maior que o menor fitness da populacao. 
    *  
    * @param individuo novo individuo. 
    */  
   private void addIndividuo(Individuo individuo) {  
      double menorFit = Integer.MAX_VALUE;  
      int indiceASubstituir = 0;  
  
      Individuo[] temp = new Individuo[tamanho];  
      individuos.toArray(temp);  
  
      for (int i = 0; i < temp.length; i++) {  
         if (temp[i].getFitness() <= menorFit) {  
            indiceASubstituir = i;  
            menorFit = temp[i].getFitness();  
         }  
      }  
  
      if (individuo.isMutante()) {  
         individuos.remove(temp[indiceASubstituir]);  
         individuos.add(individuo);  
      } else {  
         if (temp[indiceASubstituir].getFitness() < individuo.getFitness()) {  
            individuos.remove(temp[indiceASubstituir]);  
            individuos.add(individuo);  
         }  
      }  
   }  
     
   /** 
    * seleciona um casal apto ao cruzamento. 
    * @return casal. 
    */  
   private Casal getCasal() {  
  
      Individuo pai = selecionar();  
  
      Individuo mae;  
      do {  
         mae = selecionar();  
      } while (pai.equals(mae));  
  
      return new Casal(pai, mae);  
   }  
  
   /** 
    * selecao baseada no metodo da roleta. 
    * @return o individuo selecionado. 
    */  
   private Individuo selecionar() {  
      double somatorioDosFitness = 0;  
      double fitnessAcumulado = 0;  
      Individuo retorno = null;  
  
      Collection<Object[]> roleta = new ArrayList<Object[]>();  
  
      Iterator<Individuo> it = individuos.iterator();  
  
      while (it.hasNext()) {  
         somatorioDosFitness += it.next().getFitness();  
      }  
  
      Iterator<Individuo> it2 = individuos.iterator();  
      while (it2.hasNext()) {  
         Individuo individuo = it2.next();  
  
         roleta.add(new Object[] {  
               new Double(fitnessAcumulado),  
               new Double(fitnessAcumulado + individuo.getFitness()  
                     / somatorioDosFitness), individuo });  
  
         fitnessAcumulado += individuo.getFitness() / somatorioDosFitness;  
      }  
  
      double sorteio = Math.random();  
  
      Iterator<Object[]> it3 = roleta.iterator();  
      while (it3.hasNext()) {  
  
         Object[] atual = it3.next();  
           
         double limiteInferior = ((Double) atual[0]).doubleValue();  
         double limiteSuperior = ((Double) atual[1]).doubleValue();  
         Individuo individuo = (Individuo) atual[2];  
  
         if (sorteio >= limiteInferior && sorteio < limiteSuperior) {  
            retorno = individuo;  
            break;  
         }  
      }  
  
      return retorno;  
   }  
  
   /** 
    * @see #toString() 
    */  
   public String toString() {  
      StringBuffer saida = new StringBuffer("Populacao: \n");  
  
      Iterator<Individuo> it = individuos.iterator();  
  
      while (it.hasNext()) {  
         saida.append("\n");  
         saida.append(it.next().toString());  
      }  
      return saida.toString();  
   } 
    
    
}
