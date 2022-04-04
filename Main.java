package application;
	

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



public class Main extends Application {
	
	private static final int breite = 800;
	private static final int laenge = 600;
	private static int score = 0;
	private static boolean gameStart = false;
	private static final Image backGround = new Image("file:C:\\Users\\seank\\Downloads\\space_himmel.png");
	private static final double playerStartPosX = 368;
	private static final double playerStartPosY = 536;
	private static ArrayList<Bullet> bulletsList = new ArrayList<>();
	private static ArrayList<Enemy> enemyList = new ArrayList<>();
	private static Player player = new Player(new Image("file:C:\\Users\\seank\\Downloads\\spaceship.png"),playerStartPosX, playerStartPosY);
	private static boolean dead;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		Canvas canvas = new Canvas(breite,laenge);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		Timeline tl = new Timeline(new KeyFrame(Duration.millis(100), e ->run(gc)));
		tl.setCycleCount(Timeline.INDEFINITE);
		tl.play();
		
		Scene scene = new Scene(new StackPane(canvas));
		canvas.setOnMouseClicked( e-> {
			gameStart = true;
			score = 0;
		});
		
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent e) {
				switch(e.getCode()) {
				//move left
				case A,LEFT: player.moveLeft(); break;
				
				//move right
				case D,RIGHT: player.moveRight(); break;
				
				//shoot
				case SPACE: bulletsList.add(new Bullet(player)); break;
				default:break;
				}
				
			}
			
		});
		
		stage.setScene(scene);
		stage.show();
	}
	
	
	public static void run(GraphicsContext gc) {
		
		gc.drawImage(backGround, 0, 0);
		
		
		if(dead) {
			gc.setFill(Color.YELLOW);
			gc.fillText("Your Spaceship was hit your score is "+score+" \n\n click for a new Round", breite/2-170, laenge/2);
			
		}else {
			gc.getFont();
			gc.setFont(Font.font("Arial", FontWeight.BOLD, 20));
			gc.setFill(Color.WHITE);
			gc.fillText("Scorre: "+score, 10, 20, 400);
		}
		
		
		
		player.draw(gc);
		for(Enemy enemy : enemyList) {
			enemy.draw(gc);
		}
		
		if(enemyList.size() == 0) {
			for(int i=0; i<10;i++) {
				enemyList.add(new Enemy());
			}
		}
		
		
		if(gameStart) {
			for(Enemy enemy : enemyList) {
				enemy.move();
			}
			
		
			
			if(bulletsList.size() > 0) {
				for(Bullet bullet : bulletsList) {
					bullet.draw(gc);
				}
			}
			
			for (int i = bulletsList.size() - 1; i >=0 ; i--) {
				Bullet bullet = bulletsList.get(i);
				if(bullet.getyPos() < -32 )  { 
					bulletsList.remove(i);
					continue;
				}
				
				for (Enemy enemy : enemyList) {
					if(bullet.bulletHit(enemy) ) {
						score++;
						enemyList.remove(enemy);
						enemyList.add(new Enemy());
						bulletsList.remove(bullet);
						break;
					}
				}
			}
			
			for(Enemy enemy : enemyList) {
				if(enemy.getyPos()>600) {
					enemyList.remove(enemy);
					enemyList.add(new Enemy());
					break;
				}
			}
			
			dead = player.isCollided(enemyList);
			if(dead) {
				enemyList.clear();
				bulletsList.clear();
				player.setxPos(playerStartPosX);
				player.setyPos(playerStartPosY);
				gameStart = false;
				
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
