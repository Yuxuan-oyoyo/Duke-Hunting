package menu;

import java.util.*;

import controller.RegisterController;

/**
 * This is the registration menu that will prompt user to register for an account
 * in Duke Hunt.
 * @author G4-T03
 */
public class RegisterMenu{
	private RegisterController ctrl;
  
	/**
	 * The process method will print welcome message and process the activities
	 * that user chooses.
	 */
	public void process(){
		ctrl = new RegisterController();
		System.out.println();
		System.out.println("\n== Duke Hunt :: Registration ==");
		System.out.print("Enter your username > ");
		Scanner sc = new Scanner(System.in);
		String username = sc.nextLine();
    
		//Verify if username is 'free' to use
		while(ctrl.verifyHunter(username)){
			if (ctrl.verifyHunter(username)){
				System.out.print("Username is in use. Please choose another > ");
				username = sc.nextLine();
			} 
		}
      
		String password = "";
		boolean result = true;
		do{  
			System.out.print("Enter your password > ");
			password = sc.nextLine();
     
			System.out.print("Confirm your password > ");
			String confirmPassword = sc.nextLine();
			if(confirmPassword.equals(password)){
				break;
			} else{
				System.out.println("Passwords do not match! Please try again. \n");
				result = false;
			}
		} while(!result);
    
		//Method that processes the RegisterController to add Hunter to the Hunter.CSV file
		ctrl.addHunter(username,password);
    	System.out.println("Hi, " + username + "! Your account is successfully created!");
	}
}