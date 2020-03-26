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

      if (days == 0) {
        System.out.println(0);
      }
      else if (days == 1 || drones == 1) {
        System.out.println(1);
      }
      else {
        for (int i = 1; i <= drones; i++) { //days
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
              number = 1 + Math.max(search[i - 1][k - 1], search[i][j - k]);
              if (search[i][j] > number) {
                search[i][j] = number;
              }
            }
          }
        }
      }

      System.out.println(search[drones][days]);
      outputFile.println(search[drones][days]);

      readFile.close();
      outputFile.close();
    }
    catch (FileNotFoundException ex) {
      System.out.println("Request file not found");
    }
  }
}