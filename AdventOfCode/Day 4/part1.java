import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) {
    File openFile = new File("day4input.txt");
    
    try {
      Scanner readFile = new Scanner(openFile);
      boolean byr = false;
      boolean iyr = false;
      boolean eyr = false;
      boolean hgt = false;
      boolean hcl = false;
      boolean ecl = false;
      boolean pid = false;

      int batchCount = 1;
      int valid = 0;

      int pcount = 0;
      while (readFile.hasNextLine()) {
        String newLine = readFile.nextLine();

        if (newLine.isEmpty()) {
          batchCount++;
          System.out.print(pcount + " ");
          pcount = 0;
          if ((byr == true) && (iyr == true) && (eyr == true) && (hgt == true) && (hcl == true) && (ecl == true) && (pid == true)) {
            System.out.print("This counts");
            valid++;
          }

          System.out.println();
          
          byr = false;
          iyr = false;
          eyr = false;
          hgt = false;
          hcl = false;
          ecl = false;
          pid = false;
        }
        else {
          String [] check = newLine.split(" ");

          for (int i = 0; i < check.length; i++) {
            String threeLetters = check[i].substring(0, 3);
            System.out.print(threeLetters + " ");

            if (threeLetters.equals("byr")) {
              byr = true;
              pcount++;
            }
            else if (threeLetters.equals("iyr")) {
              iyr = true;
              pcount++;
            }
            else if (threeLetters.equals("eyr")) {
              eyr = true;
              pcount++;
            }
            else if (threeLetters.equals("hgt")) {
              hgt = true;
              pcount++;
            }
            else if (threeLetters.equals("hcl")) {
              hcl = true;
              pcount++;
            }
            else if (threeLetters.equals("ecl")) {
              ecl = true;
              pcount++;
            }
            else if (threeLetters.equals("pid")) {
              pid = true;
              pcount++;
            }
          }
        }
      }

      if ((byr == true) && (iyr == true) && (eyr == true) && (hgt == true) && (hcl == true) && (ecl == true) && (pid == true)) {
        System.out.print("This counts");
        valid++;
      }

      System.out.println("");
      System.out.println("Batch count: " + batchCount);
      System.out.println(valid);
      readFile.close();
    }
    catch (FileNotFoundException ex) {
      System.out.println("File not found.");
    }
  }
}
