
import Algorithms.DE;
import RealCoded.F3D;
import Algorithms.PSO;
import Algorithms.RCGA;
import RealCoded.F4_Rastrigin;
import RealCoded.Problem;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author manso
 */
public class testALL {

    public static void main(String[] args) {
        Problem problem = new F4_Rastrigin();
        int Itera = 10000;
        int sizePop = 50;

        PSO pso = new PSO();
        DE de = new DE();
        RCGA ga = new RCGA();
        
        
        System.out.println("DE  :" + de.solve(Itera, sizePop, problem));
        System.out.println("PSO :" + pso.solve(Itera, sizePop, problem));
        System.out.println("GA  :" + ga.solve(Itera, sizePop, problem));

    }
}
