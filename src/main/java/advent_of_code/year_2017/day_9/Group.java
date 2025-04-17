package advent_of_code.year_2017.day_9;

import lombok.Data;

import java.util.List;
import java.util.regex.Pattern;

@Data
public class Group {
    private int depth;
    private String value;
    private List<Group> innerGroups;
    private List<String> garbageList;

    private static final Pattern GROUP_PATTERN = Pattern.compile("\\{.*\\}");
    private static final Pattern GARBAGE_PATTERN = Pattern.compile("<.*[^!]>");

    public Group(String value) {
        this.value = value.substring(1, value.length() - 1);
    }

    public int computeGroup(int depth) {
        this.depth = depth;

        int result = this.depth;

        String[] parts = this.value.split(",");

        return result;
    }
}
