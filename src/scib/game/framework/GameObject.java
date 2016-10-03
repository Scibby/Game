package scib.game.framework;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * 
 * @author Scibby
 *
 */
public abstract class GameObject {

	protected float x, y;
	protected float width, height;
	protected ObjectId id;
	protected static Handler handler;
	protected float velX, velY;
	protected boolean falling = false;
	protected boolean jumping = false;
	
	/**
	 * @param x x co-ordinate to spawn the object
	 * @param y y co-ordinate to spawn the object
	 * @param width width of the object
	 * @param height height of the object
	 * @param id id of the object
	 * @param handler handler in order to deal with other objects
	 */
	public GameObject(float x, float y, float width, float height, ObjectId id, Handler handler){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		this.handler = handler;
	}

	/**
	 * Runs 60 times a second. Contains the bulk of the code
	 * 
	 * @param object list of all the objects
	 */
	public abstract void tick();
	
	/**
	 * Renders the graphics on the screen
	 * 
	 * @param g Graphics object
	 */
	public abstract void render(Graphics g);
	
	/**
	 * The whole player object
	 * 
	 * @return the rectangle of the whole object
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

	/**
	 * The top box of the object
	 * 
	 * @return the top part of the object, used for collision
	 */
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int) x + width / 8), (int) y, (int) (width / 8) * 6, (int) (height / 2));
	}
	
	/**
	 * The bottom box of the object
	 * 
	 * @return the bottom part of the object, used for collision
	 */
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) ((int) x + width / 8), (int) ((int) y + (height / 2)), (int) (width / 8) * 6, (int) (height / 2));
	}
	
	/**
	 * The Left box of the object
	 * 
	 * @return the left part of the object, used for collision
	 */
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) ((int) y + height / 8), (int) width / 8, (int) ((int) height - ((height / 8) * 2)));
	}
	
	/**
	 * Right box of the object
	 * 
	 * @return the right part of the object, used for collision
	 */
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int) x + ((width / 8) * 7)), (int) ((int) y + height / 8), (int) width / 8, (int) ((int) height - (height / 8) * 2));
	}

	/**
	 * @return x value of the object
	 */
	public float getX(){
		return x;
	}

	/**
	 * @return y value of the object
	 */
	public float getY(){
		return y;
	}

	/**
	 * @return width of the object
	 */
	public float getWidth(){
		return width;
	}

	/**
	 * @return height of the object
	 */
	public float getHeight(){
		return height;
	}

	/**
	 * @return velocity on the x-axis
	 */
	public float getVelX(){
		return velX;
	}

	/**
	 * @return velocity on the y-axis
	 */
	public float getVelY(){
		return velY;
	}
	
	/**
	 * @return whether the object is falling or not
	 */
	public boolean isFalling() {
		return falling;
	}

	/**
	 * @return whether the object is jumping or not
	 */
	public boolean isJumping() {
		return jumping;
	}
	
	/**
	 * @param x set x value of the object
	 */
	public void setX(float x){
		this.x = x;
	}

	/**
	 * @param y set the y value of the object
	 */
	public void setY(float y){
		this.y = y;
	}

	/**
	 * @param width set width value of the object
	 */
	public void setWidth(float width){
		this.width = width;
	}

	/**
	 * @param height set height value of the object
	 */
	public void setHeight(float height){
		this.height = height;
	}

	/**
	 * @param velX sets the velocity on the x-axis
	 */
	public void setVelX(float velX){
		this.velX = velX;
	}

	/**
	 * @param velY sets the velocity on the y-axis
	 */
	public void setVelY(float velY){
		this.velY = velY;
	}
	
	/**
	 * @param falling sets whether the object is falling or not
	 */
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	
	/**
	 * @param jumping sets whether the object is jumping or not
	 */
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	/**
	 * @return id of the object
	 */
	public ObjectId getId(){
		return id;
	}

}
