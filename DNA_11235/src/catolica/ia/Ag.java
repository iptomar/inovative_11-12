/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catolica.ia;
  
  
import java.io.File;  
import java.io.FileWriter;  
import java.io.IOException;  
  
//import catolica.ia.ser.Populacao;
/**
 *
 * @author mendes
 */
public class Ag {
    
    
    
      
   public static void main(String[] args) {  
  
      File arquivo = new File("resultado.txt");  
      FileWriter fr = null;  
  
      try {  
         fr = new FileWriter(arquivo);  
           
         System.out.println("Inicio do experimento! (processando...) ");  
         Populacao p = new Populacao(100);  
         for (int i = 0; i < 25; i++) {  
            fr.write("geracao: " + i + "\n");  
            p.novaGeracao(0.10, 0.01);  
            fr.write(p.toString());  
         }  
         System.out.println("fim do experimento!");  
         fr.close();  
      } catch (IOException e) {  
         // TODO Auto-generated catch block  
         e.printStackTrace();  
      }  
        
   } 
    
    
    
    
}
