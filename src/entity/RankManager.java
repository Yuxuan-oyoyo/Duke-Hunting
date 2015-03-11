package entity;

import java.util.*;
import java.io.*;

/**
 * This is used to extract all the data from the rank.csv file
 * @author G4-T03
 *
 */
public class RankManager{
  
	private Rank[] rankList; 
	private final String FILENAME = "data\\rank.csv";
	private RegionManager RM = new RegionManager();
	
	/**
	 * Constructs a new RankManager
	 */
	public RankManager(){
	
		rankList = new Rank[5];
		this.load();
		
	}
  
	/**
	 * Loads all data from rank.csv into the system
	 */
	public void load(){
		ArrayList<String> entries = readEntriesFromFile(FILENAME);
			
		for(int i = 1; i < entries.size(); i++){
			String entry = entries.get(i);
			String[] entryParts = entry.split(",");
			String r = entryParts[0];
			String region = entryParts[1];
			String xp = entryParts[2];
			int xpInt = Integer.parseInt(xp);
			Region regionR = RM.getRegion(region);
			Rank rank = new Rank(r, regionR, xpInt);
			rankList[i-1] = rank;
		}
	
		for (int i = 0; i < rankList.length-1; ++i) {
		    int guess = i;
		    for (int j = i+1; j < rankList.length; ++j) {
		      if (rankList[j].getXP() < rankList[guess].getXP()) {
		        guess = j;
		      }
		    }

		    Rank rmbr = rankList[i];
		    rankList[i] = rankList[guess];
		    rankList[guess] = rmbr;
		}
	}
	
	/**
	 * Gets the specific rank according to the rank name
	 * @param name The name that describe the rank
	 * @return The specific rank that the rank name refers to
	 */
	public Rank getRank(String name){
		for(int i = 0; i < rankList.length; i++){
			Rank r = rankList[i];
			if(r.getRank().equals(name)){
				return r;
			}
		}
		return null;
	}
	
	
	/**
	 * Gets all ranks in the system
	 * @return An Array of rank in the system
	 */
	public Rank[] getRankList(){
		return rankList;
	}
	
	
	/**
	 * Reads data from file
	 * @param nameOfFile The name of file that stores rank information
	 * @return An ArrayList of String that store the rank information temporarily
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