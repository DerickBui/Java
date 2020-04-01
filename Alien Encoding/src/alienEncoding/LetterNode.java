package alienEncoding;

import java.util.ArrayList;

public class LetterNode {
  String id;
  int frequency;
  ArrayList<LetterNode> children;
  
  public LetterNode() {
    this.id = ""; //id number for LetterNode, either number or letter
    this.frequency = 0;
    this.children = new ArrayList<LetterNode>();
  }

  public LetterNode(String id, int frequency) {
    this.id = id;
    this.frequency = frequency;
    this.children = new ArrayList<LetterNode>();
  }

  public void addLetterNode(LetterNode child) {
    this.id = child.id + this.id;
    this.frequency += child.frequency;
    this.children.add(0, child);
  }
}