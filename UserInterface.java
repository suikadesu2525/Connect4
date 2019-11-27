import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

class UserInterface extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private int boardRow = 8;
	private int boardColumn = 7;
	private int frameSize;
	// GUI-related fields.
	private Color playerColor;
	private Color computerColor;
	private Color emptyColor = Color.WHITE;
	private JFrame mainBoard;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem saveFile, loadFile, deleteFile;
	private JLabel playerLabel;
	private JLabel playerTurnLabel;
	private JLabel computerLabel;
	private JLabel computerTurnLabel;
	private JButton[][] discButton;
	private JButton startButton;
	private JButton pauseButton;
	private JButton resetButton;
	private Color[] colorLists = {Color.RED, Color.CYAN, Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.ORANGE, Color.BLACK};
	// Instantiation of other classes for the game.
	Connect4Data board = new Connect4Data(boardRow, boardColumn);
	SaveLoad save = new SaveLoad();
	ComputerLogic computer = new ComputerLogic(boardRow, boardColumn);
	GameSettings setting = new GameSettings();
	// Setting and game-related variables.
	private int[][] newBoard = board.createGameBoard();
	private int playerDisc;
	private int computerDisc;
	private int playerColorIndex;
	private int computerColorIndex;

	// initialising local variables with game parameters the user has chosen.
	public UserInterface(int frameSize, int playerColorIndex, int computerColorIndex, int startFirst) {
		this.frameSize = frameSize;
		this.playerColor = colorLists[playerColorIndex];
		this.computerColor = colorLists[computerColorIndex];
		this.playerDisc = startFirst;
		this.playerColorIndex = playerColorIndex;
		this.computerColorIndex = computerColorIndex;
	}
	
		private JMenuBar createMenuBar() {
			// Create the menu bar.
			menuBar = new JMenuBar();
			// Building the menu.
			menu = new JMenu("File");
			menu.setMnemonic(KeyEvent.VK_F);
			menuBar.add(menu);
			// JMenuItems for saving, loading, and delete. Also has keyboard shortcuts.
			saveFile = new JMenuItem("Save", KeyEvent.VK_S);
			loadFile = new JMenuItem("Load", KeyEvent.VK_L);
			deleteFile = new JMenuItem("Delete", KeyEvent.VK_D);
			saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
			loadFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
			deleteFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
			// Text to be shown when each option is hovered.
			saveFile.setToolTipText("Saves the current board state.");
			loadFile.setToolTipText("Loads previously saved board state.");
			deleteFile.setToolTipText("Deletes saved board state.");
			saveFile.addActionListener(this);
			loadFile.addActionListener(this);
			deleteFile.addActionListener(this);
			menu.add(saveFile);
			menu.add(loadFile);
			menu.add(deleteFile);
			return menuBar;
		}
	
		private JPanel createGameBoard() {
			// Main panel for board components.
			JPanel boardPanel = new JPanel();
			boardPanel.setLayout(new BorderLayout());
			/// Initialisation of button grid panel for discs.
			JPanel buttonsPanel = new JPanel();
			buttonsPanel.setBackground(Color.BLUE);
			buttonsPanel.setLayout(new GridLayout(boardRow, boardColumn));
			discButton = new JButton[boardRow][boardColumn];
			for (int i = 0; i < boardRow; i++) {
				for (int j = 0; j < boardColumn; j++) {
					discButton[i][j] = new JButton();
					discButton[i][j].setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLUE));
					discButton[i][j].setBackground(emptyColor);
					discButton[i][j].addActionListener(this);
					buttonsPanel.add(discButton[i][j]);
				}
			}
			/*
			 * Initialisation of statistic aspect of the board.
			 * Displays current number of turns and colour for both player and computer.
			 */
			JPanel statPanel = new JPanel();
			statPanel.setBackground(Color.DARK_GRAY);
			playerLabel = new JLabel("Player turns:");
			playerTurnLabel = new JLabel();
			computerLabel = new JLabel("Computer turns:");
			computerTurnLabel = new JLabel();
			statPanel.add(playerLabel);
			statPanel.add(playerTurnLabel);
			statPanel.add(computerLabel);
			statPanel.add(computerTurnLabel);
			playerLabel.setForeground(playerColor);
			computerLabel.setForeground(computerColor);
			playerTurnLabel.setForeground(playerColor);
			computerTurnLabel.setForeground(computerColor);
			//Initialisation of the control panel responsible for start, pause, and reset button.
			JPanel controlPanel = new JPanel();
			controlPanel.setBackground(Color.DARK_GRAY);
			startButton = new JButton("Start");
			pauseButton = new JButton("Pause");
			resetButton = new JButton("Reset");
			startButton.addActionListener(this);
			pauseButton.addActionListener(this);
			resetButton.addActionListener(this);
			startButton.setEnabled(false);
			controlPanel.add(startButton);
			controlPanel.add(pauseButton);
			controlPanel.add(resetButton);
			//Each panel is assigned to a specific area of the BorderLayout.
			boardPanel.add(statPanel, BorderLayout.NORTH);
			boardPanel.add(buttonsPanel, BorderLayout.CENTER);
			boardPanel.add(controlPanel, BorderLayout.SOUTH);
			
			return boardPanel;
		}
	
		public void createAndShowGUI() {
			// Initialising program window.
			mainBoard = new JFrame("Connect 4");
			mainBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainBoard.setSize(frameSize, frameSize);
			mainBoard.setJMenuBar(createMenuBar());
			mainBoard.setContentPane(createGameBoard());
			mainBoard.setLocationRelativeTo(null);
			mainBoard.setAlwaysOnTop(true);
			mainBoard.setVisible(true);
			mainBoard.setResizable(false);
			// Initial move. If player starts first, then assign second disc to computer.
			// If player starts second, let computer do a move first.
			if (playerDisc == 1) {
				computerDisc = 2;
			} 
			else if (playerDisc == 2) {
				computerDisc = 1;
				computer.computerMove(newBoard ,playerDisc, computerDisc);				
			}
			updateGUI();
		}
	
		private JButton[][] updateButtons(int[][] gameBoard, JButton[][] buttons) {
			//Updating the disc buttons from the values of the game board.
			for (int i = 0; i < buttons.length; i++) {
				for (int j = 0; j < buttons[0].length; j++) {
					if (gameBoard[i][j] == playerDisc) {
						buttons[i][j].setBackground(playerColor);
					} else if (gameBoard[i][j] == computerDisc) {
						buttons[i][j].setBackground(computerColor);
					} else {
						buttons[i][j].setBackground(emptyColor);
					}
				}
			}
			return buttons;
		}
	
		private void gameOver() {
			//If checkLine from player board returns true, then player wins.
			if (board.getWinner() == true) {
				int winPane = JOptionPane.showConfirmDialog(mainBoard, "Player has won!" + "\n" + 
															"Do you want to play again?", "You Won!!!",
															JOptionPane.YES_NO_OPTION);
				if (winPane == JOptionPane.YES_OPTION) {
					//Closes the grid window and call the setting window again.
					mainBoard.dispose();
					setting.createSettingWindow();
				}
				else if (winPane == JOptionPane.NO_OPTION || winPane == JOptionPane.CLOSED_OPTION) {
					//Stops the program.
					System.exit(0);
				}
			} 
			//If checkLine from computer returns true, then computer wins.
			else if (computer.getWinner() == true) {
				int losePane = JOptionPane.showConfirmDialog(mainBoard, "Computer has won!" + "\n" +
															"Do you want to play again?",
															"You Lost...", JOptionPane.YES_NO_OPTION);
				if (losePane == JOptionPane.YES_OPTION) {
					//Closes the grid window and call the setting window again.
					mainBoard.dispose();
					setting.createSettingWindow();
				}
				else if (losePane == JOptionPane.NO_OPTION || losePane == JOptionPane.CLOSED_OPTION) {
					//Stops the program.
					System.exit(0);
				}
			}
			//If game board is filled with disc, game ends in a draw.
			else if (board.isFull(newBoard) == true) {
				int resetPane = JOptionPane.showConfirmDialog(mainBoard,
															  "Board is filled, game ends in a draw!" + "\n" +
															  "Do you want to play again?", "Draw!",
															  JOptionPane.YES_NO_OPTION);
				if (resetPane == JOptionPane.YES_OPTION) {
					//Closes the grid window and call the setting window again.
					mainBoard.dispose();
					setting.createSettingWindow();
				}
				else if (resetPane == JOptionPane.NO_OPTION || resetPane == JOptionPane.CLOSED_OPTION) {
					//Stops the program.
					System.exit(0);
				}
			}
		}
		
		private void updateGUI() {
			//Updates the label for player and computer turn with values from the board.
			playerTurnLabel.setText(String.valueOf(board.getTurnCount(playerDisc, newBoard)));
			computerTurnLabel.setText(String.valueOf(computer.getTurnCount(computerDisc, newBoard)));
			//Prints a console-based representation of the current board.
			board.printBoard(newBoard);
			//Updating buttons to match values on the integer board.
			updateButtons(newBoard, discButton);
		}
	
		public void actionPerformed(ActionEvent e) {
			// If user presses any of the disc button.
			for (int i = 0; i < boardRow; i++) {
				for (int j = 0; j < boardColumn; j++) {
					if (e.getSource() == discButton[i][j]) {
						if (discButton[0][j].getBackground() != emptyColor) {							
							JOptionPane.showMessageDialog(mainBoard, "Column selected is full.",
								"Column Full", JOptionPane.WARNING_MESSAGE);
						} 
						else {
							board.setDisc(j, newBoard, playerDisc);;
							updateGUI();
							gameOver();
							 //computer moves while board is still not filled.
							if (board.isFull(newBoard) == false && board.getWinner() == false) {
								computer.computerMove(newBoard, playerDisc, computerDisc);
								updateGUI();
								gameOver();
						}
					}
				}
			}
		}
			// If user chooses to save the current state of the board.
			if (e.getSource() == saveFile) {
				int savePane = JOptionPane.showConfirmDialog(mainBoard, "Would you like to save?", 
															"Save",JOptionPane.YES_NO_OPTION);
				if (savePane == JOptionPane.YES_OPTION) {
					try {
						save.saveBoard(newBoard);
						save.saveSetting(frameSize, playerColorIndex, computerColorIndex, playerDisc);
						updateGUI();
						JOptionPane.showMessageDialog(mainBoard, "File has been successfully saved.", 
													  "Save Success!", JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(mainBoard, "An error has occured during saving, please try again.", 
													  "Save Failed!", JOptionPane.ERROR_MESSAGE);
					}

				}
			}
			// If user chooses to load a previous board state.
			if (e.getSource() == loadFile) {
				int loadPane = JOptionPane.showConfirmDialog(mainBoard, "Would you like to load your save file?", 
															 "Load",JOptionPane.YES_NO_OPTION);
				if (loadPane == JOptionPane.YES_OPTION) {
					try {
						//Updating the current GUI values with values from the saved file.
						int[] setting = save.loadSetting();
						save.loadBoard(newBoard);
						frameSize = setting[0];
						playerColor = colorLists[setting[1]];
						computerColor = colorLists[setting[2]];
						playerDisc = setting[3];
						mainBoard.setSize(frameSize, frameSize);
						playerLabel.setForeground(playerColor);
						playerTurnLabel.setForeground(playerColor);
						computerLabel.setForeground(computerColor);
						computerTurnLabel.setForeground(computerColor);
						if (playerDisc == 1) {
							computerDisc = 2;
						} else if (playerDisc == 2) {
							computerDisc = 1;
						}
						updateGUI();
						JOptionPane.showMessageDialog(mainBoard, "File has been successfully loaded.",
													  "Load Success!", JOptionPane.INFORMATION_MESSAGE);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(mainBoard, "File was not found.",
													  "Load Failed!", JOptionPane.ERROR_MESSAGE);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(mainBoard, "An error has occured during loading, please try again.",
													  "Load Failed!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			//If user selects delete.
			if (e.getSource() == deleteFile) {
				int deletePane = JOptionPane.showConfirmDialog(mainBoard, "Would you like to delete your save file?", 
															   "Delete",JOptionPane.YES_NO_OPTION);
				if (deletePane == JOptionPane.YES_OPTION) {
					if(save.checkFile() == false) {
						JOptionPane.showMessageDialog(mainBoard, "Save file does not exist.",
														"Delete Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						save.deleteSave();
						updateGUI();
						JOptionPane.showMessageDialog(mainBoard, "File has been successfully deleted.",
													  "Delete Success!", JOptionPane.INFORMATION_MESSAGE);
					}
				} 
			}
			//If user presses the start button. This button is disabled on startup.
			if (e.getSource() == startButton) {
				startButton.setEnabled(false);
				pauseButton.setEnabled(true);
				//Re-enabling disabled disc buttons.
				for (int i = 0; i < boardRow; i++) {
					for (int j = 0; j < boardColumn; j++) {
						discButton[i][j].setEnabled(true);
					}
				}
				JOptionPane.showMessageDialog(mainBoard, "Game has been resumed.",
											  "Game Unpaused!", JOptionPane.INFORMATION_MESSAGE);
			}
			//If user presses the pause button. Pause button is disabled, user needs to press Start to resume operations.
			//User is still able to access the saving and loading features.
			if (e.getSource() == pauseButton) {
				startButton.setEnabled(true);
				pauseButton.setEnabled(false);
				//disabling the disc buttons.
				for (int i = 0; i < boardRow; i++) {
					for (int j = 0; j < boardColumn; j++) {
						discButton[i][j].setEnabled(false);
					}
				}
				JOptionPane.showMessageDialog(mainBoard, "Game is currently paused.",
											  "Pause", JOptionPane.INFORMATION_MESSAGE);
			}
			// If user presses reset button on the board.
			if (e.getSource() == resetButton) {
				int resetPane = JOptionPane.showConfirmDialog(mainBoard, "Do you want to reset the board?", "Reset",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (resetPane == JOptionPane.YES_OPTION) {
					board.resetBoard(newBoard);
					computer.resetBoard(newBoard);
					mainBoard.dispose();
					createAndShowGUI();
					JOptionPane.showMessageDialog(mainBoard, "Board has been reset.", "Reset", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
}