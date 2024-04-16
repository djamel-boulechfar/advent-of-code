package advent_of_code.year_2018.day_7;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Step {
    private String letter;
    private List<Step> requiredSteps;
    private boolean completed;

    public Step(String letter) {
        this.letter = letter;
        this.requiredSteps = new ArrayList<>();
        this.completed = false;
    }

    public boolean canBeDone() {
        return this.requiredSteps.isEmpty() || this.requiredSteps.stream().allMatch(Step::isCompleted);
    }
}
