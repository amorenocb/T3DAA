import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Amoreno on 7/4/17.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        AdjacencyList aGraph = GraphGenerator.loadGraph("testGraph.txt");
        TwoApproximation twoApproximation = new TwoApproximation();
        twoApproximation.setGraph(aGraph);

        System.out.println("Number of Nodes in graph: "+aGraph.getNumberOfNodes());
        System.out.println("Number of Edges in graph: "+aGraph.getNumberOfEdges());

        ArrayList<Integer> vertexCover = twoApproximation.getVertexCover();
        System.out.println("Vertex cover for 2-Approximation: ");
        for (int v: vertexCover) {
            System.out.print(v+" ");
        }

        System.out.println(" ");

        aGraph = GraphGenerator.loadGraph("testGraph.txt");
        NotApproximation notApproximation = new NotApproximation();
        notApproximation.setGraph(aGraph);

        System.out.println("Number of Nodes in graph: "+aGraph.getNumberOfNodes());
        System.out.println("Number of Edges in graph: "+aGraph.getNumberOfEdges());

        vertexCover = notApproximation.getVertexCover();
        System.out.println("Vertex cover for Not-Approximation: ");
        for (int v: vertexCover) {
            System.out.print(v+" ");
        }

    }

}
