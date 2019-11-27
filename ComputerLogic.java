import java.util.Random;

public class ComputerLogic extends Connect4Data{
private boolean move;
	
	public ComputerLogic(int boardRow, int boardColumn) {
		super(boardRow, boardColumn);
	}
	
	public void computerMove(int[][] gameBoard, int playerDisc, int computerDisc) {
		//Sets move value to false when method is called (since computer has not decided on a move.)
		setMove(false);
		//Check if there is any available winning move.
		computerWinHorizontal(gameBoard, computerDisc);
		computerWinVertical(gameBoard, computerDisc);
		computerWinBackdiagonal(gameBoard, computerDisc);
		computerWinFrontdiagonal(gameBoard, computerDisc);
		//Check if there is any block to be done against a player winning line.
		computerBlockHorizontal(gameBoard, playerDisc, computerDisc);
		computerBlockVertical(gameBoard, playerDisc, computerDisc);
		computerBlockBackdiagonal(gameBoard, playerDisc, computerDisc);
		computerBlockFrontdiagonal(gameBoard, playerDisc, computerDisc);
		//Check if there is any two-disc that can be built into a winning move.
		computerBuildBackdiagonal(gameBoard, computerDisc);
		computerBuildFrontdiagonal(gameBoard, computerDisc);
		computerBuildHorizontal(gameBoard, computerDisc);
		computerBuildVertical(gameBoard, computerDisc);
		//Check if there is any one disc that can be built into a two-disc line.
		computerAggressiveBackdiagonal(gameBoard, computerDisc);
		computerAggressiveFrontdiagonal(gameBoard, computerDisc);
		computerAggressiveVertical(gameBoard, computerDisc);
		computerAggresiveHorizontal(gameBoard, computerDisc);
		//Move randomly.
		computerMoveRandom(gameBoard, computerDisc);
		//Inherited method from parent to check for game condition.
		checkLine(gameBoard,computerDisc);
	}
	
		private void computerAggressiveFrontdiagonal(int[][] gameBoard, int computerDisc) {
		// TODO Auto-generated method stub
			//Places a disc to the top-right of a single disc.
			for(int i = gameBoard.length-1; i >= 1; i--) {
				for(int j = 0; j < gameBoard[0].length-2; j++) {
					if(getMove() == false) {
						if (gameBoard[i][j] == computerDisc
								&& gameBoard[i-1][j+1] == 0
								&& gameBoard[i][j+1] != 0) {
							gameBoard[i-1][j+1] = computerDisc;
							setMove(true);
							System.out.println("build one right fdiagonal");
							break;
						}
					}
				}
			}
		}

		private void computerAggressiveBackdiagonal(int[][] gameBoard, int computerDisc) {
		// TODO Auto-generated method stub
			//Places a disc to the top-left of a single disc.
			for(int i = gameBoard.length-1; i >= 1; i--) {
				for(int j = gameBoard[0].length-1; j >= 1; j--) {
					if(getMove() == false) {
						if (gameBoard[i][j] == computerDisc
								&& gameBoard[i-1][j-1] == 0
								&& gameBoard[i][j-1] != 0) {
							gameBoard[i-1][j-1] = computerDisc;
							setMove(true);
							System.out.println("build one left bdiagonal");
							break;
						}
					}
				}
			}
		}

		private void computerAggressiveVertical(int[][] gameBoard, int computerDisc) {
		// TODO Auto-generated method stub
			//Places a disc on top of a single disc.
			for(int i = gameBoard.length-1; i >= 1; i--) {
				for(int j = gameBoard[0].length-1; j >= 0; j--) {
					if(getMove() == false) {
						if (gameBoard[i][j] == computerDisc 
								&& gameBoard[i-1][j] == 0) {
							gameBoard[i-1][j] = computerDisc;
							setMove(true);
							System.out.println("build vertical one");
						}
					}
				}
			}
		}

		private void computerAggresiveHorizontal(int[][] gameBoard, int computerDisc) {
		// TODO Auto-generated method stub
			for (int i = 0; i < gameBoard.length; i++) {
				for(int j = 0; j < gameBoard[0].length-2; j++) {
					if(getMove() == false) {		
						//Places a disc to the right of a disc at the base of a grid.
						if (i == gameBoard.length-1
								&& gameBoard[i][j] == computerDisc
								&& gameBoard[i][j+1] == 0) {
							gameBoard[i][j+1] = computerDisc;
							setMove(true);
							System.out.println("build one base rightside horizontal");
							break;
						}
						//Places a disc to the right of a disc anywhere above the base.
						else if (i != gameBoard.length-1
								&& gameBoard[i][j] == computerDisc
								&& gameBoard[i][j+1] == 0
								&& gameBoard[i+1][j+1] != 0) {
							gameBoard[i][j+1] = computerDisc;
							setMove(true);
							System.out.println("build one rightside horizontal");
							break;
						}
						//Places a disc to the left of a disc at the base of a grid.
						else if (i == gameBoard.length-1
								&& gameBoard[i][j+1] == computerDisc
								&& gameBoard[i][j] == 0) {
							gameBoard[i][j] = computerDisc;
							setMove(true);
							System.out.println("build one base leftside horizontal");
							break;
						}
						//Places a disc to the left of a disc anywhere above the base.
						else if (i != gameBoard.length-1
								&& gameBoard[i][j+1] == computerDisc
								&& gameBoard[i][j] == 0
								&& gameBoard[i+1][j] != 0) {
							gameBoard[i][j] = computerDisc;
							setMove(true);
							System.out.println("build one lefttside horizontal");
							break;
						}
					}
				}
			}
		}

		private void computerMoveRandom(int[][] gameBoard, int computerDisc) {
		// TODO Auto-generated method stub
			 if (getMove() == false) { 
				 Random i = new Random(); 
				 int j = i.nextInt(gameBoard[0].length); 
				 //If the column selected randomly is empty, then place the disc.
				 if (gameBoard[0][j] == 0) { 
					 setDisc(j,gameBoard,computerDisc); 
				 }
				 //If not, keep looking for an open column to fill.
				 else if (gameBoard[0][j] != 0) { 
					 while (gameBoard[0][j] !=0) { 
						 j = i.nextInt(gameBoard[0].length); 
						 if (gameBoard[0][j] == 0) { 
							 break; 
							 } 
						 }
					 setDisc(j,gameBoard,computerDisc);
				 }
			 }
		}

		private void computerWinHorizontal(int[][] gameBoard, int computerDisc) {
		// TODO Auto-generated method stub
			for (int i = 0; i < gameBoard.length; i++) {
				for(int j = 0; j < gameBoard[0].length-3; j++) {
					if(getMove() == false) {
						//Places a disc to the rightmost column of a three-disc horizontal at the base of the grid.
						if (i == gameBoard.length-1 
								&& gameBoard[i][j] == computerDisc
								&& gameBoard[i][j+1] == computerDisc 
								&& gameBoard[i][j+2] == computerDisc
								&& gameBoard[i][j+3] == 0) {
							gameBoard[i][j+3] = computerDisc;
							System.out.println("win base horizontal rightmost");
							setMove(true);
							break;
						}
						//Places a disc to the leftmost column of a three-disc horizontal at the base of the grid.
						else if(i == gameBoard.length-1 
								&& gameBoard[i][j] == 0
								&& gameBoard[i][j+1] == computerDisc 
								&& gameBoard[i][j+2] == computerDisc
								&& gameBoard[i][j+3] == computerDisc) {
							gameBoard[i][j] = computerDisc;
							setMove(true);
							System.out.println("win base horizontal leftmost");
							break;
						} 
						//Places a disc to the rightmost column of a three-disc horizontal anywhere above the base of the grid.
						else if (i != gameBoard.length-1 
								&& gameBoard[i][j] == computerDisc 
								&& gameBoard[i][j+1] == computerDisc 
								&& gameBoard[i][j+2] == computerDisc 
								&& gameBoard[i][j+3] == 0 
								&& gameBoard[i+1][j+3] != 0) {
							gameBoard[i][j+3] = computerDisc;
							setMove(true);
							System.out.println("win horizontal rightmost");
							break;
						}
						//Places a disc to the leftmost column of a three-disc horizontal anywhere above the base of the grid.
						else if (i != gameBoard.length-1 
								&& gameBoard[i][j] == 0
								&& gameBoard[i][j+1] == computerDisc 
								&& gameBoard[i][j+2] == computerDisc
								&& gameBoard[i][j+3] == computerDisc
								&& gameBoard[i+1][j] != 0) {
							gameBoard[i][j] = computerDisc;
							setMove(true);
							System.out.println("win horizontal leftmost");
							break;
						}
						//Places a disc into the gap of a one-two disc horizontal anywhere above the base of the grid.
						else if (i != gameBoard.length-1 
								&& gameBoard[i][j] == computerDisc
								&& gameBoard[i][j+1] == 0 
								&& gameBoard[i][j+2] == computerDisc
								&& gameBoard[i][j+3] == computerDisc
								&& gameBoard[i+1][j+1] != 0) {
							gameBoard[i][j+1] = computerDisc;
							setMove(true);
							System.out.println("win horizontal leftmiddle");
							break;
						}
						//Places a disc into the gap of a one-two disc horizontal at the base of the grid.
						else if (i == gameBoard.length-1 
								&& gameBoard[i][j] == computerDisc
								&& gameBoard[i][j+1] == 0 
								&& gameBoard[i][j+2] == computerDisc
								&& gameBoard[i][j+3] == computerDisc) {
							gameBoard[i][j+1] = computerDisc;
							setMove(true);
							System.out.println("win base horizontal leftmiddle");
							break;
						}
						//Places a disc into the gap of a two-one disc horizontal anywhere above the base of the grid.
						else if (i != gameBoard.length-1
								&& gameBoard[i][j] == computerDisc
								&& gameBoard[i][j+1] == computerDisc
								&& gameBoard[i][j+2] == 0
								&& gameBoard[i][j+3] == computerDisc
								&& gameBoard[i+1][j+2] != 0) {
							gameBoard[i][j+2] = computerDisc;
							setMove(true);
							System.out.println("win horizontal rightmiddle");
							break;
						}
						//Places a disc into the gap of a two-one disc horizontal at the base of the grid.
						else if (i == gameBoard.length-1
								&& gameBoard[i][j] == computerDisc
								&& gameBoard[i][j+1] == computerDisc
								&& gameBoard[i][j+2] == 0
								&& gameBoard[i][j+3] == computerDisc) {
							gameBoard[i][j+2] = computerDisc;
							setMove(true);
							System.out.println("win base horizontal rightmiddle");
							break;
						}
					}
				}		
			}
		}
	
		private void computerWinVertical(int[][] gameBoard, int computerDisc) {
		// TODO Auto-generated method stub
			for(int i = gameBoard.length-1; i >= 3; i--) {
				for(int j = gameBoard[0].length-1; j >= 0; j--) {
					if(getMove() == false) {
						//Places a disc on top of a three-disc vertical line.
						 if (gameBoard[i][j] == computerDisc
								&& gameBoard[i-1][j] == computerDisc
								&& gameBoard[i-2][j] == computerDisc
								&& gameBoard[i-3][j] == 0) {
						gameBoard[i-3][j] = computerDisc;
						setMove(true);
						System.out.println("win vertical");
						break;
						}
					}
				}
			}
		}
		
		private void computerWinBackdiagonal(int[][] gameBoard, int computerDisc) {
		// TODO Auto-generated method stub
			for(int i = gameBoard.length-1; i >= 3; i--) {
				for(int j = gameBoard[0].length-1; j >= 3; j--) {
					if(getMove() == false) {
						//Places a disc at the leftmost column of a negative diagonal.
						if (gameBoard[i][j] == computerDisc
								&& gameBoard[i-1][j-1] == computerDisc
								&& gameBoard[i-2][j-2] == computerDisc
								&& gameBoard[i-3][j-3] == 0
								&& gameBoard[i-2][j-3] != 0) {
							gameBoard[i-3][j-3] = computerDisc;
							setMove(true);
							System.out.println("win leftmost bdiagonal");
							break;
						}
						//Places a disc at the gap of a two-one disc negative diagonal.
						else if (gameBoard[i][j] == computerDisc 
								&& gameBoard[i-1][j-1] == computerDisc
								&& gameBoard[i-2][j-2] == 0
								&& gameBoard[i-3][j-3] == computerDisc
								&& gameBoard[i-1][j-2] != 0) {
							gameBoard[i-2][j-2] = computerDisc;
							setMove(true);
							System.out.println("win middleleft bdiagonal");
							break;
						}
						//Places a disc at the gap of a one-two disc negative diagonal.
						else if (gameBoard[i][j] == computerDisc
								&& gameBoard[i-1][j-1] == 0
								&& gameBoard[i-2][j-2] == computerDisc
								&& gameBoard[i-3][j-3] == computerDisc 
								&& gameBoard[i][j-1] != 0) {
							gameBoard[i-1][j-1] = computerDisc;
							setMove(true);
							System.out.println("win middleright bdiagonal");
							break;
						}
						//Places a disc at the rightmost column of a negative diagonal anywhere above the base of the grid.
						else if (i != gameBoard.length-1
								&& gameBoard[i][j] == 0
								&& gameBoard[i-1][j-1] == computerDisc
								&& gameBoard[i-2][j-2] == computerDisc
								&& gameBoard[i-3][j-3] == computerDisc
								&& gameBoard[i+1][j] != 0) {
							gameBoard[i][j] = computerDisc;
							setMove(true);
							System.out.println("win rightmost bdiagonal");
							break;
						}
						//Places a disc at the rightmost column of a negative diagonal at the base of the grid.
						else if (i == gameBoard.length-1
								&& gameBoard[i][j] == 0
								&& gameBoard[i-1][j-1] == computerDisc
								&& gameBoard[i-2][j-2] == computerDisc
								&& gameBoard[i-3][j-3] == computerDisc) {
							gameBoard[i][j] = computerDisc;
							setMove(true);
							System.out.println("win base rightmost bdiagonal");
							break;
						}
					}
				}
			}
		}
		
		private void computerWinFrontdiagonal(int[][] gameBoard, int computerDisc) {
		// TODO Auto-generated method stub
			for(int i = gameBoard.length-1; i >= gameBoard.length-3; i--) {
				for(int j = 0; j < gameBoard[0].length-3; j++) {
					if(getMove() == false) {
						//Places a disc at the leftmost column of a positive diagonal at the base of the grid.
						if (i == gameBoard.length-1 
								&& gameBoard[i][j] == 0
								&& gameBoard[i-1][j+1] == computerDisc
								&& gameBoard[i-2][j+2] == computerDisc
								&& gameBoard[i-3][j+3] == computerDisc) {
							gameBoard[i][j] = computerDisc;
							setMove(true);
							System.out.println("win base leftmost fdiagonal");
							break;
						}
						//Places a disc at the leftmost column of a positive diagonal anywhere above the base of the grid.
						else if (i != gameBoard.length-1 
								&& gameBoard[i][j] == 0
								&& gameBoard[i-1][j+1] == computerDisc
								&& gameBoard[i-2][j+2] == computerDisc
								&& gameBoard[i-3][j+3] == computerDisc
								&& gameBoard[i+1][j] != 0) {
							gameBoard[i][j] = computerDisc;
							setMove(true);
							System.out.println("win leftmost fdiagonal");
							break;
						}
						//Places a disc at the gap of a one-two disc positive diagonal.
						else if (gameBoard[i][j] == computerDisc
								&& gameBoard[i-1][j+1] == 0
								&& gameBoard[i-2][j+2] == computerDisc
								&& gameBoard[i-3][j+3] == computerDisc
								&& gameBoard[i][j+1] != 0) {
							gameBoard[i-1][j+1] = computerDisc;
							setMove(true);
							System.out.println("win middleleft fdiagonal");
							break;
						}
						//Places a disc at the gap of a two-one disc positive diagonal.
						else if (gameBoard[i][j] == computerDisc
								&& gameBoard[i-1][j+1] == computerDisc
								&& gameBoard[i-2][j+2] == 0
								&& gameBoard[i-3][j+3] == computerDisc
								&& gameBoard[i-1][j+2] != 0) {
							gameBoard[i-2][j+2] = computerDisc;
							setMove(true);
							System.out.println("win middleright fdiagonal");
							break;
						}
						//Places a disc at the rightmost column of a positive diagonal.
						else if (gameBoard[i][j] == computerDisc
								&& gameBoard[i-1][j+1] == computerDisc
								&& gameBoard[i-2][j+2] == computerDisc
								&& gameBoard[i-3][j+3] == 0
								&& gameBoard[i-2][j+3] != 0) {
							gameBoard[i-3][j+3] = computerDisc;
							setMove(true);
							System.out.println("win rightmost fdiagonal");
							break;
						}
					}
				}
			}
		}
		
		private void computerBlockHorizontal(int[][] gameBoard, int playerDisc, int computerDisc) {			
			for (int i = 0; i < gameBoard.length; i++) {
				for(int j = 0; j < gameBoard[0].length-3; j++) {
					if(getMove() == false) {
						//Places a disc to the rightmost column of player's three-disc horizontal at the base of the grid.
						if (i == gameBoard.length-1 
								&& gameBoard[i][j] == playerDisc
								&& gameBoard[i][j+1] == playerDisc 
								&& gameBoard[i][j+2] == playerDisc
								&& gameBoard[i][j+3] == 0) {
							gameBoard[i][j+3] = computerDisc;
							System.out.println("block base horizontal right");
							setMove(true);
							break;
						}
						//Places a disc to the rightmost column of player's three-disc horizontal anywhere above the base of the grid.
						else if (i != gameBoard.length-1 
								&& gameBoard[i][j] == playerDisc
								&& gameBoard[i][j+1] == playerDisc 
								&& gameBoard[i][j+2] == playerDisc
								&& gameBoard[i][j+3] == 0
								&& gameBoard[i+1][j+3] != 0) {
							gameBoard[i][j+3] = computerDisc;
							setMove(true);
							System.out.println("block horizontal right");
							break;
						}
						//Places a disc to the leftmost column of player's three-disc horizontal at the base of the grid.
						else if(i == gameBoard.length-1 
								&& gameBoard[i][j] == 0
								&& gameBoard[i][j+1] == playerDisc 
								&& gameBoard[i][j+2] == playerDisc
								&& gameBoard[i][j+3] == playerDisc) {
							gameBoard[i][j] = computerDisc;
							setMove(true);
							System.out.println("block base horizontal left");
							break;
						} 
						//Places a disc to the leftmost column of a three-disc horizontal anywhere above the base of the grid.
						else if (i != gameBoard.length-1 
								&& gameBoard[i][j] == 0
								&& gameBoard[i][j+1] == playerDisc 
								&& gameBoard[i][j+2] == playerDisc
								&& gameBoard[i][j+3] == playerDisc
								&& gameBoard[i+1][j] != 0) {
							gameBoard[i][j] = computerDisc;
							setMove(true);
							System.out.println("block horizontal left");
							break;
						}
						//Places a disc into the gap of player's one-two disc horizontal anywhere above the base of the grid.
						else if (i != gameBoard.length-1 
								&& gameBoard[i][j] == playerDisc
								&& gameBoard[i][j+1] == 0 
								&& gameBoard[i][j+2] == playerDisc
								&& gameBoard[i][j+3] == playerDisc
								&& gameBoard[i+1][j+1] != 0) {
							gameBoard[i][j+1] = computerDisc;
							setMove(true);
							System.out.println("block horizontal leftmiddle");
							break;
						}
						//Places a disc into the gap of player's one-two disc horizontal at the base of the grid.
						else if (i == gameBoard.length-1 
								&& gameBoard[i][j] == playerDisc
								&& gameBoard[i][j+1] == 0 
								&& gameBoard[i][j+2] == playerDisc
								&& gameBoard[i][j+3] == playerDisc) {
							gameBoard[i][j+1] = computerDisc;
							setMove(true);
							System.out.println("block base horizontal leftmiddle");
							break;
						}
						//Places a disc into the gap of player's two-one disc horizontal anywhere above the base of the grid.
						else if (i != gameBoard.length-1
								&& gameBoard[i][j] == playerDisc
								&& gameBoard[i][j+1] == playerDisc
								&& gameBoard[i][j+2] == 0
								&& gameBoard[i][j+3] == playerDisc
								&& gameBoard[i+1][j+2] != 0) {
							gameBoard[i][j+2] = computerDisc;
							setMove(true);
							System.out.println("block horizontal rightmiddle");
							break;
						}
						//Places a disc into the gap of player's two-one disc horizontal at the base of the grid.
						else if (i == gameBoard.length-1
								&& gameBoard[i][j] == playerDisc
								&& gameBoard[i][j+1] == playerDisc
								&& gameBoard[i][j+2] == 0
								&& gameBoard[i][j+3] == playerDisc) {
							gameBoard[i][j+2] = computerDisc;
							setMove(true);
							System.out.println("block base horizontal rightmiddle");
							break;
						}
					}
				}		
			}
		}
		
		private void computerBlockVertical(int[][] gameBoard, int playerDisc, int computerDisc) {
			for(int i = gameBoard.length-1; i >= 3; i--) {
				for(int j = gameBoard[0].length-1; j >= 0; j--) {
					if(getMove() == false) {
						//Places a disc on top of player's three-disc vertical line.
						if (gameBoard[i][j] == playerDisc
								&& gameBoard[i-1][j] == playerDisc
								&& gameBoard[i-2][j] == playerDisc
								&& gameBoard[i-3][j] == 0) {
						gameBoard[i-3][j] = computerDisc;
						setMove(true);
						System.out.println("block vertical");
						break;
						}
					}
				}
			}
		}				
			
		private void computerBlockBackdiagonal(int[][] gameBoard, int playerDisc, int computerDisc) {
			// TODO Auto-generated method stub
			for(int i = gameBoard.length-1; i >= 3; i--) {
				for(int j = gameBoard[0].length-1; j >= 3; j--) {
					if(getMove() == false) {
						//Places a disc at the leftmost column of player's negative diagonal.
						if (gameBoard[i][j] == playerDisc
								&& gameBoard[i-1][j-1] == playerDisc
								&& gameBoard[i-2][j-2] == playerDisc
								&& gameBoard[i-3][j-3] == 0
								&& gameBoard[i-2][j-3] != 0) {
							gameBoard[i-3][j-3] = computerDisc;
							setMove(true);
							System.out.println("block leftmost bdiagonal");
							break;
						}
						//Places a disc at the gap of player's two-one disc negative diagonal.
						else if (gameBoard[i][j] == playerDisc
								&& gameBoard[i-1][j-1] == playerDisc
								&& gameBoard[i-2][j-2] == 0
								&& gameBoard[i-3][j-3] == playerDisc
								&& gameBoard[i-1][j-2] != 0) {
							gameBoard[i-2][j-2] = computerDisc;
							setMove(true);
							System.out.println("block leftmiddle bdiagonal");
							break;
						}
						//Places a disc at the gap of player's one-two disc negative diagonal.
						else if (gameBoard[i][j] == playerDisc
								&& gameBoard[i-1][j-1] == 0
								&& gameBoard[i-2][j-2] == playerDisc
								&& gameBoard[i-3][j-3] == playerDisc
								&& gameBoard[i][j-1] != 0) {
							gameBoard[i-1][j-1] = computerDisc;
							setMove(true);
							System.out.println("block rightmiddle bdiagonal");
							break;
						}
						//Places a disc at the rightmost column of player's negative diagonal anywhere above the base of the grid.
						else if (i != gameBoard.length-1
								&&  gameBoard[i][j] == 0
								&&  gameBoard[i-1][j-1] == playerDisc
								&&  gameBoard[i-2][j-2] == playerDisc
								&&  gameBoard[i-3][j-3] == playerDisc
								&&  gameBoard[i+1][j] != 0) {
							gameBoard[i][j] = computerDisc;
							setMove(true);
							System.out.println("block rightmost bdiagonal");
							break;
						}
						//Places a disc at the rightmost column of player's negative diagonal at the base of the grid.
						else if (i == gameBoard.length-1
								&&  gameBoard[i][j] == 0
								&&  gameBoard[i-1][j-1] == playerDisc
								&&  gameBoard[i-2][j-2] == playerDisc
								&&  gameBoard[i-3][j-3] == playerDisc) {
							gameBoard[i][j] = computerDisc;
							setMove(true);
							System.out.println("block base rightmost bdiagonal");
							break;
							}
						}
					}
				}
			}
		
		private void computerBlockFrontdiagonal(int[][] gameBoard, int playerDisc, int computerDisc) {
			// TODO Auto-generated method stub
			for(int i = gameBoard.length-1; i >= gameBoard.length-3; i--) {
				for(int j = 0; j < gameBoard[0].length-3; j++) {
					if(getMove() == false) {
						//Places a disc at the rightmost column of player's positive diagonal.
						if (gameBoard[i][j] == playerDisc
								&& gameBoard[i-1][j+1] == playerDisc
								&& gameBoard[i-2][j+2] == playerDisc
								&& gameBoard[i-3][j+3] == 0
								&& gameBoard[i-2][j+3] != 0) {
							gameBoard[i-3][j+3] = computerDisc;
							setMove(true);
							System.out.println("block rightmost fdiagonal");
							break;
						}
						//Places a disc at the gap of player's two-one disc positive diagonal.
						else if (gameBoard[i][j] == playerDisc
								&& gameBoard[i-1][j+1] == playerDisc
								&& gameBoard[i-2][j+2] == 0 
								&& gameBoard[i-3][j+3] == playerDisc
								&& gameBoard[i-1][j+2] != 0) {
							gameBoard[i-2][j+2] = computerDisc;
							setMove(true);
							System.out.println("block middleright fdiagonal");
							break;
						}
						//Places a disc at the gap of player's one-two disc positive diagonal.
						else if (gameBoard[i][j] == playerDisc
								&& gameBoard[i-1][j+1] == 0
								&& gameBoard[i-2][j+2] == playerDisc
								&& gameBoard[i-3][j+3] == playerDisc
								&& gameBoard[i][j+1] != 0) {
							gameBoard[i-1][j+1] = computerDisc;
							setMove(true);
							System.out.println("block middleleft fdiagonal");
							break;
						}
						//Places a disc at the leftmost column of player's positive diagonal anywhere above the base of the grid.
						else if (i != gameBoard.length-1
								&& gameBoard[i][j] == 0
								&& gameBoard[i-1][j+1] == playerDisc
								&& gameBoard[i-2][j+2] == playerDisc
								&& gameBoard[i-3][j+3] == playerDisc 
								&& gameBoard[i+1][j] != 0) {
							gameBoard[i][j] = computerDisc;
							setMove(true);
							System.out.println("block leftmost fdiagonal");
							break;
						}
						//Places a disc at the leftmost column of player's positive diagonal at the base of the grid.
						else if (i == gameBoard.length-1
								&& gameBoard[i][j] == 0
								&& gameBoard[i-1][j+1] == playerDisc
								&& gameBoard[i-2][j+2] == playerDisc
								&& gameBoard[i-3][j+3] == playerDisc) {
							gameBoard[i][j] = computerDisc;
							setMove(true);
							System.out.println("block base left fdiagonal");
							break;
						}
					}
				}
			}
		}

		private void computerBuildHorizontal(int[][] gameBoard, int computerDisc) {
		// TODO Auto-generated method stub
			for (int i = 0; i < gameBoard.length; i++) {
				for(int j = 0; j < gameBoard[0].length-3; j++) {
					if(getMove() == false) {
						if (i == gameBoard.length-1
								&& gameBoard[i][j] == computerDisc
								&& gameBoard[i][j+1] == computerDisc
								&& gameBoard[i][j+2] == 0
								&& gameBoard[i][j+3] == 0 ) {
							gameBoard[i][j+2] = computerDisc;
							setMove(true);
							System.out.println("build base rightside horizontal");
							break;
						}
						else if (i != gameBoard.length-1
								&& gameBoard[i][j] == computerDisc
								&& gameBoard[i][j+1] == computerDisc
								&& gameBoard[i][j+2] == 0
								&& gameBoard[i][j+3] == 0
								&& gameBoard[i+1][j+2] != 0) {
							gameBoard[i][j+2] = computerDisc;
							setMove(true);
							System.out.println("build rightside horizontal");
							break;
						}
						else if (i == gameBoard.length-1 
								&& gameBoard[i][j] == 0
								&& gameBoard[i][j+1] == 0
								&& gameBoard[i][j+2] == computerDisc
								&& gameBoard[i][j+3] == computerDisc ) {
							gameBoard[i][j+1] = computerDisc;
							setMove(true);
							System.out.println("build base leftside horizontal");
							break;
						}
						else if (i != gameBoard.length-1 
								&& gameBoard[i][j] == 0 
								&& gameBoard[i][j+1] == 0 
								&& gameBoard[i][j+2] == computerDisc 
								&& gameBoard[i][j+3] == computerDisc 
								&& gameBoard[i+1][j+1] != 0) {
							gameBoard[i][j+1] = computerDisc;
							setMove(true);
							System.out.println("build left horizontal");
							break;
						}
						else if (i == gameBoard.length-1 
								&& gameBoard[i][j] == computerDisc
								&& gameBoard[i][j+1] == 0
								&& gameBoard[i][j+2] == 0
								&& gameBoard[i][j+3] == computerDisc ) {
							gameBoard[i][j+1] = computerDisc;
							setMove(true);
							System.out.println("build base middleleft horizontal");
							break;
						}
						else if (i != gameBoard.length-1 
								&& gameBoard[i][j] == computerDisc
								&& gameBoard[i][j+1] == 0
								&& gameBoard[i][j+2] == 0
								&& gameBoard[i][j+3] == computerDisc 
								&& gameBoard[i+1][j+1] != 0) {
							gameBoard[i][j+1] = computerDisc;
							setMove(true);
							System.out.println("build middleleft horizontal");
							break;
						}
					}
				}		
			}
		}
		
		private void computerBuildVertical(int[][] gameBoard, int computerDisc) {
		// TODO Auto-generated method stub
		for(int i = gameBoard.length-1; i >= 3; i--) {
				for(int j = gameBoard[0].length-1; j >= 0; j--) {
					if(getMove() == false) {
						//Places a disc on top of computer's two-disc vertical line.
						if (gameBoard[i][j] == computerDisc 
								&& gameBoard[i-1][j] == computerDisc
								&& gameBoard[i-2][j] == 0 
								&& gameBoard[i-3][j] == 0) {
							gameBoard[i-2][j] = computerDisc;
							setMove(true);
							System.out.println("build vertical");
						}
					}
				}
			}
		}
		
		private void computerBuildBackdiagonal(int[][] gameBoard, int computerDisc) {
		// TODO Auto-generated method stub
			for(int i = gameBoard.length-1; i >= 3; i--) {
				for(int j = gameBoard[0].length-1; j >= 3; j--) {
					if(getMove() == false) {
						if (gameBoard[i][j] == 0
								&& gameBoard[i-1][j-1] == computerDisc
								&& gameBoard[i-2][j-2] == computerDisc
								&& gameBoard[i-3][j-3] == 0
								&& gameBoard[i-2][j-3] != 0) {
							gameBoard[i-3][j-3] = computerDisc;
							setMove(true);
							System.out.println("build leftmost bdiagonal");
							break;
						}
						else if (gameBoard[i][j] == computerDisc
								&& gameBoard[i-1][j-1] == computerDisc
								&& gameBoard[i-2][j-2] == 0
								&& gameBoard[i-3][j-3] == 0
								&& gameBoard[i-2][j-2] != 0) {
							gameBoard[i-2][j-2] = computerDisc;
							setMove(true);
							System.out.println("build middleleft bdiagonal");
							break;
						}
						else if (gameBoard[i][j] == 0
								&& gameBoard[i-1][j-1] == 0
								&& gameBoard[i-2][j-2] == computerDisc
								&& gameBoard[i-3][j-3] == computerDisc 
								&& gameBoard[i][j-1] != 0) {
							gameBoard[i-1][j-1] = computerDisc;
							setMove(true);
							System.out.println("build middleright bdiagonal");
							break;
						}
						else if (i != gameBoard.length-1
								&& gameBoard[i][j] == 0
								&& gameBoard[i-1][j-1] == computerDisc
								&& gameBoard[i-2][j-2] == 0
								&& gameBoard[i-3][j-3] == computerDisc
								&& gameBoard[i+1][j] != 0) {
							gameBoard[i][j] = computerDisc;
							setMove(true);
							System.out.println("build rightmost bdiagonal");
							break;
						}
						else if (i == gameBoard.length-1
								&& gameBoard[i][j] == 0
								&& gameBoard[i-1][j-1] == computerDisc
								&& gameBoard[i-2][j-2] == 0
								&& gameBoard[i-3][j-3] == computerDisc) {
							gameBoard[i][j] = computerDisc;
							setMove(true);
							System.out.println("build base rightmost bdiagonal");
							break;
						}
					}
				}
			}
		}

		private void computerBuildFrontdiagonal(int[][] gameBoard, int computerDisc) {
		// TODO Auto-generated method stub 
			for(int i = gameBoard.length-1; i >= gameBoard.length-3; i--) {
				for(int j = 0; j < gameBoard[0].length-3; j++) {
					if(getMove() == false) {
						if (i == gameBoard.length-1 
								&& gameBoard[i][j] == 0
								&& gameBoard[i-1][j+1] == computerDisc
								&& gameBoard[i-2][j+2] == 0
								&& gameBoard[i-3][j+3] == computerDisc) {
							gameBoard[i][j] = computerDisc;
							setMove(true);
							System.out.println("build base leftmost fdiagonal");
							break;
						}
						if (i != gameBoard.length-1 
								&& gameBoard[i][j] == 0
								&& gameBoard[i-1][j+1] == computerDisc
								&& gameBoard[i-2][j+2] == 0
								&& gameBoard[i-3][j+3] == computerDisc
								&& gameBoard[i+1][j] != 0) {
							gameBoard[i][j] = computerDisc;
							setMove(true);
							System.out.println("build leftmost fdiagonal");
							break;
						}
						else if (gameBoard[i][j] == 0
								&& gameBoard[i-1][j+1] == 0
								&& gameBoard[i-2][j+2] == computerDisc
								&& gameBoard[i-3][j+3] == computerDisc
								&& gameBoard[i][j+1] != 0) {
							gameBoard[i-1][j+1] = computerDisc;
							setMove(true);
							System.out.println("build middleleft fdiagonal");
							break;
						}
						else if (gameBoard[i][j] == computerDisc
								&& gameBoard[i-1][j+1] == computerDisc
								&& gameBoard[i-2][j+2] == 0
								&& gameBoard[i-3][j+3] == 0
								&& gameBoard[i-1][j+2] != 0) {
							gameBoard[i-2][j+2] = computerDisc;
							setMove(true);
							System.out.println("build middleright fdiagonal");
							break;
						}
						else if (gameBoard[i][j] == 0
								&& gameBoard[i-1][j+1] == computerDisc
								&& gameBoard[i-2][j+2] == computerDisc
								&& gameBoard[i-3][j+3] == 0
								&& gameBoard[i-2][j+3] != 0) {
							gameBoard[i-3][j+3] = computerDisc;
							setMove(true);
							System.out.println("build rightmost fdiagonal");
							break;
						}			
					}
				}
			}
		}
		
		private boolean getMove() {
			return move;
		}
		private void setMove(boolean move) {
			this.move = move;
		}
}
