import java.util.ArrayList;

/**
 * Created by Amoreno on 7/4/17.
 *
 * This class represents a vertex in a graph represented by its adjacency list.
 * @field int vertexIdentifier : Number that identifies each vertex.
 * @field ArrayList<Integer> : ArrayList containing identifiers of the vertex neighbours.
 */
public class Vertex {

    private int vertexIdentifier;
    private ArrayList<Integer> adjacencyList;

    /**
     * Constructor: sets Vertex id and initializes its neighbours list.
     * @param id : Vertex id.
     */
    public Vertex(int id){
        vertexIdentifier = id;
        adjacencyList = new ArrayList<>();
    }

    /**
    * Adds a vertex identified by its ID to the vertex neighbour's list.
     * @param neighbourId: ID of the vertex being added as a neighbour.
     */
    public void addNeighbour(int neighbourId){
        adjacencyList.add(new Integer(neighbourId));
    }

    /**
     * Removes a vertex identified by its ID from this vertex neighbourhood.
     * @param neighbourId : ID of the vertex being removed.
     */
    public void removeNeighbour(int neighbourId){
        adjacencyList.remove(new Integer(neighbourId));
    }

    /**
     * Returns degree of the vertex.
     * @return degree of vertex as an int.
     */
    public int getDegree(){
        return adjacencyList.size();
    }

    /**
     * Removes all neighbours from this vertex.
     */
    public void clearNeighbours(){
        adjacencyList.clear();
    }

    /**
     * Get this vertex identifier.
     * @return Vertex identifier as an int.
     */
    public int getVertexIdentifier(){
        return vertexIdentifier;
    }

    /**
     * Returns identifier of neighbour at index i in neighbourhood list.
     * @param i : Index of neighbour we wish to retrieve.
     * @return Identifier of neighbour at index i as an int.
     */
    public Integer getNeighbourAt(int i){
        return adjacencyList.get(i);
    }

    /**
     * Returns list of this Vertex neighbours.
     * @return List of this Vertex neighbours as an ArrayList<Integers>.
     */
    public ArrayList<Integer> getNeighbours(){
        return adjacencyList;
    }


}
