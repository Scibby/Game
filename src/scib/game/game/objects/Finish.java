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

	public Finish(float x, float y, float width, float height, ObjectId id, Handler handler){
		super(x, y, width, height, id, handler);
	}

	public void tick(LinkedList<GameObject> object){
		
	}

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
