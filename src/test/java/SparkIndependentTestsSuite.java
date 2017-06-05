import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ FileNotFoundTest.class, InvalidInputFormatTest.class, InvalidArgumentTest.class})

public class SparkIndependentTestsSuite {
}
