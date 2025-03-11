package advent_of_code.year_2015.day_9;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2015/day_9/input.txt");

        Graph graph = new Graph();

        for (String line : input) {
            Matcher matcher = Pattern.compile("(?<source>\\w+) to (?<destination>\\w+) = (?<distance>\\d+)").matcher(line);

            if (!matcher.matches()) {
                throw new IllegalArgumentException("WTF : unable to parse line: " + line);
            }

            Node source = new Node(matcher.group("source"));
            Node destination = new Node(matcher.group("destination"));
            int distance = Integer.parseInt(matcher.group("distance"));
            Edge edge = new Edge(source.getName(), destination.getName(), distance);

            if (!graph.getNodes().contains(source)) {
                graph.getNodes().add(source);
            }

            if (!graph.getNodes().contains(destination)) {
                graph.getNodes().add(destination);
            }

            graph.getEdges().add(edge);
        }

//        for (Node node : graph.getNodes()) {
//            List<Node> visitedNodes = new ArrayList<>();
//            visitedNodes.add(node);
//
//            while (!(visitedNodes.size() == graph.getNodes().size())) {
//                Node lastVisitedNode = visitedNodes.get(visitedNodes.size() - 1);
//
//                graph.getNodes().stream()
//                        .filter(n -> !visitedNodes.contains(n))
//                        .map(n -> graph.getEdges().stream()
//                                .filter(edge -> edge.isForNodes(lastVisitedNode.getName(), n.getName()))
//                                .findFirst()
//                                .orElse(null)
//                        )
//                        .min((e1, e2) -> e1.getDistance() - e2.getDistance())
//                        .ifPresent(edge -> graph.getNodes().stream()
//                                .filter(n -> )
//                        );
//            }
//        }
    }
}
