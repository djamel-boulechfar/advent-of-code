package advent_of_code.year_2017.day_8;

import lombok.Data;

@Data
public class Instruction {
    private Operation operation;
    private Condition condition;

    public Instruction(String line) {
        String[] instructionParts = line.split(" if ");

        if (instructionParts.length != 2) {
            throw new IllegalArgumentException("Unable to create instruction from line : " + line);
        }

        this.operation = new Operation(instructionParts[0]);
        this.condition = new Condition(instructionParts[1]);
    }
}
