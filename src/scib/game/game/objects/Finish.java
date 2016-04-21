package scib.game.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.LinkedList;

import scib.game.Game;
import scib.game.framework.GameObject;
import scib.game.framework.Handler;
import scib.game.framework.ObjectId;
import scib.game.framework.Texture;

public class Finish extends GameObject{
	
	private boolean up = false;
	private Texture texture = Game.getTexture();

	/**
	 * @param x x co-ordinate to spawn the finish
	 * @param y y co-ordinate to spawn the finish
	 * @param width width of the finish
	 * @param height height of the finish
	 * @param id id of the finish
	 * @param handler handler in order to deal with other objects
	 */
	public Finish(float x, float y, float width, float height, ObjectId id, Handler handler){
		super(x, y, width, height, id, handler);
	}

	/**
	 * Runs 60 times a second
	 * 
	 * This is where the bulk of the code is contained for the {@link Finish} class
	 */
	public void tick(LinkedList<GameObject> object){
		
		GameObject tempObject;
		
		for(int i = 0; i < object.size(); i++){
			tempObject = object.get(i);
			if(tempObject.getId() == ObjectId.Player){
				if(getBounds().intersects(tempObject.getBounds())){
					up = true;
				}
			}
		}
		
	}
	
	/**
	 * Where the rendering of the {@link Finish} is contained
	 */
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		if(!up){
			g.drawImage(texture.finish[0], (int) x, (int) y, (int) width, (int) height, null);
		}else if(up){
			g.drawImage(texture.finish[1], (int) x, (int) y, (int) width, (int) height, null);
		}
		
		/*g2d.setColor(Color.RED);
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsBottom());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());*/
		
	}

	/**
	 * @return the rectangle of the whole object
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

	/**
	 * @return the top part of the object, used for collision
	 */
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int) x + width / 4), (int) y, (int) width / 2, (int) (height / 2));
	}
	
	/**
	 * @return the bottom part of the object, used for collision
	 */
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) ((int) x + width / 4), (int) ((int) y + (height / 2)), (int) width / 2, (int) (height / 2));
	}
	
	/**
	 * @return the left part of the object, used for collision
	 */
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) ((int) y + height / 16), (int) width / 4, (int) ((int) height - ((height / 16) * 2)));
	}
	
	/**
	 * @return the right part of the object, used for collision
	 */
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int) x + ((width / 4) * 3)), (int) ((int) y + height / 16), (int) width / 4, (int) ((int) height - (height / 16) * 2));
	}
}
