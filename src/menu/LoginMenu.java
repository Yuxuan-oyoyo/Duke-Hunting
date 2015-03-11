package menu;


import java.util.*;

import controller.LoginController;
import entity.Hunter;
import exception.IllegalInputException;


/**
 * The LoginMenu class represents the menu class for the function "Log In".
 * @author G4-T03
 */
public class LoginMenu{
	private LoginController ctrl;

  /**
   * Default constructor for the BuyPetMenu class.
   */
  
	public LoginMenu() throws IllegalInputException{
		ctrl = new LoginController();
	}
  
  /**
   * Processes the user's options.
   */
  public Hunter process(){
	  Scanner sc = new Scanner(System.in);
	  Hunter hunter = null;
	  String username = null;
	  String password = null;
	  //reading user input
	  do{
	      System.out.print("Enter your username > ");
	      username = sc.nextLine();
	      System.out.print("Enter your password > ");
	      password = sc.nextLine();
	      hunter = ctrl.retrieve(username);
	      if(!ctrl.verifyPassword(hunter,password)){
	    	  System.out.println("You have entered an incorrect username/password.\n");
		  }
	  }while(!ctrl.verifyPassword(hunter,password));
	
	  return hunter;
  	}
}
