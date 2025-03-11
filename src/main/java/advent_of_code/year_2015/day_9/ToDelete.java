package advent_of_code.year_2015.day_9;

import java.util.ArrayList;
import java.util.List;

public class ToDelete {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        List<Integer> integers = list.stream()
                .map(string -> Integer.parseInt(string))
                .toList();

        System.out.println(integers);
    }
}
