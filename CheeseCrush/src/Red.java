import java.awt.*;
import javax.swing.*;

public class Red extends Cheese {
	private Image img;
	private String color;
	
	public Red(String status) {
		super(status);
		color = "red";
		if (status.equals("normal")) {
			img = new ImageIcon("redLeicester.png").getImage();
		} else if (status.equals("bomb")) {
			img = new ImageIcon("redBomb.png").getImage();
		} else if (status.equals("bottle")) {
			img = new ImageIcon("redBottle.png").getImage();
		} else if (status.equals("horizontal")) {
			img = new ImageIcon("redHorizStriped.png").getImage();
		} else if (status.equals("vertical")) {
			img = new ImageIcon("redVertStriped.png").getImage();
		} else if (status.equals("wrapped")) {
			img = new ImageIcon("redWrapped.png").getImage();
		}
	}
	
	public Image getImg() {
		return img;
	}
	
	public String getColor() {
		return color;
	}
}
