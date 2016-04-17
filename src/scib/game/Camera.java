package scib.game;

import scib.game.framework.GameObject;

public class Camera {
	protected float x, y;

	public Camera(float x, float y){

	}

	public void tick(GameObject player){

		setX(x -= player.getVelX());
		x = (float) (-player.getX() + (Game.WIDTH / 2));
		y = -player.getY() + (Game.HEIGHT / 2);
		
	}

	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}

	public void setX(float x){
		this.x = x;
	}
	public void setY(float y){
		this.y = y;
	}

}
