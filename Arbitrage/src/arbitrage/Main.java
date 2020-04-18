package arbitrage;

import java.io.File;
import java.util.Scanner; //for reading files
import java.io.PrintWriter; //for outputting files
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.lang.Math;

public class Main {
  public static void main(String[] args) {
    try {
      File inputFile = new File("input.txt");
      Scanner readFile = new Scanner(inputFile);

      int ndimension = Integer.parseInt(readFile.nextLine()) + 1 ;

      double [][] grid = new double[ndimension][ndimension];
      ArrayList<Integer> path = new ArrayList<Integer>();
      double [] distance = new double[ndimension];
      int [] p = new int[ndimension];
      double [] n = new double[ndimension];
      double valueSaver = 0;

      //read file and create adjacency matrix
      for (int i = 1; i < grid.length; i++) {
        for (int j = 1; j < grid[i].length; j++) {
          grid[i][j] = Double.parseDouble(readFile.next());
          grid[i][j] = -Math.log(grid[i][j]) / Math.log(2);
        }
      }

      //normal APSP calculation, does not take arbitrage weight into account
//      for (int k = 1; k < grid.length; k++) {
//          for (int i = 1; i < grid.length; i++) {
//        	  for (int j = 1; j < grid.length; j++) {
//        		  if (grid[i][j] > (grid[i][k] + grid[k][j])) {
//        			  grid[i][j] = grid[i][k] + grid[k][j];
//        		  }
//        	  }
//          }
//      }
      
      double testgrid[][] = new double[4][4];
      testgrid[1][1] = 0;
      testgrid[1][2] = 1.2;
      testgrid[1][3] = .89;
      testgrid[2][1] = .88;
      testgrid[2][2] = 0;
      testgrid[2][3] = .51;
      testgrid[3][1] = 1.1;
      testgrid[3][2] = 0.15;
      testgrid[3][3] = 0;
      
      ndimension = 4;
      
      for (int step = 0; step < ndimension; step++) { //solving time
    	  for (int i = 0; i < ndimension; i++) { //reset array to zero
    		  distance[i] = 0;
    	  }
    	  
    	  distance[step] = 1;
    	  
    	  for(int k = 1; k < ndimension; k++) {
    		  for (int i = 1; i < ndimension; i++) {
    			  n[i] = distance[i];
    		  }
    		  
    		  for (int i = 1; i < ndimension; i++) {
    			  if (distance[i] > 0) {
    				  for (int j = 1; j < ndimension; j++) {
    					  double temp = distance[i] * testgrid[i][j];
    					  if (n[j] < temp) {
    						  n[j] = temp;
    						  p[j] = i;
    					  }
    				  }
    			  }
    		  }
    		  
    		  for (int i = 1; i < ndimension; i++) {
    			  distance[i] = n[i];
    		  }
    		  
    		  if (distance[step] > 1.01) {
    			  ArrayList<Integer> tempArrayList = new ArrayList<Integer>();
    			  for (int i = 1, tempStep = step; i <= k; i++) {
    				  tempArrayList.add(tempStep);
    				  tempStep = p[tempStep];
    			  }
    			  
    			  //System.out.println(distance[step]);
    			  System.out.println("Distance: " + distance[step]);
    			  System.out.println("Temp size: " + tempArrayList.size() + 1);
    			  double efficiency = Math.pow(distance[step], (1.0/(tempArrayList.size() + 1)));
    			  System.out.println("Before rounding: " + efficiency);
    			  efficiency = Math.pow(Math.round(efficiency), (tempArrayList.size() + 1));
    			  System.out.println("After rounding: " + efficiency);
    			  System.out.println(efficiency);
    			  
    			  if (path.isEmpty() || (valueSaver < efficiency)) { //needs checking
    				  path = tempArrayList;
    				  valueSaver = efficiency;
    			  }
    		  }
    	  }
    	  
      }
      
	  if (path.isEmpty()) {
		  System.out.println("Lol");
	  }
	  else {
		  System.out.print(path.get(0) + " ");
		  for (int i = path.size() - 1; i >= 0; i--) {
			  System.out.print(path.get(i) + " ");
		  }
	  }
      
      System.out.println("");
      
      //prints weighted grid
//      for (int i = 1; i < 5; i++) {
//    	  for (int j = 1; j < 5; j++) {
//    		  if (i == j) {
//    			  System.out.print(grid[i][j] + " ");  
//    		  }
//    	  }
//    	  System.out.println("");
//      }
       
       
      
      
      readFile.close();
    }
    catch (FileNotFoundException ex) {
      System.out.println("Input file not found");
    }
  }
}
