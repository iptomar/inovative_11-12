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
public class F3D extends Problem {

    //define domain of Genes
    static {
        MIN = -5;
        MAX = 5;
    }

    public F3D() {
        //two gene
        super(2);
    }

    @Override
    protected double calculateFunction() {
        // f(x) =  x1^3 - 2x^2 + 1;
        return Math.sin(value[0])  * Math.cos(value[1]);
    }
}
