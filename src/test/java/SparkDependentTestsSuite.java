import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({InvalidArgumentSparkTest.class})

public class SparkDependentTestsSuite {
    static IntegerGrouperSpark igs;

    @BeforeClass
    public static void setUp() {
        igs = new IntegerGrouperSpark();
    }

    @AfterClass
    public static void tearDown() {
        igs.close();
    }

}
