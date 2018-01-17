package spell;

public class Node implements ITrie.INode {

  private Node[] children = new Node[26];
  private int value = 0;

  public void add(char[] ch, int level){
    int x = (int)ch[level]-97;
    if (children[x] == null){
      Node n = new Node();
      Trie.nodeCount++;
      children[x] = n;
    }
    if (level != ch.length-1){
      children[x].add(ch, ++level);
    } else {
      children[x].increment();
    }
  }

  public int count() {
    int result = 0;
    for (int i = 0; i < children.length; i++){
      if (children[i] != null){
        result++;
        result += children[i].count();
      }
    }
    return result;
  }

  public void increment() {
    value++;
  }

  // Returns the frequency count
  public int getValue() {
    return value;
  }

}
