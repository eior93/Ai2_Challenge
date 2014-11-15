package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AnagramFinderMain {

	public static void main(String[] args) {

		boolean runGui = false;
		boolean caseSensitive = false;

		if (args.length==0)
			throw new Error("Please specify the path to a dictionary file.");

		Collection<String> inputWords = new ArrayList<String>();

		for (int i=1; i<args.length; i++) {
			if (args[i].charAt(0) == '-') {
				if (args[i].length() == 1) throw new Error("Invalid flag specified.");
				String flag = args[i].substring(1);
				if (flag.equals("g")) runGui = true;
				if (flag.equals("cs")) caseSensitive = true;
			} else{
				inputWords.add(args[i]);
			}
		}

		AnagramFinder angrmFndr = new HashAnagramFinder(args[0], caseSensitive);

		if (!inputWords.isEmpty()) {
			for (String word : inputWords) {
				printAnagrams(word, angrmFndr);
			}
			return;
		}

		if (!runGui) {
			System.out.println("Please type the word(s) for which you would like to find anagrams:");
			Reader inreader = new InputStreamReader(System.in);
			try {
				BufferedReader in = new BufferedReader(inreader);
				String str;
				while ((str = in.readLine()) != null) {
					String[] strs = str.split(" ");
					for (String s: strs){
						printAnagrams(s,angrmFndr);
					}
				}
				in.close();
			} catch (Exception e) {
			}
		} else {
			@SuppressWarnings("unused")
			AnagramUI g = new AnagramUI(angrmFndr);
		}
	}
	
	/** Returns String representing anagrams of input word (comma separated)
	 * @param word input word
	 * @param angrmFndr Anagram Finder
	 * @return String representing anagrams of input word
	 */
	static String getAnagrams(String word, AnagramFinder angrmFndr){
		List<String> result = angrmFndr.getAnagrams(word);
		if (result.size() == 0) return "";
		
		StringBuilder ans = new StringBuilder();
		int i=0;
		while (i<result.size()-1) {
			ans.append(result.get(i) + ", ");
			i++;
		}
		ans.append(result.get(i));
		return ans.toString();
	}

	/** Prints String representing anagrams of input word (comma separated)
	 * @param word input word
	 * @param angrmFndr Anagram Finder
	 */
	static void printAnagrams(String word, AnagramFinder angrmFndr){
		System.out.println("Anagrams for " + word + ": " + getAnagrams(word, angrmFndr));
	}

}