package controller;
import entity.*;
import dataManager.*;
import java.util.*;

/**
 * The TrapSmithController represents the controller class for the function "TrapSmith".
 * @author G4-T03
 *
 */
public class TravelController {
	private HunterDataManager HDM;
	private Hunter hunter;
	private ArrayList<Hunter> hunterList;
	private RankManager rankM;
	private Rank[] rankList;
	private TravelManager TM;
	private Travel[] travelList;

	/**
	 * Constructs a TravelController
	 * @param hunter The hunter the character player using in the game
	 */
	public TravelController(Hunter hunter){
		this.hunter = hunter;
		HDM = new HunterDataManager();
		hunterList = HDM.retrieveAll();
		rankM = new RankManager();
		TM = new TravelManager();
		travelList = TM.getTravelList();
		rankList = rankM.getRankList();
	}
	
	/**
	 * Shows all the travels available from hunter's current region
	 * @return An arraylist of travel from here
	 */
	public ArrayList<Travel> showTravels(){
		System.out.println("Travel to:");
		
		ArrayList<Travel> travelsFromHere = new ArrayList<Travel>();
		//going through the list that availble for the Hunter
		for(int i = 0; i < travelList.length; i++){
			Travel t = travelList[i];
			if(t.getTravelFrom().equals(hunter.getRegion())){
				travelsFromHere.add(t);
			}
		}
		
		
		ArrayList<Region> regionsAvailable = new ArrayList<Region>();
		//checking for the Hunter XP. If hunter meet the requirement for the region. The region will be unlock and available
		for(int i = 0; i < rankList.length; i++){
			Rank rank = rankList[i];
			
			if(hunter.getXP() > rank.getXP()){
				
				regionsAvailable.add(rank.getRegion());
			}
		}
		
		ArrayList<Travel> travelsAvailable = new ArrayList<Travel>();
		//checking for region availble
		for(int i = 0; i < travelsFromHere.size(); i++){
			boolean check = false;
			Travel travel = travelsFromHere.get(i);
			for(int p = 0; p < regionsAvailable.size(); p++){
				Region region = regionsAvailable.get(p);
				if(travel.getTravelTo().equals(region)){
					check = true;
				}
			}
			if(check){
				travelsAvailable.add(travel);
			}
		}
		
		for(int i = 0; i < travelsAvailable.size(); i++){
			Travel travel = travelsAvailable.get(i);
			System.out.println(i+1+". "+travel.getTravelTo()+" (-"+travel.getCost()+" gold)");
		}
		System.out.println();
		
		return travelsAvailable;
	}
	
	/**
	 * Implements the travel function
	 * @param x A integer that stores the hunter's option
	 * @param travelsAvailable The ArrayList of travel that available from this region
	 * @return True if travel successfully; false otherwise
	 */
	public boolean travel(int x, ArrayList<Travel> travelsAvailable){
		//travel function and check for the gold and XP
		if(x > 0 && x <= travelsAvailable.size()){
			if(hunter.getGold() >= travelsAvailable.get(x-1).getCost()){
				hunterList.remove(hunter);
				hunter.setRegion(travelsAvailable.get(x-1).getTravelTo());
				int initialGold = hunter.getGold();
				int currentGold = initialGold - travelsAvailable.get(x-1).getCost();
				hunter.setGold(currentGold);
				hunterList.add(hunter);
				HDM.save(hunterList);
			}else{
				System.out.println("You do not have "+travelsAvailable.get(x-1).getCost()+" gold to travel to "+travelsAvailable.get(x-1).getTravelTo().getName());
			}
			return true;
		}else{
			return false;
		}
		
	}
}
