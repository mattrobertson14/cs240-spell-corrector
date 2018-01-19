package spell;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		String dictionaryFileName = args[0];
		String inputWord = args[1];

		SpellCorrector sc = new SpellCorrector();

		sc.useDictionary(dictionaryFileName);

		System.out.println(sc.suggestSimilarWord(inputWord));

		/*** Test for Trie.toString
			Trie tr = sc.getTrie();
			System.out.println(tr.toString());
		*/

		/*** Test for Trie.hashCode (and whether or not nodeCount and wordCount work)
			Trie tr = sc.getTrie();
			System.out.println(String.format("Hash Code: %d\nWord Count: %d\nNode Count: %d",tr.hashCode(),tr.getWordCount(),tr.getNodeCount()));
		*/

		/*** Test for Trie.equals
		SpellCorrector sc2 = new SpellCorrector();
		SpellCorrector sc3 = new SpellCorrector();

		sc.useDictionary(dictionaryFileName);
		sc2.useDictionary("test.txt");
		sc3.useDictionary(dictionaryFileName);

		Trie tr = sc.getTrie();
		Trie tr2 = sc2.getTrie();
		Trie tr3 = sc3.getTrie();

		System.out.println(tr.equals(tr2)); // FALSE
		System.out.println(tr2.equals(tr)); // FALSE
		System.out.println(tr.equals(tr3)); // TRUE
		System.out.println(tr3.equals(tr)); // TRUE
		*/
	}

}
