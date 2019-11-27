import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameSettings extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	//Arrays for combo box options.
	private String[] boardSize = {"Small", "Medium", "Large"};
	private String[] colorList = {"Red", "Cyan", "Green", "Yellow", "Purple", "Orange", "Black"};
	private String[] playerTurn = {"Start first", "Start second"};
	private int[] boardSizes = {400, 600, 800};
    //Combo boxes for the user selections.
    private JComboBox<String> boardSizeSelector = new JComboBox<String>(boardSize);
    private JComboBox<String> playerColors = new JComboBox<String>(colorList);
    private JComboBox<String> computerColors = new JComboBox<String>(colorList);
    private JComboBox<String> turnSelection = new JComboBox<String>(playerTurn);
    //Variables to be passed into the board constructor when it is instantiated.
    private int frameSize;
    private int playerColorIndex;
    private int computerColorIndex;
    private int selectedTurn;
    private JFrame setting;
    private JButton confirm;
	
	private JPanel createSettingPanel() {
		//Creating the panel for user options.
		JPanel settingPanel = new JPanel();
	   	settingPanel.setLayout(new GridLayout(5,2));
    	settingPanel.add(new JLabel("Board size:"));
    	settingPanel.add(boardSizeSelector);
      	settingPanel.add(new JLabel("Player Disc Color:"));
    	settingPanel.add(playerColors);
    	settingPanel.add(new JLabel("AI Disc Color:"));
    	settingPanel.add(computerColors);
    	settingPanel.add(new JLabel("Turn Selection:"));
    	settingPanel.add(turnSelection);
    	boardSizeSelector.addActionListener(this);
    	computerColors.addActionListener(this);
    	turnSelection.addActionListener(this);
    	playerColors.addActionListener(this);
    	//Setting default selection for each option.
    	boardSizeSelector.setSelectedIndex(2);
    	playerColors.setSelectedIndex(0);
    	computerColors.setSelectedIndex(3);
    	turnSelection.setSelectedIndex(0);

    	return settingPanel;
	}
    public void createSettingWindow () {
    	//creating the window for settings.
    	setting = new JFrame("Game Settings");
    	setting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setting.setSize(350, 350);
    	JPanel options = new JPanel();
    	options.setLayout(new BorderLayout());
    	options.add(createSettingPanel(), BorderLayout.CENTER);
    	confirm = new JButton("OK");
    	confirm.addActionListener(this);
    	options.add(confirm, BorderLayout.SOUTH);
    	setting.add(options);
    	setting.setLocationRelativeTo(null);
    	setting.setResizable(false);
    	setting.setVisible(true);
    	setting.setAlwaysOnTop(true);
    }
    
    public void actionPerformed(ActionEvent e) {
    	//When confirm button is pressed.
    	if (e.getSource() == confirm) {
    		if (playerColorIndex == computerColorIndex) {
    			System.out.println("Player color cannot be the same as the computer color.");
    			JOptionPane.showMessageDialog(setting, "Player color cannot be the same as the computer color.");
    			//User will have to select a different colour before continuing.
    		}
    		else {
    			//Construct the main interface with parameters chosen.
    			UserInterface connectBoard = new UserInterface(frameSize,  playerColorIndex,  computerColorIndex , selectedTurn);
    			connectBoard.createAndShowGUI();
    			//Discards the setting window.
    			setting.dispose();
    		}
    	}
    	//Assigns chosen variable value to its respective variable, and prints out a console message for each option chosen.
    	if (e.getSource() == boardSizeSelector) {
    			frameSize = boardSizes[boardSizeSelector.getSelectedIndex()];
    			System.out.println("Board size selected: " + boardSize[boardSizeSelector.getSelectedIndex()]);
    		}
    	if (e.getSource() == playerColors) {
    		playerColorIndex = playerColors.getSelectedIndex();
    		System.out.println("Player color selected: " + colorList[playerColorIndex]);
    	}
    	if (e.getSource() == computerColors) {
    		computerColorIndex = computerColors.getSelectedIndex();
    		System.out.println("Computer color selected: " + colorList[computerColorIndex]);
    	}
    	if(e.getSource() == turnSelection) {
    		selectedTurn = turnSelection.getSelectedIndex() + 1;
    		System.out.println("Player will " + playerTurn[turnSelection.getSelectedIndex()]);
    	}
    }
}