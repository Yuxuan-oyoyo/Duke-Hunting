package entity;

import java.util.*;
import java.io.*;

/**
 * This is used to extract all the data from the trap.csv file
 * @author G4-T03
 *
 */
public class TrapManager{
  
	private Trap[] trapList; 
	private final String FILENAME = "data\\trap.csv";
	private RegionManager RM = new RegionManager();
	
	/**
	 * Constructs a new TrapManager
	 */
	public TrapManager(){
	
		trapList = new Trap[15];
		load();
		
	}
	
	/**
	 * Get the trap that are sold in a specific region
	 * @param region The region where hunter buy the trap
	 * @return An ArrayList of trap that is sold in the region
	 */
	public ArrayList<Trap> getTrapAccordingToRegion(Region region){
		ArrayList<Trap> trapsHere = new ArrayList<Trap>();
		for(int i = 0; i < trapList.length; i++){
			Trap t = trapList[i];
			if(t.getRegion().equals(region)){
				trapsHere.add(t);
			}
		}
		return trapsHere;
	}
  
	/**
	 * Loads all data from trap.csv into the system
	 */
	public void load(){
			ArrayList<String> entries = readEntriesFromFile(FILENAME);
			
			for(int i = 1; i < entries.size(); i++){
				String entry = entries.get(i);
				String[] entryParts = entry.split(",");
				String name = entryParts[0];
				String power = entryParts[1];
				String gold = entryParts[2];
				String xp = entryParts[3];
				String location = entryParts[4];
				int powerInt = Integer.parseInt(power);
				int goldInt = Integer.parseInt(gold);
				int XPInt = Integer.parseInt(xp);
				Region regionR = RM.getRegion(location);
				Trap trap = new Trap(name, powerInt, goldInt, XPInt, regionR);
				trapList[i-1] = trap;
			}
	}
	
	/**
	 * Gets the trap according to the trap name
	 * @param The name of the trap that will be returned
	 * @return The trap with specific name
	 */
	public Trap getTrap(String name){
		for(int i = 0; i < trapList.length; i++){
			Trap t = trapList[i];
			if(t.getName().equals(name)){
				return t;
			}
		}
		return null;
	}
	
	/**
	 * Reads data from file
	 * @param nameOfFile The name of file that stores trap information
	 * @return An ArrayList of String that store the trap information temporarily
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