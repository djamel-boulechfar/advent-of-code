package advent_of_code.year_2015.day_7;

import advent_of_code.utils.MyFileReader;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> inputs = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2015/day_7/input.txt");

        List<Instruction> instructions = inputs.stream()
                .map(Instruction::new)
                .toList();

        List<Instruction> doneInstructions = new ArrayList<>();

        Map<String, Integer> map = new HashMap<>();

        while(instructions.size() != doneInstructions.size()) {
            instructions.stream()
                    .filter(instruction -> !doneInstructions.contains(instruction) && (instruction.getOperation().equals(Operation.SET) || instruction.getOperation().equals(Operation.NOT)))
                    .filter(instruction -> setValue(instruction, map))
                    .forEach(doneInstructions::add);

            instructions.stream()
                    .filter(instruction -> !doneInstructions.contains(instruction) && (!instruction.getOperation().equals(Operation.SET) && !instruction.getOperation().equals(Operation.NOT)))
                    .filter(instruction -> computeInstructions(instruction, map))
                    .forEach(doneInstructions::add);
        }

        System.out.println(map.get("a"));
    }

    // TODO : utiliser Optional ?
    public static Optional<Integer> getValue(String valueString, Map<String, Integer> map) {
        return StringUtils.isNumeric(valueString) ?
                Optional.of(Integer.parseInt(valueString)) :
                Optional.ofNullable(map.get(valueString));
    }

    public static boolean setValue(Instruction instruction, Map<String, Integer> map) {
        Optional<Integer> optValue = getValue(instruction.getFirstValue(), map);

        if (optValue.isPresent()) {
            int value = optValue.get();
            if (instruction.getOperation().equals(Operation.NOT)) {
                value = ~value;
            }
            map.put(instruction.getTarget(), value);
            return true;
        }

        return false;
    }

    public static boolean computeInstructions(Instruction instruction, Map<String, Integer> map) {
        Optional<Integer> optFirstValue = getValue(instruction.getFirstValue(), map);
        Optional<Integer> optSecondValue = getValue(instruction.getSecondValue(), map);

        if (optFirstValue.isPresent() && optSecondValue.isPresent()) {
            int firstValue = optFirstValue.get();
            int secondValue = optSecondValue.get();
            switch (instruction.getOperation()) {
                case AND -> map.put(instruction.getTarget(), firstValue & secondValue);
                case OR -> map.put(instruction.getTarget(), firstValue | secondValue);
                case RSHIFT -> map.put(instruction.getTarget(), firstValue >> secondValue);
                case LSHIFT -> map.put(instruction.getTarget(), firstValue << secondValue);
            }
            return true;
        }

        return false;
    }
}
