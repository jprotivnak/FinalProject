public  interface TVShows {
	
	/**
	 * Accepts type String to set as the name
	 * of each TV Show.
	 * @param s
	 */
	public void setName(String s);
	
	 /**
	  * Returns name of show.
	  * @return name
	  */
	public String getName();

	/**
	 * Accepts type String to set as the
	 * image location for each TV Show.
	 * @param image
	 */
	public void setImage(String image);
	
	/**
	 * Returns image location set by user of show.
	 * @return image
	 */
	public String getImage();
	
	/**
	 * Accepts type integer for number of seasons
	 * for each TV Show. 
	 * @param numSeasons
	 */
	public void setSeasons(int numSeasons);
	
	 /**
	  * Returns number of seasons.
	  * @return seasons
	  */
	public int getSeasons();
	
	/**
	 * Accepts type String for the description for
	 * each TV Show.
	 * @param s
	 */
	public void setDescription(String s);
	
	/**
	 * Returns description of show.
	 * @return description
	 */
	public String getDescription();
}