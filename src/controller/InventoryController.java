package controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import dataManager.*;
import entity.*;

/**
 * The IventoryController represents the controller class for the function "Inventory".
 * @author G4-T03
 *
 */
public class InventoryController {
	private TrapDataManager TDM;
	private HunterDataManager HDM;
	private Hunter hunter;
	private ArrayList<Trap> trapList;
	private ArrayList<Hunter> hunterList;
	private HashMap<Hunter, ArrayList<Trap>> trapData;
	
	/**
	 * Constructs a HuntingController
	 * @param hunter The hunter the character player using in the game
	 */
	public InventoryController(Hunter hunter){
		this.hunter = hunter;
		TDM = new TrapDataManager();
		trapList = TDM.getTrapList(hunter.getUsername());
		trapData = TDM.retrieveAll();
		HDM = new HunterDataManager();
		hunterList = HDM.retrieveAll();
		
	}
	
	/**
	 * Shows the traps that the hunter obtains
	 */
	public void showTraps(){
		//loop through the trap list available for the hunter
		for(int i = 0; i < trapList.size(); i++){
			if(trapList.get(i) != null){
				System.out.println(i+1 + ".\t" + trapList.get(i).getName());
			}
		}
	}
	
	/**
	 * Check if the hunter can implement his or her choice
	 * @param x The option of the hunter
	 * @return true if the hunter can equip the trap successfully; false otherwise
	 */
	public boolean equip(int x){
		//changing of traps
		if(x > trapList.size()){
			return false;
		}
		Trap newTrap = trapList.get(x-1);
		Trap oldTrap = hunter.getTrap();
		changeTrap(oldTrap, newTrap);
		return true;
	}
	
	/**
	 * Equip the trap for hunter
	 * @param oldTrap The old trap the hunter was using
	 * @param newTrap The new trap the hunter want to equip
	 */
	public void changeTrap(Trap oldTrap, Trap newTrap){
		hunterList.remove(hunter);
			
		hunter.setTrap(newTrap);
		hunterList.add(hunter);
		HDM.save(hunterList);
		
		
		trapList.remove(newTrap);
		if(oldTrap != null){
			trapList.add(oldTrap);
		}
		
		
		Hunter hx = null;
		Iterator<Hunter> iter = trapData.keySet().iterator();
		//looping for the hunter that changes the traps
		while(iter.hasNext()){
			Hunter h = iter.next();
			if(h.getUsername().equals(hunter.getUsername())){
				hx = h;
			}
		}
		
		if(hx!=null){
			trapData.remove(hx);
		}
		
		trapData.put(hunter, trapList);
		TDM.save(trapData);
		
	}
}
