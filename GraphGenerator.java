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

public class GraphGenerator {

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
