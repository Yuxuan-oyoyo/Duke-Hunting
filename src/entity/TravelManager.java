package entity;

import java.util.*;
import java.io.*;

/**
 * This is used to extract all the data from the travel.csv file
 * @author G4-T03
 *
 */
public class TravelManager{
  
	private Travel[] travelList; 
	private final String FILENAME = "data\\travel.csv";
	private RegionManager RM = new RegionManager();
	
	/**
	 * Constructs a new TravelManager
	 */
	public TravelManager(){
	
		travelList = new Travel[20];
		this.load();
		
	}
  
	/**
	 * Loads all data from travel.csv into the system
	 */
	public void load(){
		ArrayList<String> entries = readEntriesFromFile(FILENAME);
			
		for(int i = 1; i < entries.size(); i++){
			String entry = entries.get(i);
			String[] entryParts = entry.split(",");
			String travelFrom = entryParts[0];
			String travelTo = entryParts[1];
			String cost = entryParts[2];
			int costInt = Integer.parseInt(cost);
			Region travelFromR = RM.getRegion(travelFrom);
			Region travelToR = RM.getRegion(travelTo);
			Travel travel = new Travel(travelFromR, travelToR, costInt);
			travelList[i-1] = travel;
		}
	}
	
	/**
	 * Get travel according to the departure an arrival region
	 * @param region1 The departure region of the travel
	 * @param region2 The arrival region of the travel
	 * @return The specific travel according to the departure and arrival region
	 */
	public Travel getRank(String region1, String region2){
		for(int i = 0; i < travelList.length; i++){
			Travel t = travelList[i];
			if(t.getTravelFrom().equals(region1) && t.getTravelTo().equals(region2)){
				return t;
			}
		}
		return null;
	}
	
	
	/**
	 * Get all the travels
	 * @return An Array of all travels available in the system
	 */
	public Travel[] getTravelList(){
		return travelList;
	}
	
	/**
	 * Reads data from file
	 * @param nameOfFile The name of file that stores travel information
	 * @return An ArrayList of String that store the travel information temporarily
	 */
	private ArrayList<String> readEntriesFromFile(String nameOfFile){
		ArrayList<String> foundEntries = new ArrayList<String>();
		
		try{
			File file = new File(nameOfFile); 
			Scanner fileIn = new Scanner(file);
			while (fileIn.hasNext()){
				String currentLine = fileIn.nextLine();
				foundEntries.add(currentLine);
			} 
			fileIn.close();
		}catch (IOException e){
		}
		
		if(foundEntries.size()==0){
		  return null;
		}else{
			return foundEntries;
		}
	}
  
}