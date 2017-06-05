import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import utils.GenericFunctions;

final class IntegerGrouperSpark {

    private JavaSparkContext jsc;

    /**
     * Spark setup
     */
    IntegerGrouperSpark() {
        SparkConf conf = new SparkConf();
        conf.setAppName("GroupingIntegers");
        conf.setMaster("local[1]");
        this.jsc = new JavaSparkContext(new SparkContext(conf));
        this.jsc.setLogLevel("error");
    }

    /**
     *  Function for mapping single Doubles to a vector of a single double (needed by Spark mllib)
     */
    private static Function<Double, Vector> integersToPoints() {
        return (Function<Double, Vector>) d -> {
            double[] point = new double[1];
            point[0] = d;
            return Vectors.dense(point);
        };
    }

    /**
     * Usage KMeans parallel algorithm from the machine learning library from Spark to divide integers into groups
     * @param values has the integers to be grouped
     * @param groups has the numbers of groups
     * @return the integers grouped by proximity
     */
    List<List<Integer>> group(List<Integer> values, Integer groups) {
        GenericFunctions.groupArgumentsValidator(values, groups);

        List<List<Integer>> result = new ArrayList<>(groups);
        if (groups == 1) {
            result.add(values);
            return result;
        }
        List<Double> localValues = values.stream().mapToDouble(Double::valueOf).boxed().collect(Collectors.toList());
        JavaDoubleRDD data = jsc.parallelizeDoubles(localValues);
        JavaRDD<Vector> parsedData = data.map(IntegerGrouperSpark.integersToPoints());

        parsedData.cache();

        KMeansModel clusters = KMeans.train(parsedData.rdd(), groups, 50, KMeans.K_MEANS_PARALLEL());
        List<Integer> indexes = clusters.predict(parsedData).collect();


        IntStream.range(0, groups).forEach((i) -> result.add(new ArrayList<>()));
        IntStream.range(0, indexes.size()).forEach((j) -> result.get(indexes.get(j)).add(values.get(j)));

        return result;
    }

    /**
     * To close the spark configuration created
     */
    void close() {
        jsc.close();
    }
}
