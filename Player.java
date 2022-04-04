package application;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player {
	private Image spaceShip;
	private double xPos;
	private double yPos;

	
	
	public Player(Image image, double x, double y) {
		this.spaceShip = image;
		this.xPos = x;
		this.yPos = y;
	}
	
	//for Moving
	public void moveLeft() {
		if(this.getxPos()<=0) {
			this.setxPos(0);
		}
		else {
			this.setxPos(this.getxPos()-25);
		}
	}
	
	public void moveRight() {
		if(this.getxPos()>=736.0) {
			this.setxPos(736.0);
		}
		else {
			this.setxPos(this.getxPos()+23.0);
		}
	}
	
	///////////////CHECK/////////////////
	public boolean isCollided(ArrayList<Enemy> enemyList) {
		boolean dead = false;
		for(Enemy enemy : enemyList) {
			double distancY = (this.yPos+16) - (enemy.getyPos()+48);
			double distancX = (this.xPos+32) - (enemy.getxPos()+32);
			if(distancX < 48 && distancX > (-48)) {
				if(distancY <= 0) {
					dead = true;
				}
			}
		}
		return dead;
	}
	
	
	
	public void draw(GraphicsContext gc) {
		gc.drawImage(this.getSpaceShip(), this.getxPos(), this.getyPos());
		
	}
	
	


	public double getxPos() {
		return xPos;
	}


	public void setxPos(double xPos) {
		this.xPos = xPos;
	}


	public double getyPos() {
		return yPos;
	}


	public void setyPos(double yPos) {
		this.yPos = yPos;
	}


	public Image getSpaceShip() {
		return spaceShip;
	}

}
