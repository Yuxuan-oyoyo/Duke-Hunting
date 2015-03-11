package dataManager;

import entity.*;

import java.util.*;
import java.io.*;


/**
 * This is used to extract all the data from the HunteingRecord.csv file
 * @author G4-T03
 *
 */
public class HuntingDataManager {
	private final String FILENAME = "data\\HuntingRecord.csv";
	private HunterDataManager HDM = new HunterDataManager();
	private String HEADER = "username, record";
	private HashMap<Hunter, String[]> huntingRecords = new HashMap<Hunter, String[]>();
	
	/**
	 * Constructs a new HuntingDataManager
	 */
	public HuntingDataManager(){
		try{
			load();
		}catch(FileNotFoundException e){
		}
	}
	
	/**
	 * Loads all data from HuntingRecord.csv into the system
	 * @throws FileNotFoundException Throw exception when there is no HuntingRecord.csv
	 */
	public void load() throws FileNotFoundException{
		ArrayList<String> entries = readEntriesFromFile(FILENAME);
		//load the data and separate the data into its variable
		if(entries == null){
			throw new FileNotFoundException();
		}
		for(int i = 1; i < entries.size(); i++){
			String entry = entries.get(i);
			String[] entryParts = entry.split(",");
			String username = entryParts[0];
			String[] huntingRecord = new String[10];
			for(int p = 1; p < entryParts.length; p++){
				String record = entryParts[p];
				huntingRecord[p-1] = record;
			}
			huntingRecords.put(HDM.getHunter(username), huntingRecord);
		}
	}
	
	/**
	 * Updates the HuntingRecord.csv according to the changes made in the system
	 * @param newHuntingRecords The new hunting record of all hunters
	 */
	public void save(HashMap<Hunter, String[]> newHuntingRecords){
		huntingRecords = newHuntingRecords;
		PrintStream writer = null;
		//save the data and formatting it as the csv file
		try{
			writer = new PrintStream(new FileOutputStream(FILENAME, false));
			String[] headerParts = HEADER.split(",");
			writer.print(headerParts[0]);
			writer.print(",");
			writer.print(headerParts[1]);
			writer.print(",\r\n");
			
			Iterator<Hunter> iter = huntingRecords.keySet().iterator();
			while(iter.hasNext()){
				Hunter h = iter.next();
				String[] huntingRecord = huntingRecords.get(h);
				writer.print(h.getUsername());
				for(int p = 0; p < huntingRecord.length; p++){
					writer.print(",");
					writer.print(huntingRecord[p]);
				}
				writer.print(",\r\n");
			}
		}catch (IOException e) {
			System.out.println("\nWARNING: HuntingDataManager: Method save: IOException caught. Please contact data administrator");
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	
	/**
	 * Gets hunting record of all hunters
	 * @return A HashMap that store the information of hunting record of all hunters
	 */
	public HashMap<Hunter, String[]> retrieveAll(){
		return huntingRecords;
	}
	
	/**
	 * Get all hunting record the specific hunter has
	 * @param username The user name of a hunter whose hunting record will be retrieved
	 * @return An Array that store 10 hunting record
	 */
	public String[] getHuntingRecord(String username){
		//going through the record and return the username record
		Iterator<Hunter> iter = huntingRecords.keySet().iterator();
		while(iter.hasNext()){
			Hunter h = iter.next();
			if(h.getUsername().equals(username)){
				return huntingRecords.get(h);
			}
		}
		return null;
	}
	
	
	/**
	 * Reads data from file
	 * @param nameOfFile The name of file that stores hunter information
	 * @return An ArrayList of String that store the hunter information temporarily
	 */
	public ArrayList<String> readEntriesFromFile(String nameOfFile) {
		ArrayList<String> foundEntries = new ArrayList<String>();
		//reading the file
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
