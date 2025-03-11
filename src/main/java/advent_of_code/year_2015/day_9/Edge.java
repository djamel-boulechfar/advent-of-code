package advent_of_code.year_2015.day_9;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Edge {
    private String firstNodeName;
    private String secondNodeName;
    private int distance;

    public boolean isForNodes(String firstNodeName, String secondNodeName) {
        return (this.firstNodeName.equals(firstNodeName) && this.secondNodeName.equals(secondNodeName)) || (firstNodeName.equals(secondNodeName) && secondNodeName.equals(firstNodeName));
    }
}
