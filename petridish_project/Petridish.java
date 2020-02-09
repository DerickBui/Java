package petridish_project;

import java.util.ArrayList;

public class Petridish {
  private char letters[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
  private int marker; //for marking a letter used
  private char wholeDish[][];
  private ArrayList<Bacteria> BactList;
  private boolean flag = true;

  // Default constructor for the Petridish class
  public Petridish() {
    wholeDish = null;
    BactList = new ArrayList<Bacteria>();
    marker = 0;
  }

  // Constructor for Petridish class
  // @param wholeDish The dish from scanning and searching for different bacteria
  public Petridish(char wholeDish[][]) {
    this.wholeDish = wholeDish;
    BactList = new ArrayList<Bacteria>();
    marker = 0;
  }

  //This starts the scanning the dish
  public void scanDish() {
    for (int i = 0; i < wholeDish.length; i++) {
      for (int j = 0; j < wholeDish[i].length; j++) {
        if (wholeDish[i][j] == '*') {
        	wholeDish[i][j] = '#';
          scanBact(i,j);
        }
      }
    }
  }

  // scan localized area of bacteria
  // @param x The x-axis
  // @param y The y-axis
  private void scanBact(int x, int y) {
    int down = 0;
    int left = 0;
    int right = 0;
    int origX = x;
    int origY = y;
    flag = true;

    //for going down
    while (flag == true) {
      flag = false;

      //search in range of row (center)
      boolean DLR = false; //Down left right search
      for (int i = (y - left); i < ((y + right) + 1); i++) { //scan center
        if (wholeDish[x][i] == '*' || wholeDish[x][i] == '#') {
        	if (wholeDish[x][i] == '*') {
                if ((x - 1) >= 0) { //top edge
                	if (wholeDish[x - 1][i] == '#') {
                		flag = true;
                        wholeDish[x][i] = '#';
                        DLR = true;
                	}
                }
                //top left edge
                if (((x - 1) >= 0) && ((i - 1) >= 0)) {
                	if (wholeDish[x - 1][i - 1] == '#') {
                		flag = true;
                        wholeDish[x][i] = '#';
                        DLR = true;
                	}
                }
                //left edge
                if ((i - 1) >= 0) {
                	if (wholeDish[x][i - 1] == '#') {
                		flag = true;
                        wholeDish[x][i] = '#';
                        DLR = true;
                	}
                }
                //left down edge
                if (((x + 1) < wholeDish.length) && ((i - 1) >= 0)) {
                	if (wholeDish[x + 1][i - 1] == '#') {
                		flag = true;
                        wholeDish[x][i] = '#';
                        DLR = true;
                	}
                }
                //down edge
                if ((x + 1) < wholeDish.length) {
                	if (wholeDish[x + 1][i] == '#') {
                		flag = true;
                        wholeDish[x][i] = '#';
                        DLR = true;
                	}
                }
                //right down edge
                if (((x + 1) < wholeDish.length) && ((i + 1) < wholeDish[0].length)) {
                	if (wholeDish[x + 1][i + 1] == '#') {
                		flag = true;
                        wholeDish[x][i] = '#';
                        DLR = true;
                	}
                }
                //right edge
                if ((i + 1) < wholeDish[0].length) {
                	if (wholeDish[x][i + 1] == '#') {
                		flag = true;
                        wholeDish[x][i] = '#';
                        DLR = true;
                	}
                }
                //top right edge
                if (((x - 1) >= 0) && ((i + 1) < wholeDish[0].length)) {
                	if (wholeDish[x - 1][i + 1] == '#') {
                		flag = true;
                        wholeDish[x][i] = '#';
                        DLR = true;
                	}
                }
        	}
        	else {
        		flag = true;
        		DLR = true;
        	}
        }
      }
      left = searchLeft(left, x, y, DLR); //search left
      right = searchRight(right, x, y, DLR); //search right

      if (flag == false) {
        down--;
        x--;
      }
      else if ((x + 1) > (wholeDish.length - 1)) {
        flag = false;
      }
      else {
        down++;
        x++;
      }
    }
    
    for (int i = x; i > -1; i--) {
      for (int j = (y - left); j < ((y + right) + 1); j++) { //search up
    	  if (wholeDish[i][j] == '*' || wholeDish[i][j] == '#') {
          	if (wholeDish[i][j] == '*') {
                  if ((i - 1) >= 0) { //top edge
                  	if (wholeDish[i - 1][j] == '#') {
                  		flag = true;
                          wholeDish[i][j] = '#';
                  	}
                  }
                  //top left edge
                  if (((i - 1) >= 0) && ((j - 1) >= 0)) {
                  	if (wholeDish[i - 1][j - 1] == '#') {
                  		flag = true;
                          wholeDish[i][j] = '#';
                  	}
                  }
                  //left edge
                  if ((j - 1) >= 0) {
                  	if (wholeDish[i][j - 1] == '#') {
                  		flag = true;
                          wholeDish[i][j] = '#';
                  	}
                  }
                  //left down edge
                  if (((i + 1) < wholeDish.length) && ((j - 1) >= 0)) {
                  	if (wholeDish[i + 1][j - 1] == '#') {
                  		flag = true;
                          wholeDish[i][j] = '#';
                  	}
                  }
                  //down edge
                  if ((i + 1) < wholeDish.length) {
                  	if (wholeDish[i + 1][j] == '#') {
                  		flag = true;
                          wholeDish[i][j] = '#';
                  	}
                  }
                  //right down edge
                  if (((i + 1) < wholeDish.length) && ((j + 1) < wholeDish[0].length)) {
                  	if (wholeDish[i + 1][j + 1] == '#') {
                  		flag = true;
                          wholeDish[i][j] = '#';
                  	}
                  }
                  //right edge
                  if ((j + 1) < wholeDish[0].length) {
                  	if (wholeDish[i][j + 1] == '#') {
                  		flag = true;
                          wholeDish[i][j] = '#';
                  	}
                  }
                  //top right edge
                  if (((i - 1) >= 0) && ((j + 1) < wholeDish[0].length)) {
                  	if (wholeDish[i - 1][j + 1] == '#') {
                  		flag = true;
                          wholeDish[i][j] = '#';
                  	}
                  }
          	}
          	else {
          		flag = true;
          	}
          }
      }

      left = searchLeft(left, i, y, false);
      right = searchRight(right, i, y, false);
    }

    spliceDish(left, right, down, origX, origY);
  }

  // Scan bacteria to the left
  // @param right How many times the bacteria goes left
  // @param x Present location of the scan
  // @param y Present location of the scan
  private int searchLeft(int left, int x, int y, boolean DLR) {
    boolean leftFlag = true;
    while (leftFlag == true) {
      if (((y - left) - 1) < 0) { //if left edge
        leftFlag = false;
      }
      else {
        if (wholeDish[x][(y-left) - 1] == '*') { //if mark on left
        	wholeDish[x][(y-left) - 1] = '#';
        	flag = true;
        	left++;
		}
        else if (((x + 1) < (wholeDish.length)) && (DLR == true)) { //if bottom edge (x+1 is greater than x-length - 1?) ///////////
        	if (wholeDish[x + 1][(y-left) - 1] == '*') {
        		flag = true;
        		left++;
        	}
        	else {
        		leftFlag = false;
        	}
        }
        else {
          leftFlag = false;
        }
      }
    }
    return left;
  }

  // Scan bacteria to the right
  // @param right How many times the bacteria goes right
  // @param x Present location of the scan
  // @param y Present location of the scan
  private int searchRight(int right, int x, int y, boolean DLR) {
    boolean rightFlag = true;
    while (rightFlag == true) {
      if (((y + right) + 1) > (wholeDish[x].length) - 1) { 
        rightFlag = false;
      }
      else {
        if (wholeDish[x][(y + right) + 1] == '*') {
          wholeDish[x][(y + right) + 1] = '#';
          flag = true;
          right++;
        }
        else if (((x + 1) < (wholeDish.length)) && (DLR == true)) { //if bottom edge (x+1 is greater than x-length - 1?) ///////////
        	if (wholeDish[x + 1][(y + right) + 1] == '*') {
        		flag = true;
        		right++;
        	}
        	else {
        		rightFlag = false;
        	}
        }
        else {
          rightFlag = false;
        }
      }
    }
    return right;
  }

  // Separates the area of the bacteria from the dish
  // @param left How many times the scan goes left
  // @param right How many times the scan goes right
  // @param down How many times the scan goes down
  // @param originX The x-axis of where the scan starts
  // @paam originY The y-axis of where the scan starts
  private void spliceDish(int left, int right, int down, int originX, int originY) { 
    int newXLength = down + 1;
    int newYLength = ((left + right) + 1);
    int subX = 0;
    int subY = 0;

    char subGrid[][] = new char[newXLength][newYLength];

    for (int i = originX; i < (originX + down + 1); i++) {
      for (int j = (originY - left); j < (originY + right + 1); j++) {
    	  if (wholeDish[i][j] == '#') {
    		  subGrid[subX][subY] = wholeDish[i][j];
    	      subY++;  
    	  }
    	  else {
    		  subGrid[subX][subY] = ' ';
    	      subY++; 
    	  }
      }
      subX++;
      subY = 0;
    }
    

    boolean matchFound = false;
    for (int i = 0; i < BactList.size(); i++) {
      boolean checker = BactList.get(i).compareBact(subGrid); //if matching bact found
      if (checker == true) {
        matchFound = true;
        for (int j = originX; j < (originX + down + 1); j++) { //sub letter print function
          for (int k = (originY - left); k < (originY + right + 1); k++) {
            if (wholeDish[j][k] == '#') {
              wholeDish[j][k] = BactList.get(i).getBactChar();
            }
          }
        }
        break;
      }
    }
    if (matchFound == false) {
      Bacteria test = new Bacteria(letters[marker], subGrid);
      BactList.add(test);
      for (int i = originX; i < (originX + down + 1); i++) {
        for (int j = (originY - left); j < (originY + right + 1); j++) {
          if (wholeDish[i][j] == '#') {
            wholeDish[i][j] = letters[marker];
          }
        }
      }
      marker++;
    }   
  }
}