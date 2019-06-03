import java.awt.*;
import javax.swing.*;

public class Green extends Cheese {
	private Image img;
	private String color;
	
	public Green(String status) {
		super(status);
		color = "green";
		if (status.equals("normal")) {
			img = new ImageIcon("greenCherniVit.png").getImage();
		} else if (status.equals("bomb")) {
			img = new ImageIcon("greenBomb.png").getImage();
		} else if (status.equals("bottle")) {
			img = new ImageIcon("greenBottle.png").getImage();
		} else if (status.equals("horizontal")) {
			img = new ImageIcon("greenHorizStriped.png").getImage();
		} else if (status.equals("vertical")) {
			img = new ImageIcon("greenVertStriped.png").getImage();
		} else if (status.equals("wrapped")) {
			img = new ImageIcon("greenWrapped.png").getImage();
		}
	}
	
	public Image getImg() {
		return img;
	}
	
	public String getColor() {
		return color;
	}
}
