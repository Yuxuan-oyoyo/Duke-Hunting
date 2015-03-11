package menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.InventoryController;
import entity.*;

/**
 * This is the Inventory menu that allow hunter to check the inventory and change traps
 * @author G4-T03
 *
 */
public class InventoryMenu {
	private InventoryController IC;
	private Hunter hunter;
	
	/**
	 * Constructs a new InventoryMenu
	 * @param hunter The hunter of the game 
	 */
	public InventoryMenu(Hunter hunter){
		IC = new InventoryController(hunter);
		this.hunter = hunter;
	}
	
	/**
	 * Displays the title, traps belonging to the hunter and action options
	 */
	public void display(){
		System.out.println();
		System.out.println("== Duke Hunt :: Inventory ==");
		if(hunter.getTrap() == null){
			System.out.println("Current Trap: -Nil-" );
		}else{
			System.out.println("Current Trap: " + hunter.getTrap().getName());
		}
		
		System.out.println();
		
		IC.showTraps();
		
		System.out.print("[R]eturn to main | Enter your choice > ");	
	}
	
	/**
	 * Reads the option of the hunter and lead to the corresponding operation
	 */
	public void readOption(){
		
		Scanner sc = new Scanner(System.in);
		String option = null;
		boolean result = true;
		//reading the user input
		do{
			try{
				display();
				option = sc.nextLine();
				int x = Integer.parseInt(option);
				boolean success = IC.equip(x);
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
