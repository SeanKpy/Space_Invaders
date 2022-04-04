package application;



import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Enemy {
	Image image = new Image("file:C:\\Users\\seank\\Downloads\\space_enemy.png");
	int xPos = ThreadLocalRandom.current().nextInt(0, 736);
	int yPos = ThreadLocalRandom.current().nextInt(0, 150);
	
	
	
	
	public void draw(GraphicsContext gc) {
		gc.drawImage(this.getImage(), this.getxPos(), this.getyPos());
	}
	
	
	
	public void move() {
		this.setyPos(this.getyPos()+5);
	}
	
	
	
	
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public Image getImage() {
		return image;
	}
	
}
