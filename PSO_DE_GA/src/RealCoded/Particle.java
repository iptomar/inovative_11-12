/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RealCoded;

/**
 *
 * @author manso
 */
public class Particle {

    Problem position; //position of the particle
    public Problem myBest;   //best position ocupied by this particle
    protected double[] velocity; //current velocity
    public static double c1 = 2; //learning factor from current best
    public static double c2 = 2; //leraning factor from global best

    /**
     * create a particle cloning the parameter
     *
     * @param template
     */
    public Particle(Problem template) {
        position = template.getClone();
        velocity = new double[template.value.length];
        myBest = position.getClone();
    }

    /**
     * initialize the particle in the random position
     */
    public void fillRandom() {
        position.fillRandom();
        velocity = new double[position.value.length];
        myBest = position.getClone();
    }

    /**
     * update particle position by the velocity
     */
    protected void updatePosition() {
        for (int i = 0; i < position.value.length; i++) {
            position.value[i] += velocity[i];
        }
    }

    /**
     * update particle velocity
     *
     * @param bestInPopulation global best
     */
    protected void updateVelocity(Problem bestInPopulation) {
        // v[] = v[] + c1 * rand() * (pbest[] - present[]) 
        //           + c2 * rand() * (gbest[] - present[]) 
        for (int i = 0; i < position.value.length; i++) {
            velocity[i] += c1 * Math.random() * (myBest.value[i] - position.value[i])
                    + c2 * Math.random() * (bestInPopulation.value[i] - position.value[i]);
        }
    }

    /**
     * performs the evolution of the particle
     *
     * @param best global best
     */
    public void evolve(Problem best) {
        updateVelocity(best);
        updatePosition();
        position.evaluate();
        //update my best position
        if (best.compareTo(position) < 0) {
            myBest = position.getClone();
        }
    }
}
