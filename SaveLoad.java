import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveLoad {
	//A class for save and load-related functions. Exceptions are thrown to be handled by the UserInterface class.
	//Save files are written to the root folder of the project.
	private File boardSaveFile = new File ("saveFile.txt");
	private File settingfile = new File ("setting.txt");
	private FileWriter writehandle;
	private BufferedWriter bw;
	private FileReader reader;
	private BufferedReader bReader;
	
	//Saves the internal game board to a file, separated by space. 
	public void saveBoard(int[][] gameBoard) throws IOException {
	
			writehandle = new FileWriter(this.boardSaveFile);
			bw = new BufferedWriter(writehandle);
			
			for (int i = 0; i < gameBoard.length; i++) {
				for (int j = 0; j < gameBoard[0].length; j++) {
					bw.write(gameBoard[i][j] + " ");
				}
			}	
			System.out.println("file has been written to " + boardSaveFile);
			bw.close();
			writehandle.close();
		
	}
	//Saves the current game setting to a text file, with parameters separated by space.
	public void saveSetting(int frameSize, int playerColorIndex,
							int computerColorIndex, int startFirst) 
							throws IOException {
			writehandle = new FileWriter(this.settingfile);
			bw = new BufferedWriter(writehandle);
		    bw.write(String.valueOf(frameSize) + " ");
		    bw.write(String.valueOf(playerColorIndex) + " ");
		    bw.write(String.valueOf(computerColorIndex) + " ");
		    bw.write(String.valueOf(startFirst));
		    bw.close();
		    writehandle.close();
		 System.out.println("setting file has been saved to" + this.settingfile); 
	}
	//Reads a saved game grid, and assign them to the current grid.
	public void loadBoard(int[][] gameBoard) throws IOException, FileNotFoundException  {
	
		reader = new FileReader(this.boardSaveFile);
		bReader = new BufferedReader(reader);
		String line; //storing read save data in a String.
			while((line = bReader.readLine()) != null) {
				String words [] = line.split(" ");
				for (int a = 0; a < words.length;) { 
					for (int i = 0; i < gameBoard.length; i++) { 
						for (int j = 0; j < gameBoard[0].length; j++) {
							 gameBoard[i][j] = Integer.parseInt(words[a]); 
							 a++;
						}
					} 
				}
			}
		reader.close();
		bReader.close();
	}
	//Loads previously selected user preferences.
	public int[] loadSetting() throws IOException, FileNotFoundException{
		String line;
		String words[] = new String[4];
		int [] settingval = new int [4];
		reader = new FileReader(this.settingfile);
		bReader = new BufferedReader(reader);

		while((line = bReader.readLine()) != null) {
				 words = line.split(" ");	
			}
		reader.close();
		bReader.close();
		
		for (int i = 0; i < settingval.length; i++) {
			settingval[i] = Integer.parseInt(words[i]);
		}
		return settingval;
	}
	//Deletes both board data and its setting.
	public void deleteSave(){
			this.boardSaveFile.delete();
			this.settingfile.delete();
			System.out.println("File has been deleted.");
	}
	//Checks if both save file exists in user's storage.
	public boolean checkFile() {
		if (this.boardSaveFile.exists() == true &&
			this.settingfile.exists() == true) {
			return true;
		}	else {
			return false;
		}
	}
}
