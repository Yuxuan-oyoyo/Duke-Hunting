package menu;


import java.util.*;

import entity.Hunter;
import exception.IllegalInputException;


/**
 * The class MainMenu represents the different activities Hunter can do after they login.
 * @author G4-T03
 */
public class MainMenu{
  
	private Hunter hunter;
	HuntingMenu HM;
	InventoryMenu IM;
	TrapSmithMenu TSM;
	TravelMenu TM;
	LoginMenu lm;
  
	/**
	 * MainMenu Constructor
	 * 
	 */
	public MainMenu(){
	}
  
  
  
	/**
   	* Displays the welcome message and various activities.
   	*/
	public void display(){
		System.out.println("\n== Duke Hunt :: Main Menu ==");
		System.out.println("Welcome, " + hunter.getRank().getRank() + " " + hunter.getUsername() + "!");
		System.out.println("Location: " + hunter.getRegion().getName());
		System.out.println("XP: " + hunter.getXP());
		System.out.println("Gold: " + hunter.getGold());
		System.out.println();
		System.out.println("1. Hunting Ground");
		System.out.println("2. My Inventory");
		System.out.println("3. The Trap Smith");
		System.out.println("4. Travel");
		System.out.println("5. Logout");
		System.out.print("Enter your choice > ");
	}
  
	/**
	 *  Reads the option of hunter and direct the user to the specific menu.
   	*/
	public void readOption(){
		Scanner sc = new Scanner(System.in);
		int option = 0;
		//reading user input
		do{
			try{
				display();
				option = sc.nextInt();
				switch(option){
				case 1:
					HM = new HuntingMenu(hunter);
					HM.readOption();
					break;
				case 2:
					IM = new InventoryMenu(hunter);
					IM.readOption();
					break;
				case 3:
					TSM = new TrapSmithMenu(hunter);
					TSM.readOption();
					break;
				case 4:
					TM = new TravelMenu(hunter);
					TM.readOption();
					break;
				case 5:
					System.out.println("Bye bye!");
					break;
				default:
					System.out.println("\nInvalid option. Please enter a valid option (1 to 5).");
				}
			} catch(InputMismatchException e) {
				System.out.println("\nInvalid option. Please enter a valid option (1 to 5).");
				sc.nextLine();
			}
		} while (option!=5);
	}
  
	/**
	 * The process method will process the read options in the main menu.
	 */
	public void process() throws IllegalInputException{
		lm = new LoginMenu();
		hunter = lm.process();
		readOption();
	}
}