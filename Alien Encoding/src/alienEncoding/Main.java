package alienEncoding;

import java.io.File;
import java.util.Scanner; //for reading files
import java.io.PrintWriter; //for outputing files
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Main {
  public static void main(String[] args) {
    try {
      File inputFile = new File("input.txt");
      Scanner readFile = new Scanner(inputFile);

      int k_value = Integer.parseInt(readFile.nextLine());

      PriorityQueue<LetterNode> nodes = new PriorityQueue<LetterNode>(new MyComparator());

      while (readFile.hasNextLine()) {
    	  String line = readFile.nextLine();
    	  String ALetter = line.substring(0, 4);
    	  int AFrequency = Integer.parseInt(line.substring(5));

    	  nodes.add(new LetterNode(ALetter, AFrequency));
      }
      
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
      
      printLeaf(nodes.peek());
      


//      //add weight to each node? heavier weight do opposite
//      //new nodes after initial will have negative weight
      
//      LetterNode root = new LetterNode("", 0);
//      LetterNode child1 = new LetterNode("A", 1);
//      LetterNode child2 = new LetterNode("B", 1);
//      LetterNode child3 = new LetterNode("C", 1);
//      LetterNode child4 = new LetterNode("D", 1);
//      
//      child1.addLetterNode(child2);
//      child1.addLetterNode(child3);
//      child1.addLetterNode(child4);
//      root.addLetterNode(child1);
//      root.addLetterNode(child2);
//      
//      System.out.println(root.id);
//      
//      printLeaf(root);

      //use Integer.toString() for id
      

      readFile.close();
    }
    catch (FileNotFoundException ex) {
      System.out.println("Input file not found");
    }
  }
  
  public static void printLeaf(LetterNode node) {
	  if (!(node.children.isEmpty())) {
		  try {
			  PrintWriter outputFile = new PrintWriter("output2.txt"); //check
			  outputFile.print("");
			  outputFile.close();
			  for (int i = 0; i < node.children.size(); i++) {
				  printLeaf(node.children.get(i), Integer.toString(i));
			  }
		  }
		  catch (IOException ex) {
			  System.out.println("File Not Found");
		  }
	  }
  }
  
  private static void printLeaf(LetterNode node, String tag) {
	  if (node.children.isEmpty()) {
		  //System.out.println(node.id + " " + tag);
		  try {
			  PrintWriter outputFile = new PrintWriter(new FileWriter("output2.txt", true)); //check
			  outputFile.write(node.id + " " + tag + "\n");
			  outputFile.close();
		  }
		  catch (IOException ex) {
			  System.out.println("File Not Found");
		  }
	  }
	  else {
		  for (int i = 0; i < node.children.size(); i++) {
			  String newTag = tag + "-" + i;
			  printLeaf(node.children.get(i), newTag);
		  }
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
