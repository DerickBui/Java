package excavation;

import java.io.File;
import java.util.Scanner; //for reading files
import java.io.PrintWriter; //for outputing files
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) {
		try {
			  //Fill in the grid based on the N number
		      File inputFile = new File("input.txt");
		      Scanner readFile = new Scanner(inputFile);
		      
		      PrintWriter outputFile = new PrintWriter("output.txt");

		      int ndimension = Integer.parseInt(readFile.nextLine());

		      int [][] grid = new int[ndimension + 1][ndimension + 1];

		      for (int i = 1; i < grid.length; i++) {
		        for (int j = 1; j < grid[i].length; j++) {
		          grid[i][j] = Integer.parseInt(readFile.next());
		        }
		      }

//		      for (int i = 1; i < grid.length; i++) {
//		        for (int j = 1; j < grid[i].length; j++) {
//		          System.out.print(grid[i][j] + " ");
//		        }
//		        System.out.println("");
//		      }
//		      

		      //Permanent values if total is largest
		      int maxTotal = Integer.MIN_VALUE;
		      int keepLeft = 0;
		      int keepRight = 0;
		      int keepUp = 0;
		      int keepDown = 0;

		      //Start finding the largest sub-rectangle
		      for (int i = 1; i < ndimension + 1; i++) { //left column movement
		        int [] sumKeeper = new int[ndimension + 1];
		        for (int j = i; j < ndimension + 1; j++) { //right column movement
		          int tempSum = 0;
		          int tempUp = 0;
		          int tempDown = 0;

		          for (int k = 1; k < ndimension + 1; k++) {
		            sumKeeper[k] += grid[k][j];  //add int to respective part of column
		          }
		          
		          int total = 0;  
		          int pointer = 1; //should start at 1, not 0
		          
		          for (int k = 1; k < sumKeeper.length; k++) { 
		        	  total += sumKeeper[k]; //add total of calculated dynamic array
		        	  
		        	  if (total < 0) { //if total is less than 0, start over and move the temporary highest row down
		        		  total = 0;
		        		  pointer = k + 1;  
			        	}  
			        	else if (total > tempSum) { //save as temporary maximum
			        		tempSum = total;
			        		tempUp = pointer;
			        		tempDown = k; 
			        	}
		          }

		          if (maxTotal < tempSum) { //if temporary maximum is larger than current max
		              if ((i == tempUp) && (j != tempDown)) { //if sub-grid is not square
		                keepLeft = i;
		                keepRight = j;
		                keepUp = tempUp;
		                keepDown = tempDown;
		                maxTotal = tempSum;
		              }
		              else if ((i != tempUp) && (j == tempDown)) {
		            	keepLeft = i;
		                keepRight = j;
		                keepUp = tempUp;
		                keepDown = tempDown;
		                maxTotal = tempSum;
		              }
		              else if ((i != tempUp) && (j != tempDown)) {
		            	keepLeft = i;
		                keepRight = j;
		                keepUp = tempUp;
		                keepDown = tempDown;
		                maxTotal = tempSum;
		              }
		            }
		        }
		      }
		      System.out.println(keepLeft);
		      System.out.println(keepUp);
		      System.out.println(keepRight);
		      System.out.println(keepDown);
		      System.out.println(maxTotal);

		      outputFile.println(keepUp + " " + keepLeft);
		      outputFile.println(keepDown + " " + keepRight);
		      
		      readFile.close();
		      outputFile.close();
		    }
		    catch (FileNotFoundException ex) {
		      System.out.println("Input file not found");
		    }
	}
}
