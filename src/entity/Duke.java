package entity;


/**
 * This represents an instance of a Duke.
 * @author G4-T03
 *
 */
public class Duke {
	
	private String name;
	private int power;
	private Region region;
	private int population;
	
	
	/**
	 * Constructs a new Duke
	 * @param name The name of the duke
	 * @param power The power of the duke
	 * @param region The region where the duke is located in
	 * @param population The population of the duke in the specific region
	 * 
	 */
	public Duke(String name, int power, Region region, int population){
		this.name = name;
		this.power = power;
		this.region = region;
		this.population = population;
		
	}
	
	/**
	 * Get the name of the duke
	 * @return The name of the duke 
	 * 
	 */
	public String getName(){
		return name;
	}
	
	
	/**
	 * Get the name of the region
	 * @return The power of the duke
	 * 
	 */
	public int getPower(){
		return power;
	}
	
	/**
	 * Get the region where the duke is located
	 * @return The region of duke 
	 * 
	 */
	public Region getRegion(){
			return region;
	}
	
	/**
	 * Get the population of duke
	 * @return The population of the duke
	 * 
	 */
	public int getPopulation(){
		return population;
	}
}


