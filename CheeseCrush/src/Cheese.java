import java.awt.Image;

public abstract class Cheese {
	private String status;
	private Image img;
	
	public Cheese(String status) {
		status = status;
	}
	
	public Image getImg() {
		return img;
	}
}
