package dataManager;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import entity.Hunter;
import entity.Rank;
import entity.RankManager;
import entity.RegionManager;
import entity.Trap;
import entity.TrapManager;

/**
 * This is used to extract all the data from the Hunter.csv file
 * @author G4-T03
 *
 */
public class HunterDataManager {
	
	private final String FILENAME = "data\\Hunter.csv";
	private ArrayList<Hunter> hunterList = new ArrayList<Hunter>();
	private final String HEADER = "username, password, XP, rank, gold, region, trap";
	private final TrapManager TM = new TrapManager();
	private final RegionManager RM = new RegionManager();
	private final RankManager RMM = new RankManager();
	
	/**
	 * Constructs a new HunterDataManager
	 */
	public HunterDataManager(){
		try{
			load();
		}catch(FileNotFoundException e){
			hunterList = null;
		}
	}
	
	/**
	 * Loads all data from Hunter.csv into the system
	 * @throws FileNotFoundException Throw exception when there is no Hunter.csv
	 */
	private void load() throws FileNotFoundException{
		ArrayList<String> entries = readEntriesFromFile(FILENAME);
      		//read all the files and separate the component into it specific variable	
		if(entries == null){
			throw new FileNotFoundException();
		}
			for(int i=1;i<entries.size();i++){
				String entry = entries.get(i);
				String[] entryParts = entry.split(",");
				String username = entryParts[0];
				String password = entryParts[1];
				String XP = entryParts[2];
				String rank = entryParts[3];
				String gold = entryParts[4];
				String region = entryParts[5];
				String trap = entryParts[6];
				int XPInt = Integer.parseInt(XP);
				Rank r = RMM.getRank(rank);
				int GoldInt = Integer.parseInt(gold);
				Trap t;
				if(trap.equals("null")){
					t= null;
				}else{
					t = TM.getTrap(trap);
				}
				Hunter h = new Hunter(username, password, XPInt, r, GoldInt, RM.getRegion(region), t);
				hunterList.add(h);
			}
	}
	
	
	
	
	/**
	* This private method saves all new changes made into the database.
	*/
	public void save(ArrayList<Hunter> newHunterList){
		hunterList = newHunterList;
		PrintStream writer = null;
		//saving the data by putting all the variable into the csv format
		try {
			writer = new PrintStream(new FileOutputStream(FILENAME,false));
			String[] headerParts = HEADER.split(",");
			writer.print(headerParts[0]);
			writer.print(",");
			writer.print(headerParts[1]);
			writer.print(",");
			writer.print(headerParts[2]);
			writer.print(",");
			writer.print(headerParts[3]);
			writer.print(",");
			writer.print(headerParts[4]);
			writer.print(",");
			writer.print(headerParts[5]);
			writer.print(",");
			writer.print(headerParts[6]);
			writer.print(",\r\n");
      
			for (int i = 0; i < hunterList.size(); i++) {
				Hunter h = hunterList.get(i);
				writer.print(h.getUsername());
				writer.print(",");
				writer.print(h.getPassword());
				writer.print(",");
				writer.print(Integer.toString(h.getXP()));
				writer.print(",");
				writer.print(h.getRank().getRank());
				writer.print(",");
				writer.print(Integer.toString(h.getGold()));
				writer.print(",");
				writer.print(h.getRegion().getName());
				writer.print(",");
				if(h.getTrap() == null){
					writer.print("null");
				}else{
					writer.print(h.getTrap().getName());
				}
				writer.print(",\r\n");
			}
		} catch (IOException e) {
			System.out.println("\nWARNING: hunterDataManager: Method save: IOException caught. Please contact data administrator");
		} finally {
			if (writer != null) {
				writer.close();
			}
		}  
	}
	
	
	
	/**
	*  This method allows the program to retrieve all the hunters in the network.
	* @return all the hunters in the network.
	*/
	public ArrayList<Hunter> retrieveAll(){
		return this.hunterList;
	}
	
	public Hunter getHunter(String username){
		Hunter h = null;
		for(int i = 0; i < hunterList.size(); i++){
			if(hunterList.get(i).getUsername().equals(username)){
				h = hunterList.get(i);
			}
		}
		return h;
	}
	
	
	  /**
		*  This private method reads from a file.
		*/
	private ArrayList<String> readEntriesFromFile(String nameOfFile) {
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
