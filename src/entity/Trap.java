package entity;

/**
 * This represents an instance of a Trap.
 * @author G4-T03
 * 
 */
public class Trap{
	
	private String name;
	private int power;
	private int gold;
	private int xp;
	private Region region;
	
	/**
	 * Specific constructor of Trap
	 * @param name The name of the trap 
	 * @param power The power of the trap
	 * @param gold The gold that need to pay for the trap from Trap Smith
	 * @param xp The experience needed to buy and equip the trap 
	 * @param region The region where can buy the trap
	 */
	public Trap(String name, int power, int gold, int xp, Region region){
		this.name = name;
		this.power = power;
		this.gold = gold;
		this.xp = xp;
		this.region = region;
	}
	
	/**
	 * Get trap name
	 * @return The name of the trap
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Get trap power
	 * @return The power of the trap
	 */
	public int getPower(){
		return power;
	}
	
	/**
	 * Get the gold that need to pay for the trap from Trap Smith
	 * @return The gold that need to pay for the trap from Trap Smith
	 */
	public int getGold(){
		return gold;
	}
	
	/**
	 * Get the experience needed to buy and equip the trap 
	 * @return The experience needed to buy and equip the trap 
	 */
	public int getXP(){
		return xp;
	}
	
	/**
	 * Get the region where can buy the trap
	 * @return The region where can buy the trap
	 */
	public Region getRegion(){
		return region;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj){
		if(obj instanceof Trap){
			Trap another = (Trap)obj;
			if(another.name.equals(name)){
				return true;
			}
		}
		return false;	
	}
}