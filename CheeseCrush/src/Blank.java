import java.awt.*;
import javax.swing.*;

public class Blank extends Cheese {
	private Image img;
	private String color;
	
	public Blank(String status) {
		super(status);
		color = "blank";
		if (status.equals("blank")) {
			img = new ImageIcon("blank.png").getImage();
		}
	}
	
	public Image getImg() {
		return img;
	}
	
	public String getColor() {
		return color;
	}
}
