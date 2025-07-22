package advent_of_code.year_2024.day_9;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Integer> input = MyFileReader.readFileAndReturnIntList("src/main/java/advent_of_code/year_2024/day_9/input.txt", "");

        List<Integer> diskWithSpaces = new ArrayList<>();
        int fileId = 0;
        for (int index = 0; index < input.size(); index++) {
            int number = input.get(index);
            if (index % 2 == 0) {
                for (int i = 0; i < number; i++) {
                    diskWithSpaces.add(fileId);
                }
                fileId++;
            } else {
                for (int i = 0; i < number; i++) {
                    diskWithSpaces.add(null);
                }
            }
        }

        List<Integer> nonNullNumbers = new ArrayList<>(diskWithSpaces.stream()
                .filter(Objects::nonNull)
                .toList());
        for (int index = 0; index < diskWithSpaces.size(); index++) {
            if (diskWithSpaces.get(index) == null) {
                int indexOfLastNumber = diskWithSpaces.lastIndexOf(
                        nonNullNumbers.get(nonNullNumbers.size() - 1)
                );

                if (indexOfLastNumber > index) {
                    Collections.swap(diskWithSpaces, index, indexOfLastNumber);
                    nonNullNumbers.remove(nonNullNumbers.size() - 1);
                }
            }
        }

        List<Integer> diskWithNoSpaces = diskWithSpaces.stream()
                .filter(Objects::nonNull)
                .toList();
        long result = 0;
        for (int index = 0; index < diskWithNoSpaces.size(); index++) {
            result += (long) index *  diskWithNoSpaces.get(index);
        }

        System.out.println(result);
    }
}
