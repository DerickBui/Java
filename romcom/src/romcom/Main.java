package romcom;

import java.io.File;
import java.util.Scanner; //for reading files
import java.io.PrintWriter; //for outputing files
import java.io.FileNotFoundException;

public class Main {

  public static void main(String[] args) {
    try {
      File inputFile = new File("input.txt");
      Scanner readFile = new Scanner(inputFile);
      PrintWriter outputFile = new PrintWriter("output.txt");
      int days = Integer.parseInt(readFile.next());
      int drones = Integer.parseInt(readFile.next());
      int search[][] = new int[drones + 1][days + 1];
      int number = 0;
      
      boolean flag = true;
      
      if (days == 0) {
        System.out.println(0);
        outputFile.println(0);
      }
      else if (days == 1) {
    	  flag = false;
    	  System.out.println(1);
    	  outputFile.println(1);
      }
      else if (drones == 1) {
    	  flag = false;
    	  System.out.println(days);
    	  outputFile.println(days);
      }
      else {
        for (int i = 1; i <= drones; i++) {
          search[i][0] = 0;
          search[i][1] = 1;
        }

        for (int j = 1; j <= days; j++) {
          search[1][j] = j;
        }

        for (int i = 2; i <= drones; i++) {
          for (int j = 2; j <= days; j++) {
            search[i][j] = Integer.MAX_VALUE;
            for (int k = 1; k <= j; k++) {
            	int tempNum1 = search[i - 1][k - 1];
            	int tempNum2 = search[i][j - k];
            	
            	if (tempNum1 > tempNum2) {
            		number = 1 + tempNum1;
            	}
            	else {
            		number = 1 + tempNum2;
            	}
            	
            	if (search[i][j] > number) {
            		search[i][j] = number;
            	}
            }
          }
        }
      }
      
      if (flag) {
    	  System.out.println(search[drones][days]);
          outputFile.println(search[drones][days]);  
      }

      readFile.close();
      outputFile.close();
    }
    catch (FileNotFoundException ex) {
      System.out.println("Request file not found");
    }
  }
}