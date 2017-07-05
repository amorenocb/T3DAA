import java.io.IOException;

/**
 * Created by Amoreno on 7/4/17.
 */
public class InputGenerator {

    public static void main(String[] args) throws IOException{
        int [] sizes = {10,11,12,13,14,15,16,17};

        for (int size: sizes) {
            double [] probabilities = {0.005, 0.05, 0.3, 0.6, 0.9};
            for (double p: probabilities) {
                String name = size+"-"+p+".txt";
                AdjacencyList aGraph = GraphGenerator.generate((int)Math.pow(2,size), p);
                GraphGenerator.saveGraph(aGraph, name, p);
            }
        }

    }

}
