package scib.game.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import scib.game.Game;
import scib.game.framework.Animation;
import scib.game.framework.GameObject;
import scib.game.framework.Handler;
import scib.game.framework.ObjectId;
import scib.game.framework.Texture;

public class Player extends GameObject {

	//ImageLoader loader = new ImageLoader();
	//BufferedImage image;

	/**
	 * Gravity of the player, how fast he falls to the ground
	 */
	private final float GRAVITY = 1.0f;
	
	/**
	 * Max speed of the player
	 */
	private final float MAX_SPEED = 10;
	
	private Texture texture;
	private Animation walkRight;
	private Animation walkLeft;
	
	
	private enum Direction{
		Right(),
		Left();
	}
	
	/**
	 * Direction the player is facing
	 */
	private Direction direction = Direction.Right;
	

	/**
	 * Creates a new player
	 * 
	 * @param x x co-ordinate to spawn the player
	 * @param y y co-ordinate to spawn the player
	 * @param width width of the player
	 * @param height height of the player
	 * @param id id of the player
	 * @param handler handler in order to deal with other objects
	 */
	public Player(float x, float y, float width, float height, ObjectId id, Handler handler) {
		super(x, y, width, height, id, handler);
		texture = Game.getTexture();
		walkRight = new Animation(1, texture.player[3], texture.player[2], texture.player[1]);
		walkLeft = new Animation(1, texture.player[6], texture.player[7], texture.player[8]);
	}

	/**
	 * Runs 60 times a second
	 * 
	 * Where the bulk of the code is contained for the {@link Player} class
	 */
	public void tick(LinkedList<GameObject> object){
		//image = loader.loadImage("/res/Player.png");
		x += velX;
		y += velY;

		if((y + height) > Game.level1.getHeight() * 48 + (height * 2)){
			System.exit(1);
		}

		if(velX > MAX_SPEED) velX = MAX_SPEED;
		if(velY > MAX_SPEED) velY = MAX_SPEED;


		if(falling || jumping){
			velY += GRAVITY;
		}

		if(velY >= 0){
			falling = false;
		}
		
		if(velX > 0){
			direction = Direction.Right;
		}else if(velX < 0){
			direction = Direction.Left;
		}
		
		walkRight.runAnimation(); //Runs the walkRight animation
		walkLeft.runAnimation(); //Runs the walkLeft animation
		
		collision();
	}

	/**
	 * Checks when the player collides with another {@link GameObject}
	 */
	public void collision(){
		for(int i = 0; i < handler.objectList.size(); i++){

			GameObject tempObject = handler.objectList.get(i);

			if(tempObject.getId() == ObjectId.Block){

				/**
				 * Top collision
				 */
				if(getBoundsTop().intersects(tempObject.getBounds())){
					setVelY(0);
					setY(tempObject.getY() + tempObject.getHeight());
				}

				/**
				 * Bottom collision
				 */
				if(getBoundsBottom().intersects(tempObject.getBoundsTop())){
					setVelY(0);
					falling = false;
					jumping = false;

					setY(tempObject.getY() - getHeight());
				}else if(!getBoundsBottom().intersects(tempObject.getBounds())){
					falling = true;
				}

				/**
				 * Right collision
				 */
				if(getBoundsRight().intersects(tempObject.getBounds())){
					//setVelX(0);
					setX(tempObject.getX() - getWidth());
				}

				/**
				 * Left collision
				 */
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					//setVelX(0);
					setX(tempObject.getX() + getWidth());
				}
			}
		}
	}

	/**
	 * Where the rendering of the {@link Player} is contained
	 */
	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g.setColor(Color.RED);
		//g.fillRect((int) x, (int) y, (int) width, (int) height);

		if(isJumping()){
			if(direction == Direction.Right){
				g.drawImage(texture.player[4], (int) x, (int) y, (int) width, (int) height, null);
			}else if(direction == Direction.Left){
				g.drawImage(texture.player[9], (int) x, (int) y, (int) width, (int) height, null);
			}
		}else{
			
			if(velX > 0){
				walkRight.drawAnimation(g, (int) x,(int) y, (int) width, (int) height);
			}else if(velX < 0){
				walkLeft.drawAnimation(g, (int) x, (int) y, (int) width, (int) height);
			}else{
				if(velX == 0){
					if(direction == Direction.Right){
						g.drawImage(texture.player[0], (int) x, (int) y, (int) width, (int) height, null);
					}else if(direction == Direction.Left){
						g.drawImage(texture.player[5], (int) x, (int) y, (int) width, (int) height, null);
					}
				}
			}
			
		}
		
		
		
		if(isJumping() && direction == Direction.Right){
			
		}else if(isJumping() && direction == Direction.Left){
			
		}else if(velX > 0){
			
		}else if(velX < 0){
			
		}else if(velX == 0 && direction == Direction.Right){
			
		}else if(velX == 0 && direction == Direction.Left){
			
		}
		
		
		
		
		/*g2d.setColor(Color.BLUE);
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsBottom());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());*/
		
		g.setColor(Color.WHITE);
	}
	
	/**
	 * 
	 * @return the rectangle of the whole object
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

	/**
	 * 
	 * @return the top part of the object, used for collision
	 */
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int) x + width / 4), (int) y, (int) width / 2, (int) (height / 2));
	}
	
	/**
	 * 
	 * @return the bottom part of the object, used for collision
	 */
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) ((int) x + width / 4), (int) ((int) y + (height / 2)), (int) width / 2, (int) (height / 2));
	}
	
	/**
	 * 
	 * @return the left part of the object, used for collision
	 */
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) ((int) y + height / 16), (int) width / 4, (int) ((int) height - ((height / 16) * 2)));
	}
	
	/**
	 * 
	 * @return the right part of the object, used for collision
	 */
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int) x + ((width / 4) * 3)), (int) ((int) y + height / 16), (int) width / 4, (int) ((int) height - (height / 16) * 2));
	}

}