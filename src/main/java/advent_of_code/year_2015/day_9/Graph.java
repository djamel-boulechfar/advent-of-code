package advent_of_code.year_2015.day_9;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Graph {
    private List<Node> nodes;
    private List<Edge> edges;

    public Graph() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
