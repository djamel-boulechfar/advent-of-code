package advent_of_code.year_2018.day_8;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Node {
    private List<Node> children;
    private List<Integer> metadata;

    public Node() {
        this.children = new ArrayList<>();
        this.metadata = new ArrayList<>();
    }

    public static CreateNodeResult createNode(List<Integer> licenceNumbers, int index) {
        Node node = new Node();

        int currentIndex = index;

        int childrenCount = licenceNumbers.get(currentIndex);
        currentIndex++;
        int metadataCount = licenceNumbers.get(currentIndex);
        currentIndex++;

        for (int i = 0; i < childrenCount; i++) {
            CreateNodeResult createNodeResult = createNode(licenceNumbers, currentIndex);
            node.getChildren().add(createNodeResult.createdNode());
            currentIndex = createNodeResult.index();
        }

        for (int i = 0; i < metadataCount; i++) {
            node.getMetadata().add(licenceNumbers.get(currentIndex));
            currentIndex++;
        }

        return new CreateNodeResult(node, currentIndex);
    }

    public int metadataSum() {
        int sum = this.metadata.stream()
                .reduce(0, Integer::sum);

        for (Node child : children) {
            sum += child.metadataSum();
        }

        return sum;
    }
}
