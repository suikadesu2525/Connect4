
public class Main {

	public static void main(String[] args) {
		//Calls the GameSettings class.
		GameSettings setting = new GameSettings();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	setting.createSettingWindow();
            }
        });
    }
}
