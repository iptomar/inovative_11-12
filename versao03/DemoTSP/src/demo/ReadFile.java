/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Chorinca-Notebook
 */
public class ReadFile {
    
    private String _filename;
    private Problem _problem;
    
    public ReadFile() {
        this._filename = "C:\\TSP\\a280.tsp";
        this._problem = new Problem();
    }
    
    public void read() throws FileNotFoundException, IOException{
        
        BufferedReader __in = new BufferedReader(new FileReader(this._filename));
        String __leitura;

        this._problem.Name              = __in.readLine();
        this._problem.Comment           = __in.readLine();
        this._problem.Type              = __in.readLine();
        this._problem.Dimension         = __in.readLine();
        this._problem.Edge_Weight_Type  = __in.readLine();
        
        // Le o "NODE_COORD_SECTION" onde indica onde comecam os dados
        __in.readLine();
        
        // So para quando chegar ao fim do ficheiro
        while (!(__leitura = __in.readLine()).equals("EOF")) { 
            System.out.println(__leitura);
            
            Cidade __cidade = new Cidade();
            String[] __arrayDados = __leitura.split(" ");
            int indexDados = 0;
            
            // Passar espacos em branco
            while(__arrayDados[indexDados].equals(""))indexDados++;
            
            __cidade.Index = Integer.parseInt(__arrayDados[indexDados]);
            
            // Passar espacos em branco
            do {indexDados++;} while(__arrayDados[indexDados].equals(""));
            
            __cidade.X = Integer.parseInt(__arrayDados[indexDados]);
            
            // Passar espacos em branco
             do {indexDados++;} while(__arrayDados[indexDados].equals(""));
            
            __cidade.Y = Integer.parseInt(__arrayDados[indexDados]);

            
            this._problem.Cidades.add(__cidade);
        }
    }
    
    public int[][] convertToCostMatrix() {
        int[][] __costMatrix = new int[this._problem.Cidades.size()][this._problem.Cidades.size()];
        
        // Calcula a distancia para todas as cidades e devolve em forma de matriz de custos
        // Atenção: O custo de ir para a mesma cidade, CidadeA -> CidadeA, tem custo de um e sera
        // acrescentado ao fitness, mas segundo a formula dada pelo site é assim que se deve calcular
        // as distancias, se mais tarde vir que essa distancia deve ser 0 então deve se alterar aqui neste
        // ciclo
        for (int __indexCidadeA = 0; __indexCidadeA < this._problem.Cidades.size(); __indexCidadeA++) {
            for (int __indexCidadeB = 0; __indexCidadeB < this._problem.Cidades.size(); __indexCidadeB++) {
                __costMatrix[__indexCidadeA][__indexCidadeB] = this._problem.distanciaEntreCidades(
                        this._problem.Cidades.get(__indexCidadeA), 
                        this._problem.Cidades.get(__indexCidadeB));
            }
        }
        
        return __costMatrix;
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        ReadFile __readFile = new ReadFile();
        __readFile.read();
        _writeToConsoleMatrix(__readFile.convertToCostMatrix());
    }
    
    private static void _writeToConsoleMatrix(int[][] matrix) {
        StringBuilder __result = new StringBuilder();
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                __result.append(matrix[i][j]);
                __result.append(" ");
            }
            __result.append("\n");
        }
        
        System.out.println(__result);
    }

    
    
}
