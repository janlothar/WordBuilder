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
		//Should return valid words that can be built from any given letters
		WordBuilder testFindBuild = new WordBuilder();
		//case 1
		String[] toTestList1 = {"ad", "ads", "as", "fa", "fad", "fads", "fas", "sad"};
		List<String> testList1 = new ArrayList<String>(Arrays.asList(toTestList1));
		assertEquals(testList1, testFindBuild.find("build", "asdf"));
		//case 2
		String[] toTestList2 = {"de", "ed", "er", "re", "red"};
		List<String> testList2 = new ArrayList<String>(Arrays.asList(toTestList2));
		assertEquals(testList2, testFindBuild.find("build", "red"));
		//case 3
		String[] toTestList3 = {"ar", "arc", "ay", "car", "carr", "carry", "cay", "cry", "racy", "ray", "rya", "ya", "yar"};
		List<String> testList3 = new ArrayList<String>(Arrays.asList(toTestList3));
		assertEquals(testList3, testFindBuild.find("build", "carry"));
	}
	
	@Test
	public void testFindContains() throws IOException {
		//Should return valid words that contain input letters
		WordBuilder testFindContains = new WordBuilder();
		//case 1
		String[] toTestList1 = {"fatheadedness", "fatheadednesses"};
		List<String> testList1 = new ArrayList<String>(Arrays.asList(toTestList1));
		assertEquals(testList1, testFindContains.find("contains", "asdfasd"));
		//case 2
		String[] toTestList2 = {"accessorizing", "conservatizes", "conversaziones", "craziness", "crazinesses", "schizophrenias", 
				"sclerotizations", "sectarianizes", "secularizations", "securitizations"};
		List<String> testList2 = new ArrayList<String>(Arrays.asList(toTestList2));
		assertEquals(testList2, testFindContains.find("contains", "craziness"));
		//case 3
		String[] toTestList3 = {"autotransformer", "formularization", "manufactories", "manufactory", 
				"neurofibromata", "uniformitarian", "uniformitarians"};
		List<String> testList3 = new ArrayList<String>(Arrays.asList(toTestList3));
		assertEquals(testList3, testFindContains.find("contains", "faroutman"));
	}
	
	@Test
	public void testFindAnagram() throws IOException {
		//Should return valid anagrams of input
		WordBuilder testFindAnagram = new WordBuilder();
		//case 1
		String[] toTestList1 = {"opts", "post", "pots", "spot", "stop", "tops"};
		List<String> testList1 = new ArrayList<String>(Arrays.asList(toTestList1));
		assertEquals(testList1, testFindAnagram.find("anagram", "opst"));
		//case 2
		String[] toTestList2 = {"trout", "tutor"};
		List<String> testList2 = new ArrayList<String>(Arrays.asList(toTestList2));
		assertEquals(testList2, testFindAnagram.find("anagram", "trout"));
		//case 3
		String[] toTestList3 = {"arm", "mar", "ram"};
		List<String> testList3 = new ArrayList<String>(Arrays.asList(toTestList3));
		assertEquals(testList3, testFindAnagram.find("anagram", "ram"));
	}
	
	@Test
	public void testNonAlphabet() throws IOException {
		//All returns should be empty when using non-alphabet characters
		WordBuilder testNonAlphabet = new WordBuilder();
		//findBuild()
		List<String> testList1 = testNonAlphabet.find("build", "( ) ` ~ ! @ # $ % ^ & * - + = | \\ { } [ ] : ; \" ' < > , . ? /");
		assertTrue(testList1.isEmpty());
		//findContains()
		List<String> testList2 = testNonAlphabet.find("contains", "( ) ` ~ ! @ # $ % ^ & * - + = | \\ { } [ ] : ; \" ' < > , . ? /");
		assertTrue(testList2.isEmpty());
		//findAnagram()
		List<String> testList3 = testNonAlphabet.find("anagram", "( ) ` ~ ! @ # $ % ^ & * - + = | \\ { } [ ] : ; \" ' < > , . ? /");
		assertTrue(testList3.isEmpty());
	}

}
