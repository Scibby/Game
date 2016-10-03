package scib.game.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;

import scib.game.Game;
import scib.game.framework.GameObject;
import scib.game.framework.Handler;
import scib.game.framework.ObjectId;
import scib.game.framework.Texture;

public class Finish extends GameObject{

	private boolean up = false;

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
	public void tick(){

		GameObject tempObject;

		for(int i = 0; i < handler.objectList.size(); i++){
			tempObject = handler.objectList.get(i);
			if(tempObject.getId() == ObjectId.Player){
				if(getBounds().intersects(tempObject.getBounds())){
					Game.level++;
					Game.loadLevel(Game.level);
				}
			}
		}

	}

	/**
	 * Where the rendering of the {@link Finish} is contained
	 */
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;

		g.drawImage(Texture.finish[1], (int) x, (int) y, (int) width, (int) height, null);


		/*g2d.setColor(Color.RED);
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsBottom());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());*/

	}


}
