package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import dataManager.*;
import entity.*;

/**
 * The TrapSmithController represents the controller class for the function "TrapSmith".
 * @author G4-T03
 *
 */
public class TrapSmithController {
	private Hunter hunter;
	private HunterDataManager HDM;
	private ArrayList<Hunter> hunterList;
	private TrapDataManager TDM;
	private HashMap<Hunter, ArrayList<Trap>> trapData;
	private ArrayList<Trap> trapYouHave;
	private TrapManager TM;
	private ArrayList<Trap> trapSmith;
	
	/**
	 * Constructs a TrapSmithController
	 * @param hunter The hunter the character player using in the game
	 */
	public TrapSmithController(Hunter hunter){
		this.hunter = hunter;
		HDM = new HunterDataManager();
		hunterList = HDM.retrieveAll();
		TDM = new TrapDataManager();
		trapData = TDM.retrieveAll();
		trapYouHave = TDM.getTrapList(hunter.getUsername());
		TM = new TrapManager();
		trapSmith = TM.getTrapAccordingToRegion(hunter.getRegion());
	}
	
	/**
	 * Shows options of trapSmith
	 * @return String that store the option information
	 */
	public String showOptions(){
		String str = "";
		int count = 1;
		//checking for the traps available at the region
		if(hunter.getTrap() != null && hunter.getTrap().getRegion().equals(hunter.getRegion())){
			
			System.out.println(count + " Sell " + hunter.getTrap().getName() + " (+" + countSellPrice(hunter.getTrap()) + " gold | " + hunter.getTrap().getXP() + " XP)");
			count++;
			str += (count -1);
		}else{
			str += (count -1);
		}
		
		if(trapYouHave != null){
			//check for the traps belong to the Hunter and display for selling price
			for(int i = 0; i < trapYouHave.size(); i++){
				if(trapYouHave.get(i).getRegion().equals(hunter.getRegion())){
					System.out.println(count + " Sell " + trapYouHave.get(i).getName() + " (+" + countSellPrice(trapYouHave.get(i)) + " gold | " + trapYouHave.get(i).getXP() + " XP)");
					count++;
				}
				
			}
			str += (count-1);
		}
		//check for the traps that does not belong to the Hunter and display the buying price
		for(int i = 0; i <trapSmith.size(); i++){
			Trap t = trapSmith.get(i);
			if((!t.equals(hunter.getTrap())) && (!checkIfHave(t))){
				System.out.println(count + " Buy " + t.getName() + " (-" + t.getGold() + " gold | " + t.getXP() + " XP)");
				count++;
			}
		}
		str += (count-1);
		return str;
	}
	
	/**
	 * Checks if the hunter has bought or equiped the trap
	 * @param trap the Trap will show off
	 * @return true if the hunter has bought or equiped the trap; false otherwise
	 */
	public boolean checkIfHave(Trap trap){
		for(int i = 0; i < trapYouHave.size(); i++){
			Trap test = trapYouHave.get(i);
			if(test.getName().equals(trap.getName())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Calculate the selling price of the trap
	 * @param trap The trap that will sell
	 * @return the price of the trap in integer
	 */
	public int countSellPrice(Trap trap){
		double i = ((double)trap.getGold() *0.25);
		BigDecimal d = new BigDecimal(i).setScale(0, BigDecimal.ROUND_HALF_UP);
		int price = d.intValue();
		return price;
	}
	
	/**
	 * Buys of sells trap according to the hunter's option
	 * @param x The integer that stores the option information
	 * @param message The message stores the traps available status
	 * @return true if the trap sells or buys successfully; false otherwise
	 */
	public boolean makeChoice(int x, String message){
		
		int message1 = (message.charAt(0) - 48);
		int message2 = (message.charAt(1) - 48);
		int message3 = (message.charAt(2) - 48);
		
		if(message1 == 1 && x == 1){
			if(trapYouHave.size() == 0){
				System.out.println("Sale of "+ hunter.getTrap().getName() +" is rejected. Is this your last trap?");
				return true;
			}
			hunterList.remove(hunter);
			int initialGold = hunter.getGold();
			int currentGold = initialGold + countSellPrice(hunter.getTrap());
			hunter.setGold(currentGold);
			hunter.setTrap(null);
			hunterList.add(hunter);
			HDM.save(hunterList);
			
			return true;
		}
		
		if( x > message1 && x <= message2){
			if(trapYouHave.size() == 1 && hunter.getTrap() == null){
				System.out.println("Sale of "+ hunter.getTrap() +" is rejected. Is this your last trap?");
				return true;
			}
			hunterList.remove(hunter);
			ArrayList<Trap> trapBoughtHere = new ArrayList<Trap>();
			for(int i = 0; i < trapYouHave.size(); i++){
				if(trapYouHave.get(i).getRegion().equals(hunter.getRegion())){
					trapBoughtHere.add(trapYouHave.get(i));
				}
			}
			Trap trap = trapBoughtHere.get((x-message1)-1);
			trapYouHave.remove(trap);
			int initialGold = hunter.getGold();
			int currentGold = initialGold + countSellPrice(trap);
			hunter.setGold(currentGold);
			hunterList.add(hunter);
			HDM.save(hunterList);
			
			
			Hunter hx = null;
			Iterator<Hunter> iter = trapData.keySet().iterator();
			while(iter.hasNext()){
				Hunter h = iter.next();
				if(h.getUsername().equals(hunter.getUsername())){
					hx = h;
				}
			}
			
			if(hx!=null){
				trapData.remove(hx);
			}
			
			trapData.put(hunter, trapYouHave);
			TDM.save(trapData);
			return true;
		}
		
		if( x > message2 && x <= message3){
			ArrayList<Trap> trapsYouCanBuy = new ArrayList<Trap>();
			for(int i = 0; i < trapSmith.size(); i++){
				Trap trap = trapSmith.get(i);
				boolean test = false;
				
				if(hunter.getTrap() != null){
					if(hunter.getTrap().equals(trapSmith.get(i))){
						test = true;
					}
				}
				
				for(int p = 0; p < trapYouHave.size(); p++){
					if(trapYouHave.get(p).equals(trap)){
						test = true;
					}
				}
				
				if(!test){
					trapsYouCanBuy.add(trap);
				}
			}
			
			
			Trap trap = trapsYouCanBuy.get((x-message2)-1);
			if(hunter.getGold() < trap.getGold() || hunter.getXP() < trap.getXP()){
				System.out.println("You do not have " + trap.getGold() + " gold and/or minimum " + trap.getXP() + "XP to buy " + trap.getName() + ".");
				return true;
			}else{
				hunterList.remove(hunter);
				trapYouHave.add(trap);
				int initialGold = hunter.getGold();
				int currentGold = initialGold - trap.getGold();
				hunter.setGold(currentGold);
				hunterList.add(hunter);
				HDM.save(hunterList);
				
				Hunter hx = null;
				Iterator<Hunter> iter = trapData.keySet().iterator();
				while(iter.hasNext()){
					Hunter h = iter.next();
					if(h.getUsername().equals(hunter.getUsername())){
						hx = h;
					}
				}
				if(hx != null){
					trapData.remove(hx);
				}
			
				
				
				trapData.put(hunter, trapYouHave);
				TDM.save(trapData);
				return true;
			}
			
		}
		/*what is this for?*/System.out.println("aoch!");
		return false;
	}
}
