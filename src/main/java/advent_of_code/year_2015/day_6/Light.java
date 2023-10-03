package advent_of_code.year_2015.day_6;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Light {
    private LightState state;

    public void toggle() {
        if (this.state.equals(LightState.ON)) {
            this.state = LightState.OFF;
        } else {
            this.state = LightState.ON;
        }
    }
}
