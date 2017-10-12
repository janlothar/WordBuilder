//Written by Jan Warncke
//Reading from text files heavily based on the tutorial @ http://www.javapractices.com/topic/TopicAction.do?Id=42

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WordBuilder{
		
	final static String FILE_NAME = "dictionary.txt";
	final static Charset ENCODING = StandardCharsets.UTF_8;
	private List<String> dictionary;
	
	public WordBuilder() throws IOException {
		dictionary = readFile(FILE_NAME);
	}
	
	//Reads lines from FILE_NAME and adds to variable dictionary
	List<String> readFile(String aFileName) throws IOException {
		Path path = Paths.get(aFileName);
		return Files.readAllLines(path, ENCODING);
	}
	
	//Print methods aren't used any more since GUI but are useful for tests
	//Prints contents of List to console line-by-line 
	private void print(List<String> toPrint){
		for(int i=0;i<toPrint.size();i++){
			System.out.println(toPrint.get(i));
		}
	}
	
	//Unused but useful for checking if word was added to list
	//Returns index of exact match and -1 if not found
	private int find(String toFind, List<String> inList){
		if (inList.contains(toFind)){
			return inList.indexOf(toFind);
		}
		return -1;
	}
	
	public List<String> find(String buildType, String contains){
		List<String> listOfFoundWords = new ArrayList<String>();
		for(int i=0;i<dictionary.size();i++){
			switch(buildType) {
			case "build":
				if (checkLettersBuild(contains.split(""), dictionary.get(i).split(""))){
					listOfFoundWords.add(dictionary.get(i));
				}
				break;
			case "contains":
				if (checkLetters(contains.split(""), dictionary.get(i).split(""))){
					listOfFoundWords.add(dictionary.get(i));
				}
				break;
			case "anagram":
				if (contains.length() == dictionary.get(i).length() && checkLetters(contains.split(""), dictionary.get(i).split(""))) {
					listOfFoundWords.add(dictionary.get(i));
				}
				break;
			}
			
		}
		return listOfFoundWords;
	}
	
	//[ARRAY] returns true if letters can make word
	public boolean checkLettersBuild(String[] letters, String[] word){
		for(int x=0;x<word.length;x++){							//go through each letter in word for which a match must be found
			for(int y=0;y<letters.length;y++){					//go through each letter
				if(word[x].equals(letters[y])){
					letters[y]="0";							//if match is found then that letter is marked as used by writing over with "0"
					break;									//no other matches need to be found so look at next letter in word
				}
				else if(y==letters.length-1){
					return false;							//if no match for that letter in word is found then letters cannot make word
				}
			}
		}
		return true;
	}
	
	public boolean checkLetters(String[] letters, String[] word){
		for(int x=0;x<letters.length;x++){					//go through each letter for which a match must be found
			for(int y=0;y<word.length;y++){					//go through each letter of the word
				if(letters[x].equals(word[y])){
					word[y]="0";							//if match is found then that letter from word is removed by writing over with "0"
					break;									//no other matches need to be found so look at next letter
				}
				else if(y==word.length-1){
					return false;							//if no match for that letter is found in word then word does not contain all letters
				}
			}
		}
		return true;
	}

}