package alienEncoding;

import java.io.File;
import java.util.Scanner; //for reading files
import java.io.PrintWriter; //for outputing files
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Main {
  public static void main(String[] args) {
    try {
      File inputFile = new File("input.txt");
      Scanner readFile = new Scanner(inputFile);

      int k_value = Integer.parseInt(readFile.nextLine());

      PriorityQueue<LetterNode> nodes = new PriorityQueue<LetterNode>(new MyComparator());

      ArrayList<LetterNode> list = new ArrayList<LetterNode>();
      ArrayList<LetterNode> buffer = new ArrayList<LetterNode>();

      while (readFile.hasNextLine()) {
    	  String line = readFile.nextLine();
    	  String ALetter = line.substring(0, 4);
    	  int AFrequency = Integer.parseInt(line.substring(5));

    	  nodes.add(new LetterNode(ALetter, AFrequency));
      }
      
      //for checking order
      while (!(nodes.isEmpty())) {
    	  list.add(nodes.poll());
      }
      
      for (int i = 0; i < 10000; i++) {
    	  System.out.println(list.get(i).id + " " + list.get(i).frequency);
      }

//      //add weight to each node? heavier weight do opposite
//      //new nodes after initial will have negative weight
//      LetterNode l1 = new LetterNode("A", 1);
//      LetterNode l2 = new LetterNode("B", 2);
//      LetterNode l3 = new LetterNode("C", 3);
//      LetterNode l4 = new LetterNode("D", 4);
//      LetterNode l5 = new LetterNode("E", 5);
//      LetterNode l6 = new LetterNode("F", 6);
//      LetterNode l7 = new LetterNode("G", 7);
//      LetterNode l8 = new LetterNode("H", 8);
//      LetterNode l9 = new LetterNode("I", 9);
//      LetterNode l10 = new LetterNode("abc", 6);
//      
//      nodes.add(l1);
//      nodes.add(l2);
//      nodes.add(l3);
//      nodes.add(l4);
//      nodes.add(l5);
//      nodes.add(l6);
//      nodes.add(l7);
//      nodes.add(l8);
//      nodes.add(l9);
//
//      LetterNode newL = new LetterNode();
//
//      nodes.poll(); //a
//      nodes.poll(); //b
//      nodes.poll(); //c
//      nodes.add(l10); //abc
//      
//      newL.addLetterNode(nodes.poll());
//      newL.addLetterNode(nodes.poll());
//      newL.addLetterNode(nodes.poll());
//      newL.addLetterNode(nodes.poll());
//      newL.addLetterNode(nodes.poll());
//      newL.addLetterNode(nodes.poll());
//      newL.addLetterNode(nodes.poll());
//      
//      System.out.println(newL.children.get(2).id);
//      System.out.println(newL.id);
//      
//      for (int i = 0; i < newL.children.size(); i++) {
//    	  System.out.println(newL.children.get(i).id);
//      }


      //use Integer.toString() for id
      

      readFile.close();
    }
    catch (FileNotFoundException ex) {
      System.out.println("Input file not found");
    }
  }
}

class MyComparator implements Comparator<LetterNode> { 
    public int compare(LetterNode x, LetterNode y) {
        if (x.frequency > y.frequency) {
        	return 1;
        }
        else if (x.frequency < y.frequency) {
        	return -1;
        }
        else {
        	return x.id.compareTo(y.id);
        }
    } 
}
