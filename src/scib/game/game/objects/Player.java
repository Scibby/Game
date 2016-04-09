package scib.game.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import scib.game.framework.GameObject;
import scib.game.framework.Handler;
import scib.game.framework.ObjectId;

public class Player extends GameObject {

	//ImageLoader loader = new ImageLoader();
	//BufferedImage image;

	
	private final float GRAVITY = 1.0f;
	private final float MAX_SPEED = 10;

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
				
				if(getBoundsTop().intersects(tempObject.getBounds())){
					//setVelX(0);
					setY(tempObject.getY() + tempObject.getHeight());
				}
				
				if(getBoundsBottom().intersects(tempObject.getBoundsTop())){
					setVelY(0);
					falling = false;
					jumping = false;

					setY(tempObject.getY() - getHeight());
				}else if(!getBoundsBottom().intersects(tempObject.getBoundsTop())){
					falling = true;
				}
				
				if(getBoundsRight().intersects(tempObject.getBounds())){
					//setVelX(0);
					setX(tempObject.getX() - getWidth());
				}
				
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					//setVelX(0);
					setX(tempObject.getX() + getWidth());
				}
				
			}
		}
	}

	

	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y, (int) width, (int) height);
		
		g2d.setColor(Color.BLUE);
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsBottom());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int) x + width / 4), (int) y, (int) width / 2, (int) (height / 2));
	}
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) ((int) x + width / 4), (int) ((int) y + (height / 2)), (int) width / 2, (int) (height / 2));
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) ((int) y + height / 16), (int) width / 4, (int) ((int) height - ((height / 16) * 2)));
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int) x + ((width / 4) * 3)), (int) ((int) y + height / 16), (int) width / 4, (int) ((int) height - (height / 16) * 2));
	}

}