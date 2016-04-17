package scib.game.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	Handler handler;

	public KeyInput(Handler handler){
		this.handler = handler;
	}

	/**
	 * Runs when a key is pressed
	 */
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();

		for(int i = 0; i < handler.objectList.size(); i++){
			GameObject tempObject = handler.objectList.get(i);

			if(tempObject.getId() == ObjectId.Player){
				if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) tempObject.setVelX(7); //Moves the player right
				if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) tempObject.setVelX(-7); //Moves the player left
				if((key == KeyEvent.VK_W  || key == KeyEvent.VK_UP || key == KeyEvent.VK_SPACE) && !tempObject.isJumping() && tempObject.isFalling()){ //Lets the player jump
					tempObject.setJumping(true);
					tempObject.setVelY(-17);
				}
			}
		}
		
		/**
		 * Exits the game
		 */
		if(key == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
	}

	/**
	 * Runs when a key is released
	 */
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();

		for(int i = 0; i < handler.objectList.size(); i++){
			GameObject tempObject = handler.objectList.get(i);

			if(tempObject.getId() == ObjectId.Player){
				if((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && tempObject.getVelX() != -5) tempObject.setVelX(0);
				if((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && tempObject.getVelX() != 5) tempObject.setVelX(0);
				if(key == KeyEvent.VK_W){
					//tempObject.setVelY(0);
				}
			}
		}
	}

}
