/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Chorinca-Notebook
 */
public class TSPTest {

    @Test
    public void testInversionOrderWithParNumbersAndPointers0To7(){
        int[] __individuo = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
        int __pointer1 = 0;
        int __pointer2 = 7;
        
        TSP._invertOrderBetweenTwoPointers(__pointer1, __individuo, __pointer2);

        assertArrayEquals(__individuo, new int[] { 7, 6, 5, 4, 3, 2, 1, 0});
    }
    
    @Test
    public void testInversionOrderWithParNumbersAndPointers3To4(){
        int[] __individuo = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
        int __pointer1 = 3;
        int __pointer2 = 4;
        
        TSP._invertOrderBetweenTwoPointers(__pointer1, __individuo, __pointer2);
        
        assertArrayEquals(__individuo, new int[] { 0, 1, 2, 4, 3, 5, 6, 7 });
    }
    
    @Test
    public void testInversionOrderWithParNumbersAndPointers7To0(){
        int[] __individuo = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
        int __pointer1 = 7;
        int __pointer2 = 0;
        
        TSP._invertOrderBetweenTwoPointers(__pointer1, __individuo, __pointer2);
        
        assertArrayEquals(__individuo, new int[] { 7, 1, 2, 3, 4, 5, 6, 0 });
    }
    
    @Test
    public void testInversionOrderWithParNumbersAndPointers5To4(){
        int[] __individuo = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
        int __pointer1 = 5;
        int __pointer2 = 4;
        
        TSP._invertOrderBetweenTwoPointers(__pointer1, __individuo, __pointer2);
        
        assertArrayEquals(__individuo, new int[] { 1, 0, 7, 6, 5, 4, 3, 2 });
    }
 
    @Test
    public void testInversionOrderWithImparNumbersAndPointers0To6(){
        int[] __individuo = new int[] { 0, 1, 2, 3, 4, 5, 6 };
        int __pointer1 = 0;
        int __pointer2 = 6;
        
        TSP._invertOrderBetweenTwoPointers(__pointer1, __individuo, __pointer2);
        
        assertArrayEquals(__individuo, new int[] { 6, 5, 4, 3, 2, 1, 0});
    }
    
    @Test
    public void testInversionOrderWithImparNumbersAndPointers3To4(){
        int[] __individuo = new int[] { 0, 1, 2, 3, 4, 5, 6 };
        int __pointer1 = 3;
        int __pointer2 = 4;
        
        TSP._invertOrderBetweenTwoPointers(__pointer1, __individuo, __pointer2);
        
        assertArrayEquals(__individuo, new int[] { 0, 1, 2, 4, 3, 5, 6 });
    }
    
    @Test
    public void testInversionOrderWithImparNumbersAndPointers6To0(){
        int[] __individuo = new int[] { 0, 1, 2, 3, 4, 5, 6};
        int __pointer1 = 6;
        int __pointer2 = 0;
        
        TSP._invertOrderBetweenTwoPointers(__pointer1, __individuo, __pointer2);
        
        assertArrayEquals(__individuo, new int[] { 6, 1, 2, 3, 4, 5, 0 });
    }
    
    @Test
    public void testInversionOrderWithImparNumbersAndPointers5To4(){
        int[] __individuo = new int[] { 0, 1, 2, 3, 4, 5, 6 };
        int __pointer1 = 5;
        int __pointer2 = 4;
        
        TSP._invertOrderBetweenTwoPointers(__pointer1, __individuo, __pointer2);
        
        assertArrayEquals(__individuo, new int[] { 2, 1, 0, 6, 5, 4, 3 });
    }
    
    @Test
    public void testGeneratorTwoPointsDistincts(){
        int[] __points = new int[]{ 0, 0 };
        int __maxValueExclusive = 10;
        
        TSP._generatorTwoPointsDistincts(__points, __maxValueExclusive);
        
        assertFalse(__points[0] == __points[1]);
    }
    
    @Test
    public void testGeneratorTwoPointsDistinctsRespectMaxValue(){
        int[] __points = new int[]{ 0, 0 };
        int __maxValueExclusive = 10;
        
        for (int i = 0; i < 100; i++) {
            TSP._generatorTwoPointsDistincts(__points, __maxValueExclusive);

            assertFalse(__points[0] >= __maxValueExclusive);
            assertFalse(__points[1] >= __maxValueExclusive); 
        }
    }
    
    @Test
    public void testGeneratorTwoPointsDistinctsRespectMaxValueExclusive(){
        int[] __points = new int[]{ 0, 0 };
        int __maxValueExclusive = 10;
        
        for (int i = 0; i < 100; i++) {
            TSP._generatorTwoPointsDistincts(__points, __maxValueExclusive);
        
            assertTrue(__points[0] < __maxValueExclusive);
            assertTrue(__points[1] < __maxValueExclusive);
        }       
    }
    
    @Test
    public void testGeneratorTwoPointsDistinctsRespectMinValue0Inclusive(){
        int[] __points = new int[]{ 0, 0 };
        int __maxValueExclusive = 10;
        
        for (int i = 0; i < 100; i++) {
            TSP._generatorTwoPointsDistincts(__points, __maxValueExclusive);
        
            assertTrue(__points[0] >= 0);
            assertTrue(__points[1] >= 0);
        }       
    }

    @Test
    public void testExchangeValuesFromArrayInTwoPoints1And4(){
        int[] __points = new int[]{ 1, 4 };
        int[] __array = new int[] { 0, 1, 2, 3, 4, 5 };

        TSP._exchangeValuesFromArrayInTwoPoints(__array, __points);
        
        assertArrayEquals(new int[] { 0, 4, 2, 3, 1, 5 }, __array);
    }
    
    @Test
    public void testExchangeValuesFromArrayInTwoPoints4And1(){
        int[] __points = new int[]{ 1, 4 };
        int[] __array = new int[] { 0, 1, 2, 3, 4, 5 };

        TSP._exchangeValuesFromArrayInTwoPoints(__array, __points);
        
        assertArrayEquals(new int[] { 0, 4, 2, 3, 1, 5 }, __array);
    }
}
