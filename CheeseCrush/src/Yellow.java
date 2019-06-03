import java.awt.*;
import javax.swing.*;

public class Yellow extends Cheese {
	private Image img;
	private String color;
	
	public Yellow(String status) {
		super(status);
		color = "yellow";
		if (status.equals("normal")) {
			img = new ImageIcon("yellowCamembert.png").getImage();
		} else if (status.equals("bomb")) {
			img = new ImageIcon("yellowBomb.png").getImage();
		} else if (status.equals("bottle")) {
			img = new ImageIcon("yellowBottle.png").getImage();
		} else if (status.equals("horizontal")) {
			img = new ImageIcon("yellowHorizStriped.png").getImage();
		} else if (status.equals("vertical")) {
			img = new ImageIcon("yellowVertStriped.png").getImage();
		} else if (status.equals("wrapped")) {
			img = new ImageIcon("yellowWrapped.png").getImage();
		}
	}
	
	public Image getImg() {
		return img;
	}
	
	public String getColor() {
		return color;
	}
}
