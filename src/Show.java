import java.io.Serializable;

/**
 * Class for the shows being used in the Main.
 * @author Jack Protivnak
 */
public class Show implements TVShows, Serializable {
	private static final long serialVersionUID = 1;
	public String name; public String description;
	public String image;
	public int seasons;

	/**
	 * Constructor - No use.
	 */
	public Show() {
	}
	
	/**
	 * Converts information to readable English.
	 */
	public String toString() {
		return name + " - " + description + " - " + seasons;
	}

	/**
	 * Called to set the name of the show.
	 * @param s
	 */
	public void setName(String s) {
		name = s;
	}
	
	/**
	 * Called to get the name of the show.
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Called to set the image of the show.
	 * @param i
	 */
	public void setImage(String i) {
		image = i;
	}
	
	/**
	 * Called to get the image location for the show.
	 * @return image
	 */
	public String getImage() {
		return image;
	}
	
	/**
	 * Called to set the number of seasons for the show.
	 * @param i
	 */
	public void setSeasons(int i) {
		seasons = i;
	}

	/**
	 * Called to get the number of seasons for the show.
	 * @return seasons
	 */
	public int getSeasons() {
		return seasons;
	}
	
	/**
	 * Called to set the description of the show.
	 * @param s
	 */
	public void setDescription(String s) {
		description = s;
	}
	
	/**
	 * Called to get the description of the show.
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
}