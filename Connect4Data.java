
public class Connect4Data {
	private final int boardRow;
	private final int boardColumn;
	private boolean gameOver;
	private int[][] gameBoard;
	
	public Connect4Data(int boardRow, int boardColumn) {
		this.boardRow = boardRow;
		this.boardColumn = boardColumn;
		this.gameOver = false;
	}

		public int[][] createGameBoard() {
			this.gameBoard = new int [boardRow][boardColumn];
			return this.gameBoard;
		}
		
		public void setDisc(int column, int[][] gameBoard, int playerDisc) {
			for(int a = boardRow-1; a >= 0; a--)  {
				//if column is full, do nothing. UserInterface class has a similar check.
				if (gameBoard[0][column] != 0){ 
					break; 
				} 
				else if (gameBoard[a][column] == 0) {
					gameBoard[a][column] = playerDisc;
					break;
				}
			}
			checkLine(gameBoard, playerDisc);
		}
		
		public void checkLine(int [][] gameBoard, int player) {
			checkHorizontal(gameBoard,player);
			checkVertical(gameBoard,player);
			checkFrontDiagonal(gameBoard,player);
			checkBackDiagonal(gameBoard,player);
		}
		
		private void checkHorizontal(int[][] gameBoard, int player) {
			for (int j = 0; j < boardColumn-3 ; j++) {
				for (int i = boardRow-1; i >= 0 ; i--) {
					//Checking for a horizontal line from the base of the grid, left to right.
					if(gameBoard[i][j] == player
							&& gameBoard[i][j+1] == player 
							&& gameBoard[i][j+2] == player 
							&& gameBoard[i][j+3] == player) {
						System.out.println("horizontal");
						gameOver = true;
						break;			
					}		
				}
			}
		}
		
		private void checkVertical(int[][] gameBoard, int player) {
			for (int i = 0; i < boardRow-3 ; i++) {
				for (int j = boardColumn-1; j >= 0 ; j--) {
					//Checking for a vertical line from the base of the grid.
					if(gameBoard[i][j] == player
					&& gameBoard[i+1][j] == player 
					&& gameBoard[i+2][j] == player 
					&& gameBoard[i+3][j] == player)
					{
						System.out.println("vertical");
						gameOver = true;
						break;
					}
				}
			}
		}
		
		private void checkFrontDiagonal (int[][] gameBoard, int player) {
			for (int i = boardRow-1; i >= boardRow-3 ; i--) {
				for (int x = 0; x < boardColumn-3 ; x++) {
					//Checking for a positive diagonal.
					if(gameBoard[i][x] == player
					&& gameBoard[i-1][x+1] == player 
					&& gameBoard[i-2][x+2] == player 
					&& gameBoard[i-3][x+3] == player)
					{
						System.out.println("fdiagonal");
						gameOver = true;
						break;
					}
				}
			}
		}
		
		private void checkBackDiagonal (int[][] gameBoard, int player) {
			for (int i = boardRow-4; i >= 0; i--) {
				for (int x = 0; x < boardColumn-3 ; x++) {
					//Checking for a negative diagonal.
					if(gameBoard[i][x] == player
					&& gameBoard[i+1][x+1] == player 
					&& gameBoard[i+2][x+2] == player 
					&& gameBoard[i+3][x+3] == player)
					{
						System.out.println("bdiagonal");
						gameOver = true;
						break;
					}
				}
			}
		}
		
		public void printBoard(int[][] gameBoard) {
			for(int i = 0; i < gameBoard.length; i++) {
			    for(int j = 0; j < gameBoard[0].length; j++) {
			    	//Prints a console representation of the grid.
			        System.out.print(gameBoard[i][j] + " ");
			    }
			    System.out.println("");
			}
			System.out.println("");
			
		}
		
		public void resetBoard(int[][] gameBoard) {
		    //resets value inside the board to its initial state.
			gameOver = false;
			for(int r = 0; r < gameBoard.length; r++) {
			    for(int c = 0; c < gameBoard[0].length; c++) {
			        gameBoard[r][c] = 0;
			    }
		}
}
		
		public boolean getWinner() {
			return this.gameOver;
		}
		
		public boolean isFull(int[][] gameBoard) {
		int column = 0;
		boolean full = false;
			for (int j = 0; j < boardColumn; j++) {
				//If the top row is filled, raise the count.
				if (gameBoard[0][j] != 0) {
					column++;
				}
				if (column == boardColumn) {
					full = true;
				}
				else {
					full = false;
				}
			}
			return full;
		}
		
		public int getTurnCount(int currentPlayerDisc, int[][] gameBoard) {
			//To count the pieces in the grid given the player disc.
			 int count = 0; 
			 for (int i = 0; i < gameBoard.length; i++) { 
				 for (int j = 0; j < gameBoard[0].length;j++) { 
					 if (gameBoard[i][j] == currentPlayerDisc) { 
						 count++; 
						 } 
					 } 
				 }
			 return count;
		}
}