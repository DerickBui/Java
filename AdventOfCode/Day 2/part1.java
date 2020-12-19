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

        int p1 = Integer.parseInt(strVal[0]);
        int p2 = Integer.parseInt(strVal[1]);

        char checkLetter = splitStr[1].charAt(0);


        int letterCount = 0;
        for (int i = 0; i < splitStr[2].length(); i++) {
          if (checkLetter == splitStr[2].charAt(i)) {
            letterCount++;
          }
        }

        if ((letterCount >= p1) && (letterCount <= p2)) {
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
