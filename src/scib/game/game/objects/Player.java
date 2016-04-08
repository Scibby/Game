package scib.game.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import scib.game.framework.GameObject;
import scib.game.framework.Handler;
import scib.game.framework.ObjectId;

public class Player extends GameObject {
	
	//ImageLoader loader = new ImageLoader();
	//BufferedImage image;
	
	private final float WIDTH = 32;
	private final float HEIGHT = 64;
	private final float GRAVITY = 0.5f;
	private final float MAX_SPEED = 10;
	
	public Player(float x, float y, ObjectId id, Handler handler) {
		super(x, y, id, handler);
	}

	public void tick(LinkedList<GameObject> object){
		//image = loader.loadImage("/res/Player.png");
		x += velX;
		y += velY;
		
		if(velX > MAX_SPEED) velX = MAX_SPEED;
		if(velY > MAX_SPEED) velY = MAX_SPEED;
		
		
		if(falling || jumping){
			velY += GRAVITY;
			
			Collision();
		}
	}

	
	
	public void Collision(){
		if(getBounds().intersects(handler.objectList.get(1).getBounds())){
			velY = 0;
			falling = false;
			jumping = false;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y, (int) WIDTH, (int) HEIGHT);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) WIDTH, (int) HEIGHT);
	}

	

}
