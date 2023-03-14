package djamel.year_2019.day_2;

import djamel.utils.MyFileReader;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final int ADDITION_CODE = 1;
    private static final int MULTIPLICATION_CODE = 2;
    private static final int HALT_CODE = 99;

    public static void main(String[] args) throws IOException {
        List<Integer> inputs = MyFileReader.readFileAndReturnIntList("C:\\DEV\\Java\\advent-of-code\\src\\main\\java\\djamel\\year_2019\\day_2\\input.txt", ",");

        for (int i = 0; i < inputs.size(); i += 4) {
            int opcode = inputs.get(i);
            if (opcode == HALT_CODE) break;
            int input1 = inputs.get(i + 1);
            int input2 = inputs.get(i + 2);
            int storageIndex = inputs.get(i + 3);
            if (opcode == ADDITION_CODE) inputs.set(storageIndex, input1 + input2);
            if (opcode == MULTIPLICATION_CODE) inputs.set(storageIndex, input1 * input2);
        }

        System.out.println(inputs.get(0));
    }
}
