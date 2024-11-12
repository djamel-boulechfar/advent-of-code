package advent_of_code.year_2022.day_8;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2022/day_8/input.txt");

        List<Tree> trees = new ArrayList<>();
        for (int row = 0; row < input.size(); row++) {
            String currentLine = input.get(row);
            for (int column = 0; column < currentLine.length(); column++) {
                int height = currentLine.charAt(column);
                trees.add(new Tree(new Position(column, row), height));
            }
        }

        int visibleTrees = 0;
        for (Tree tree : trees) {
            if (tree.getPosition().getX() == 0 || tree.getPosition().getY() == 0) {
                visibleTrees++;
            } else {
                visibleTrees += (visibleFromLeft(tree, trees) || visibleFromTop(tree, trees) || visibleFromRight(tree, trees) || visibleFromBottom(tree, trees)) ? 1 : 0;
            }
        }

        System.out.println("Part 1 answer = " + visibleTrees);
    }

    public static boolean visibleFromLeft(Tree tree, List<Tree> forest) {
        return forest.stream()
                .filter(otherTree -> otherTree.getPosition().getY() == tree.getPosition().getY() && otherTree.getPosition().getX() < tree.getPosition().getX())
                .noneMatch(otherTree -> otherTree.getValue() >= tree.getValue());
    }

    public static boolean visibleFromTop(Tree tree, List<Tree> forest) {
        return forest.stream()
                .filter(otherTree -> otherTree.getPosition().getX() == tree.getPosition().getX() && otherTree.getPosition().getY() < tree.getPosition().getY())
                .noneMatch(otherTree -> otherTree.getValue() >= tree.getValue());
    }

    public static boolean visibleFromRight(Tree tree, List<Tree> forest) {
        return forest.stream()
                .filter(otherTree -> otherTree.getPosition().getX() == tree.getPosition().getX() && otherTree.getPosition().getY() > tree.getPosition().getY())
                .noneMatch(otherTree -> otherTree.getValue() >= tree.getValue());
    }

    public static boolean visibleFromBottom(Tree tree, List<Tree> forest) {
        return forest.stream()
                .filter(otherTree -> otherTree.getPosition().getY() == tree.getPosition().getY() && otherTree.getPosition().getX() > tree.getPosition().getX())
                .noneMatch(otherTree -> otherTree.getValue() >= tree.getValue());
    }
}
