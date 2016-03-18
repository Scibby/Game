package scib.game.framework;

import java.awt.Graphics;
import java.util.LinkedList;

public class ObjectHandler {

	public LinkedList<GameObject> objectList = new LinkedList<GameObject>();
	
	private GameObject object;
	
	public void tick(){
		for(int i = 0; i < objectList.size(); i++){
			object = objectList.get(i);
			object.tick(objectList);
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i < objectList.size(); i++){
			object = objectList.get(i);
			object.render(g);
		}
	}
	
	public void addObject(GameObject object){
		this.objectList.add(object);
	}
	
	public void removeObject(GameObject object){
		this.objectList.remove(object);
	}
	
}
