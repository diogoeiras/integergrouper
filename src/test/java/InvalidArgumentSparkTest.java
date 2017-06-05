import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import utils.GenericFunctions;
import utils.InputReader;

import java.util.ArrayList;
import java.util.List;

public class InvalidArgumentSparkTest {
	@Rule
    public ExpectedException exceptions = ExpectedException.none();

	@Test
	public void EmptyInputVariableGrouper() throws Exception {
		exceptions.expect(IllegalArgumentException.class);
		exceptions.expectMessage("Variable 'values' must not be empty");
		List<Integer> input = new ArrayList<>();
        SparkDependentTestsSuite.igs.group(input,3);
	}

	@Test
	public void NullInputVariableGrouper() throws Exception {
		exceptions.expect(IllegalArgumentException.class);
		exceptions.expectMessage("Variable 'values' must not be null");
        SparkDependentTestsSuite.igs.group(null,3);
	}

	@Test
    public void InvalidGroupsMinimum() throws Exception {
	    exceptions.expect(IllegalArgumentException.class);
	    exceptions.expectMessage("Variable 'groups' must be 1 or higher, was 0");
	    List<Integer> list = new ArrayList<>();
        list.add(1);
        SparkDependentTestsSuite.igs.group(new ArrayList<>(list),0);
    }

    @Test
    public void InvalidGroupsInputSize(){
        exceptions.expect(IllegalArgumentException.class);
        exceptions.expectMessage("Variable 'groups' must be equal or less than variable 'values' size");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        SparkDependentTestsSuite.igs.group(list,2);
    }

}
