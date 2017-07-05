import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Amoreno on 7/4/17.
 *
 * This class corresponds to the type of graph representation Adjacency List.
 * It contains an ArrayList of Vertexs. Where each Vertex contains a list with the
 * identifiers of other Vertexs he is connected to.
 *
 * Here we assume Vertexs are added in order so Vertex at index i in the ArrayList
 * corresponds to Vertex with identifier i.
 */
public class AdjacencyList {

    private ArrayList<Vertex> adjacencyList;
    private Random random = new Random();
    private int numberOfNodes;

    /**
     * Constructor: Given a number of nodes V we initialize the
     * Vertex ArrayList with V Vertexs inside. In the beginning Vertexs
     * don't have any neighbours assigned to them.
     * @param V : Number of Nodes in the graph.
     */
    public AdjacencyList(int V){
        adjacencyList = new ArrayList<>();
        numberOfNodes = V;
        for (int i = 0; i < numberOfNodes; i++) {
            Vertex v = new Vertex(i);
            adjacencyList.add(v);
        }
    }

    /**
     * Return Vertex given its identifier.
     * @param identifier : Vertex identifier
     * @return Vertex with identifier passed as parameter.
     */
    public Vertex getVertex(int identifier){
        return adjacencyList.get(identifier);
    }

    /**
     * Return number of Edges in the graph.
     * This is calculated by getting the sum of the degrees of all nodes
     * and then dividing this by two. Great theorem!
     * @return Number of edges in graph as int.
     */
    public int getNumberOfEdges(){
        int sumOfDegrees = 0;
        for (Vertex v: adjacencyList) {
            sumOfDegrees += v.getDegree();
        }
        return sumOfDegrees/2;
    }

    /**
     * Return number of nodes in the graph
     * @return Number of nodes as int.
     */
    public int getNumberOfNodes(){
        return numberOfNodes;
    }

    /**
     * Gets a random edge from the graph. The idea is to get a random
     * Vertex from the Vertex ArrayList and then get a random identifier
     * from that Vertex neighbours list.
     * @return int [2] where each position corresponds to
     * a Vertex identifier thus representing an edge.
     */
    public int[] getEdgeFromGraph(){
        int [] edge = new int[2];
        int index = random.nextInt(adjacencyList.size());
        Vertex v = adjacencyList.get(index);

        while(v.getDegree() == 0){
            index = random.nextInt(adjacencyList.size());
            v = adjacencyList.get(index);
        }

        index = random.nextInt(v.getDegree());
        int u = v.getNeighbourAt(index);

        edge[0] = v.getVertexIdentifier();
        edge[1] = u;

        return edge;
    }

    /**
     * Eliminates all the edges incident to Vertex with identifier u.
     * Basically go through its neighbour list and eliminate himself from his neighbours
     * neighbours lists. Then clear entirely his neighbour list.
     * Eg: if u - v is an edge we need to go to v's neighbour list and eliminate u.
     * Then eliminate u's neighbours list.
     * @param u : Identifier of Vertex that we wish to eliminates incident edges from.
     */
    public void eliminateEdgesIncidentTo(Integer u){
        Vertex vertex = adjacencyList.get(u);
        for (int neighbourIdentifier: vertex.getNeighbours()) {
            Vertex neighbour = adjacencyList.get(neighbourIdentifier);
            if(neighbour.getDegree() == 0){
                numberOfNodes--;
            }
            neighbour.removeNeighbour(u);
        }
        vertex.clearNeighbours();
        numberOfNodes--;
    }

    /**
     * Return the ArrayList of Vertexs.
     * @return ArrayList<Vertex> list of nodes in the graph.
     */
    public ArrayList<Vertex> getAdjacencyList(){
        return adjacencyList;
    }

    /**
     * Returns identifier of the Vertex of maximum degree.
     * @return Identifier of Vertex of maximum degree.
     */
    public int getMaximumDegreeVertex(){
        int max = Integer.MIN_VALUE;
        Vertex maxVertex = null;
        for (Vertex v: adjacencyList) {
            if(v.getDegree() > max){
                maxVertex = v;
                max = v.getDegree();
            }
        }
        return maxVertex.getVertexIdentifier();
    }

}
