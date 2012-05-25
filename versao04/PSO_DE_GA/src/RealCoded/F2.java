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
package RealCoded;

/**
 *
 * @author ZULU
 */
public class F2 extends Problem {

    //define domain of Genes
    static {
        MIN = -2;
        MAX = 2;
    }

    public F2() {
        //one gene
        super(1);
    }

    @Override
    protected double calculateFunction() {
        // f(x) =  x1^3 - 3x - 1;
        double x = value[0];
        return x*x*x - 3*x - 1;
    }
}
