import java.awt.*;
import javax.swing.*;

public class Green extends Cheese {
	private Image img;
	
	public Green(String status) {
		super(status);
		if (status.equals("normal")) {
			img = new ImageIcon("greenCherniVit.png").getImage();
		} else if (status.equals("bomb")) {
			img = new ImageIcon("greenBomb.png").getImage();
		} else if (status.equals("bottle")) {
			img = new ImageIcon("greenBottle.png").getImage();
		} else if (status.equals("striped")) {
			img = new ImageIcon("greenStriped.png").getImage();
		} else if (status.equals("wrapped")) {
			img = new ImageIcon("greenWrapped.png").getImage();
		}
	}
	
	public Image getImg() {
		return img;
	}
}