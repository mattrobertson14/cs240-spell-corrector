package spell;
import java.util.*;

public class Trie implements ITrie {

  private Node[] nodes = new Node[26];
  public static StringBuilder strB = new StringBuilder();

  public void add(String word)  {
    word.toLowerCase();
    char[] ch = word.toCharArray();
    int x = (int)ch[0]-97;
    if (nodes[x] == null){
      String str = "" + ch[0];
      Node n = new Node(str);
      nodes[x] = n;
    }
    if (ch.length > 1){
      nodes[x].add(ch, 1);
    } else {
      nodes[x].increment();
    }
  }

  public ITrie.INode find(String word){
    for (int i = 0; i < nodes.length; i ++){
      if (nodes[i] != null){
        if ( word.substring(0,1).equals(nodes[i].getWord()) ){
          if (nodes[i].getValue() > 0 && word.length() == 1){
            return nodes[i];
          }
          return nodes[i].find(word.substring(1,word.length()));
        }
      }
    }

    return null;
  }

  public int getWordCount() {
    int wordCount = 0;

    for (int i = 0; i < nodes.length; i++){
      if (nodes[i] != null){
        if (nodes[i].getValue() > 0){
          wordCount++;
        }

        wordCount = nodes[i].getWordCount(wordCount);
      }
    }

    return wordCount;
  }

  public int getNodeCount() {
    int nodeCount = 0;

    for (int i = 0; i < nodes.length; i++){
      if (nodes[i] != null){
        nodeCount++;
        nodeCount = nodes[i].getNodeCount(nodeCount);
      }
    }

    return nodeCount;
  }

  @Override
  public String toString() {

    for (int i = 0; i < nodes.length; i++){
      if (nodes[i] != null){
        if(nodes[i].getValue() > 0){
          Trie.strB.append(nodes[i].getWord() + "\n");
        }

        nodes[i].toString();

      }
    }

    return strB.toString();
  }

  @Override
  public int hashCode() {
    int node = getNodeCount();
    int word = getWordCount();
    return (node%1234)*(word%4321)*(node/word);
  }

  @Override
  public boolean equals(Object o) {
    return false;
  }


  // Edit Distance Calculations
  public Set<Node> deletion(String word, Set<Node> list){
    int count = 0;
    while (count <= word.length()){
      for (int i = 0; i < nodes.length; i++){
        if (nodes[i] != null){
          if (count == 0){
            nodes[i].deletion(word,count,1,list,word.length());
          } else if (nodes[i].getWord().equals(word.substring(0,1))) {
              nodes[i].deletion(word.substring(1,word.length()),count,1,list,word.length());
            }
        }
      }

      count++;
    }

    return list;
  }
}
