package spell;
import java.util.*;
import java.io.*;

public class SpellCorrector implements ISpellCorrector {

  private Trie trie = new Trie();

	public void useDictionary(String dictionaryFileName) throws IOException {
    File file = new File(dictionaryFileName);

    Scanner scanner = new Scanner(file);

    while (scanner.hasNext()){
      if (scanner.hasNext("[a-zA-Z ]+")){
        String word = scanner.next();
        trie.add(word);
      } else {
        scanner.next();
      }
    }
  }

  public Trie getTrie(){
    return trie;
  }

	public String suggestSimilarWord(String inputWord){
    String word = inputWord.toLowerCase();

    if (trie.find(word) != null){
      return word;
    }

    

    return "";
  }

}
