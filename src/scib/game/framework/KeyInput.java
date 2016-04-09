package scib.game.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	Handler handler;
	GameObject tempObject;

	public KeyInput(Handler handler){
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();

		for(int i = 0; i < handler.objectList.size(); i++){
			tempObject = handler.objectList.get(i);

			if(tempObject.getId() == ObjectId.Player){
				if(key == KeyEvent.VK_D) tempObject.setVelX(5);
				if(key == KeyEvent.VK_A) tempObject.setVelX(-5);
				if(key == KeyEvent.VK_W && !tempObject.isJumping()){
					tempObject.setJumping(true);
					tempObject.setVelY(-10);
				}
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
	}

	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();

		for(int i = 0; i < handler.objectList.size(); i++){
			GameObject tempObject = handler.objectList.get(i);

			if(tempObject.getId() == ObjectId.Player){
				if(key == KeyEvent.VK_D && tempObject.getVelX() != -5) tempObject.setVelX(0);
				if(key == KeyEvent.VK_A && tempObject.getVelX() != 5) tempObject.setVelX(0);
				if(key == KeyEvent.VK_W){
					tempObject.setVelY(0);
				}
			}
		}
	}

}
