/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_v04;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.*;

/**
 *
 * @author Chorinca-Notebook
 */
public class Function {
    
    public static final Random RANDOM_GENERATOR = new Random();
    
    private final int X1 = 0;    
    private final int X2 = 1;

    private boolean[][] _individual;
    private double _beginDomainX1;    
    private double _beginDomainX2;
    private double _endDomainX1;    
    private double _endDomainX2;
    private double _sizeDomainX1;    
    private double _sizeDomainX2;
    private String _scriptFunctionMath;

    public Function(Function function){
        this(function._individual[0].clone(), 
            function._individual[1].clone(), 
            function._beginDomainX1,
            function._endDomainX1,
            function._beginDomainX2,
            function._endDomainX2, 
            function._scriptFunctionMath);
    }
    
    public Function(double beginDomainX1, double endDomainX1, double beginDomainX2, double endDomainX2, String scriptFunctionMath){
        this._beginDomainX1         = beginDomainX1;
        this._endDomainX1           = endDomainX1;
        this._beginDomainX2         = beginDomainX2;
        this._endDomainX2           = endDomainX2;
        this._sizeDomainX1          = Function.sizeDomain(this._beginDomainX1, this._endDomainX1);
        this._sizeDomainX2          = Function.sizeDomain(this._beginDomainX2, this._endDomainX2);
        this._scriptFunctionMath    = scriptFunctionMath;
        
        this._initIndividual();
    }
    
    public Function(boolean[] x1, boolean[] x2, double beginDomainX1, double endDomainX1, double beginDomainX2, double endDomainX2, String scriptFunctionMath){
        this._beginDomainX1         = beginDomainX1;
        this._endDomainX1           = endDomainX1;
        this._beginDomainX2         = beginDomainX2;
        this._endDomainX2           = endDomainX2;
        this._sizeDomainX1          = Function.sizeDomain(this._beginDomainX1, this._endDomainX1);
        this._sizeDomainX2          = Function.sizeDomain(this._beginDomainX2, this._endDomainX2);
        this._scriptFunctionMath    = scriptFunctionMath;
        
        this._initIndividual(x1, x2);
    }
    
    /**
     * Inicializar Individuo
     */
    private void _initIndividual() {      
        this._initIndividual(
                Function.generateRandomIndividual(Function.calculateSizeAllelo(Function.convertRealToInteger(this._sizeDomainX1))), 
                Function.generateRandomIndividual(Function.calculateSizeAllelo(Function.convertRealToInteger(this._sizeDomainX2))));
    }
    
    private void _initIndividual(boolean[] x1, boolean[] x2) {
        this._individual        = new boolean[2][];
        this._individual[X1]    = new boolean[Function.calculateSizeAllelo(Function.convertRealToInteger(this._sizeDomainX1))];
        this._individual[X2]    = new boolean[Function.calculateSizeAllelo(Function.convertRealToInteger(this._sizeDomainX2))];        
        this._individual[X1]    = x1;
        this._individual[X2]    = x2;
    }
    
    public final double Fitness() {
        return Function.parseFunctionECMAScript(_scriptFunctionMath, getX1(), getX2());
    }
    
    public final double getX1() {
        return Function.convertBinaryToReal(this._beginDomainX1, getChromossome(X1), this._sizeDomainX1);
    }

    public final double getX2() {
        return Function.convertBinaryToReal(this._beginDomainX2, getChromossome(X2), this._sizeDomainX2);
    }
    
    public final void setGene(int indexChromosome, int indexGene, boolean valueGene) {
        this._individual[indexChromosome][indexGene] = valueGene;
    }
    
    public final boolean getGene(int indexChromosome, int indexGene) {
        return this._individual[indexChromosome][indexGene];
    }
    
    protected final String getChromossome(int index){
        String[] __chromosomes;
        __chromosomes = this.toString().split(" ");
        
        return __chromosomes[index];
    }
    
    @Override
    public final String toString(){
        final StringBuilder __result;
        __result = new StringBuilder(this._individual[X1].length + this._individual[X2].length + 1);
        
        for (int __indexGene = 0; __indexGene < _individual[X1].length; __indexGene++)
            if(_individual[X1][__indexGene])
                __result.append("1");
            else
                __result.append("0");
        
        // espaço em branco a dividir os chromossomes
        __result.append(" ");
        
        for (int __indexGene = 0; __indexGene < _individual[X2].length; __indexGene++)
            if(_individual[X2][__indexGene])
                __result.append("1");
            else
                __result.append("0");
        
        return __result.toString();
    }
    
    
    
    public static int calculateSizeAllelo(int value){
        final int __sizeAllelo;
        
        __sizeAllelo = (int)Math.round((Math.log10(value) / Math.log10(2)) + 0.5);
        
        return __sizeAllelo;
    } 
    
    public static int convertRealToInteger(double realValue){
        final int __integerValue;
        final int __fourDecimalsHouses;
        
        __fourDecimalsHouses    = 10000;
        __integerValue          = (int)(realValue * __fourDecimalsHouses);
        
        return __integerValue;
    }
    
    public static int convertBinaryToInteger(String binaryValue){
        final int __base;
        __base = 2;
        return Integer.parseInt(binaryValue, __base);
    } 
    
    public static double sizeDomain(double value1, double value2){
        return value2-value1;
    }

    public static double convertBinaryToReal(double beginDomain, String binary, double sizeDomain){
        double __valueRealOfBinary;
        __valueRealOfBinary = Function.convertBinaryToInteger(binary);
        
        return beginDomain + __valueRealOfBinary * ( sizeDomain / ( Math.pow(2, Function.calculateSizeAllelo((int)__valueRealOfBinary)) - 1 ) );
    }

    public static double parseFunctionECMAScript(String script, double x1, double x2){
        double              __resultValue;
        ScriptEngineManager __scriptEngineManager;
        ScriptEngine        __ECMAScript;
        ScriptEngineFactory __ECMAScriptParse;
        String              __programScript;
        StringBuffer        __scriptInFunction;
        
        __scriptInFunction = new StringBuffer(script.length());
        __scriptInFunction.append("function functionMath(x1, x2) { return ");
        __scriptInFunction.append(script);
        __scriptInFunction.append("; }");
        
        __resultValue           = Double.NaN;
        __scriptEngineManager   = new ScriptEngineManager();
        __ECMAScript            = __scriptEngineManager.getEngineByName("ECMAScript");
        __ECMAScriptParse       = __ECMAScript.getFactory();
        __programScript         = __ECMAScriptParse.getProgram(__scriptInFunction.toString());
        
        try {
            __ECMAScript.eval(__programScript);
            
            if (__ECMAScript instanceof Invocable) {
                
                Invocable __invokeFunction = (Invocable) __ECMAScript;
                
                try {
                    
                    Object __valueReturn = __invokeFunction.invokeFunction("functionMath", x1, x2);
                    if(__valueReturn instanceof Double) 
                        __resultValue = (Double)__valueReturn;
                    
                } catch (NoSuchMethodException | ScriptException ex) {
                }
            }

        } catch (ScriptException ex) {
            Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return __resultValue;
    }
    
    public static boolean[] generateRandomIndividual(int size) {
        boolean[] __newIndividual = new boolean[size];
        // fill individual width indexes between 0 and size
        for (int __indexGene = 0; __indexGene < __newIndividual.length; __indexGene++) {
            __newIndividual[__indexGene] = RANDOM_GENERATOR.nextBoolean();
        }
        return __newIndividual;
    }

}
