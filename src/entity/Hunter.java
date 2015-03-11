package entity;

/**
 * This represents an instance of a Hunter.
 * @author G4-T03
 *
 */
public class Hunter {
	
	private String username;
	private String password;
	private int xp;
	private Rank rank;
	private int gold;
	private Region region;
	private Trap trap;
	private RegionManager RM = new RegionManager();
	private TrapManager TM = new TrapManager();
	private RankManager RMM = new RankManager();
	
	/**
	 * Constructs a new Hunter
	 * @param username The username of the hunter account to identify users
	 * @param password The password of the account to verify the identity of the user
	 */
	public Hunter(String username, String password){
		this.username = username;
		this.password = password;
		xp = 0;
		rank = RMM.getRank("Freshman");
		gold = 500;
		region = RM.getRegion("SIS");
		trap = TM.getTrap("Trap of Least Resistance");
	}
	
	/**
	 * Construct a Hunter that the system stored already
	 * @param username The username of the hunter account to identify users
	 * @param password The password of the hunter account to verify the identity of the user
	 * @param xp The experience of a hunter
	 * @param rank The rank of a hunter according to the experience
	 * @param gold The gold the hunter have
	 * @param region The current location of the hunter
	 * @param trap The equiped trap of the hunter
	 */
	public Hunter(String username, String password, int xp, Rank rank, int gold, Region region, Trap trap){
		this.username = username;
		this.password = password;
		this.xp = xp;
		this.rank = rank;
		this.gold = gold;
		this.region = region;
		this.trap = trap;
	}
	
	
	/**
	 * Get the username of a hunter
	 * @return The username of a hunter
	 * 
	 */
	public String getUsername(){
		return  username;
	}
	
	/**
	 * Get the password of a hunter
	 * @return The password of a hunter
	 * 
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * Get the experience the hunter got already
	 * @return The experience of the hunter
	 */
	public int getXP(){
		return xp;
	}
	
	/**
	 * Get the gold the hunter have
	 * @return The gold amount of a hunter
	 */
	public int getGold(){
		return gold;
	}
	
	/**
	 * Get the rank of the hunter
	 * @return The rank of the hunter, according to the xp points of the hunter
	 */
	public Rank getRank(){
		return rank;
	}
	
	/**
	 * Get the region where the hunter is located
	 * @return The region of the hunter
	 */
	public Region getRegion(){
		return region;
	}
	
	/**
	 * Get the trap the hunter is using
	 * @return The equipped trap
	 */
	public Trap getTrap(){
		return trap;
	}
	
	
	/**
	 * Set the specific experience of a hunter
	 * @param xp The specific experience of a hunter 
	 */
	public void setXP(int xp){
		this.xp = xp;
	}
	
	/**
	 * Set the gold of a hunter
	 * @param gold The specific amount of gold that the hunter has
	 */
	public void setGold(int gold){
		this.gold = gold;
	}
	
	/**
	 * Set rank for the hunter according to his or her experience
	 * @param rank The rank of the hunter according to the XP
	 */
	public void setRank(Rank rank){
		this.rank = rank;
	}
	
	/**
	 * 	Set the region where the hunter is located
	 * @param region The location of the hunter
	 */
	public void setRegion(Region region){
		this.region = region;
	}
	
	/**
	 * Change trap for hunter or set the trap that the hunter equip last time
	 * @param trap The trap that the hunter is going to equip
	 * 
	 */
	public void setTrap(Trap trap){
		this.trap = trap;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj){
		if(obj instanceof Hunter){
			Hunter another = (Hunter)obj;
			if(another.username.equals(username)){
				return true;
			}
		}
		return false;	
	}
}
