import java.util.ArrayList;

/**
 * Created by Amoreno on 7/4/17.
 */
public class TwoApproximation {

    private AdjacencyList graph;
    private ArrayList<Integer> vertexCover;

    public void setGraph(AdjacencyList aGraph){
        graph = aGraph;
        vertexCover = new ArrayList<>();
    }

    public ArrayList<Integer> getVertexCover(){
        int [] anEdge;

        while(graph.getNumberOfEdges() > 0){

            anEdge = graph.getEdgeFromGraph();

            vertexCover.add(anEdge[0]);
            vertexCover.add(anEdge[1]);

            graph.eliminateEdgesIncidentTo(anEdge[0]);
            graph.eliminateEdgesIncidentTo(anEdge[1]);

        }

        return vertexCover;
    }


}
