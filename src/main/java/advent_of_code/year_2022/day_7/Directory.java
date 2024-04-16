package advent_of_code.year_2022.day_7;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class Directory {
    private String name;
    private Optional<Directory> parentDirectoryOptional;
    private List<Directory> subDirectories;
    private List<File> files;

    public Directory(String name, Optional<Directory> parentDirectoryOptional) {
        this.name = name;
        this.parentDirectoryOptional = parentDirectoryOptional;
        this.subDirectories = new ArrayList<>();
        this.files = new ArrayList<>();
    }

    public Directory getParentDirectory() {
        if (this.parentDirectoryOptional.isEmpty()) {
            throw new IllegalStateException("No parent directory found");
        }

        return this.parentDirectoryOptional.get();
    }

    public Optional<Directory> getSubDirectoryByName(String subDirectoryName) {
        return this.subDirectories.stream()
                .filter(subDirectory -> subDirectoryName.equals(subDirectory.getName()))
                .findFirst();
    }

    public Directory createSubDirectory(String subDirectoryName) {
        Directory subDirectory = new Directory(subDirectoryName, Optional.of(this));
        this.subDirectories.add(subDirectory);
        return subDirectory;
    }

    public void createFile(String fileName, int fileSize) {
        this.files.add(new File(fileName, fileSize));
    }

    public int computeSize() {
        int size = this.files.stream()
                .map(File::getSize)
                .reduce(0, Integer::sum);

        for (Directory subDirectory: this.subDirectories) {
            size += subDirectory.computeSize();
        }

        return size;
    }
}
