/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catolica.ia;

/**
 *
 * @author mendes
 */
public class Casal {
    
    
       private Individuo pai;  
   private Individuo mae;  
     
   /** 
    *  construtor. 
    * @param pai individuo 1. 
    * @param mae individuo 2. 
    */  
   public Casal(Individuo pai, Individuo mae) {  
      this.pai = pai;  
      this.mae = mae;  
   }  
     
   /** 
    *  realiza o cruzamento entre o casal. 
    * @param taxaDeMutacao taxa de mutacao. 
    * @return filho gerado do cruzamento. 
    */  
   public Individuo cruzar(double taxaDeMutacao) {  
      return pai.cruzar(mae, taxaDeMutacao);  
   } 
    
}
