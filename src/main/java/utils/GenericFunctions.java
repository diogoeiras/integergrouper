package utils;

import java.util.List;

public class GenericFunctions {
    /**
     * Validates parameters for the grouping functions
     * @param values input parameters with the integers to be divided
     * @param groups number of groups
     * @throws IllegalArgumentException if the arguments have invalid properties this is throw
     */
    public static void groupArgumentsValidator(List<Integer> values, Integer groups) throws IllegalArgumentException {
        if (groups < 1) {
            throw new IllegalArgumentException("Variable 'groups' must be 1 or higher, was " + groups);
        } else if (values == null) {
            throw new IllegalArgumentException("Variable 'values' must not be null");
        } else if (values.isEmpty()){
            throw new IllegalArgumentException("Variable 'values' must not be empty");
        } else if (groups > values.size()){
            throw new IllegalArgumentException("Variable 'groups' must be equal or less than variable 'values' size");
        }
    }
}
