package menu;


import java.util.*;

import exception.IllegalInputException;

/**
 * The StartupMenu class represents the menu class for the default menu shown when the application is started.
 * @author G4-T03
 */
public class StartupMenu{
	RegisterMenu m1;
	MainMenu m2;
	
	public StartupMenu(){
		m1 = new RegisterMenu();
		m2 = new MainMenu();
	}
	
	/**
     * Displays the menu.
     */
	public void display(){
	
		System.out.println("\n== Duke Hunt :: Welcome ==\n");
       
	    System.out.println("Hi,  Player!");
	    System.out.println("1. Register");
	    System.out.println("2. Login");
	    System.out.println("3. Exit");
	    System.out.print("Enter your choice > ");
	}
  
  /**
   * Reads the user's options.
   */
	public void readOption(){
	    Scanner sc = new Scanner(System.in);
		int option = 0;
		boolean result = false;
		//reading user input
		do{
			try{
				display();
				option = sc.nextInt();
				sc.nextLine();
				switch(option){
					case 1:
						m1.process();
						break;
					case 2:
						try{
							m2.process();
						}catch(IllegalInputException e){
							System.out.println(e.getMessage());
						}
						break;
					case 3:
						System.out.println("Thank you for playing Duke Hunt! Goodbye!");
						return;
					default:
						System.out.println("\nInvalid option. Please enter a valid option (1 to 3).");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid option. Please enter a valid option (1 to 3).");
				sc.nextLine();
			}
		}while (!result);
	}  
}  