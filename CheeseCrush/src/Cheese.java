import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Cheese {
	private String status;
	private Image img;
	private String color;
	
	public Cheese(String status) {
		this.status = status;
		img = new ImageIcon("colorBomb.png").getImage();
		color = "none";
	}
	
	public Image getImg() {
		return img;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getColor() {
		return color;
	}
	
	public void changeStatus(String newStatus) {
		this.status = newStatus;
	}
	
	public boolean equals(Cheese other) {
		if (this.getColor().equals(other.getColor())) {
			return true;
		}
		return false;
	}
}
