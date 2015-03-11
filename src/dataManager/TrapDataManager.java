package dataManager;
import java.io.*;

import entity.*;

import java.util.*;

/**
 * This is used to extract all the data from the Inventory.csv file
 * @author G4-T03
 *
 */
public class TrapDataManager {
	
	private final String FILENAME = "data\\Inventory.csv";
	private final TrapManager TM = new TrapManager();
	private final HunterDataManager HDM = new HunterDataManager();
	private final String HEADER = "username, inventory";
	private HashMap<Hunter, ArrayList<Trap>> trapData = new HashMap<Hunter, ArrayList<Trap>>();
	
	/**
	 * Constructs a new TrapDataManager
	 */
	public TrapDataManager(){
		try{
			load();
		}catch(FileNotFoundException e){
		}
	}
	
	/**
	 * Loads all data from Inventory.csv into the system
	 * @throws FileNotFoundException Throw exception when there is no Inventory.csv
	 */
	public void load() throws FileNotFoundException{
		
		ArrayList<String> entries = readEntriesFromFile(FILENAME);
		//loading the file and separate the data into the variable
		if(entries == null){
			throw new FileNotFoundException();
		}
		for(int i = 1; i < entries.size(); i++){
			String entry = entries.get(i);
			String[] entryParts = entry.split(",");
			String username = entryParts[0];
			ArrayList<Trap> trapList = new ArrayList<Trap>();
			for(int p = 1; p < entryParts.length; p++){
				String trapName = entryParts[p];
				Trap t = TM.getTrap(trapName);
				trapList.add(t);
			}
			trapData.put(HDM.getHunter(username), trapList);
		}
		
	}
	
	/**
	 * Updates the Inventory.csv according to the changes made in the system
	 * @param newTrapData The new trap data of all hunters
	 */
	public void save(HashMap<Hunter, ArrayList<Trap>> newTrapData){
		trapData = newTrapData;
		PrintStream writer = null;
		//saving the data and formatting the data same as the csv file
		try{
			writer = new PrintStream(new FileOutputStream(FILENAME, false));
			String[] headerParts = HEADER.split(",");
			writer.print(headerParts[0]);
			writer.print(",");
			writer.print(headerParts[1]);
			writer.print(",\r\n");
			
			Iterator<Hunter> iter = trapData.keySet().iterator();
			while(iter.hasNext()){
				Hunter h = iter.next();
				ArrayList<Trap> trapList = trapData.get(h);
				writer.print(h.getUsername());
				for(int p = 0; p < trapList.size(); p++){
					if(trapList.get(p) != null){
						writer.print(",");
						writer.print(trapList.get(p).getName());
					}
					
				}
				writer.print(",\r\n");
			}
		}catch (IOException e) {
			System.out.println("\nWARNING: hunterDataManager: Method save: IOException caught. Please contact data administrator");
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	
	
	/**
	 * Gets inventory information of all hunters
	 * @return A HashMap that store the information of inventory of all hunters
	 */
	public HashMap<Hunter, ArrayList<Trap>> retrieveAll(){
		return this.trapData;
	}
	
	/**
	 * Get all the traps the specific hunter has
	 * @param username The user name of a hunter whose inventory will be retrieved
	 * @return An ArrayList that store all the Inventory information
	 */
	public ArrayList<Trap> getTrapList(String username){
		//getting all the traps available for the username
		Iterator<Hunter> iter = trapData.keySet().iterator();
		while(iter.hasNext()){
			Hunter h = iter.next();
			if(h.getUsername().equals(username)){
				return trapData.get(h);
			}
		}
		return null;
	}
	
	
	/**
	 * Reads data from file
	 * @param nameOfFile The name of file that stores inventory information of all hunters
	 * @return An ArrayList of String that store the inventory information of all hunters temporarily
	 */
	public ArrayList<String> readEntriesFromFile(String nameOfFile) {
		ArrayList<String> foundEntries = new ArrayList<String>();
		//reading the entire file
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
