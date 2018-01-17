package spell;

public class Node implements ITrie.INode {

  private Node[] children = new Node[26];
  private int value = 0;

  public void add(char[] ch, int level){
    int x = (int)ch[level]-96;
    if (children[x] == null){
      Node n = new Node();
      children[x] = n;
    }
    if (level != ch.length-1){
      children[x].add(ch, ++level);
    } else {
      children[x].increment();
    }
  }

  public void increment() {
    value++;
  }

  // Returns the frequency count
  public int getValue() {
    return value;
  }

}
