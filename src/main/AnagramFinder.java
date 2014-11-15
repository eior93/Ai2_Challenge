package main;

import java.util.List;

public interface AnagramFinder {

	/**
	 * Add the input word to the word bank
	 * @param word input word
	 */
	public void addWord(String word);
	
	/**
	 * Get all words that are anagrams of the input word
	 * @param word input word
	 * @return anagrams of the input word
	 */
	public List<String> getAnagrams(String word);
	
	
}
