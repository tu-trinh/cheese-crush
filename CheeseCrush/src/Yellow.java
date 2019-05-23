import java.awt.*;
import javax.swing.*;

public class Yellow extends Cheese {
	private Image img;
	
	public Yellow(String status) {
		super(status);
		if (status.equals("normal")) {
			img = new ImageIcon("yellowCamembert.png").getImage();
		} else if (status.equals("bomb")) {
			img = new ImageIcon("yellowBomb.png").getImage();
		} else if (status.equals("bottle")) {
			img = new ImageIcon("yellowBottle.png").getImage();
		} else if (status.equals("striped")) {
			img = new ImageIcon("yellowStriped.png").getImage();
		} else if (status.equals("wrapped")) {
			img = new ImageIcon("yellowWrapped.png").getImage();
		}
	}
	
	public Image getImg() {
		return img;
	}
}