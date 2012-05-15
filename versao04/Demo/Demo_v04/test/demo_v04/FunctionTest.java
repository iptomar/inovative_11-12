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
public class FunctionTest {

    /**
     * Testar se o tamanho do allelo igual a 18 para o valor 151000.
     */
    @Test
    public void testCalculateSizeAlleloTo151000() {
        int __sizeAllelo;
        
        __sizeAllelo = Function.calculateSizeAllelo(151000);

        // Testar se o tamanho do allelo igual a 18 para o valor 151000
        assertEquals(18, __sizeAllelo); 
    }
    
    /**
     * Testar se o tamanho do allelo igual a 15 para o valor 17000.
     */
    @Test
    public void testCalculateSizeAlleloTo17000() {
        int __sizeAllelo;
        
        __sizeAllelo = Function.calculateSizeAllelo(17000);

        // Testar se o tamanho do allelo igual a 15 para o valor 17000
        assertEquals(15, __sizeAllelo); 
    }
    
    /**
     * Testar a conversão de real para inteiro com quatro casas decimais. 15,1 para 151000
     */
    @Test
    public void testConvertRealToInteger15_1() {
        int __integerNumber;
        
        __integerNumber = Function.convertRealToInteger(15.1);

        assertEquals(151000, __integerNumber); 
    }
    
    /**
     * Testar a conversão de real para inteiro com quatro casas decimais. 1,7 para 17000
     */
    @Test
    public void testConvertRealToInteger1_7() {
        int __integerNumber;
        
        __integerNumber = Function.convertRealToInteger(1.7);

        assertEquals(17000, __integerNumber); 
    }

    @Test
    public void testConvertBinaryToIntegerTest1(){
        int __integerNumber;
        String __numberBinary;
        
        __numberBinary = "111101001010111000";
        __integerNumber = Function.convertBinaryToInteger(__numberBinary);

        assertEquals(250552, __integerNumber); 
    }
    
    @Test
    public void testConvertBinaryToIntegerTest2(){
        int __integerNumber;
        String __numberBinary;
        
        __numberBinary = "001001011000000";
        __integerNumber = Function.convertBinaryToInteger(__numberBinary);

        assertEquals(4800, __integerNumber); 
    }

    @Test
    public void testSizeDomainTest15_1(){
        assertEquals(15.1, Function.sizeDomain(-3.0, 12.1), 1);
    }
    
    @Test
    public void testSizeDomainTest1_7(){
        assertEquals(1.7, Function.sizeDomain(4.1, 5.8), 1);
    }
    
    @Test
    public void testConvertBinaryToReal11_4323(){
        double  __beginDomain; 
        String  __binaryValue; 
        double  __sizeDomain;
        
        __beginDomain = -3.0; 
        __binaryValue = "111101001010111000"; 
        __sizeDomain  = 15.1;
        
        assertEquals(11.4323, Function.convertBinaryToReal(__beginDomain, __binaryValue, __sizeDomain), 4);
    }
    
    @Test
    public void testConvertBinaryToReal4_3490(){
        double  __beginDomain; 
        String  __binaryValue; 
        double  __sizeDomain;
        
        __beginDomain = 4.1; 
        __binaryValue = "001001011000000"; 
        __sizeDomain  = 1.7;
        
        assertEquals(4.3490, Function.convertBinaryToReal(__beginDomain, __binaryValue, __sizeDomain), 4);
    }
    
    @Test
    public void testParseFunctionECMAScriptSum(){
        String __script;
        double __x1;
        double __x2;
        double __result;
        
        __script    = "x1+x2";
        __x1        = 1;
        __x2        = 1;
        __result    = 2;
        
        assertEquals(__result, Function.parseFunctionECMAScript(__script, __x1, __x2), 0);
    }
    
    @Test
    public void testParseFunctionECMAScriptSumAndSubtraction(){
        String __script;
        double __x1;
        double __x2;
        double __result;
        
        __script    = "x1+x2-x1-x2-x2";
        __x1        = 1;
        __x2        = 1;
        __result    = -1;
        
        assertEquals(__result, Function.parseFunctionECMAScript(__script, __x1, __x2), 0);
    }

    @Test
    public void testParseFunctionECMAScriptMultiplication(){
        String __script;
        double __x1;
        double __x2;
        double __result;
        
        __script    = "x1*x2";
        __x1        = 2;
        __x2        = 10;
        __result    = 20;
        
        assertEquals(__result, Function.parseFunctionECMAScript(__script, __x1, __x2), 0);
    }

    @Test
    public void testParseFunctionECMAScriptDivision(){
        String __script;
        double __x1;
        double __x2;
        double __result;
        
        __script    = "x1/x2";
        __x1        = 5;
        __x2        = 2;
        __result    = 2.5;
        
        assertEquals(__result, Function.parseFunctionECMAScript(__script, __x1, __x2), 1);
    }
    
    
    @Test
    public void testParseFunctionECMAScriptMathFunction(){
        String __script;
        double __x1;
        double __x2;
        double __result;
        
        __script    = "Math.sin(3)";
        __x1        = 5;
        __x2        = 2;
        __result    = 0.0523;
        
        assertEquals(__result, Function.parseFunctionECMAScript(__script, __x1, __x2), 4);
    }
    
    @Test
    public void testParseFunctionECMAScriptFullExpression(){
        String __script;
        double __x1;
        double __x2;
        double __result;
        
        __script    = "21.5+x1*Math.sin(4*Math.PI*x1)+x2*Math.sin(20*Math.PI*x2)";
        __x1        = 11.4323;
        __x2        = 4.3490;
        __result    = 13.1743;
        
        assertEquals(__result, Function.parseFunctionECMAScript(__script, __x1, __x2), 4);
    }
    
    @Test
    public void testParseFunctionECMAScriptFullExpressionMax(){
        String __script;
        double __x1;
        double __x2;
        double __result;
        
        __script    = "21.5+x1*Math.sin(4*Math.PI*x1)+x2*Math.sin(20*Math.PI*x2)";
        __x1        = 12.1;
        __x2        = 5.7250;
        __result    = 38.7328;
        
        assertEquals(__result, Function.parseFunctionECMAScript(__script, __x1, __x2), 4);
    }
    
    @Test
    public void testParseFunctionECMAScriptFullExpressionOverFlow(){
        String __script;
        double __x1;
        double __x2;
        double __result;
        
        __script    = "21.5+x1*Math.sin(4*Math.PI*x1)+x2*Math.sin(20*Math.PI*x2)";
        __x1        = 11.62639513547949;
        __x2        = 5.7270524323994385;
        __result    = 38.8041;
        
        assertEquals(__result, Function.parseFunctionECMAScript(__script, __x1, __x2), 4);
    }

    @Test
    public void testFunctionFitness() {
        final Function  __functionTest;
        final boolean[] __x1;
        final boolean[] __x2;
        final double    __beginDomainX1;
        final double    __beginDomainX2;
        final double    __endDomainX1;
        final double    __endDomainX2;
        final String    __scriptFitness;
        
        __x1            = new boolean[] { true,true,true,true,false,true,false,false,true,false,true,false,true,true,true,false,false,false };
        __x2            = new boolean[] { false,false,true,false,false,true,false,true,true,false,false,false,false,false,false };
        __beginDomainX1 = -3.0;
        __beginDomainX2 = 4.1;
        __endDomainX1   = 12.1;
        __endDomainX2   = 5.8;
        __scriptFitness = "21.5+x1*Math.sin(4*Math.PI*x1)+x2*Math.sin(20*Math.PI*x2)";
        
        __functionTest = new Function(
                __x1, 
                __x2, 
                __beginDomainX1, 
                __endDomainX1, 
                __beginDomainX2, 
                __endDomainX2, 
                __scriptFitness);
        
        assertEquals(13.1733, __functionTest.Fitness(), 4);
    }
    
    @Test
    public void testFunctionGetsX() {
        final Function  __functionTest;
        final boolean[] __x1;
        final boolean[] __x2;
        final double    __beginDomainX1;
        final double    __beginDomainX2;
        final double    __endDomainX1;
        final double    __endDomainX2;
        final String    __scriptFitness;
        
        __x1            = new boolean[] { true,true,true,true,false,true,false,false,true,false,true,false,true,true,true,false,false,false };
        __x2            = new boolean[] { false,false,true,false,false,true,false,true,true,false,false,false,false,false,false };
        __beginDomainX1 = -3.0;
        __beginDomainX2 = 4.1;
        __endDomainX1   = 12.1;
        __endDomainX2   = 5.8;
        __scriptFitness = "21.5+x1*Math.sin(4*Math.PI*x1)+x2*Math.sin(20*Math.PI*x2)";
        
        __functionTest = new Function(
                __x1, 
                __x2, 
                __beginDomainX1, 
                __endDomainX1, 
                __beginDomainX2, 
                __endDomainX2, 
                __scriptFitness);
        
        assertEquals("Testar o valor de x1.", 11.4323, __functionTest.getX1(), 4);
        assertEquals("Testar o valor de x2.", 4.3490, __functionTest.getX2(), 4);
    }
    
    @Test
    public void testFunctionIndividualRandom() {
        final Function  __functionTest;
        final double    __beginDomainX1;
        final double    __beginDomainX2;
        final double    __endDomainX1;
        final double    __endDomainX2;
        final String    __scriptFitness;
        
        __beginDomainX1 = -3.0;
        __beginDomainX2 = 4.1;
        __endDomainX1   = 12.1;
        __endDomainX2   = 5.8;
        __scriptFitness = "21.5+x1*Math.sin(4*Math.PI*x1)+x2*Math.sin(20*Math.PI*x2)";
        
        __functionTest = new Function(
                __beginDomainX1, 
                __endDomainX1, 
                __beginDomainX2, 
                __endDomainX2, 
                __scriptFitness);
        
        assertEquals("Testar se foram criados os dois chromosomes.", true, __functionTest.getChromossome(0) != null && __functionTest.getChromossome(1) != null);
        assertEquals("Testar o tamanho de x1.", 18, __functionTest.getChromossome(0).length());
        assertEquals("Testar o tamanho de x2.", 15, __functionTest.getChromossome(1).length());
    }
}
