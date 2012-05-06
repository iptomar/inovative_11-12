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
    
    public double distanciaEntreCidades(Cidade cidadeA, Cidade cidadeB) {
        double xd = cidadeA.X - cidadeB.X;
        double yd = cidadeA.Y - cidadeB.Y;

        return Math.sqrt(xd*xd + yd*yd);
    }
}
