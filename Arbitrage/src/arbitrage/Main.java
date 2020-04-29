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
      Scanner readFile = new Scanner(inputFile);

      int ndimension = Integer.parseInt(readFile.nextLine() + 1);

      double [][] grid = new double[ndimension][ndimension];
      ArrayList<Integer> path = new ArrayList<Integer>();
      double [] distance = new double[ndimension];
      double [] n = new double[ndimension];
      double valueSaver = 0;

      //read file and create adjacency matrix
//      for (int i = 1; i < grid.length; i++) {
//        for (int j = 1; j < grid[i].length; j++) {
//          grid[i][j] = Double.parseDouble(readFile.next());
//          grid[i][j] = -Math.log(grid[i][j]) / Math.log(2);
//        }
//      }
      
    double testgrid[][] = new double[5][5];
//    testgrid[1][1] = 0;
//    testgrid[1][2] = -0.648613;
//    testgrid[1][3] = -0.327216;
//    testgrid[1][4] = 0.0305971;
//    testgrid[2][1] = 0.648614;
//    testgrid[2][2] = 0;
//    testgrid[2][3] = 0.321387;
//    testgrid[2][4] = 0.67922;
//    testgrid[3][1] = 0.327227;
//    testgrid[3][2] = -0.321386;
//    testgrid[3][3] = 0;
//    testgrid[3][4] = 0.357833;
//    testgrid[4][1] = -0.0305903;
//    testgrid[4][2] = -0.679217;
//    testgrid[4][3] = -0.357822;
//    testgrid[4][4] = 0;
    
    testgrid[1][1] = 1;
    testgrid[1][2] = 1.56766;
    testgrid[1][3] = 1.25459;
    testgrid[1][4] = 0.979015;
    testgrid[2][1] = 0.637893;
    testgrid[2][2] = 1;
    testgrid[2][3] = 0.8003;
    testgrid[2][4] = 0.624503;
    testgrid[3][1] = 0.797067;
    testgrid[3][2] = 1.24953;
    testgrid[3][3] = 1;
    testgrid[3][4] = 0.780336;
    testgrid[4][1] = 1.02143;
    testgrid[4][2] = 1.60127;
    testgrid[4][3] = 1.28149;
    testgrid[4][4] = 1;
    
    for (int i = 1; i < 5; i++) {
    	for (int j = 1; j < 5; j++) {
    		testgrid[i][j] = -Math.log(testgrid[i][j]) / Math.log(2);
    	}
    }
    
//    testgrid[1][1] = 0;
//    testgrid[1][2] = 5;
//    testgrid[1][3] = 9;
//    testgrid[1][4] = Double.POSITIVE_INFINITY;
//    testgrid[2][1] = Double.POSITIVE_INFINITY;
//    testgrid[2][2] = 0;
//    testgrid[2][3] = 1;
//    testgrid[2][4] = Double.POSITIVE_INFINITY;
//    testgrid[3][1] = Double.POSITIVE_INFINITY;
//    testgrid[3][2] = Double.POSITIVE_INFINITY;
//    testgrid[3][3] = 0;
//    testgrid[3][4] = 2;
//    testgrid[4][1] = Double.POSITIVE_INFINITY;
//    testgrid[4][2] = 3;
//    testgrid[4][3] = Double.POSITIVE_INFINITY;
//    testgrid[4][4] = 0;
    
    ndimension = 5;
    
    double storevaluegrid[][][] = new double[ndimension][ndimension][ndimension];
    int storepathgrid[][][] = new int[ndimension][ndimension][ndimension];
    int pathgrid[][] = new int[ndimension][ndimension];
    double wgrid[][] = new double[ndimension][ndimension];
    double finalgrid[][] = new double[ndimension][ndimension];
    
    //Create w grid
    for (int i = 1; i < ndimension; i++) {
    	for (int j = 1; j < ndimension; j++) {
    		wgrid[i][j] = testgrid[i][j];
    	}
    }
    
    //Min-Plus Matrix Multiplication Algorithm
    for (int p = 1; p < ndimension; p++) {
    	finalgrid = new double[ndimension][ndimension];
    	
    	for (int k = 1; k < ndimension; k++) {
    		for (int l = 1; l < ndimension; l++) {
    			finalgrid[k][l] = Double.POSITIVE_INFINITY;
    		}
    	}
    	
    	for (int i = 1; i < ndimension; i++) {
        	for (int j = 1; j < ndimension; j++) {
        		for (int x = 1; x < ndimension; x++) {
        			if (finalgrid[i][j] >= (testgrid[i][x] + wgrid[x][j])) {
          			  finalgrid[i][j] = testgrid[i][x] + wgrid[x][j];
          			  pathgrid[i][j] = x;
        			}
        		}
        	}
        }
    	
    	for (int k = 1; k < ndimension; k++) {
    		for (int l = 1; l < ndimension; l++) {
    			testgrid[k][l] = finalgrid[k][l];
    			storevaluegrid[p][k][l] = finalgrid[k][l];
    		}
    	}
    	
    	for (int k = 1; k < ndimension; k++) {
    		for (int l = 1; l < ndimension; l++) {
    			storepathgrid[p][k][l] = pathgrid[k][l];
    		}
    	}
    	
    	for (int k = 1; k < ndimension; k++) {
    		for (int l = 1; l < ndimension; l++) {
    			System.out.print(storevaluegrid[p][k][l] + " ");
    		}
    		System.out.println("");
    	}
    	
    	System.out.println("");
    	
    	for (int k = 1; k < ndimension; k++) {
    		for (int l = 1; l < ndimension; l++) {
    			System.out.print(storepathgrid[p][k][l] + " ");
    		}
    		System.out.println("");
    	}

    	System.out.println("");
    }
      
      readFile.close();
    }
    catch (FileNotFoundException ex) {
      System.out.println("Input file not found");
    }
  }
}
