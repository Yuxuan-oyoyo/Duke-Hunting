package controller;


import java.util.ArrayList;
import java.util.HashMap;

import dataManager.*;
import entity.Hunter;
import entity.Trap;


/**
 * The RegisterController represents the controller class for the function "Register".
 * @author G4-T03
 */
public class RegisterController {
	private HunterDataManager hunterDM;
	private ArrayList<Hunter> hunterList;
	private TrapDataManager TDM;
	private HashMap<Hunter, ArrayList<Trap>> trapData;
	
	
	/**
	   * Default Constructor for RegisterController.
	   */
	public RegisterController(){
		hunterDM = new HunterDataManager();
		hunterList = hunterDM.retrieveAll();
		TDM = new TrapDataManager();
		trapData = TDM.retrieveAll();
	}
	
	/**
	 * Verifies if the username has been used before by other player
	 * @param username The the username new player want to use
	 * @return true if the username has been used; false if the username is still available
	 */
	public boolean verifyHunter(String username){
		// checking for the username
		if(hunterList == null){
			return false;
		}
		for(int i = 0; i < hunterList.size(); i++){
			Hunter t = hunterList.get(i);
			if(t.getUsername().equals(username)){
				return true;
			}
		}
		return false;
	}

	/**
	*  Add a hunter to the list.
	* This allows program to add new users into the database after they register.
	* @param username The username of new player
	* @param password The password of the new hunter account
	*/
	public void addHunter(String username, String password){
		Hunter newHunter = new Hunter(username, password);
		if(hunterList == null){
			hunterList = new ArrayList<Hunter>();
		}
		hunterList.add(newHunter);
		hunterDM.save(hunterList);
		trapData.put(newHunter, new ArrayList<Trap>());
		TDM.save(trapData);
		
	}
	
	
}
