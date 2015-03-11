package entity;

/**
 * This represents an instance of a Region.
 * @author G4-T03
 * 
 */
public class Region{
	
	private String name;
	
	/**
	 * Specific constructor of Region
	 * @param name The name of a Region
	 */
	public Region(String name){
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return name;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj){
		if(obj instanceof Region){
			Region another = (Region)obj;
			if(another.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get the name of a Region
	 * @return The region name
	 */
	public String getName(){
		return name;
	}
}