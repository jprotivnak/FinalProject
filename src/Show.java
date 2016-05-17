import java.awt.image.BufferedImage;

public class Show implements TVShows {
	public String name; public String description;
	public BufferedImage image;
	public int seasons;

	public Show() {
	}

	public void setName(String s) {
		name = s;
	}
	
	public String getName() {
		return name;
	}

	public void setImage(BufferedImage i) {
		image = i;
	}
	
	public BufferedImage getImage() {
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
