package FractalFunctions;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args)
            throws Exception {
        WCCI2008 wcci = new WCCI2008("cara", "UWA", "cara@csse.uwa.edu.au");
        for (int problem = 1; problem <= 30; problem++) {
            wcci.nextProblem();
            double bestResult = 1.7976931348623157E+308D;
            for (int i = 0; i < 1000; i++) {
                double[] point = {Math.random(), Math.random()};
                double result = wcci.evaluate(point);
                if (result >= bestResult) {
                    continue;
                }
                bestResult = result;
            }
            System.out.println("Problem " + problem + ": " + bestResult);
        }

        wcci = new WCCI2008("cara", "UWA", "cara@csse.uwa.edu.au");
        for (int problem = 1; problem <= 30; problem++) {
            wcci.nextProblem();
            double[][] points = new double[1000][2];
            for (int i = 0; i < 1000; i++) {
                double[] point = {Math.random(), Math.random()};
                points[i] = point;
            }
            wcci.evaluate(points);
        }

        wcci = new WCCI2008("cara", "UWA", "cara@csse.uwa.edu.au");
        for (int problem = 1; problem <= 30; problem++) {
            wcci.nextProblem();
            List points = new ArrayList(1000);
            for (int i = 0; i < 1000; i++) {
                double[] point = {Math.random(), Math.random()};
                points.add(point);
            }
            wcci.evaluate(points);
        }

        TimingUtilities tu = new TimingUtilities();

        int dimension = 1000;
        String unitFunctionName = "DoubleDip";
        int fracDepth = 3;
        int density = 1;
    }
}