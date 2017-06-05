import utils.GenericFunctions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class IntegerGrouperAlgorithm {

    /**
     *
     * @param values has the list of integers to calculate the variance
     * @param left is the first integer (index) in the calculation
     * @param right is the first integer (index) not included in the calculation
     * @return variance from @param left to @param right of the list
     */
    private static Double variance(List<Integer> values, int left, int right) {
        if (Math.abs(right - left) == 1 || right - left == 0) {
            return 0.0;
        }
        Double average = IntStream.range(left, right).map(values::get).sum() / (double) (right - left);
        return IntStream.range(left, right).mapToDouble((i) -> (values.get(i) - average) * (values.get(i) - average)).sum() / (right - left);
    }

    /**
     *
     * @param values has the integers to be grouped
     * @param groups has the numbers of groups
     * @return the integers grouped by proximity (variance is used)
     */
    static List<List<Integer>> group(List<Integer> values, Integer groups) {
        GenericFunctions.groupArgumentsValidator(values, groups);
        List<List<Integer>> result = new ArrayList<>();
        if (groups == 1) {
            result.add(values);
            return result;
        }
        IntStream.range(0, groups).forEach((x) -> result.add(new ArrayList<>()));
        List<Integer> indexes = new ArrayList<>(groups - 1);
        List<Integer> sorted = new ArrayList<>(values);
        sorted.sort(Collections.reverseOrder());

        Integer numberIndexesInserted = 0;
        double[] minVariance = new double[2];
        double[] tempVariance = new double[2];
        while (numberIndexesInserted < groups - 1) {
            Integer index, minIndex, leftIndex;
            if (indexes.isEmpty()) {
                leftIndex = 0;
            } else {
                leftIndex = indexes.get(indexes.size() - 1);
            }
            index = minIndex = leftIndex + 1;
            minVariance[0] = variance(sorted, leftIndex, index);
            minVariance[1] = variance(sorted, index, sorted.size());
            while (index < sorted.size() - 1) {
                tempVariance[0] = variance(sorted, leftIndex, index);
                tempVariance[1] = variance(sorted, index, sorted.size());
                if ((tempVariance[0] + tempVariance[1]) < minVariance[0] + minVariance[1]) {
                    minIndex = index;
                    minVariance[0] = tempVariance[0];
                    minVariance[1] = tempVariance[1];
                }
                index++;
            }
            indexes.add(minIndex);
            numberIndexesInserted++;
        }

        for (int i = 0; i < indexes.size(); i++) {
            if (i != 0 && i != indexes.size() - 1) {
                result.set(i, IntStream.range(indexes.get(i - 1), indexes.get(i)).map(sorted::get).boxed().collect(Collectors.toList()));
            } else if (i == 0) {
                IntStream.range(0, indexes.get(0)).forEach(x -> result.get(0).add(sorted.get(x)));
            } else {
                IntStream.range(indexes.get(indexes.size() - 2), indexes.get(indexes.size() - 1)).forEach(x -> result.get(groups - 2).add(sorted.get(x)));
                IntStream.range(indexes.get(indexes.size() - 1), sorted.size()).forEach(x -> result.get(groups - 1).add(sorted.get(x)));
            }
        }

        return result;
    }

}
