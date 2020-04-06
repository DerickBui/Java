package alienEncoding;

import java.io.File;
import java.util.Scanner; //for reading files
import java.io.PrintWriter; //for outputing files
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Comparator;

public class Main {
  public static void main(String[] args) {
    try {
      //Read file
      File inputFile = new File("input.txt");
      Scanner readFile = new Scanner(inputFile);
      PrintWriter outputFile = new PrintWriter("output.txt");

      //value for k-its
      int k_value = Integer.parseInt(readFile.nextLine());

      //Data structures used
      PriorityQueue<LetterNode> nodes = new PriorityQueue<LetterNode>(new MyComparator());
      LinkedList<LetterNode> store = new LinkedList<LetterNode>();

      //Create nodes
      while (readFile.hasNextLine()) {
    	  String line = readFile.nextLine();
    	  String ALetter = line.substring(0, 4);
    	  int AFrequency = Integer.parseInt(line.substring(5));

    	  nodes.add(new LetterNode(ALetter, AFrequency));
      }
      
      //Huffman's coding starts here
      while (nodes.size() >= k_value) {
    	  LetterNode newNode = new LetterNode();
    	  for (int i = 0; i < k_value; i++) {
    		  newNode.addLetterNode(nodes.poll());
    	  }
    	  nodes.add(newNode);
      }
      
      if (nodes.size() > 1) {
    	  LetterNode newNode = new LetterNode();
    	  while (!(nodes.isEmpty())) {
    		  newNode.addLetterNode(nodes.poll());
    	  }
    	  nodes.add(newNode);
      }
      
      //store ordered leaves in linkedlist
      searchLeaf(nodes.peek(), store);
      
      //output final results
      for (LetterNode node : store) {
    	  System.out.println(node.id + " " + node.kits);
    	  outputFile.println(node.id + " " + node.kits);
      }

      readFile.close();
      outputFile.close();
    }
    catch (FileNotFoundException ex) {
      System.out.println("Input file not found");
    }
  }
  
  public static void searchLeaf(LetterNode node, LinkedList<LetterNode> store) {
	  if (!(node.children.isEmpty())) {
		  try {
			  PrintWriter outputFile = new PrintWriter("output.txt"); //check
			  outputFile.print("");
			  outputFile.close();
			  for (int i = 0; i < node.children.size(); i++) {
				  searchLeaf(node.children.get(i), Integer.toString(i), store);
			  }
		  }
		  catch (IOException ex) {
			  System.out.println("File Not Found");
		  }
	  }
  }
  
  private static void searchLeaf(LetterNode node, String tag, LinkedList<LetterNode> store) {
	  if (node.children.isEmpty()) {
		  node.kits = tag;
		  store.add(node);
	  }
	  else {
		  for (int i = 0; i < node.children.size(); i++) {
			  String newTag = tag + "-" + i;
			  searchLeaf(node.children.get(i), newTag, store);
		  }
	  }
  }
}

class MyComparator implements Comparator<LetterNode> { 
    public int compare(LetterNode x, LetterNode y) {
    	int xint = x.frequency;
    	int yint = y.frequency;
        if (xint > yint) {
        	return 1;
        }
        else if (xint < yint) {
        	return -1;
        }
        else {
        	//x.id.compareTo(y.id)
        	return 0;
        }
    } 
}
