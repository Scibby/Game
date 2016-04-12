package scib.game.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.LinkedList;

import scib.game.framework.GameObject;
import scib.game.framework.Handler;
import scib.game.framework.ObjectId;

public class Finish extends GameObject{
	
	Polygon p = new Polygon();

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
	 * runs 60 times a second
	 * 
	 * this is where the bulk of the code is contained for the {@link Finish} class
	 */
	public void tick(LinkedList<GameObject> object){
	}
	
	/**
	 * where the rendering of the {@link Player} is contained
	 */
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLUE.brighter().brighter());
		
		p.addPoint((int) x, (int) ((int) y + (height / 4)));
		p.addPoint((int) ((int) x + width), (int) y);
		p.addPoint((int) ((int) x + width), (int) ((int) y + (height / 4)));
		g2d.drawPolygon(p);
		
		/*p.addPoint((int) ((int) x + width) - 10, (int) (y + height));
		p.addPoint((int) ((int) x + width), (int) (y + height));
		g2d.fillPolygon(p);*/
		
		g2d.fillRect((int) ((int) x + width) - 5, (int) (y + (height / 4)), 5, (int) (height - (height / 4)));
	
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
