import java.util.*;
import java.io.*;

class Main {
  public static void main(String[] args) {
    File openFile = new File("day3input.txt");

    try {
      Scanner readFile = new Scanner(openFile);

      int treeCounter = 0;
      int mover = 0;
      while (readFile.hasNextLine()) {
        String newString = readFile.nextLine();
        if (mover > newString.length() - 1) {
          mover -= (newString.length());
        }

        for (int i = 0; i < newString.length(); i++) {
          if (i != mover) {
            System.out.print(newString.charAt(i));
          }
          else {
            if (newString.charAt(i) == '.') {
              System.out.print('O');
            }
            else {
              treeCounter++;
              System.out.print('X');
            }
          }
        }

        mover += 3;
        System.out.println("");
      }

      System.out.println(treeCounter);
      readFile.close();
    }
    catch (FileNotFoundException ex) {
      System.out.println("File not found.");
    }
  }
}
