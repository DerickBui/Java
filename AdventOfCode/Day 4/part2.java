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

            if (threeLetters.equals("byr")) {
              int year = Integer.parseInt(check[i].substring(4));
              if ((year >= 1920) && (year <= 2002)) {
                System.out.print(threeLetters + " ");
                byr = true;
                pcount++;
              }
            }
            else if (threeLetters.equals("iyr")) {
              int year = Integer.parseInt(check[i].substring(4));
              if ((year >= 2010) && (year <= 2020)) {
                System.out.print(threeLetters + " ");
                iyr = true;
                pcount++;
              }
            }
            else if (threeLetters.equals("eyr")) {
              int year = Integer.parseInt(check[i].substring(4));
              if ((year >= 2020) && (year <= 2030)) {
                System.out.print(threeLetters + " ");
                eyr = true;
                pcount++;
              }
            }
            else if (threeLetters.equals("hgt")) {
              String[] part = check[i].substring(4).split("(?<=\\d)(?=\\D)");

              if (part.length == 2) {
                if (part[1].equals("cm")) {
                  if ((Integer.parseInt(part[0]) >= 150) && (Integer.parseInt(part[0]) <= 193)) {
                    System.out.print(threeLetters + " ");
                    hgt = true;
                    pcount++;
                  }
                }
                else if (part[1].equals("in")) {
                  if ((Integer.parseInt(part[0]) >= 59) && (Integer.parseInt(part[0]) <= 76)) {
                    System.out.print(threeLetters + " ");
                    hgt = true;
                    pcount++;
                  }
                }
              }
            }
            else if (threeLetters.equals("hcl")) {
              String color = check[i].substring(4);
              boolean validation = true;
              if ((color.charAt(0) == '#') && (color.length() == 7)) {
                for (int j = 1; j < color.length(); j++) {
                  if ((color.charAt(j) != '0') && (color.charAt(j) != '1') && (color.charAt(j) != '2') && (color.charAt(j) != '3') && (color.charAt(j) != '4') && (color.charAt(j) != '5') && (color.charAt(j) != '6') && (color.charAt(j) != '7') && (color.charAt(j) != '8') && (color.charAt(j) != '9') && (color.charAt(j) != 'a') && (color.charAt(j) != 'b') && (color.charAt(j) != 'c') && (color.charAt(j) != 'd') && (color.charAt(j) != 'e') && (color.charAt(j) != 'f')) {
                    validation = false;
                  }
                }
              }
              else {
                validation = false;
              }

              if (validation) {
                System.out.print(threeLetters + " ");
                hcl = true;
                pcount++;
              }
            }
            else if (threeLetters.equals("ecl")) {
              String color = check[i].substring(4);

              if (color.equals("amb") || color.equals("blu") || color.equals("brn") || color.equals("gry") || color.equals("grn") || color.equals("hzl") || color.equals("oth")) {
                System.out.print(threeLetters + " ");
                ecl = true;
                pcount++;
              }
            }
            else if (threeLetters.equals("pid")) {
              String number = check[i].substring(4);

              if (number.length() == 9) {
                try {
                  Integer.parseInt(number);
                  System.out.print(threeLetters + " ");
                  pid = true;
                  pcount++;
                }
                catch (NumberFormatException e) {
                  System.out.print("Can't format");
                }
              }
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
