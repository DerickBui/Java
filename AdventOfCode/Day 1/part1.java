import java.io.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Set<Integer> list = new HashSet<Integer>();

    try {
      int largestNum = 0;
      File openFile = new File("numbers.txt");
      Scanner readFile = new Scanner(openFile);
      while (readFile.hasNextLine()) {
        int newNum = Integer.parseInt(readFile.nextLine());

        if (list.contains(2020 - newNum)) {
          int checkNum = newNum * (2020 - newNum);
          if (checkNum > largestNum) { 
            largestNum = checkNum;
          }
        }
        else {
          list.add(newNum);
        }
      }

      System.out.println(largestNum);
      readFile.close();
    }
    catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
  }
}
