package spell;
import java.util.*;

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

  public Node find(String word){
    String s;
    for (int i = 0; i < children.length; i++){
      if (word.equals("")){
        return null;
      }
      if (children[i] != null){
        s = children[i].getWord();
        if ( word.substring(0,1).equals(s.substring(s.length()-1,s.length())) ) {
          if (children[i].getValue() > 0 && word.length() == 1){
            return children[i];
          }
          return children[i].find(word.substring(1,word.length()));
        }
      }
    }
    return null;
  }

  // Returns the frequency count
  public int getValue() {
    return value;
  }



  // Edit Distance Calculations
  public Set<Node> deletion(String word, int index, int level, Set<Node> list, int max){
    String s;
    int lvl;
    for (int i = 0; i < children.length; i++){
      if (word.equals("") && index != max){
        return list;
      }
      if (children[i] != null){
        s = children[i].getWord();
        if (index == max && level == max){
          if (children[i].getValue() > 0){
            list.add(children[i]);
          }
          continue;
        } else if (level == index){
          String str = new String(word);
          lvl = level +1;
          children[i].deletion(str, index, lvl, list, max);
        } else if (s.substring(s.length()-1, s.length()).equals(word.substring(0,1))){
          if (children[i].getValue() > 0 && word.length() == 1){
            list.add(children[i]);
          }
          lvl = level +1;
          children[i].deletion(word.substring(1,word.length()), index, lvl, list, max);
        }
      }
    }

    return list;
  }

}
