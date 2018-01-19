package spell;
import java.util.*;

public class Trie implements ITrie {

  private int nodeCount = 0;
  private int wordCount = 0;
  private Node root = new Node();

	public void add(String word) {
    root.addWord(word);
  }

	public ITrie.INode find(String word) {
    return findHelper(root, word);
  }

  private Node findHelper(Node n, String word){
    if (word.length() == 0){
      return null;
    }

    char c = word.charAt(0);
    int index = c - 'a';

    for (int i = 0; i < 26; i++){
      if (n.children[i] != null){
        if (index == i){
          if (word.length() == 1 && n.children[i].getValue() > 0){
            return n.children[i];
          }
          return findHelper(n.children[i], word.substring(1,word.length()));
        }
      }
    }

    return null;
  }

	public int getWordCount() {
    return wordCount;
  }

	public int getNodeCount() {
    return nodeCount;
  }

	@Override
	public String toString() {
    StringBuilder output = new StringBuilder();
    StringBuilder word = new StringBuilder();
    toStringHelper(root, word, output);
    return output.toString();
  }

  private void toStringHelper(Node n, StringBuilder word, StringBuilder output){
    if (n.getValue() > 0){
      output.append(word.toString() + "\n");
    }

    for (int i = 0; i < 26; i++){
      char c = (char)(i + 'a');
      if (n.children[i] != null){
        word.append(c);
        toStringHelper(n.children[i], word, output);
        word.deleteCharAt(word.length()-1);
      }
    }
  }

	@Override
	public int hashCode() {
    int node = nodeCount;
    int word = wordCount;
    return (node%1234)*(word%4321);
  }

	@Override
	public boolean equals(Object o) {
    return false;
  }



  public class Node implements INode {

    private int value = 0;
    private Node[] children = new Node[26];

    Node() {
      nodeCount++;
    }

    public void increment() {
      value++;
    }

    public void addWord(String word){
      char c = word.charAt(0);
      int index = c - 'a';

      if (children[index] == null){
        Node n = new Node();
        //nodeCount++;
        children[index] = n;
      }

      if (word.length() == 1){
        children[index].increment();
        if (children[index].getValue() == 1){ wordCount++; }
      } else {
        children[index].addWord(word.substring(1,word.length()));
      }
    }

 		public int getValue() {
      return value;
    }
  }
}
