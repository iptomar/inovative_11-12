package catolica.ia; 
  
import java.util.Random;  
  
/** 
 * Esta classe tem o objetivo de prover a abstração necessaria para o funcionamento 
 * de um gene. 
 * 
 * @author Daniel Gatis Carrazzoni 
 */  
public class Gene implements Comparable {  
  
   private byte valor;  
  
     
   /** 
    * construtor simples. 
    */  
   public Gene() {  
      Random r = new Random();  
      this.valor = (byte) r.nextInt(2);  
   }  
     
   /** 
    *  seta um valor para o gene 0 ou 1 
    * @param valor valor. 
    */  
   public Gene(byte valor) {  
      setValor(valor);  
   }  
     
   /** 
    *  retorna o valor do gene. 
    * @return valor. 
    */  
   public byte getValor() {  
      return valor;  
   }  
     
   /** 
    *  causa a mutacao no gene dependendo da porcentagem. 
    *  
    * @param porcentagem 
    * @return caso tenha ocorrido a mutacao no gene retorna true. 
    */  
   public boolean mutation(double porcentagem) {  
      boolean retorno = false;  
      if (Math.random() < porcentagem) {  
         if (valor == 1)  
            valor = 0;  
         else  
            valor = 1;  
         retorno = true;  
      }  
      return retorno;  
   }  
     
   /** 
    *  seta um valor no gene. 
    *   
    * @param valor valor. 
    */  
   public void setValor(byte valor) {  
      if (valor > 1) {  
         this.valor = 1;  
      } else {  
         if (valor < 0) {  
            this.valor = 0;  
         }  
      }  
      this.valor = valor;  
   }  
  
   /** 
    *  @see #toString() 
    */  
   public String toString() {  
      return String.valueOf(valor);  
   }  
     
   /** 
    *  @see #equals(Object) 
    */  
   public boolean equals(Object cromossomo) {  
      if (cromossomo == this) {  
         return true;  
      }  
      if (!(cromossomo instanceof Gene)) {  
         return false;  
      }  
      Gene that = (Gene) cromossomo;  
  
      return this.getValor() == that.getValor();  
   }  
  
   public int compareTo(Object cromossomo) {  
      return new Byte(valor).compareTo((Byte)cromossomo);  
   }  
}