package spell;

public class Trie implements ITrie {

  private Node[] nodes = new Node[26];

  public void add(String word)  {
    word.toLowerCase();
    char[] ch = word.toCharArray();
    int x = (int)ch[0]-96;
    if (nodes[x] == null){
      Node n = new Node();
      nodes[x] = n;
    }
    if (ch.length > 1){
      nodes[x].add(ch, 1);
    } else {
      nodes[x].increment();
    }
    System.out.println(nodes[x].getValue());
  }

  public ITrie.INode find(String word){
    Node n = new Node();

    return n;
  }

  public int getWordCount() {
    return -1;
  }

  public int getNodeCount() {
    return -1;
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
