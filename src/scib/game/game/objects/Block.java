package scib.game.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import scib.game.Game;
import scib.game.framework.GameObject;
import scib.game.framework.Handler;
import scib.game.framework.ImageLoader;
import scib.game.framework.ObjectId;
import scib.game.framework.Texture;

public class Block extends GameObject {

	ImageLoader loader = new ImageLoader();
	private int blockId;
	Texture texture = Game.getTexture();

	/**
	 * @param x x co-ordinate to spawn the block
	 * @param y y co-ordinate to spawn the block
	 * @param width width of the block
	 * @param height height of the block
	 * @param id id of the block
	 * @param handler handler in order to deal with other objects
	 */
	public Block(float x, float y, float width, float height, int blockId, ObjectId id, Handler handler) {
		super(x, y, width, height, id, handler);
		this.blockId = blockId;
	}

	/**
	 * Runs 60 times a second
	 * 
	 * Where the bulk of the code is contained for the {@link Block} class
	 */
	public void tick(LinkedList<GameObject> object){

	}

	/**
	 * Where the rendering of the {@link Block} is contained
	 */
	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g.setColor(Color.WHITE);
		//g.drawRect((int) x, (int) y, (int) width, (int) height);

		for(int i = 0; i < texture.block.length; i++){
			if(blockId == i){
				g.drawImage(texture.block[i], (int) x, (int) y, (int) width, (int) height, null);
			}
		}

		//g.drawImage(Game.blockImage,(int) x,(int) y, (int) width, (int) height, null);

		/*g2d.setColor(Color.GREEN);
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsBottom());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());*/
	}


	/**
	 * 
	 * @return the rectangle of the whole object
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

	/**
	 * 
	 * @return the top part of the object, used for collision
	 */
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int) x + width / 4), (int) y, (int) width / 2, (int) (height / 2));
	}

	/**
	 * 
	 * @return the bottom part of the object, used for collision
	 */
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) ((int) x + width / 4), (int) ((int) y + (height / 2)), (int) width / 2, (int) (height / 2));
	}

	/**
	 * 
	 * @return the left part of the object, used for collision
	 */
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) ((int) y + height / 16), (int) width / 4, (int) ((int) height - ((height / 16) * 2)));
	}

	/**
	 * 
	 * @return the right part of the object, used for collision
	 */
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int) x + ((width / 4) * 3)), (int) ((int) y + height / 16), (int) width / 4, (int) ((int) height - (height / 16) * 2));
	}



}
