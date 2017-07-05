import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * This script receives as an argument a name for a directory where the inputs are located.
 * It then goes through every input an computes it vertex cover with the two algorithms implemented.
 * The results for every input are stored together for the same algorithm.
 * For the 2-Approximation the results for every input are stored in results2Approximation.txt .
 * Each line contains the following:
 * Input file name, Probability of having edge between any two given vertexs, Number of vertexs, Number of edges, Vertex cover size.
 * Created by Amoreno on 7/4/17.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        Path inputsFolderPath = Paths.get(args[0]);
        Path resultPath2App = Paths.get("results2Approximation.txt");
        Path resultPathNotApp = Paths.get("resultsNotApproximation.txt");

        NotApproximation notApproximation = new NotApproximation();
        TwoApproximation twoApproximation = new TwoApproximation();
        ArrayList<Integer> vertexCover;

        // Here we store the paths to all input files.
        List<Path> inputPaths;
        try (Stream<Path> paths = Files.walk(inputsFolderPath)) {
            inputPaths = paths.filter(Files::isRegularFile).collect(Collectors.toList());
        }

        StringBuilder line = new StringBuilder();
        for (Path aPath: inputPaths) {
            line.setLength(0);
            AdjacencyList aGraph = GraphGenerator.loadGraph(aPath.toString());
            twoApproximation.setGraph(aGraph);


            // Construct the line to be written to the file.
            line.append(aPath.getFileName().toString());
            line.append(",");
            line.append(aGraph.getProbabilityOfEdge());
            line.append(",");
            line.append(aGraph.getNumberOfNodes());
            line.append(",");
            line.append(aGraph.getNumberOfEdges());
            line.append(",");
            // We need to do this here because this changes the amount of nodes.
            vertexCover = twoApproximation.getVertexCover();
            line.append(vertexCover.size());
            line.append(System.lineSeparator());
            // Write to it.
            Files.write(resultPath2App, line.toString().getBytes(), CREATE, APPEND);
            System.out.println("Finished 2-Approximation of file: "+aPath.getFileName().toString());

            line.setLength(0);
            aGraph = GraphGenerator.loadGraph(aPath.toString());
            notApproximation.setGraph(aGraph);

            // Construct the line to be written to the file.
            line.append(aPath.getFileName().toString());
            line.append(",");
            line.append(aGraph.getProbabilityOfEdge());
            line.append(",");
            line.append(aGraph.getNumberOfNodes());
            line.append(",");
            line.append(aGraph.getNumberOfEdges());
            line.append(",");
            // We need to do this here because this changes the amount of nodes.
            vertexCover = notApproximation.getVertexCover();
            line.append(vertexCover.size());
            line.append(System.lineSeparator());
            // Write to it.
            Files.write(resultPathNotApp, line.toString().getBytes(), CREATE, APPEND);
            System.out.println("Finished Not-Approximation of file: "+aPath.getFileName().toString());
        }

    }

}
