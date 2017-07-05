/**
 * Created by Amoreno on 7/4/17.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * This class is used for generating graph test instances. It includes static methods
 * for generating, saving and loading graph to and from memory. Specifically from .txt files.
 * The format generated is as follows:
 *
 * Number of nodes
 * Node0 NodeJ
 * Node0 NodeI
 * Node1 NodeL
 * Node1 NodeM
 *
 * Where the first line indicates that there is an edge between Node0 and NodeJ.
 */
public class GraphGenerator {

    /**
     * Generates a graph with an specified number of nodes and putting an edge between every pair
     * of nodes with probability p.
     * @param numberOfNodes : Number of nodes in the generated graph.
     * @param p : Probability that any two given nodes have an edge between them.
     * @return AdjacencyList representing the generated graph.
     */
    public static AdjacencyList generate(int numberOfNodes, double p){
        AdjacencyList graph = new AdjacencyList(numberOfNodes);
        for (int i = 0; i < numberOfNodes; i++) {
            Vertex v = graph.getVertex(i);
            for (int j = 0; j < numberOfNodes; j++) {
                if(Math.random() > (1 - p) && i != j){
                    v.addNeighbour(j);
                }
            }
        }

        return graph;
    }

    /**
     * Given a AdjacencyList representation of a graph and and output file name
     * it saves the graph a to .txt file with format specified in the beggining of this class
     * documentation.
     *
     * @param aGraph : AdjacencyList representing graph to be saved.
     * @param outputFileName : Name of the .txt file where the graph will be stored.
     * @throws IOException
     */
    public static void saveGraph(AdjacencyList aGraph, String outputFileName) throws IOException{
        Path outputFilePath = Paths.get(outputFileName);
        StringBuilder line = new StringBuilder();

        int numberOfNodes = aGraph.getNumberOfNodes();
        line.append(numberOfNodes);
        line.append(System.lineSeparator());
        Files.write(outputFilePath,line.toString().getBytes(), CREATE, APPEND);

        ArrayList<Vertex> adjList = aGraph.getAdjacencyList();

        for (Vertex v: adjList) {
            ArrayList<Integer> neighbours = v.getNeighbours();
            for (int neighbour: neighbours) {
                line.setLength(0);
                line.append(v.getVertexIdentifier());
                line.append(" ");
                line.append(neighbour);
                line.append(System.lineSeparator());
                Files.write(outputFilePath, line.toString().getBytes(), CREATE, APPEND);
            }
        }
    }

    /**
     * Loads a graph from memory. It is important to note that the graph need to be in the
     * corresponding format.
     *
     * @param inputFileName : Name of .txt file where the graph is stored.
     * @return AdjacencyList representing the loaded graph.
     */
    public static AdjacencyList loadGraph(String inputFileName){
        AdjacencyList aGraph = null;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            int numberOfNodes = Integer.parseInt(br.readLine());
            aGraph = new AdjacencyList(numberOfNodes);

            String line;
            while ((line = br.readLine()) != null) {
                String [] nodes = line.split(" ");
                Vertex v = aGraph.getVertex(Integer.parseInt(nodes[0]));
                v.addNeighbour(Integer.parseInt(nodes[1]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return aGraph;
    }


}
