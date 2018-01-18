package spell;

import java.io.IOException;

/**
 * A simple main class for running the spelling corrector. This class is not
 * used by the passoff program.
 */
public class Main {

	/**
	 * Give the dictionary file name as the first argument and the word to correct
	 * as the second argument.
	 */
	public static void main(String[] args) throws IOException {

		String dictionaryFileName = args[0];
		String inputWord = args[1];

		/**
		 * Create an instance of your corrector here
		 */
		//ISpellCorrector corrector = null;

		//corrector.useDictionary(dictionaryFileName);
		//String suggestion = corrector.suggestSimilarWord(inputWord);
		//if (suggestion == null) {
		//    suggestion = "No similar word found";
		//}

		//System.out.println("Suggestion is: " + suggestion);
		SpellCorrector sc = new SpellCorrector();
		sc.useDictionary(args[0]);

		Trie t = sc.getTrie();
		//System.out.println(t.toString());
		System.out.println(String.format("Word Count: %d\nNode Count: %d\nInput Word: %s\nHash Code: %d\nSuggested Word: %s", t.getWordCount(), t.getNodeCount(), inputWord, t.hashCode(), sc.suggestSimilarWord(args[1])));
	}

}
