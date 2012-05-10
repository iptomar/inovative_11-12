/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_v04;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Chorinca-Notebook
 */
public class Demo_v04Test {
    
    @Test
    public void testCrossover() {
        final Function[]    __descendents;
        final int           __pointCutX1;
        final int           __pointCutX2;

        __pointCutX1    = 5;
        __pointCutX2    = 5;
        __descendents   = __runCrossover(__pointCutX1, __pointCutX2);
        
        assertEquals("Testar se o filho foi bem criado no crossover.", "1111111111 1111111111", __descendents[0].toString());
        assertEquals("Testar se a filha foi bem criado no crossover.", "0000000000 0000000000", __descendents[1].toString());
    }
    
    @Test
    public void testCrossoverBeging() {
        final Function[]    __descendents;
        final int           __pointCutX1;
        final int           __pointCutX2;

        __pointCutX1    = 1;
        __pointCutX2    = 1;
        __descendents   = __runCrossover(__pointCutX1, __pointCutX2);
        
        assertEquals("Testar se o filho foi bem criado no crossover.", "1000011111 1000011111", __descendents[0].toString());
        assertEquals("Testar se a filha foi bem criado no crossover.", "0111100000 0111100000", __descendents[1].toString());
    }
    
    @Test
    public void testCrossoverEnd() {
        final Function[]    __descendents;
        final int           __pointCutX1;
        final int           __pointCutX2;

        __pointCutX1    = 9;
        __pointCutX2    = 9;
        __descendents   = __runCrossover(__pointCutX1, __pointCutX2);
        
        assertEquals("Testar se o filho foi bem criado no crossover.", "1111100001 1111100001", __descendents[0].toString());
        assertEquals("Testar se a filha foi bem criado no crossover.", "0000011110 0000011110", __descendents[1].toString());
    }
        
    private Function[] __runCrossover(int pointCutX1, int pointCutX2){
        final Function  __father;
        final Function  __mother;
        final boolean[] __x1Father;
        final boolean[] __x2Father;
        final boolean[] __x1Mother;
        final boolean[] __x2Mother;
        
        final double    __beginDomainX1;
        final double    __beginDomainX2;
        final double    __endDomainX1;
        final double    __endDomainX2;
        final String    __scriptFitness;
        
        __x1Father = new boolean[] { true, true, true, true, true, false, false, false, false, false };
        __x2Father = new boolean[] { true, true, true, true, true, false, false, false, false, false};
        __x1Mother = new boolean[] { false, false, false, false, false, true, true, true, true, true };
        __x2Mother = new boolean[] { false, false, false, false, false, true, true, true, true, true};
        
        __beginDomainX1 = -3.0;
        __beginDomainX2 = 4.1;
        __endDomainX1   = 12.1;
        __endDomainX2   = 5.8;
        __scriptFitness = "21.5+x1*Math.sin(4*Math.PI*x1)+x2*Math.sin(20*Math.PI*x2)";
        
        __father = new Function(__x1Father, __x2Father, __beginDomainX1, __endDomainX1, __beginDomainX2, __endDomainX2, __scriptFitness);
        __mother = new Function(__x1Mother, __x2Mother, __beginDomainX1, __endDomainX1, __beginDomainX2, __endDomainX2, __scriptFitness);

        return Demo_v04.crossover(__father, __mother, pointCutX1, pointCutX2);
    }
    
    
    @Test
    public void testRandomNumber() {
        int __size  = 10;
        int __valueRandom;
        for (int __cycle = 0; __cycle < 1000; __cycle++) {
            __valueRandom = Demo_v04.pointCutRandom(__size);
            
            assertFalse("Testar se o valor é maior que 9", __valueRandom > 9);
            assertFalse("Testar se o valor é menor que 1", __valueRandom < 1);
        }
    }
    
}
