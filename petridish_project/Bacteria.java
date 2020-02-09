package petridish_project;

public class Bacteria {
	  private char name;
	  private char normalBact[][];
	  private char bact90Clockwise[][];
	  private char bact90CounterClockwise[][];
	  private char bact180[][];
	  private char bactFlipped[][]; //all are flipped vertically
	  private char bactF90Clockwise[][];
	  private char bactF90CounterClockwise[][];
	  private char bactF180[][];

	  // Default constructor for Bacteria class
	  public Bacteria() {
	    name = ' ';
	    normalBact = null;
	    bact90Clockwise = null;
	    bact90CounterClockwise = null;
	    bact180 = null;
	    bactFlipped = null;
	    bactF90Clockwise = null;
	    bactF90CounterClockwise = null;
	    bactF180 = null;
	  }

	  // Constructor for the bacteria class
	  // @param name The name for the bacteria
	  // @param grid The area of dish with the bacteria inside
	  public Bacteria(char name, char[][] grid) {
	    this.name = name;
	    normalBact = grid;
	    bact90CounterClockwise = makeBact90CC(grid);
	    bact180 = makeBact90CC(bact90CounterClockwise);
	    bact90Clockwise = makeBact90CC(bact180);
	    bactFlipped = makeFlippedBact(grid);
	    bactF90CounterClockwise = makeBact90CC(bactFlipped);
	    bactF180 = makeBact90CC(bactF90CounterClockwise);
	    bactF90Clockwise = makeBact90CC(bactF180);
	  }

	  // Turns bacteria 90 degrees counter-clockwise
	  // @param grid The area that is to be turned
	  private char[][] makeBact90CC(char[][] grid) {
	    char CCGrid[][] = new char[grid[0].length][grid.length];
	    //for loop in here
	    for (int i = 0; i < grid.length; i++) {
	      for (int j = 0; j < grid[i].length; j++) {
	        CCGrid[((grid[i].length - 1) - j)][i] = grid[i][j];
	      }
	    }
	    return CCGrid;
	  }

	  // Flips the bacteria
	  // @param grid The area that is to be flipped
	  private char[][] makeFlippedBact(char[][] grid) {
	    char flippedGrid[][] = new char[grid.length][grid[0].length];

	    for (int i = 0; i < grid.length; i++) {
	      for (int j = 0; j < grid[i].length; j++) {
	        flippedGrid[i][((grid[i].length - 1) - j)] = grid[i][j];
	      }
	    }
	    return flippedGrid;
	  }

	  // Compare bacteria for a bactMatch
	  // @param grid The bacteria that is be checked
	  public boolean compareBact(char[][] grid) {
	    boolean bactMatch = checkUnits(normalBact, grid);
	    boolean bact90CCM = checkUnits(bact90CounterClockwise, grid);
	    boolean bact180M = checkUnits(bact180, grid);
	    boolean bact90CM = checkUnits(bact90Clockwise, grid);
	    boolean bactFM = checkUnits(bactFlipped, grid);
	    boolean bactf90CCM = checkUnits(bactF90CounterClockwise, grid);
	    boolean bactF180M = checkUnits(bactF180, grid);
	    boolean bactF90CM = checkUnits(bactF90Clockwise, grid);

	    if ((bactMatch == true) || (bact90CCM == true) || (bact180M == true) || (bact90CM == true) ||(bactFM == true) || (bactf90CCM == true) || (bactF180M == true) || (bactF90CM == true)) {
	      return true;
	    }
	    return false;
	  }

	  // Helper function for matching bacteria
	  // @param bactGrid Own bacteria to be compared to
	  // @param grid New bacteria to be compared to
	  private boolean checkUnits(char[][] bactGrid, char[][] grid) {
	    // System.out.println(bactGrid.length);
	    // System.out.println(bactGrid[0].length);
	    // System.out.println(grid.length);
	    // System.out.println(grid[0].length);
	    // System.out.println("");
	    if ((bactGrid.length == grid.length) && (bactGrid[0].length == grid[0].length)) {
	      for (int i = 0; i < grid.length; i++) {
	        for (int j = 0; j < grid[i].length; j++) {
	          if (bactGrid[i][j] != grid[i][j]) {
	            return false;
	          }
	        }
	      }
	    }
	    else {
	      return false;
	    }
	    return true;
	  }

	  // @return name of bacteria
	  public char getBactChar() {
	    return name;
	  }

	  // @return normal grid
	  public char[][] getNormalBact() {
	    return normalBact;
	  }

	  // @return 90 degree clockwise grid
	  public char[][] getBact90C() {
	    return bact90Clockwise;
	  }

	  // @return 90 degree counter-clockwise grid
	  public char[][] getBact90CC() {
	    return bact90CounterClockwise;
	  }

	  // @return 180 degree grid
	  public char[][] getBact180() {
	    return bact180;
	  }

	  // @return flipped grid
	  public char[][] getFBact() {
	    return bactFlipped;
	  }

	  // @return flipped 90 degree clockwise grid
	  public char[][] getFBact90C() {
	    return bactF90Clockwise;
	  }

	  // @return flipped 90 degree counter-clockwise grid
	  public char[][] getFBact90CC() {
	    return bactF90CounterClockwise;
	  }

	  // @return flipped 180 degree grid
	  public char[][] getFBact180() {
	    return bactF180;
	  }
	}