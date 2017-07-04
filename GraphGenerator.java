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

    public static AdjacencyMatrix generate(int numberOfNodes, int p){
        AdjacencyMatrix graph = new AdjacencyMatrix(numberOfNodes);
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                if(Math.random() > (1 - p)){
                    graph.addEdge(i,j);
                }
            }
        }

        return graph;
    }

    public static void saveGraph(AdjacencyMatrix aGraph, String outputFileName) throws IOException{
        Path outputFilePath = Paths.get(outputFileName);
        StringBuilder line = new StringBuilder();

        int numberOfNodes = aGraph.getNumberOfNodes();
        line.append(numberOfNodes);
        line.append(System.lineSeparator());
        Files.write(outputFilePath,line.toString().getBytes(), CREATE, APPEND);

        for (int i = 0; i < numberOfNodes; i++) {
            ArrayList<Integer> neighbors = aGraph.getNodeNeighbors(i);
            for (int neighbor: neighbors) {
                line.setLength(0);
                line.append(i);
                line.append(",");
                line.append(neighbor);
                line.append(System.lineSeparator());
                Files.write(outputFilePath, line.toString().getBytes(), CREATE, APPEND);
            }
        }
    }

    public static AdjacencyMatrix loadGraph(String inputFileName){
        AdjacencyMatrix aGraph = null;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {

            int numberOfNodes = Integer.parseInt(br.readLine());

            aGraph = new AdjacencyMatrix(numberOfNodes);

            String line;
            while ((line = br.readLine()) != null) {
                String [] nodes = line.split(",");
                aGraph.addEdge(Integer.parseInt(nodes[0]), Integer.parseInt(nodes[1]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return aGraph;
    }

}
