import java.util.*;
import java.io.*;

class Main {
  public static void main(String[] args) {
    int down = 2;
    int right = 1;

    File openFile = new File("day3input.txt");
    // ArrayList<String> row = new ArrayList<String>();

    try {
      Scanner readFile = new Scanner(openFile);

      int treeCounter = 0;
      int mover = right;
      String newString = "";

      if (readFile.hasNextLine()) {
        newString = readFile.nextLine();
        System.out.println(newString);
      }

      while (readFile.hasNextLine()) {
        boolean doRead = true;

        for (int i = 0; i < down; i++) {
          if (readFile.hasNextLine()) {
            newString = readFile.nextLine();

            if (i != (down - 1)) {
              System.out.println(newString);
            }
          }
          else {
            doRead = false;
          }
        }

        if (doRead) {
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

          mover += right;
          System.out.println("");
        }
      }

      System.out.println(treeCounter);
      readFile.close();
    }
    catch (FileNotFoundException ex) {
      System.out.println("File not found.");
    }
  }
}
