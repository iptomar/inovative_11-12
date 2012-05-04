/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.util.ArrayList;

/**
 *
 * @author Chorinca-Notebook
 */
public class Problem {
    
    public String Name              = "";
    public String Comment           = "";
    public String Type              = "";
    public String Dimension         = "";
    public String Edge_Weight_Type  = "";
  
    public ArrayList<Cidade> Cidades = new ArrayList<Cidade>();
    
    public int distanciaEntreCidades(Cidade cidadeA, Cidade cidadeB) {
        int xd = cidadeA.X - cidadeB.X;
        int yd = cidadeA.Y - cidadeB.Y;
        
        // O 0.5 é porque o Math.round arredonda sempre para baixo, ou seja,
        // 1.5 fica sempre 1
        return (int)Math.round(Math.sqrt(xd*xd + yd*yd) + 0.5);
    }
}
