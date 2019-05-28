import java.awt.*;
import javax.swing.*;

public class Purple extends Cheese {
	private Image img;
	private String color;
	
	public Purple(String status) {
		super(status);
		color = "purple";
		if (status.equals("normal")) {
			img = new ImageIcon("purpleRicotta.png").getImage();
		} else if (status.equals("bomb")) {
			img = new ImageIcon("purpleBomb.png").getImage();
		} else if (status.equals("bottle")) {
			img = new ImageIcon("purpleBottle.png").getImage();
		} else if (status.equals("striped")) {
			img = new ImageIcon("purpleStriped.png").getImage();
		} else if (status.equals("wrapped")) {
			img = new ImageIcon("purpleWrapped.png").getImage();
		}
	}
	
	public Image getImg() {
		return img;
	}
	
	public String getColor() {
		return color;
	}
}
