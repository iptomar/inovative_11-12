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
public class F1 extends Problem {

    //define domain of Genes
    static{
        MIN =-0.5;
        MAX = 0.5;
    }
    
    public F1() {
        //one gene
        super(1);
    }

    @Override
    protected double calculateFunction() {
        // f(x) = - x^2
        return -value[0] * value[0];
    }
}
