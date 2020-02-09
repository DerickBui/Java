package petridish_project;

//Derick Bui
//2-20-20
//This program identifies different bacteria in the petridish

import java.io.File;
import java.util.Scanner; //for reading files
import java.io.PrintWriter; //for outputing files
import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) {
		try {
			File inputFile = new File("input.txt"); // the input file
			char grid[][] = makeGrid(inputFile); // creates dimensions of grid

			if (grid != null) { // prevent measure if file not found
				Scanner readFile = new Scanner(inputFile);

				// Creates a map of grid
				while (readFile.hasNextLine()) {
					for (int i = 0; i < grid.length; i++) { // x-value
						String line = readFile.nextLine();
						for (int j = 0; j < line.length(); j++) { // y-value
							grid[i][j] = line.charAt(j);
						}
					}
				}

				Petridish identifier = new Petridish(grid); 										
				identifier.scanDish();
				printGrid(grid);
				readFile.close();
			}
		} catch (FileNotFoundException ex) {
			System.out.println("File not found");
		}
	}

	// Print and Output Grid
	// @param grid The petridish that is outputted
	public static void printGrid(char grid[][]) {
		try {
			PrintWriter outputFile = new PrintWriter("output.txt");

			for (int i = 0; i < grid.length; i++) {
				String line = "";
				for (int j = 0; j < grid[i].length; j++) {
					line += grid[i][j];
				}
				System.out.println(line);
				outputFile.println(line);
			}

			outputFile.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Can't output file.");
		}
	}

	// Make size of whole petri-dish
	// @param inputFile The file that is inputted
	public static char[][] makeGrid(File inputFile) {
		try {
			int lineCount = 0;
			int lineLength = 0;
			Scanner readFile = new Scanner(inputFile);
			while (readFile.hasNextLine()) {
				lineCount++;
				lineLength = readFile.nextLine().length();
			}

			char newArray[][] = new char[lineCount][lineLength];
			readFile.close();
			return newArray; // needs checking
		} catch (FileNotFoundException ex) {
			System.out.println("File not found for counting");
			return null;
		}
	}
}