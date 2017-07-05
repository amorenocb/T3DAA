import java.util.ArrayList;

/**
 * Created by Amoreno on 7/4/17.
 */
public class NotApproximation {

    private AdjacencyList aGraph;
    private ArrayList<Integer> vertexCover;

    public void setGraph(AdjacencyList aGraph){
        this.aGraph = aGraph;
        this.vertexCover = new ArrayList<>();
    }

    public ArrayList<Integer> getVertexCover(){
        while(aGraph.getNumberOfEdges() > 0){
            int maxDegreeVertex = aGraph.getMaximumDegreeVertex();
            vertexCover.add(maxDegreeVertex);
            aGraph.eliminateEdgesIncidentTo(maxDegreeVertex);
        }

        return vertexCover;
    }

}
