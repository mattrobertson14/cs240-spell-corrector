package spell;

public class Node implements ITrie.INode {

  private Node[] children = new Node[26];
  private int value = 0;
  private String word;

  Node(String word){
    this.word = new String(word);
  }

  public void add(char[] ch, int level){
    int x = (int)ch[level]-97;
    if (children[x] == null){
      String str = word + ch[level];
      Node n = new Node(str);
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

  public String getWord(){
    return word;
  }

  public int getWordCount(int count) {
    for (int i = 0; i < children.length; i++){
      if (children[i] != null){
        if (children[i].getValue() > 0){
          count++;
        }

        count = children[i].getWordCount(count);
      }
    }
    return count;
  }

  public int getNodeCount(int count) {
    for (int i = 0; i < children.length; i++){
      if (children[i] != null){
        count++;
        count = children[i].getNodeCount(count);
      }
    }
    return count;
  }

  @Override
  public String toString(){

    for (int i = 0; i < children.length; i++){
      if (children[i] != null) {
        if (children[i].getValue() > 0){
          Trie.strB.append(children[i].getWord() + "\n");
        }
        children[i].toString();
      }
    }

    return "";
  }

  // Returns the frequency count
  public int getValue() {
    return value;
  }

}
