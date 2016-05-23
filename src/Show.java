import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Show implements TVShows, Serializable {
	public String name; public String description;
	public String image;
	public int seasons;

	public Show() {
	}
	
	public String toString() {
		return name + " - " + description + " - " + seasons;
	}

	public void setName(String s) {
		name = s;
	}
	
	public String getName() {
		return name;
	}

	public void setImage(String i) {
		image = i;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setSeasons(int i) {
		seasons = i;
	}
	
	public int getSeasons() {
		return seasons;
	}
	
	public void setDescription(String s) {
		description = s;
	}
	
	public String getDescription() {
		return description;
	}

}
