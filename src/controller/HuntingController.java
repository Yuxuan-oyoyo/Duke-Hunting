package controller;
import java.math.BigDecimal;
import java.util.*;

import entity.*;
import dataManager.*;

/**
 * The HuntingController presents the controller class for the function "Hunting Around".
 * @author G4-T03
 *
 */
public class HuntingController {
	private Hunter hunter;
	private HunterDataManager HDM;
	private ArrayList<Hunter> hunterList;
	private HuntingDataManager HingDM;
	private HashMap<Hunter, String[]> huntingRecords;
	private String[] huntingRecord;
	private DukeManager DM;
	private RankManager rankM;
	private Rank[] rankList;
	
	/**
	 * Constructs a HuntingController
	 * @param The hunter the character player using in the game
	 */
	public HuntingController(Hunter hunter){
		this.hunter = hunter;
		HDM = new HunterDataManager();
		hunterList = HDM.retrieveAll();
		HingDM = new HuntingDataManager();
		huntingRecords = HingDM.retrieveAll();
		huntingRecord = HingDM.getHuntingRecord(hunter.getUsername());
		DM = new DukeManager();
		rankM = new RankManager();
		rankList = rankM.getRankList();
	}
	
	/**
	 * Shows the hunting record of the hunter
	 */
	public void showRecord(){
		if(huntingRecord == null){
			huntingRecord = new String[10];
		}
		//display the 10 records of the Hunter
		for(int i = 0; i < huntingRecord.length; i++){
			if(huntingRecord[i] != null){
				String recor = huntingRecord[i];
				if(!recor.equals("null")){
					String str1 = recor.substring(0, recor.indexOf("#"));
					String str2 = recor.substring(recor.indexOf("#")+1);
					System.out.println(str1);
					System.out.println(str2);
					System.out.println();
				}
				
			}
			
		}
	}
	
	/**
	 * Checks if there is a trap equiped for the hunter
	 * @return true if there is a trap equiped; fase no trap is equiped
	 */
	public boolean checkTrap(){
		if(hunter.getTrap() == null){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Implements the hunting function
	 */
	public void hunting(){
		Duke duke = chooseDuke();
		boolean result = killDuke(duke);
		String str = null;
		//determine whether the duke is captured or escaped and return the outcome.
		if(result == false){
			String time = new java.text.SimpleDateFormat("dd-MM-yy HH:mm:ss").format(new Date());
			str = time + " @ " + hunter.getRegion() + "#" + "A " + duke.getName() + " escaped from my " + hunter.getTrap().getName() + ".";
		}else{
			str = getSomething(duke);
		}
		addRecord(str);
	}
	
	/**
	 * Updates the newest hunting record to the hunting records
	 * @param str The newest hunting record
	 */
	public void addRecord(String str){
		Hunter hx = null;
		Iterator<Hunter> iter = huntingRecords.keySet().iterator();
		//looping hunter record
		while(iter.hasNext()){
			Hunter h = iter.next();
			if(h.getUsername().equals(hunter.getUsername())){
				hx = h;
			}
		}
		
		if(hx!=null){
			huntingRecords.remove(hx);
		}
		
		
		if(huntingRecord == null){
			huntingRecord = new String[10];
		}
		String current = huntingRecord[0];
		String later = huntingRecord[1];
		for(int i = 1; i < 10; i++){
			huntingRecord[i] = current;
			current = later;
			if(i < 9){
				later = huntingRecord[i+1];
			}
		}
		huntingRecord[0] = str;
		
		huntingRecords.put(hunter, huntingRecord);
		HingDM.save(huntingRecords);
	}
	
	/**
	 * Gives hunter certain xp and gold when hunter killed a duke successfully
	 * @param duke The duke the hunter killed
	 * @return the corresponding hunting record 
	 */
	public String getSomething(Duke duke){
		
		hunterList.remove(hunter);
		
		Random xp = new Random();
		int xpIndex = xp.nextInt(9951) + 50;
		double i = duke.getPower()*xpIndex/1000;
		int xpGain = (new BigDecimal(i).setScale(0, BigDecimal.ROUND_HALF_UP)).intValue();
		int currentXP = hunter.getXP() + xpGain;
		hunter.setXP(currentXP);
		
		Random gold = new Random();
		int goldIndex = gold.nextInt(4951) + 50;
		double p = duke.getPower()*goldIndex/1000;
		int goldGain = (new BigDecimal(p).setScale(0, BigDecimal.ROUND_HALF_UP)).intValue();
		int currentGold = hunter.getGold() + goldGain;
		hunter.setGold(currentGold);
		
		rankUp();
		
		hunterList.add(hunter);
		
		HDM.save(hunterList);
		
		String time = new java.text.SimpleDateFormat("dd-MM-yy HH:mm:ss").format(new Date());
		String str = time + " @ " + hunter.getRegion() + "#" + "I caught a " + duke.getName() + " using " + hunter.getTrap().getName() + " and gained" + goldGain + " gold and" + xpGain + "XP.";
		return str;

	}
	
	/**
	 * Check and upgrade the rank for hunter automatically
	 * 
	 */
	public void rankUp(){
		for(int i = 0; i < rankList.length; i++){
			if(hunter.getXP() >= rankList[i].getXP()){
				hunter.setRank(rankList[i]);
			}
		}
	}
	
	/**
	 * Determines if the hunter kill a duke successfully
	 * @param duke The duke chosen
	 * @return true if the hunter kills the duke successfully; false if the duke escaped for the trap
	 */
	public boolean killDuke(Duke duke){
		double i = ((double)hunter.getTrap().getPower() - duke.getPower())/duke.getPower();
		BigDecimal d = new BigDecimal(i).setScale(0, BigDecimal.ROUND_HALF_UP);
		int diff = d.intValue();
		int escapeRange = 0;
		//determine whether the hunter kill the duke
		if(diff <= 0){
			escapeRange = 94;
		}else if(diff > 0 && diff <=25){
			escapeRange = 14;
		}else if(diff > 25 && diff <= 75){
			escapeRange = 8;
		}else if(diff > 75 && diff <= 100){
			escapeRange = 6;
		}else{
			escapeRange = 2;
		}
		
		
		Random rand = new Random();
		int num = rand.nextInt(100);
		
		
		if(num <= escapeRange){
			return false;
		}else{
			return true;
		}
		
	}
	
	/**
	 * Chooses a duke in the region to kill
	 * @return the duke chosen
	 */
	public Duke chooseDuke(){
		Duke[] dukes = DM.getDukeInside(hunter.getRegion());
		//sort dukes according to the population
		for (int i = 0; i < dukes.length-1; ++i) {
			int guess = i;
		    for (int j = i+1; j < dukes.length; ++j) {
		    	if (dukes[j].getPopulation() > dukes[guess].getPopulation()) {
		    		guess = j;
		    	}
		    }
	
		    Duke rmbr = dukes[i];
		    dukes[i] = dukes[guess];
		    dukes[guess] = rmbr;
		}
		
		
		Random rd = new Random();
		int num = rd.nextInt(101);
		
		int totalPopulation = 0;
		for(int i = 0; i < dukes.length; i++){
			totalPopulation += dukes[i].getPopulation();
		}
		
		int accumulatePopulation = 0;
		for(int i = 0; i < dukes.length; i++){
			accumulatePopulation += dukes[i].getPopulation();
			if(num <= (double)accumulatePopulation/totalPopulation*100){
				return dukes[i];
			}
		}
		
		return null;
		

	}
}
