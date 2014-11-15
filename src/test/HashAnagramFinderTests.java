package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import main.AnagramFinder;
import main.HashAnagramFinder;

import org.junit.Test;

public class HashAnagramFinderTests {

	@Test
	public void smallTest() {
		AnagramFinder haf = new HashAnagramFinder(false);
		haf.addWord("shoe");
		haf.addWord("shoe");
		haf.addWord("hsoe");
		haf.addWord("shoes");
		haf.addWord("alal");
		haf.addWord("lala");
		haf.addWord("hoes");
		
		List<String> result = haf.getAnagrams("shoe");
		String[] r = {"shoe", "hsoe", "hoes"};
		
		assertTrue(result.containsAll(Arrays.asList(r)));
		assertTrue(Arrays.asList(r).containsAll(result));
		
		result = haf.getAnagrams("");
		assertTrue(result.isEmpty());

		result = haf.getAnagrams("table");
		assertTrue(result.isEmpty());
	}

	@Test
	public void bigTest() {
		AnagramFinder haf = new HashAnagramFinder("test/dict.txt", false);
		
		List<String> result = haf.getAnagrams("maiden");
		String[] r = {"damien", "damine", "daimen", "demian",
				"median", "demain", "medina", "manide", "mandie",
				"nemadi", "aidmen", "maiden", "madine"};
		
		System.out.println(result);
		
		assertTrue(result.containsAll(Arrays.asList(r)));
		assertTrue(Arrays.asList(r).containsAll(result));
		
	}

}
