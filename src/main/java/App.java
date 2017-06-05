import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import utils.InputReader;
import utils.InvalidInputFormat;

public class App {
    public static void main(String[] args) {

        System.out.println("Grouping Integers App");

        Map<String, Integer> files = new LinkedHashMap<>();

        files.put("example-1.dat", 3);
        files.put("example-2.dat", 3);
        files.put("example-3.dat", 4);
        files.put("example-4.dat", 5);

        IntegerGrouperSpark intGpr = new IntegerGrouperSpark();

        for (Map.Entry<String, Integer> entry : files.entrySet()) {
            try {
                InputReader input = new InputReader(entry.getKey());
                List<List<Integer>> resultKMeans = intGpr.group(input.getValues(), entry.getValue());
                List<List<Integer>> resultAlgorithm = IntegerGrouperAlgorithm.group(input.getValues(), entry.getValue());
                System.out.println("===");
                System.out.println("Input     : " + input.getValues());
                System.out.println("KMeans||  : " + resultKMeans);
                System.out.println("Algorithm : " + resultAlgorithm);
            } catch (InvalidInputFormat | FileNotFoundException | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        intGpr.close();
    }
}
