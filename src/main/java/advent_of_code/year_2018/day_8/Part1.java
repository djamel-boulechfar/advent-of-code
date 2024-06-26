package advent_of_code.year_2018.day_8;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<Integer> licenceNumbers = MyFileReader.readFileAndReturnIntList("src/main/java/advent_of_code/year_2018/day_8/input.txt", " ");

        Node rootNode = Node.createNode(licenceNumbers, 0).createdNode();

        int answer = rootNode.metadataSum();

        System.out.println("Part 1 answer = " + answer);
    }
}
