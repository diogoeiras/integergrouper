import java.io.FileNotFoundException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import utils.InputReader;

public class FileNotFoundTest {
	@Rule
    public ExpectedException exceptions = ExpectedException.none();
	
	@Test
	public void FileNotFoundExceptionThrow() throws Exception {
		exceptions.expect(FileNotFoundException.class);
		exceptions.expectMessage("File 'notafile.dat' doesn't exists.");
		new InputReader("notafile.dat");
	}

}
