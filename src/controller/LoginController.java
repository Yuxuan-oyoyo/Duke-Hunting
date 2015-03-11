package controller;


import java.util.ArrayList;

import dataManager.*;
import entity.*;
import exception.IllegalInputException;


/**
 * The LoginController represents the controller class for the function "Login".
 * @author G4-T03
 *
 */
public class LoginController {
	
	private HunterDataManager hunterDM;
	private ArrayList<Hunter> hunterList;
	
	
	/**
	 * Constructs a LoginController
	 * @throws IllegalInputException if the hunter does not exist in the system
	 */
	public LoginController() throws IllegalInputException{
		hunterDM = new HunterDataManager();
		hunterList = hunterDM.retrieveAll();
		if(hunterList == null){
			throw new IllegalInputException("Note: No hunters currently in the system.");
		}
	}
	
	/**
	 * Retrieves a hunter according to his or her username
	 * @param username the username of the corresponding hunter
	 * @return The corresponding hunter
	 */
	public Hunter retrieve(String username){
		//loop the record and look for the hunter by using the username 
		for(int i = 0; i < hunterList.size(); i++){
			Hunter h = hunterList.get(i);
			if(h.getUsername().equals(username)){
				return h;
			}
		}
		return null;
	}
	
	/**
	 * Verifies if the password is correct
	 * @param hunter The hunter that will be retrieved
	 * @param password The password the user entered
	 * @return true if the password entered is same as the corresponding password of the username; false otherwise
	 */
	public boolean verifyPassword(Hunter hunter, String password){
		//verifying the password
		if(hunter == null){
			return false;
		}
		if(hunter.getPassword().equals(password)){
			return true;
		}else{
			return false;
		}
	}
}
