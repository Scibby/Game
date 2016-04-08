package scib.game.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import scib.game.framework.GameObject;
import scib.game.framework.Handler;
import scib.game.framework.ImageLoader;
import scib.game.framework.ObjectId;

public class Block extends GameObject {
	
	ImageLoader loader = new ImageLoader();
	
	public Block(float x, float y, ObjectId id, Handler handler) {
		super(x, y, id, handler);
	}

	public void tick(LinkedList<GameObject> object){
		//loader.loadImage("/res/Player.png");
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect((int) x, (int) y, 32, 32);
	}


	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	

}
