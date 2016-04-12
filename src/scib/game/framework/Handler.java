package scib.game.framework;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import scib.game.Game;
import scib.game.game.objects.Block;

public class Handler {

	public LinkedList<GameObject> objectList = new LinkedList<GameObject>();

	private GameObject object;

	/**
	 * runs 60 times every second
	 * ticks the other objects
	 */
	public void tick(){
		for(int i = 0; i < objectList.size(); i++){
			object = objectList.get(i);
			object.tick(objectList);
		}
	}

	/**
	 * starts the render method for the other objects
	 * @param g
	 */
	public void render(Graphics g){
		for(int i = 0; i < objectList.size(); i++){
			object = objectList.get(i);
			object.render(g);
		}
	}

	/**
	 * adds an object onto the screen
	 * 
	 * @param object
	 */
	public void addObject(GameObject object){
		this.objectList.add(object);
	}

	/**
	 * removes an object from the screen
	 * 
	 * @param object
	 */
	public void removeObject(GameObject object){
		this.objectList.remove(object);
	}

	/*public void createLevel(Handler handler){
		for(int i = 0; i < Game.WIDTH + 32; i += 32){
			handler.addObject(new Block(i, Game.HEIGHT - 24, 32, 32, ObjectId.Block, handler));
		}
	}*/

}
