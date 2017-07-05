import java.util.ArrayList;

/**
 * Created by Amoreno on 7/4/17.
 */
public class Vertex {

    private int vertexIdentifier;
    private ArrayList<Integer> adjacencyList;

    public Vertex(int id){
        vertexIdentifier = id;
        adjacencyList = new ArrayList<>();
    }

    public void addNeighbour(int neighbourId){
        adjacencyList.add(new Integer(neighbourId));
    }

    public void removeNeighbour(int neighbourId){
        adjacencyList.remove(new Integer(neighbourId));
    }

    public int getDegree(){
        return adjacencyList.size();
    }

    public void clearNeighbours(){
        adjacencyList.clear();
    }

    public int getVertexIdentifier(){
        return vertexIdentifier;
    }

    public Integer getNeighbourAt(int i){
        return adjacencyList.get(i);
    }

    public ArrayList<Integer> getNeighbours(){
        return adjacencyList;
    }


}
