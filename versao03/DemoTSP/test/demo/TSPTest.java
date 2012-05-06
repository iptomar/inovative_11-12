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
    
    @Test
    public void testGeneratorTwoPointsDistinctsSequential(){
        int[]   __points;
        int     __maxValueExclusive;
        
        __points            = new int[]{ 0, 0 };
        __maxValueExclusive = 10;

        for (int __cycleOf100 = 0; __cycleOf100 < 100; __cycleOf100++) {
            TSP._generatorTwoPointsDistinctsSequential(__points, __maxValueExclusive);
            assertFalse(__points[0] == __points[1]);
        }
    }
    
    @Test
    public void testGeneratorTwoPointsDistinctsSequentialSecondPointGreaterThanFirstPoint(){
        int[]   __points;
        int     __maxValueExclusive;
        
        __points            = new int[]{ 0, 0 };
        __maxValueExclusive = 10;
        
        for (int __cycleOf100 = 0; __cycleOf100 < 100; __cycleOf100++) {
            TSP._generatorTwoPointsDistinctsSequential(__points, __maxValueExclusive);
            assertFalse(__points[0] > __points[1]);
        }
    }
    
    @Test
    public void testGeneratorTwoPointsDistinctsSequentialFirstPointNot0(){
        int[]   __points;
        int     __maxValueExclusive;
        
        __points            = new int[]{ 0, 0 };
        __maxValueExclusive = 10;
        
        for (int __cycleOf100 = 0; __cycleOf100 < 100; __cycleOf100++) {
            TSP._generatorTwoPointsDistinctsSequential(__points, __maxValueExclusive);
            assertFalse(__points[0] == 0);
        }
    }
    
    @Test
    public void testGeneratorTwoPointsDistinctsSequentialSecondPointNotEqualToMaxValueExclusive(){
        int[]   __points;
        int     __maxValueExclusive;
        
        __points            = new int[]{ 0, 0 };
        __maxValueExclusive = 10;
        
        for (int __cycleOf100 = 0; __cycleOf100 < 100; __cycleOf100++) {
            TSP._generatorTwoPointsDistinctsSequential(__points, __maxValueExclusive);
            System.out.println(__points[1]);
            assertTrue(__points[1] < __maxValueExclusive - 1);
        }
    }

    @Test
    public void testPartiallyMatchedCrossoverLogicMatch(){
        int                 __dimensionProgenitors;
        int[]               __pointsOfCut;
        int[]               __father;
        int[]               __mother;
        int[]               __son;
        int[]               __daughter;
        
        __dimensionProgenitors  = 10;
        __father                = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        __mother                = new int[] { 9, 5, 7, 6, 2, 8, 3, 4, 1, 0 };
        __son                   = new int[__dimensionProgenitors];
        __daughter              = new int[__dimensionProgenitors];
        __pointsOfCut           = new int[] { 3, 5 };
        
        TSP._partiallyMatchedCrossoverLogic(__father, __mother, __son, __daughter, __pointsOfCut);

        assertArrayEquals("Son dont match", new int[] { 0, 1, 4, 6, 2, 8, 3, 7, 5, 9 }, __son);
        assertArrayEquals("Daughter dont match", new int[] { 9, 8, 7, 3, 4, 5, 6, 2, 1, 0 }, __daughter);
    }
    
    @Test
    public void testPartiallyMatchedCrossoverLogicMatchPointsTogether(){
        int                 __dimensionProgenitors;
        int[]               __pointsOfCut;
        int[]               __father;
        int[]               __mother;
        int[]               __son;
        int[]               __daughter;
        
        __dimensionProgenitors  = 10;
        __father                = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        __mother                = new int[] { 9, 5, 7, 6, 2, 8, 3, 4, 1, 0 };
        __son                   = new int[__dimensionProgenitors];
        __daughter              = new int[__dimensionProgenitors];
        __pointsOfCut           = new int[] { 4, 5 };
        
        TSP._partiallyMatchedCrossoverLogic(__father, __mother, __son, __daughter, __pointsOfCut);

        assertArrayEquals("Son dont match", 
                new int[] { 0, 1, 4, 3, 2, 8, 6, 7, 5, 9 }, __son);
        assertArrayEquals("Daughter dont match", 
                new int[] { 9, 8, 7, 6, 4, 5, 3, 2, 1, 0 }, __daughter);
    }
    
    @Test
    public void testPartiallyMatchedCrossoverLogicMatchPointsOnExtremes(){
        int                 __dimensionProgenitors;
        int[]               __pointsOfCut;
        int[]               __father;
        int[]               __mother;
        int[]               __son;
        int[]               __daughter;
        
        __dimensionProgenitors  = 10;
        __father                = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        __mother                = new int[] { 9, 5, 7, 6, 2, 8, 3, 4, 1, 0 };
        __son                   = new int[__dimensionProgenitors];
        __daughter              = new int[__dimensionProgenitors];
        __pointsOfCut           = new int[] { 1, 8 };
        
        TSP._partiallyMatchedCrossoverLogic(__father, __mother, __son, __daughter, __pointsOfCut);

        assertArrayEquals("Son dont match", 
                new int[] { 0, 5, 7, 6, 2, 8, 3, 4, 1, 9 }, __son);
        assertArrayEquals("Daughter dont match", 
                new int[] { 9, 1, 2, 3, 4, 5, 6, 7, 8, 0 }, __daughter); 
    }

    @Test
    public void testPartiallyMatchedCrossoverLogicDontRepetValuesInGenes(){
        int     __dimensionProgenitors;
        int[]   __pointsOfCut;
        int[]   __father;
        int[]   __mother;
        int[]   __son;
        int[]   __daughter;
        
        __dimensionProgenitors  = 10;
        __father                = TSP._generateRandomIndividual(__dimensionProgenitors);
        __mother                = TSP._generateRandomIndividual(__dimensionProgenitors);
        __son                   = new int[__dimensionProgenitors];
        __daughter              = new int[__dimensionProgenitors];
        __pointsOfCut           = new int[2];
        
        for (int __cycle100 = 0; __cycle100 < 100; __cycle100++) {
            TSP._generatorTwoPointsDistinctsSequential(__pointsOfCut, __dimensionProgenitors);
            TSP._partiallyMatchedCrossoverLogic(__father, __mother, __son, __daughter, __pointsOfCut);

            assertFalse("Foram encontrados values repetidos nos genes do filho", _verificRepetValuesInArray(__son));
            assertFalse("Foram encontrados values repetidos nos genes da filha", _verificRepetValuesInArray(__daughter));
        }
    }
    
    @Test
    public void testGenerateRandomIndividualDontRepetValuesInGenes(){
        int     __dimensionIndividual;
        int[]   __individual;
        
        for (int __cycle100 = 0; __cycle100 < 100; __cycle100++) {
            __dimensionIndividual   = 100;
            __individual            = TSP._generateRandomIndividual(__dimensionIndividual);

            assertFalse("Existem valores nos genes repetidos", _verificRepetValuesInArray(__individual));
        }
    }
    
    private boolean _verificRepetValuesInArray(int[] array){
        for (int __indexPointSelect = 0; __indexPointSelect < array.length; __indexPointSelect++) 
            for (int __indexPointToCompare = __indexPointSelect+1; __indexPointToCompare < array.length; __indexPointToCompare++) 
                if(array[__indexPointSelect] == array[__indexPointToCompare])
                    return true;

        return false;
    }
}
