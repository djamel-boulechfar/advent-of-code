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
}
