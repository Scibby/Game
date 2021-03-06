package scib.game.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import scib.game.Game;
import scib.game.Game.STATES;
import scib.game.objects.Player;

public class KeyInput extends KeyAdapter{

	private Handler handler;

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
				if((key == KeyEvent.VK_W  || key == KeyEvent.VK_UP) && !tempObject.isJumping() && tempObject.isFalling()){ //Lets the player jump
					tempObject.setJumping(true);
					tempObject.setVelY(-23);
				}
				if(key == KeyEvent.VK_SPACE){
					((Player) tempObject).shoot = true;
				}
			}
		}

		/**
		 * Exits the game
		 */
		if(key == KeyEvent.VK_ESCAPE){
			if(Game.state != STATES.PAUSE){
				if(Game.state == STATES.GAME){
					Game.state = STATES.PAUSE;
				}
			}else{
				Game.state = STATES.GAME;
			}
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
				if((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && tempObject.getVelX() != -7) tempObject.setVelX(0);
				if((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && tempObject.getVelX() != 7) tempObject.setVelX(0);
				if(key == KeyEvent.VK_W){
					//tempObject.setVelY(0);
				}
				if(key == KeyEvent.VK_SPACE){
					((Player) tempObject).shoot = false;
					((Player) tempObject).count = 0;
				}
			}
		}
	}

}
