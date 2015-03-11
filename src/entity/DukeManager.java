package entity;

import java.util.*;
import java.io.*;

/**
 * This is used to extract all the data from the duke.csv file
 * @author G4-T03
 *
 */
public class DukeManager{
  
	private Duke[] dukeList; 
	private final String FILENAME = "data\\duke.csv";
	private RegionManager RM = new RegionManager();
	
	/**
	 * Constructs a new DukeManager
	 */
	public DukeManager(){
	
		dukeList = new Duke[27];
		this.load();
		
	}
  
	/**
	 * Loads all data from duke.csv into the system
	 */
	public void load(){
		ArrayList<String> entries = readEntriesFromFile(FILENAME);
		
		for(int i = 1; i < entries.size(); i++){
			String entry = entries.get(i);
			String[] entryParts = entry.split(",");
			String name = entryParts[0];
			String power = entryParts[1];
			String region = entryParts[2];
			String population = entryParts[3];
			int powerInt = Integer.parseInt(power);
			int populationInt = Integer.parseInt(population);
			Region regionR = RM.getRegion(region);
			Duke duke = new Duke(name, powerInt, regionR, populationInt);
			dukeList[i-1] = duke;
		}
		
	}
	
	
	/**
	 * Gets all dukes in a specific region
	 * @param r The region where the hunter hunting
	 * @return The Array of duke in the region
	 */
	public Duke[] getDukeInside(Region r){
		ArrayList<Duke> dukes = new ArrayList<Duke>();
		for(int i = 0; i < dukeList.length; i++){
			Duke duke = dukeList[i];
			if(duke.getRegion().equals(r)){
				dukes.add(duke);
			}
		}
		final int size = dukes.size();
		Duke[] dukeArr = (Duke[])dukes.toArray(new Duke[size]);
		return dukeArr;
	}
  
	
	/**
	 * Reads data from file
	 * @param nameOfFile The name of file that stores duke information
	 * @return An ArrayList of String that store the duke information temporarily
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