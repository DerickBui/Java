package arbitrage;

import java.io.File;
import java.util.Scanner; //for reading files
import java.io.PrintWriter; //for outputting files
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.lang.Math;

public class Main {
	public static void main(String[] args) {
		try {
			File inputFile = new File("input.txt");
			PrintWriter outputFile = new PrintWriter("output.txt");
			Scanner readFile = new Scanner(inputFile);

			int ndimension = Integer.parseInt(readFile.nextLine()) + 1;

			double[][] grid = new double[ndimension][ndimension];
			double[][] adjgrid = new double[ndimension][ndimension];
			ArrayList<Integer> pathArray = new ArrayList<Integer>();
			ArrayList<Integer> uniqueValueArray = new ArrayList<Integer>();

			// read file and create adjacency matrix
			for (int i = 1; i < grid.length; i++) {
				for (int j = 1; j < grid[i].length; j++) {
					grid[i][j] = Double.parseDouble(readFile.next());
					adjgrid[i][j] = -Math.log(grid[i][j]) / Math.log(2);
				}
			}

			// double storevaluegrid[][][] = new double[ndimension][ndimension][ndimension];
			int storepathgrid[][][] = new int[ndimension][ndimension][ndimension];
			int pathgrid[][] = new int[ndimension][ndimension];
			double wgrid[][] = new double[ndimension][ndimension];
			double finalgrid[][] = new double[ndimension][ndimension];

			// Create w grid
			for (int i = 1; i < ndimension; i++) {
				for (int j = 1; j < ndimension; j++) {
					wgrid[i][j] = adjgrid[i][j];
				}
			}

			// Min-Plus Matrix Multiplication Algorithm
			for (int p = 1; p < ndimension; p++) {
				System.out.println(p);
				finalgrid = new double[ndimension][ndimension];

				// Reset values
				for (int k = 1; k < ndimension; k++) {
					for (int l = 1; l < ndimension; l++) {
						finalgrid[k][l] = Double.POSITIVE_INFINITY;
					}
				}

				// Main algorithm
				for (int i = 1; i < ndimension; i++) {
					for (int j = 1; j < ndimension; j++) {
						for (int x = 1; x < ndimension; x++) {
							if (finalgrid[i][j] >= (adjgrid[i][x] + wgrid[x][j])) {
								finalgrid[i][j] = adjgrid[i][x] + wgrid[x][j];
								pathgrid[i][j] = x;
							}
						}
					}
				}

				// d to d-1, store values grid
				for (int k = 1; k < ndimension; k++) {
					for (int l = 1; l < ndimension; l++) {
						adjgrid[k][l] = finalgrid[k][l];
						// storevaluegrid[p][k][l] = finalgrid[k][l];
					}
				}

				// store path grid
				for (int k = 1; k < ndimension; k++) {
					for (int l = 1; l < ndimension; l++) {
						storepathgrid[p][k][l] = pathgrid[k][l];
					}
				}
			}

			// Create path
			int funsize = ndimension - 1;
			int theI = 150;
			int juice = 150;
			while (funsize != 0) {

				// System.out.print(storepathgrid[funsize][1][juice] + " ");
				pathArray.add(0, storepathgrid[funsize][theI][juice]);

				if (uniqueValueArray.isEmpty()) {
					uniqueValueArray.add(0, storepathgrid[funsize][theI][juice]);
				} else {
					boolean check = true;
					for (int num : uniqueValueArray) {
						if (storepathgrid[funsize][theI][juice] == num) {
							check = false;
						}
					}

					if (check) {
						uniqueValueArray.add(0, storepathgrid[funsize][theI][juice]);
					}
				}

				juice = storepathgrid[funsize][theI][juice];
				funsize--;
			}

			for (int num : pathArray) {
				System.out.print(num + " ");
			}

			System.out.println("");

			for (int num : uniqueValueArray) {
				System.out.print(num + " ");
			}

			System.out.println("");

			ArrayList<Integer> efficientPath = new ArrayList<Integer>();
			double efficientValue = -100;

			// Create cycle and calculate efficiency
			for (int uValue : uniqueValueArray) { // unique number loop
				boolean printicus = true;
				boolean printicus2 = true;
				boolean start = true;
				ArrayList<Integer> tempPath = new ArrayList<Integer>();
				for (int num : pathArray) { // path loop, create cycle
					if (uValue == num) { // if unique and path numbers are same
						if (start) {
							start = false;
							tempPath.add(num);
						} else {
							tempPath.add(num);
//							if (printicus) {
//								for (int i = 0; i < tempPath.size(); i++) {
//									System.out.print(tempPath.get(i) + " ");
//								}
//								printicus = false;
//								System.out.println("");
//								System.out.println("");
//							}

							// efficiency function, variables include tempPath, grid
							double tempEff = calc(tempPath, grid);
//							if (printicus2) {
//								printicus2 = false;
//								System.out.println("Value eff: " + tempEff);
//								System.out.println("");
//							}

							if (tempEff > efficientValue) {
								efficientValue = tempEff;
								efficientPath.clear();
								for (int value : tempPath) {
									efficientPath.add(value);
								}
							}

							tempPath.clear();
							tempPath.add(num);
						}
					} else {
						if (start == false) {
							tempPath.add(num);
						}
					}
				}
			}

			System.out.println("Most efficient path: ");
			for (int value : efficientPath) {
				System.out.print(value + " ");
				outputFile.println(value);
			}
//			System.out.println("");
//			System.out.println(efficientValue);

			readFile.close();
			outputFile.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Input file not found");
		}
	}

	public static double calc(ArrayList<Integer> tempPath, double grid[][]) {
		int startEnd = 150;
		double profit = 1;

		for (int value : tempPath) {
			profit *= grid[startEnd][value];
			//int temp = value;
			//startEnd = temp;
		}
		
		profit *= grid[tempPath.get(0)][startEnd];

		profit = Math.pow(profit, (1.0 / (tempPath.size() - 1)));

		return profit;
	}
}
