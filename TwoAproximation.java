import java.util.ArrayList;

/**
 * Created by Amoreno on 7/4/17.
 */
public class TwoAproximation {

    private AdjacencyMatrix aGraph;
    private ArrayList<Integer> vertexCover;

    public void setGraph(AdjacencyMatrix aGraph){
        this.aGraph = aGraph;
        this.vertexCover = new ArrayList<>();
    }

    public ArrayList<Integer> getVertexCover(){
        int [] anEdge;
        while(aGraph.getNumberOfEdges() > 0){
            anEdge = aGraph.getEdge();
            vertexCover.add(anEdge[0]);
            vertexCover.add(anEdge[1]);
            aGraph.eliminateEdges(anEdge[0]);
            aGraph.eliminateEdges(anEdge[1]);
        }

        return vertexCover;
    }


}
