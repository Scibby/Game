package scib.game;

import scib.game.framework.GameObject;

public class Camera {
	protected float x, y;

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
		setX(x -= player.getVelX());
		x = (float) (-player.getX() + (Game.WIDTH / 2));
		//y = -player.getY() + (Game.HEIGHT / 2);
		
	}

	/**
	 * @return x value of the camera
	 */
	public float getX(){
		return x;
	}

	/**
	 * @return y value of the camera
	 */
	public float getY(){
		return y;
	}

	/**
	 * @param x set x value of the camera
	 */
	public void setX(float x){
		this.x = x;
	}

	/**
	 * @param y set the y value of the camera
	 */
	public void setY(float y){
		this.y = y;
	}

}
