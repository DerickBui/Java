import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) {
    ArrayList<Integer> intList = new ArrayList<Integer>();
    Set<Integer> list = new HashSet<Integer>();

    try {
      int largestNum = 0;
      File openFile = new File("numbers.txt");
      Scanner readFile = new Scanner(openFile);
      while (readFile.hasNextLine()) {
        intList.add(Integer.parseInt(readFile.nextLine()));

        for (int i = 0; i < intList.size(); i++) {

          for (int j = 0; j < intList.size(); j++) {
            if (j != i) {
              if (list.contains(2020 - (intList.get(i) + intList.get(j)))) {
                int checkNum = (2020 - (intList.get(i) + intList.get(j))) * intList.get(i) * intList.get(j);
                if (checkNum > largestNum) { 
                  largestNum = checkNum;
                }

                System.out.println(intList.get(i));
                System.out.println(intList.get(j));
                System.out.println(2020 - (intList.get(i) + intList.get(j)));
                int p1 = intList.get(i);
                int p2 = intList.get(j);
                int p3 = 2020 - (intList.get(i) + intList.get(j));
                System.out.println("Sum: " + (p1 + p2 + p3));
                System.out.println();
              }
              else {
                list.add(intList.get(j));
              }
            }
          }

          list.clear();
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
