package entity;


/**
 * This represents an instance of a Travel.
 * @author G4-T03
 * 
 */
public class Travel {
	
	private Region travelFrom;
	private Region travelTo;
	private int cost;
	
	/**
	 * Specific constructor of Travel
	 * @param travelFrom The region where the travel starts
	 * @param travelTo The region where the travel ends
	 * @param cost The amount of gold needed for this travel
	 */
	public Travel(Region travelFrom, Region travelTo, int cost){
		this.travelFrom = travelFrom;
		this.travelTo = travelTo;
		this.cost = cost;
	}
	
	/**
	 * Get the region the travel starts
	 * @return The departure region
	 */
	public Region getTravelFrom(){
		return travelFrom;
	}
	
	/**
	 * Get the region the travel ends
	 * @return The arrival region
	 */
	public Region getTravelTo(){
		return travelTo;
	}
	
	/**
	 * Get the amount of gold that needed for the travel
	 * @return The amount of gold that needed for the travel
	 */
	public int getCost(){
		return cost;
	}
}
