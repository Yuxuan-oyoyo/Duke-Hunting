package menu;
import java.util.InputMismatchException;
import java.util.Scanner;

import entity.*;
import controller.*;

/**
 * This is the TrapSmith menu that allow hunter to buy and sell traps
 * @author G4-T03
 *
 */
public class TrapSmithMenu {
	private Hunter hunter;
	private TrapSmithController TSC;
	private String message;
	
	/**
	 * Constructs a new TrapSmithMenu
	 * @param hunter The hunter of the game
	 */
	public TrapSmithMenu(Hunter hunter){
		this.hunter = hunter;
		TSC = new TrapSmithController(hunter);
		message = "";
	}
	
	/**
	 * Displays the title, traps sold in the region and action options
	 */
	public void display(){
		System.out.println();
		System.out.println("== Duke Hunt :: TrapSmith ==");
		System.out.println("Your XP: " + hunter.getXP());
		System.out.println("Available Gold: " + hunter.getGold());
		System.out.println();
		
		message = TSC.showOptions();
		
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
				boolean success = TSC.makeChoice(x, message);
				if(success == false){
					System.out.println("\nInvalid option\n\n");
				}
			}catch (InputMismatchException e){
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
