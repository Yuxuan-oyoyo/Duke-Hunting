package menu;

import java.util.*;

import entity.*;
import exception.IllegalInputException;
import controller.*;

/**
 * This is the Inventory menu that allow hunter to check the inventory and change traps
 * @author G4-T03
 *
 */
public class TravelMenu {
	private TravelController TC;
	private Hunter hunter;
	private ArrayList<Travel> travelsAvailable;
	
	/**
	 * Construct a new TravelMenu
	 * @param hunter The hunter of the game 
	 */
	public TravelMenu(Hunter hunter){
		this.hunter = hunter;
		TC = new TravelController(hunter);
		travelsAvailable = new ArrayList<Travel>();
	}
	
	/**
	 * Displays the title, travels from the regon where the hunter located and action options
	 */
	public void display(){
		System.out.println();
		System.out.println("== Duke Hunt :: Travel ==");
		System.out.println("You are at " + hunter.getRegion().getName() + ".");
		System.out.println();
		
		travelsAvailable = TC.showTravels();
		
		System.out.print("[R]eturn to main | Enter your choice > ");
	}
	
	/**
	 * Reads the option of the hunter and lead to the corresponding operation
	 */
	public void readOption(){
		Scanner sc = new Scanner(System.in);
		String option = null;
		boolean result = true;
		//reading user input
		do{
			try{
				display();
				option = sc.nextLine();
				int x = Integer.parseInt(option);
				boolean success = TC.travel(x, travelsAvailable);
				if(success == false){
					System.out.println("\nInvalid option\n\n");
				}
			}catch (InputMismatchException e) {
				if(option.equals("R")|| option.equals("r")){
					result = !result;
				}else{
					System.out.println("\nInvalid option.\n\n");
				}
			}catch (NumberFormatException e){
				if(option.equals("R")|| option.equals("r")){
					result = !result;
				}else{
					System.out.println("\nInvalid option.\n\n");
				}
			}
			System.out.println();
		} while (result);
	}
}
