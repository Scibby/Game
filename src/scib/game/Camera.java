package scib.game;

import scib.game.framework.GameObject;

public class Camera {
	
	public float x, y;

	/**
	 * Initiates the camera object
	 * 
	 * @param x x co-ordinate to start the camera
	 * @param y y co-ordinate to start the camera
	 */
	public Camera(float x, float y){
	}

	/**
	 * Ticks the camera 60 times a second
	 * 
	 * @param player player that the camera is following
	 */
	public void tick(GameObject player){
		x = x -= player.getVelX();
		x = (float) (-player.getX() + (Game.WIDTH / 2));
		//y = -player.getY() + (Game.HEIGHT / 2);
		
	}

	

}
