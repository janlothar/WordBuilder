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
		
		WordBuilder dictionary = new WordBuilder();							//create non-static object
		List<String> lines = dictionary.readFile(FILE_NAME);				//create List to hold lines from FILE_NAME
		//Scanner keyboard = new Scanner(System.in);							//Scanner for keyboard input
		//String userInput = keyboard.nextLine();								//user input as string in userInput
		
		String[] test1 = "hello".split("");
		String[] test2 = "hhlo".split("");
		System.out.println(dictionary.lettersInWord(test2, test1));
	}
	
	final static String FILE_NAME = "dictionary.txt";
	//final static String OUTPUT_FILE_NAME = "C:\\Temp\\output.txt";
	final static Charset ENCODING = StandardCharsets.UTF_8;
	
	//Reads lines from FILE_NAME and adds to variable lines
	List<String> readFile(String aFileName) throws IOException {
		Path path = Paths.get(aFileName);
		return Files.readAllLines(path, ENCODING);
	}
	
	//Prints contents of List to console line-by-line 
	public void print(List toPrint){
		for(int i=0;i<toPrint.size();i++){
			System.out.println(toPrint.get(i));
		}
	}
	
	//Prints contents of String[] to console line-by-line 
	public void print(String[] toPrint){
		for(int i=0;i<toPrint.length;i++){
			System.out.println(toPrint[i]);
		}
	}
	
	//Returns index of found word and -1 if not found
	public int find(String toFind, List inList){
		if (inList.contains(toFind)){
			return inList.indexOf(toFind);
		}
		return -1;
	}
	
	//Find words containing all letters entered
	public List findContains(String contains, List inList){
		/* String[] toFind = contains.split("");
		String[] temp;
		List toReturn;
		for(int i=0;i<inList.size();i++){
			temp = inList.get(i).split("");
			for(int x=0;x<temp.length;x++){
				for(int y=0;y<toFind.length;y++){
					if(temp[x].equals(toFind[y])){
						break;
					}
					
				}
			}
		} */
		return null;
	}
	
	//[ARRAY] returns true if word contains all characters in letters
	public boolean lettersInWord(String[] letters, String[] word){
		for(int x=0;x<letters.length;x++){					//go through each letter for which a match must be found
			for(int y=0;y<word.length;y++){					//go through each letter of the word
				if(letters[x].equals(word[y])){
					//System.out.printf("\nfound--- letters[%d]: %s = word[%d]: %s", x, letters[x], y, word[y]);							//Debug: Check why a match on each letter
					word[y]="0";							//if match is found then that letter from word is removed by writing over with "0"
					break;									//no other matches need to be found so look at next letter
				}
				else if(y==word.length-1){
					//System.out.printf("\nreturning false letter[%d]: %s has no match in %s", x, letters[x], Arrays.toString(word));		//Debug: Check why not a match
					return false;							//if no match for that letter is found in word then word does not contain all letters
				}
			}
		}
		return true;
	}

}