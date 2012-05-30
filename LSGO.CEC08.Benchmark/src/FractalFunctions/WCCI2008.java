package FractalFunctions;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class WCCI2008 {

    public static final int NUM_PROBLEMS = 30;
    public static final int NUM_EVALUATIONS = 1000;
    private static final int START_NUMBER = 1;
    private static final boolean OUTPUT_ON = false;
    private static final boolean OUTPUT_TO_FILE = false;
    private static final String dir = ".";
    private static final String file = "results.txt";
    private PrintStream out;
    private static final int FRACTAL_DEPTH = 48;
    private static final Object[] PROBLEM1 = {"Sphere", new Integer(3)};
    private static final Object[] PROBLEM2 = {"Cube", new Integer(4)};
    private static final Object[] PROBLEM3 = {"Sphere", new Integer(4)};
    private static final Object[][] PROBLEMS = {PROBLEM1, PROBLEM2, PROBLEM3};
    private ArrayList problemSet = new ArrayList(30);
    private ListIterator problemIterator;
    private FractalFunction2D currentProblem;
    private int problemNumber;
    private Random ran = new Random(1L);
    private double[] origin = new double[2];
    private double scale;
    private double result;
    private double bestResult;
    private int count;

    public WCCI2008(String name, String affiliation, String email)
            throws Exception {
        File target = new File(".", "results.txt");
        target.createNewFile();

        out = System.out;

        List problems = Arrays.asList(PROBLEMS);
        while (problemSet.size() < 30) {
            problemSet.addAll(problems);
        }
        Collections.shuffle(problemSet, new Random(1L));
        problemIterator = problemSet.listIterator();
        problemNumber = 0;
    }

    public void nextProblem()
            throws Exception {
        if (problemNumber >= 30) {
            throw new Exception("All problems finished.");
        }

        problemNumber += 1;

        Object[] nextProblem = (Object[]) (Object[]) problemIterator.next();
        currentProblem = new FractalFunction2D((String) nextProblem[0], 48, ((Integer) nextProblem[1]).intValue(), 1 + 7 * problemNumber);
        origin[0] = ran.nextDouble();
        origin[1] = ran.nextDouble();
        scale = Math.pow(2.0D, ran.nextDouble() * 40.0D);
        System.out.println(origin[0] + "," + origin[1] + " " + scale);
        count = 0;
        bestResult = 1.7976931348623157E+308D;
    }

    public double evaluate(double[] point) {
        count += 1;
        if (count <= 1000) {
            double[] scaledPoint = {origin[0] + point[0] / scale, origin[1] + point[1] / scale};
            result = currentProblem.evaluate(scaledPoint);
            if (result < bestResult) {
                bestResult = result;
            }
            if (count != 1000);
        } else {
            result = 0.0D;
        }
        return result;
    }

    public double[] evaluate(double[][] points) {
        double[] results = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            results[i] = evaluate(points[i]);
        }
        return results;
    }

    public List evaluate(List points) {
        List results = new ArrayList(points.size());
        ListIterator li = points.listIterator();
        while (li.hasNext()) {
            results.add(new Double(evaluate((double[]) (double[]) li.next())));
        }
        return results;
    }

    public int getProblemNumber() {
        return problemNumber;
    }

    public int getCount() {
        return count;
    }
}