package spell;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		if (args.length < 2){
			System.out.println("\n\tERROR: Please include a .txt file AND a suggested word\n");
			System.exit(0);
		}
		String dictionaryFileName = args[0];
		String inputWord = args[1];

		try{

			SpellCorrector sc = new SpellCorrector();

			System.out.print("Reading Dictionary File Into Trie...");
			sc.useDictionary(dictionaryFileName);
			System.out.println(" Done.");

			System.out.println(sc.suggestSimilarWord(inputWord));

		} catch (IOException ex){
			System.out.println(String.format("\n\tERROR: %s was either not found or invalid, please enter a different file\n", dictionaryFileName));
			System.exit(0);
		}


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
