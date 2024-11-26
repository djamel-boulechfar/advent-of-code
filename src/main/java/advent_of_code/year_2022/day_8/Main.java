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

        int width = input.get(0).length();
        int height = input.size();

        int part2Result = trees.stream()
                .map(tree -> scenicScore(tree, trees, width, height))
                .max(Integer::compareTo)
                .orElseThrow();
        System.out.println("Part 2 answer = " + part2Result);
    }

    public static boolean visibleFromLeft(Tree tree, List<Tree> forest) {
        return forest.stream()
                .filter(otherTree -> otherTree.getPosition().getY() == tree.getPosition().getY() && otherTree.getPosition().getX() < tree.getPosition().getX())
                .noneMatch(otherTree -> otherTree.getHeight() >= tree.getHeight());
    }

    public static boolean visibleFromTop(Tree tree, List<Tree> forest) {
        return forest.stream()
                .filter(otherTree -> otherTree.getPosition().getX() == tree.getPosition().getX() && otherTree.getPosition().getY() < tree.getPosition().getY())
                .noneMatch(otherTree -> otherTree.getHeight() >= tree.getHeight());
    }

    public static boolean visibleFromRight(Tree tree, List<Tree> forest) {
        return forest.stream()
                .filter(otherTree -> otherTree.getPosition().getX() == tree.getPosition().getX() && otherTree.getPosition().getY() > tree.getPosition().getY())
                .noneMatch(otherTree -> otherTree.getHeight() >= tree.getHeight());
    }

    public static boolean visibleFromBottom(Tree tree, List<Tree> forest) {
        return forest.stream()
                .filter(otherTree -> otherTree.getPosition().getY() == tree.getPosition().getY() && otherTree.getPosition().getX() > tree.getPosition().getX())
                .noneMatch(otherTree -> otherTree.getHeight() >= tree.getHeight());
    }

    public static int scenicScore(Tree tree, List<Tree> forest, int width, int height) {
        return scenicScoreInDirection(tree, forest, width, height, Direction.TOP)
                * scenicScoreInDirection(tree, forest, width, height, Direction.RIGHT)
                * scenicScoreInDirection(tree, forest, width, height, Direction.BOTTOM)
                * scenicScoreInDirection(tree, forest, width, height, Direction.LEFT);
    }

    public static int scenicScoreInDirection(Tree tree, List<Tree> forest, int width, int height, Direction direction) {
        int result = 0;

        Position nextPosition = Position.move(tree.getPosition(), direction);
        while (nextPosition.getX() >= 0 && nextPosition.getX() < width && nextPosition.getY() >= 0 && nextPosition.getY() < height) {
            result++;

            Position finalNextPosition = nextPosition;
            Tree nextTree = forest.stream()
                    .filter(aTree -> finalNextPosition.equals(aTree.getPosition()))
                    .findFirst()
                    .orElseThrow();
            if (tree.getHeight() <= nextTree.getHeight()) {
                break;
            }

            nextPosition = Position.move(nextPosition, direction);
        }

        return result;
    }
}
