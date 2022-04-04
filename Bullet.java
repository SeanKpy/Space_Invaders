package application;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet {
	private Image image = new Image("file:C:\\Users\\seank\\Downloads\\patrone.png");
	private double xPos;
	private double yPos;
	
	
	
	public Bullet(Player player) {
		this.xPos = player.getxPos();
		this.yPos = player.getyPos()-32;
		
	}
	
	public void draw(GraphicsContext gc) {
		
			gc.drawImage(this.getImage(), this.getxPos(), this.getyPos());
			this.setyPos(this.getyPos()-30);
	}
	
	public boolean bulletHit(Enemy enemy) {
		boolean hit = false;
		double yDistanc = (this.yPos-20) - (enemy.getyPos());
		double xDistanc = (this.xPos+32) - (enemy.getxPos()+32);
		if(xDistanc < 32 && xDistanc > (-32)) {
			if(yDistanc <= 0) {
				hit = true;;
			}
		}
		
		return hit;
	}
	
	
	
	public double getxPos() {
		return xPos;
	}
	
	public double getyPos() {
		return yPos;
	}
	public void setyPos(double yPos) {
		this.yPos = yPos;
	}
	public Image getImage() {
		return image;
	}

}
