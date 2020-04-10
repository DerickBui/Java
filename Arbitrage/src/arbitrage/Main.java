package arbitrage;

import java.io.File;
import java.util.Scanner; //for reading files
import java.io.PrintWriter; //for outputting files
import java.io.FileNotFoundException;

public class Main {
  public static void main(String[] args) {
    try {
      File inputFile = new File("input.txt");
      Scanner readFile = new Scanner(inputFile);

      int ndimension = Integer.parseInt(readFile.nextLine());

      double [][] grid = new double[ndimension][ndimension];

      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[i].length; j++) {
          grid[i][j] = Double.parseDouble(readFile.next());
        }
      }
      
      System.out.println(grid[1][1]);



      // for (int i = 1; i < grid.length; i++) {
      //   for (int j = 1; j < grid[i].length; j++) {
      //     System.out.print(grid[i][j] + " ");
      //   }
      //   System.out.println("");
      // }
      
      //normal APSP calculation, does not take arbitrage weight into account
//      for (int k = 0; k < grid.length; k++) {
//          for (int i = 0; i < grid.length; i++) {
//        	  for (int j = 0; j < grid.length; j++) {
//        		  if (grid[i][j] > (grid[i][k] + grid[k][j])) {
//        			  grid[i][j] = grid[i][k] + grid[k][j];
//        		  }
//        	  }
//          }
//      }
      

      

      readFile.close();
    }
    catch (FileNotFoundException ex) {
      System.out.println("Input file not found");
    }
  }
}
