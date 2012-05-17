
import RealCoded.F3D;
import RealCoded.F2;
import RealCoded.RealCodedGA;

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
public class TestRealCodedGA {

    public static void main(String[] args) {
        RealCodedGA ga = new RealCodedGA();
        //System.out.println("Solution:" + ga.solve(100, 50, new F1()));
//        System.out.println("Solution:" + ga.solve(100, 50, new F2()));
        System.out.println("Solution:" + ga.solve(100, 50, new F2()));

    }
}
