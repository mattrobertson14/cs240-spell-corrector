package spell;

public class Trie implements ITrie {

  private Node[] nodes = new Node[26];
  public static int nodeCount = 0;
  public static int wordCount = 0;

  public void add(String word)  {
    Trie.wordCount++;
    word.toLowerCase();
    char[] ch = word.toCharArray();
    int x = (int)ch[0]-97;
    if (nodes[x] == null){
      Node n = new Node();
      Trie.nodeCount++;
      nodes[x] = n;
    }
    if (ch.length > 1){
      nodes[x].add(ch, 1);
    } else {
      nodes[x].increment();
    }
  }

  public ITrie.INode find(String word){
    Node n = new Node();

    return n;
  }

  public int getWordCount() {
    return Trie.wordCount;
  }

  public int getNodeCount() {
    return Trie.nodeCount;
  }

  @Override
  public String toString() {
    return "";
  }

  @Override
  public int hashCode() {
    return -1;
  }

  @Override
  public boolean equals(Object o) {
    return false;
  }
}
