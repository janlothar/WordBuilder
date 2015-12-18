//Written by Jan Warncke
//Heavily based on the tutorial @ http://www.javapractices.com/topic/TopicAction.do?Id=42
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WordBuilder{
	
	public static void main (String[] args) throws IOException{
		WordBuilder dictionary = new WordBuilder();
		List<String> lines = dictionary.readFile(FILE_NAME);
		//text.print(lines);
		System.out.print(dictionary.find("scabble", lines));
	}
	
	final static String FILE_NAME = "dictionary.txt";
	//final static String OUTPUT_FILE_NAME = "C:\\Temp\\output.txt";
	final static Charset ENCODING = StandardCharsets.UTF_8;
	
	//Reads lines from FILE_NAME and adds to variable lines
	List<String> readFile(String aFileName) throws IOException {
		Path path = Paths.get(aFileName);
		return Files.readAllLines(path, ENCODING);
	}
	
	//Prints contents of list to console line-by-line 
	public void print(List toPrint){
		for(int i=0;i<toPrint.size();i++){
			System.out.println(toPrint.get(i));
		}
	}
	
	//Returns index of found word and -1 if not found
	public int find(String toFind, List inList){
		if (inList.contains(toFind)){
			return inList.indexOf(toFind);
		}
		return -1;
	}
	
}