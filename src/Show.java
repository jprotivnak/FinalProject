import java.awt.image.BufferedImage;

public class Show implements TVShows {
	public String name; public String description;
	public BufferedImage image;
	public int seasons;

	public Show() {
	}

	public void name(String s) {
		name = s;
	}
	
	public String getName() {
		return name;
	}

	public void image(BufferedImage i) {
		image = i;
	}
	
	public void seasons(int i) {
		seasons = i;
	}
	
	public void description(String s) {
		description = s;
	}

}
