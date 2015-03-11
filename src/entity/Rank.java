package entity;

/**
 * This represents an instance of a Rank of a Hunter.
 * @author G4-T03
 * 
 */

public class Rank{
	private String rank;
	private Region region;
	private int XP;
	
	/**
	 * Constructs a rank
	 * @param rank The description of the rank
     * @param regionR The region unlocked after achieving the rank
	 * @param XP The amount of experience points required to obtain the rank
	 */
	public Rank(String rank, Region regionR, int XP) {
		this.rank = rank;
		this.region = regionR;
		this.XP = XP;
	}

	/**
	 * Get the name of the rank
	 * @return the name of the rank, according to the EXP points of the Hunter
	 */
	public String getRank() {
		return rank;
	}
	
	
  
  /**
	 * Get the name of the region
	 * @return the region unlocked after achieving the rank
	 */
	public Region getRegion() {
		return region;
	}

	
	
	/**
	 * Get the minimum experience required of the rank
	 * @return the minimum experience points required to reach the rank
	 */
	public int getXP() {
		return XP;
	}	
}
