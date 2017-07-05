import java.util.ArrayList;

/**
 * Created by Amoreno on 7/4/17.
 */
public class NotAproximation {

    private AdjacencyMatrix aGraph;
    private ArrayList<Integer> vertexCover;

    public void setGraph(AdjacencyMatrix aGraph){
        this.aGraph = aGraph;
        this.vertexCover = new ArrayList<>();
    }

    public ArrayList<Integer> getVertexCover(){
        while(aGraph.getNumberOfEdges() > 0){
            int maxDegreeVertex = aGraph.getMaximumDegreeVertex();
            vertexCover.add(maxDegreeVertex);
            aGraph.eliminateVertex(maxDegreeVertex);
        }

        return vertexCover;
    }

}
