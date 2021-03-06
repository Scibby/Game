package scib.game.objects;

import java.awt.Color;
import java.awt.Graphics;

import scib.game.framework.GameObject;
import scib.game.framework.Handler;
import scib.game.framework.ObjectId;
import scib.game.framework.Texture;

public class Block extends GameObject {

	private int blockId;

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

	public void tick(){

	}

	/**
	 * Where the rendering of the {@link Block} is contained
	 */
	public void render(Graphics g) {

		g.setColor(Color.WHITE);
		//g.drawRect((int) x, (int) y, (int) width, (int) height);

		for(int i = 0; i < Texture.block.length; i++){
			if(blockId == i){
				g.drawImage(Texture.block[i], (int) x, (int) y, (int) width, (int) height, null);
			}
		}

		/*if ((this.x > -cam.getX() + Game.WIDTH) || (this.x < -cam.getX() - 32) || (this.y > -cam.getY() + Game.HEIGHT) || (this.y < -cam.getY() - 32)) {

		}else{

			for(int i = 0; i < texture.block.length; i++){
				if(blockId == i){
					g.drawImage(texture.block[i], (int) x, (int) y, (int) width, (int) height, null);
				}
			}
		}*/


		//g.drawImage(Game.blockImage,(int) x,(int) y, (int) width, (int) height, null);

		/*g2d.setColor(Color.GREEN);
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsBottom());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());*/
	}
}
