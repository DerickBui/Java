import java.io.*;
import java.util.*;

import javax.annotation.processing.FilerException;

class Main {
  public static void main(String[] args) {
    File openFile = new File("file.txt");

    try {
      Scanner readFile = new Scanner(openFile);

      int correctPasswordCount = 0;

      while (readFile.hasNextLine()) {
        String line = readFile.nextLine();

        String [] splitStr = line.split(" ");
        String [] strVal = splitStr[0].split("-");

        int p1 = Integer.parseInt(strVal[0]) - 1; //index 1
        int p2 = Integer.parseInt(strVal[1]) - 1; //index 2

        char checkLetter = splitStr[1].charAt(0);


        if ((splitStr[2].charAt(p1) == checkLetter) && (splitStr[2].charAt(p2) != checkLetter)) {
          correctPasswordCount++;
        }
        else if ((splitStr[2].charAt(p1) != checkLetter) && (splitStr[2].charAt(p2) == checkLetter)) {
          correctPasswordCount++;
        }
      }

      System.out.println(correctPasswordCount);
      readFile.close();
    }
    catch (FileNotFoundException ex) {
      System.out.println("File not found.");
    }
  }
}
