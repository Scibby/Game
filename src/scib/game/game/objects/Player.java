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

	
	private final float GRAVITY = 1.0f;
	private final float MAX_SPEED = 3;

	public Player(float x, float y, float width, float height, ObjectId id, Handler handler) {
		super(x, y, width, height, id, handler);
	}

	public void tick(LinkedList<GameObject> object){
		//image = loader.loadImage("/res/Player.png");
		x += velX;
		y += velY;

		if(velX > MAX_SPEED) velX = MAX_SPEED;
		if(velY > MAX_SPEED) velY = MAX_SPEED;


		if(falling || jumping){
			velY += GRAVITY;
		}

		collision();
	}

	public void collision(){
		for(int i = 0; i < handler.objectList.size(); i++){

			GameObject tempObject = handler.objectList.get(i);

			if(tempObject.getId() == ObjectId.Block){
				if(getBounds().intersects(tempObject.getBounds())){
					velY = 0;
					falling = false;
					jumping = false;

					setY(tempObject.getY() - height);
				}else if(!getBounds().intersects(tempObject.getBounds())){
					falling = true;
				}
				
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y, (int) width, (int) height);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

}