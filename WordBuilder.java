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
	
	//Reads lines from FILE_NAME and adds to variable lines
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
	
	//Prints contents of String[] to console line-by-line 
	private void print(String[] toPrint){
		for(int i=0;i<toPrint.length;i++){
			System.out.println(toPrint[i]);
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
	
	//Return List of words containing searched letters
	public List<String> findContains(String contains, List<String> inList){
		List<String> toReturn = new ArrayList<String>();						//List to return that will be empty if no matching words are found
		for(int i=0;i<inList.size();i++){										//Go through each word in dictionary
			if (checkLettersContains(contains.split(""), inList.get(i).split(""))){	
				toReturn.add(inList.get(i));									//add word to toReturn if matches
			}
		}
		return toReturn;
	}
	
	//[ARRAY] returns true if word contains all characters in letters
	public boolean checkLettersContains(String[] letters, String[] word){
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
	
	//Return List of words that can be built from given letters
	public List<String> findBuild(String contains, List<String> inList){
		List<String> toReturn = new ArrayList<String>();						//List to return that will be empty if no matching words are found
		for(int i=0;i<inList.size();i++){										//Go through each word in dictionary
			if (checkLettersBuild(contains.split(""), inList.get(i).split(""))){	
				toReturn.add(inList.get(i));									//add word to toReturn if matches
			}
		}
		return toReturn;
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
	
	//Return list of words that use exactly the given letters
	public List<String> findAnagram(String contains, List<String> inList){
		List<String> toReturn = new ArrayList<String>();						//List to return that will be empty if no matching words are found
		for(int i=0;i<inList.size();i++){										//Go through each word in dictionary
			if (contains.length() == inList.get(i).length() && checkLettersAnagram(contains.split(""), inList.get(i).split(""))){	//For anagram word must be as long as input string
				toReturn.add(inList.get(i));									//add word to toReturn if matches
			}
		}
		return toReturn;
	}
	
	public boolean checkLettersAnagram(String[] letters, String[] word){
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