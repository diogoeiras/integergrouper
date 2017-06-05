import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({SparkDependentTestsSuite.class, SparkIndependentTestsSuite.class})

public class AllTestsSuite {
}
