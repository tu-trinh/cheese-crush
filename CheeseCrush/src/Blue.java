import java.awt.*;
import javax.swing.*;

public class Blue extends Cheese {
	private Image img;
	private String color;
	
	public Blue(String status) {
		super(status);
		color = "blue";
		if (status.equals("normal")) {
			img = new ImageIcon("blueGorgonzola.png").getImage();
		} else if (status.equals("bomb")) {
			img = new ImageIcon("blueBomb.png").getImage();
		} else if (status.equals("bottle")) {
			img = new ImageIcon("blueBottle.png").getImage();
		} else if (status.equals("horizontal")) {
			img = new ImageIcon("blueHorizStriped.png").getImage();
		} else if (status.equals("vertical")) {
			img = new ImageIcon("blueVertStriped.png").getImage();
		} else if (status.equals("wrapped")) {
			img = new ImageIcon("blueWrapped.png").getImage();
		}
	}
	
	public Image getImg() {
		return img;
	}
	
	public String getColor() {
		return color;
	}
}
