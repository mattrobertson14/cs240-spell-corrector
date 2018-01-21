package spell;
import java.util.*;
import java.io.*;

public class SpellCorrector implements ISpellCorrector {

  private Trie trie;
  private HashSet<String> deletionSet = new HashSet<String>();
  private HashSet<String> transposeSet = new HashSet<String>();
  private HashSet<String> alterationSet = new HashSet<String>();
  private HashSet<String> insertionSet = new HashSet<String>();
  private String[] letters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

	public void useDictionary(String dictionaryFileName) throws IOException {
    File file = new File(dictionaryFileName);

    Scanner scanner = new Scanner(file);
    trie = new Trie();
    while (scanner.hasNext()){
      if (scanner.hasNext("[a-zA-Z ]+")){
        String word = scanner.next();
        trie.add(word);
      } else {
        scanner.next();
      }
    }

    scanner.close();
  }

  //public Trie getTrie(){
  //  return trie;
  //}

	public String suggestSimilarWord(String inputWord){
    String word = inputWord.toLowerCase();

    if (trie.find(word) != null){
      return word;
    }

    deletionSet.clear();
    transposeSet.clear();
    alterationSet.clear();
    insertionSet.clear();

    RunningResult result = new RunningResult();

    deletion(word);
    transpose(word);
    alteration(word);
    insertion(word);

    findResult(deletionSet, result);
    findResult(transposeSet, result);
    findResult(alterationSet, result);
    findResult(insertionSet, result);

    if (result.str == null){
      HashSet<String> editDistanceTwo = new HashSet<String>(deletionSet);
      editDistanceTwo.addAll(transposeSet);
      editDistanceTwo.addAll(alterationSet);
      editDistanceTwo.addAll(insertionSet);
      deletionSet.clear();
      transposeSet.clear();
      alterationSet.clear();
      insertionSet.clear();
      for (String s : editDistanceTwo){
        deletion(s);
      }

      for (String s : editDistanceTwo){
        transpose(s);
      }

      for (String s : editDistanceTwo){
        alteration(s);
      }

      for (String s : editDistanceTwo){
        insertion(s);
      }

      findResult(deletionSet, result);
      findResult(transposeSet, result);
      findResult(alterationSet, result);
      findResult(insertionSet, result);
    }

    return result.str;
  }

  public class RunningResult {
    public int count = 0;
    public String str = null;
  }

  private void findResult(HashSet<String> set, RunningResult result){
    String res = result.str;
    int c = result.count;

    ITrie.INode n;
    for (String s : set){
      n = trie.find(s);
      if (n != null){
        if (res == null){
          res = s;
          c = n.getValue();
        } else if (n.getValue() > c){
          res = s;
          c = n.getValue();
        } else if (n.getValue() == c && s.compareTo(res) < 0){
          res = s;
          c = n.getValue();
        }
      }
    }

    result.str = res;
    result.count = c;
  }

  private void deletion(String word){
    for (int i = 0; i < word.length()+1; i++){
      for (int j = 0; j < 26; j++){
        String str = word.substring(0,i) + letters[j] + word.substring(i,word.length());
        deletionSet.add(str);
      }
    }
  }

  private void transpose(String word){
    char[] ch;
    for (int i = 0; i < word.length()-1; i++){
      ch = word.toCharArray();
      char temp = ch[i];
      ch[i] = ch[i+1];
      ch[i+1] = temp;

      String str = new String(ch);
      transposeSet.add(str);
    }
  }

  private void alteration(String word){
    char[] ch;
    for (int i = 0; i < word.length(); i++){
      ch = word.toCharArray();
      for (int j = 0; j < 26; j++){
        ch[i] = letters[j].charAt(0);
        String str = new String(ch);
        alterationSet.add(str);
      }
    }
  }

  private void insertion(String word){
    for (int i = 0; i < word.length(); i++){
      String str = word.substring(0,i) + word.substring(i+1, word.length());
      insertionSet.add(str);
    }
  }

}
