package app;
import menu.StartupMenu;


/**
 * This will run the OwnedApplication.
 * @author G4-T03
 */
public class DukeHuntApplication{
	static StartupMenu menu;
	
	/**
     * The main method.
     */
	public static void main(String [] args){
		menu = new StartupMenu();
		menu.readOption();
	}	
}