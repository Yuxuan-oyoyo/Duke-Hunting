package entity;

import java.util.*;
import java.io.*;

/**
 * This is used to extract all the data from the region.csv file
 * @author G4-T03
 *
 */
public class RegionManager{

	private Region[] regionList; 
	private final String FILENAME = "data\\region.csv";
	
	/**
	 * Constructs a new RegionManager
	 */
	public RegionManager(){
	
		regionList = new Region[5];
		this.load();
		
	}
	
	/**
	 * Loads all data from region.csv into the system
	 */
	public void load(){
		ArrayList<String> entries = readEntriesFromFile(FILENAME);
		
		for(int i = 1; i < entries.size(); i++){
			String entry = entries.get(i);
			String[] entryParts = entry.split(",");
			String name = entryParts[0];
			Region region = new Region(name);
			regionList[i-1] = region;
		}
	}
	
	/**
	 * Gets a region according to its name
	 * @param name The name of the region that will be retrieve
	 * @return The region according to the name
	 */
	public Region getRegion(String name){
		for(int i = 0; i < regionList.length; i++){
			Region r = regionList[i];
			if(r.getName().equals(name)){
				return r;
			}
		}
		return null;
	}
	
	/**
	 * Reads data from file
	 * @param nameOfFile The name of file that stores region information
	 * @return An ArrayList of String that store the region information temporarily
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