package advent_of_code.year_2022.day_7;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static final Pattern CD_PATTERN = Pattern.compile("\\$ cd (?<directoryName>.*)");
    private static final Pattern LS_PATTERN = Pattern.compile("\\$ ls");
    private static final Pattern DIRECTORY_PATTERN = Pattern.compile("dir (?<directoryName>(\\w+))");
    private static final Pattern FILE_PATTERN = Pattern.compile("(?<fileSize>\\d+) (?<fileName>\\w+(\\.\\w+)?)");

    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2022/day_7/input.txt");

        Directory rootDirectory = parseInput(input);

        int answer = sumOfAtMost100000SizedDirectories(rootDirectory);

        System.out.println(answer);
    }

    private static Directory parseInput(List<String> input) {
        Directory rootDirectory = new Directory("/", Optional.empty());

        Directory currentDirectory = rootDirectory;

        for (String line: input) {
            Matcher cdMatcher = CD_PATTERN.matcher(line);
            Matcher lsMatcher = LS_PATTERN.matcher(line);
            Matcher directoryMatcher = DIRECTORY_PATTERN.matcher(line);
            Matcher fileMatcher = FILE_PATTERN.matcher(line);

            if (cdMatcher.matches()) {
                String directoryName = cdMatcher.group("directoryName");
                currentDirectory = computeCd(currentDirectory, directoryName, rootDirectory);
            } else if (lsMatcher.matches()) {
                System.out.println("Matching LS");
            } else if (directoryMatcher.matches()) {
                String directoryName = directoryMatcher.group("directoryName");
                if (currentDirectory.getSubDirectoryByName(directoryName).isEmpty()) {
                    currentDirectory.createSubDirectory(directoryName);
                }
            } else if (fileMatcher.matches()) {
                int fileSize = Integer.parseInt(fileMatcher.group("fileSize"));
                String fileName = fileMatcher.group("fileName");
                currentDirectory.createFile(fileName, fileSize);
            }
        }

        return rootDirectory;
    }

    private static Directory computeCd(Directory currentDirectory, String directoryName, Directory rootDirectory) {
        switch (directoryName) {
            case "/" -> {
                return rootDirectory;
            }
            case ".." -> {
                return currentDirectory.getParentDirectory();
            }
            default -> {
                Optional<Directory> directoryOptional = currentDirectory.getSubDirectoryByName(directoryName);
                return directoryOptional.orElseGet(() -> currentDirectory.createSubDirectory(directoryName));
            }
        }
    }

    private static int sumOfAtMost100000SizedDirectories(Directory currentDirectory) {
        int subDirectoriesRes = 0;
        for (Directory subDirectory: currentDirectory.getSubDirectories()) {
            subDirectoriesRes += sumOfAtMost100000SizedDirectories(subDirectory);
        }

        int currentDirectorySize = currentDirectory.computeSize();
        if (currentDirectorySize <= 100000) {
            return currentDirectorySize + subDirectoriesRes;
        } else {
            return subDirectoriesRes;
        }
    }
}
