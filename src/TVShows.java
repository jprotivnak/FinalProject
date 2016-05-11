import java.awt.*;
import java.awt.image.BufferedImage;

public  interface TVShows {
	
	/**
	 * Accepts type String to set as the name
	 * of each TV Show.
	 * @param s
	 */
	public void name(String s);

	/**
	 * Accepts type BufferedImage to set as the
	 * image for each TV Show.
	 * @param image
	 */
	public void image(BufferedImage image);
	
	/**
	 * Accepts type integer for number of seasons
	 * for each TV Show. 
	 * @param numSeasons
	 */
	public void seasons(int numSeasons);
	
	/**
	 * Accepts type String for the description for
	 * each TV Show.
	 * @param s
	 */
	public void description(String s);
	
//	public void 
	
	
	
	
	
	
	
	
}
