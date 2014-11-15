package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class HashAnagramFinder implements AnagramFinder {

	private HashMap<String, Collection<String>> _wordBank;
	private boolean _caseSensitive;
	
	/**
	 * Creates a HashAnagramFinder and sets caseSensitive appropriately
	 * @param caseSensitive Flag specifying whether case matters
	 */
	public HashAnagramFinder(boolean caseSensitive){
		_wordBank = new HashMap<String, Collection<String>>();
		_caseSensitive = caseSensitive;
	}
	
	/**
	 * Creates a HashAnagramFinder, loads the dictionary specified by filePath, and sets caseSensitive appropriately
	 * @param filePath Path to dictionary
	 * @param caseSensitive Flag specifying whether case matters
	 */
	public HashAnagramFinder(String filePath, boolean caseSensitive){
		
		this(caseSensitive);
		BufferedReader br = null;
		
		try {
			String line;
			br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null) {
				addWord(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private String sortString(String input) {
		char[] tmp = input.toCharArray();
		Arrays.sort(tmp);
		return new String(tmp);
	}
	
	@Override
	public void addWord(String word){
		if (!_caseSensitive) word = word.toLowerCase();
		String key = sortString(word);
		if(!_wordBank.containsKey(key)){
			_wordBank.put(key, new HashSet<String>());
		}
		_wordBank.get(key).add(word);
	}

	@Override
	public List<String> getAnagrams(String word) {
		if (!_caseSensitive) word = word.toLowerCase();
		String key = sortString(word);
		if (!_wordBank.containsKey(key)) return new ArrayList<String>();
		else return new ArrayList<String>(_wordBank.get(key));
	}
	
}
