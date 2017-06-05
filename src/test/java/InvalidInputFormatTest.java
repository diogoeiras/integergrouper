import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import utils.InputReader;
import utils.InvalidInputFormat;

public class InvalidInputFormatTest {
	@Rule
    public ExpectedException exceptions = ExpectedException.none();
	
	@Test
	public void FileNotFoundExceptionThrow() throws Exception {
		exceptions.expect(InvalidInputFormat.class);
		exceptions.expectMessage("File invalid.dat has an invalid format, it should only contain integers separated by spaces.");
		new InputReader("invalid.dat");
	}

}
