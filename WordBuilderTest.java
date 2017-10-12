import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBuilderTest {

	@Test
	public void testFindBuild() throws IOException {
		WordBuilder testFindBuild = new WordBuilder();
		String[] toTestList1 = {"ad", "ads", "as", "fa", "fad", "fads", "fas", "sad"};
		List<String> testList1 = new ArrayList<String>(Arrays.asList(toTestList1));
		assertEquals(testList1, testFindBuild.findBuild("asdf"));
	}
	
	@Test
	public void testFindContains() throws IOException {
		WordBuilder testFindContains = new WordBuilder();
		String[] toTestList1 = {"fatheadedness", "fatheadednesses"};
		List<String> testList1 = new ArrayList<String>(Arrays.asList(toTestList1));
		assertEquals(testList1, testFindContains.findContains("asdfasd"));
	}
	
	@Test
	public void testFindAnagram() throws IOException {
		WordBuilder testFindAnagram = new WordBuilder();
		String[] toTestList1 = {"opts", "post", "pots", "spot", "stop", "tops"};
		List<String> testList1 = new ArrayList<String>(Arrays.asList(toTestList1));
		assertEquals(testList1, testFindAnagram.findAnagram("opst"));
	}

}
