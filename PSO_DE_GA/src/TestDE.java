
import Algorithms.DE;
import RealCoded.*;

///****************************************************************************/
///****************************************************************************/
///****     Copyright (C) 2012                                             ****/
///****     António Manuel Rodrigues Manso                                 ****/
///****     e-mail: manso@ipt.pt                                           ****/
///****     url   : http://orion.ipt.pt/~manso    manso@ipt.pt             ****/
///****     Instituto Politécnico de Tomar                                 ****/
///****     Escola Superior de Tecnologia de Tomar                         ****/
///****                                                                    ****/
///****************************************************************************/
///****     This software was build with the purpose of learning.          ****/
///****     Its use is free and is not provided any guarantee              ****/
///****     or support.                                                    ****/
///****     If you met bugs, please, report them to the author             ****/
///****                                                                    ****/
///****************************************************************************/
///****************************************************************************/
/**
 *
 * @author ZULU
 */
public class TestDE {

    public static void main(String[] args) {
        //problema com 20 variaveris
         F4_Rastrigin.DIM = 20;
        DE de = new DE();
//        System.out.println("Solution:" + de.solve(1000, 50, new F1()));
//        System.out.println("Solution:" + de.solve(100, 50, new F2()));
        System.out.println("Solution:" + de.solve(10000, 150, new F6_Shifted_Ackley()));

    }
}
