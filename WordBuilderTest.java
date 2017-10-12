import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBuilderTest {

	//All tests were built before major refactoring to make sure post-refactoring code gave same results
	@Test
	public void testFindBuild() throws IOException {
		WordBuilder testFindBuild = new WordBuilder();
		//case 1
		String[] toTestList1 = {"ad", "ads", "as", "fa", "fad", "fads", "fas", "sad"};
		List<String> testList1 = new ArrayList<String>(Arrays.asList(toTestList1));
		assertEquals(testList1, testFindBuild.findBuild("asdf"));
		//case 2
		String[] toTestList2 = {"de", "ed", "er", "re", "red"};
		List<String> testList2 = new ArrayList<String>(Arrays.asList(toTestList2));
		assertEquals(testList2, testFindBuild.findBuild("red"));
		//case 3
		String[] toTestList3 = {"ar", "arc", "ay", "car", "carr", "carry", "cay", "cry", "racy", "ray", "rya", "ya", "yar"};
		List<String> testList3 = new ArrayList<String>(Arrays.asList(toTestList3));
		assertEquals(testList3, testFindBuild.findBuild("carry"));
	}
	
	@Test
	public void testFindContains() throws IOException {
		WordBuilder testFindContains = new WordBuilder();
		//case 1
		String[] toTestList1 = {"fatheadedness", "fatheadednesses"};
		List<String> testList1 = new ArrayList<String>(Arrays.asList(toTestList1));
		assertEquals(testList1, testFindContains.findContains("asdfasd"));
		//case 2
		String[] toTestList2 = {"accessorizing", "conservatizes", "conversaziones", "craziness", "crazinesses", "schizophrenias", 
				"sclerotizations", "sectarianizes", "secularizations", "securitizations"};
		List<String> testList2 = new ArrayList<String>(Arrays.asList(toTestList2));
		assertEquals(testList2, testFindContains.findContains("craziness"));
		//case 3
		String[] toTestList3 = {"autotransformer", "formularization", "manufactories", "manufactory", 
				"neurofibromata", "uniformitarian", "uniformitarians"};
		List<String> testList3 = new ArrayList<String>(Arrays.asList(toTestList3));
		assertEquals(testList3, testFindContains.findContains("faroutman"));
	}
	
	@Test
	public void testFindAnagram() throws IOException {
		WordBuilder testFindAnagram = new WordBuilder();
		//case 1
		String[] toTestList1 = {"opts", "post", "pots", "spot", "stop", "tops"};
		List<String> testList1 = new ArrayList<String>(Arrays.asList(toTestList1));
		assertEquals(testList1, testFindAnagram.findAnagram("opst"));
		//case 2
		String[] toTestList2 = {"trout", "tutor"};
		List<String> testList2 = new ArrayList<String>(Arrays.asList(toTestList2));
		assertEquals(testList2, testFindAnagram.findAnagram("trout"));
		//case 3
		String[] toTestList3 = {"arm", "mar", "ram"};
		List<String> testList3 = new ArrayList<String>(Arrays.asList(toTestList3));
		assertEquals(testList3, testFindAnagram.findAnagram("ram"));
	}
	
	@Test
	public void testNonAlphabet() throws IOException {
		WordBuilder testNonAlphabet = new WordBuilder();
		//findBuild()
		List<String> testList1 = testNonAlphabet.findBuild("( ) ` ~ ! @ # $ % ^ & * - + = | \\ { } [ ] : ; \" ' < > , . ? /");
		assertTrue(testList1.isEmpty());
		//findContains()
		List<String> testList2 = testNonAlphabet.findContains("( ) ` ~ ! @ # $ % ^ & * - + = | \\ { } [ ] : ; \" ' < > , . ? /");
		assertTrue(testList2.isEmpty());
		//findAnagram()
		List<String> testList3 = testNonAlphabet.findAnagram("( ) ` ~ ! @ # $ % ^ & * - + = | \\ { } [ ] : ; \" ' < > , . ? /");
		assertTrue(testList3.isEmpty());
	}

}
