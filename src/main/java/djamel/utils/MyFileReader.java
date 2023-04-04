package djamel.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyFileReader {
    public static List<String> readFileAndReturnStringList(String file) throws IOException {
        List<String> res = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();
        while(line != null) {
            res.add(line);
            line = bufferedReader.readLine();
        }
        return res;
    }

    public static List<String> readFileCharsAndReturnStringList(String file) throws IOException {
        List<String> res = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        int i = bufferedReader.read();
        while(i != -1) {
            char c = (char) i;
            res.add(String.valueOf(c));
            i = bufferedReader.read();
        }
        return res;
    }

    public static List<Integer> readFileAndReturnIntList(String file, String separator) throws IOException {
        List<Integer> res = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String input = bufferedReader.readLine();
        String[] splitInput = input.split(separator);
        for (int i = 0; i < splitInput.length; i++) res.add(Integer.parseInt(splitInput[i]));
        return res;
    }
}
