import java.awt.*;
import java.awt.image.BufferedImage;

public  interface TVShows {
	
	/**
	 * Accepts type String to set as the name
	 * of each TV Show.
	 * @param s
	 */
	public void setName(String s);
	 /**
	  * Returns name of show.
	  * @return
	  */
	public String getName();

	/**
	 * Accepts type BufferedImage to set as the
	 * image for each TV Show.
	 * @param image
	 */
	public void setImage(BufferedImage image);
	
	/**
	 * Returns image set by user of show.
	 * @return
	 */
	public BufferedImage getImage();
	
	/**
	 * Accepts type integer for number of seasons
	 * for each TV Show. 
	 * @param numSeasons
	 */
	public void setSeasons(int numSeasons);
	 /**
	  * Returns number of seasons.
	  * @return
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
	 * @return
	 */
	public String getDescription();
	
//	public void 
	
	
	
	
	
	
	
	
}
