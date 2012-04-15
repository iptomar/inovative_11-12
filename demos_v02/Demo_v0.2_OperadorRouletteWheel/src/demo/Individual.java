/*
 *
 * # #    # #    #  ####  #    #   ##   ##### # #    # ###### 
 * # ##   # ##   # #    # #    #  #  #    #   # #    # #      
 * # # #  # # #  # #    # #    # #    #   #   # #    # #####  
 * # #  # # #  # # #    # #    # ######   #   # #    # #      
 * # #   ## #   ## #    #  #  #  #    #   #   #  #  #  #      
 * # #    # #    #  ####    ##   #    #   #   #   ##   ###### 
 *                                                           
 *
 *                       
 * #    # # #    # #####  
 * ##  ## # ##   # #    # 
 * # ## # # # #  # #    # 
 * #    # # #  # # #    # 
 * #    # # #   ## #    # 
 * #    # # #    # #####  
 *
 * 
 * ======
 * Meta
 * ======

 * project: Life Inspiration
 * version: 0.2
 *
 */
package demo;

/**
 *
 * @author diogoantonio
 */
public class Individual implements Comparable<Individual> {
    
    private String _name;
    private int _fitness;
    private double _percentage;

    public Individual(String _name, int _fitness, int _percentage) {
        this._name = _name;
        this._fitness = _fitness;
        this._percentage = _percentage;
    }

    /**
     * @return the _name
     */
    public String getName() {
        return _name;
    }

    /**
     * @param name the _name to set
     */
    public void setName(String name) {
        this._name = name;
    }

    /**
     * @return the _fitness
     */
    public int getFitness() {
        return _fitness;
    }

    /**
     * @param fitness the _fitness to set
     */
    public void setFitness(int fitness) {
        this._fitness = fitness;
    }

    /**
     * @return the _percentage
     */
    public double getPercentage() {
        return _percentage;
    }

    /**
     * @param percentage the _percentage to set
     */
    public void setPercentage(double percentage) {
        this._percentage = percentage;
    }

    @Override
    public String toString() {
        return "["+_name+" "+_fitness+" "+_percentage+"]";
    }

    @Override
    public int compareTo(Individual t) { //descendant
        if(this._fitness == t.getFitness())
            return 0;
        else if(this._fitness > t.getFitness())
            return -1;
        else
            return 1;
    }
    
}
