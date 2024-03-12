package advent_of_code.year_2018.day_7;

import advent_of_code.utils.MyFileReader;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2018/day_7/input.txt");

        List<Step> steps = parseInput(input);

        steps.sort(new StepComparator());

        List<Step> completedSteps = new ArrayList<>();

        while (completedSteps.size() != steps.size()) {
            Step stepThatCanBeDone = steps.stream().filter(step -> !step.isCompleted() && step.canBeDone())
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Can't continue"));

            stepThatCanBeDone.setCompleted(true);
            completedSteps.add(stepThatCanBeDone);
        }

        for (Step completedStep: completedSteps) {
            System.out.print(completedStep.getLetter());
        }
    }

    public static List<Step> parseInput(List<String> input) {
        Map<String, Step> stepsMap = new HashMap<>();

        for (String line: input) {
            String requiredStepLetter = StringUtils.substringBetween(line, "Step ", " must");
            String stepLetter = StringUtils.substringBetween(line, "step ", " can");

            stepsMap.putIfAbsent(requiredStepLetter, new Step(requiredStepLetter));

            stepsMap.putIfAbsent(stepLetter, new Step(stepLetter));

            stepsMap.get(stepLetter).getRequiredSteps().add(stepsMap.get(requiredStepLetter));
        }

        return new ArrayList<>(stepsMap.values());
    }
}
