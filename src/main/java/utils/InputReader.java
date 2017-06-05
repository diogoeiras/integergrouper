package utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputReader {


    private List<Integer> values;

    /**
     * Class with integers parsed from a text file
     * @param name file to be opened
     * @throws FileNotFoundException if @param name doesn't exists
     * @throws InvalidInputFormat if @param name has an invalid format
     */
    public InputReader(String name) throws FileNotFoundException, InvalidInputFormat {

        if(name == null){
            throw new IllegalArgumentException("Argument 'name' must not be null or empty");
        } else if (name.isEmpty()){
            throw new IllegalArgumentException("Argument 'name' must not be empty");
        }

        values = new ArrayList<>();
        Scanner scanner = null;

        try {
            InputStream fileStream = this.getClass().getResourceAsStream("/input/" + name);
            if (fileStream == null) {
                throw new FileNotFoundException("File '" + name + "' doesn't exists.");
            }

            scanner = new Scanner(fileStream);
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    values.add(scanner.nextInt());
                } else {
                    throw new InvalidInputFormat("File " + name + " has an invalid format, it should only contain integers separated by spaces.");
                }
            }

        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

    }

    public List<Integer> getValues() {
        return values;
    }
}
