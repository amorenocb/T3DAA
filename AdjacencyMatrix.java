/**
 * Created by Amoreno on 7/4/17.
 */

import java.util.ArrayList;
import java.util.Random;

public class AdjacencyMatrix {

    private int [][] adjMat;
    private Random random;
    private int numberOfNodes;
    private int numberOfEdges;
    private int [] degrees;
    private ArrayList<Integer> validNodes;


    public AdjacencyMatrix(int V){
        degrees = new int[V];
        adjMat = new int[V][V];
        validNodes = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            validNodes.add(new Integer(i));
        }
        numberOfNodes = validNodes.size();
    }

    public void addEdge(int u, int v){
        adjMat[u][v] = 1;
        numberOfEdges++;
        degrees[u]++;
        degrees[v]++;
    }

    public int[] getEdge(){
        int [] anEdge = new int[2];
        int i = random.nextInt(validNodes.size());
        int j = random.nextInt(validNodes.size());
        while(i == j){
            i = random.nextInt(validNodes.size());
            j = random.nextInt(validNodes.size());
        }

        anEdge[0] = validNodes.get(new Integer(i));
        anEdge[1] = validNodes.get(new Integer(j));

        return anEdge;
    }

    public void eliminateEdges(int v){
        for (Integer i: validNodes) {
            if(adjMat[v][i.intValue()] == 1) {
                adjMat[v][i.intValue()] = adjMat[i.intValue()][v] = 0;
                numberOfEdges--;
                degrees[v]--;
                degrees[i.intValue()]--;
            }
        }
    }

    public void eliminateVertex(int v){
        eliminateEdges(v);
        validNodes.remove(new Integer(v));
        numberOfNodes = validNodes.size();
    }

    public int getNumberOfEdges(){
        return numberOfEdges;
    }

    public int getDegreeOfVertex(int v){
        int degree = 0;
        for (Integer i: validNodes) {
            if (adjMat[v][i.intValue()] == 1){
                degree++;
            }
        }

        return degree;
    }

    public int getNumberOfNodes(){
        return numberOfNodes;
    }
}
