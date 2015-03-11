package menu;

import java.util.Scanner;

import controller.*;
import entity.*;

/**
 * This is the Hunting menu that will prompt user to sound horn
 * @author G4-T03
 *
 */
public class HuntingMenu {
	private HuntingController HC;
	private Hunter hunter;
	
	/**
	 * Constructs a new Hunting Menu
	 * @param hunter The hunter of the game 
	 */
	public HuntingMenu(Hunter hunter){
		this.hunter = hunter;
		HC = new HuntingController(this.hunter);
		
	}
	
	/**
	 * Displays the title, hunting record of the hunter and two options
	 */
	public void display(){
		System.out.println();
		System.out.println("== Duke Hunt :: Hunting Ground ==");
		
		HC.showRecord();
		
		System.out.print("[R]eturn to main | [S]ound horn > ");
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
			display();
			option = sc.nextLine();
			if(option.equals("R")|| option.equals("r")){
				result = !result;
			}else if(option.equals("S")|| option.equals("s")){
				if(HC.checkTrap()){
					HC.hunting();
				}else{
					System.out.println("Unable to hunt. Do you have an equipped trap?");
				}
				
			}else{
				System.out.println("\nInvalid option.\n\n");
			}
		}while(result);
	}
}
