package scib.game.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import scib.game.Game;
import scib.game.Game.STATES;
import scib.game.framework.Animation;
import scib.game.framework.GameObject;
import scib.game.framework.Handler;
import scib.game.framework.ObjectId;
import scib.game.framework.Texture;
import scib.game.levels.Level1;

public class Player extends GameObject {

	/**
	 * Gravity of the player, how fast he falls to the ground
	 */
	private final float GRAVITY = 1.25f;

	/**
	 * Max speed of the player
	 */
	private final float MAX_SPEED = 15;

	private Texture texture = Game.getTexture();
	private Animation walkRight;
	private Animation walkLeft;
	private Animation hitRight;
	private Animation hitLeft;
	private int count;
	private Rectangle shovel = new Rectangle((int) ((int) x + width), (int) height / 2, (int) width / 3, (int) height / 8);

	public static int lives = 3;
	public static int points = 0;

	public static boolean hit = false;

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
		walkRight = new Animation(4, texture.player[3], texture.player[2], texture.player[1]);
		walkLeft = new Animation(4, texture.player[6], texture.player[7], texture.player[8]);
		hitRight = new Animation(4, texture.player[13], texture.player[12], texture.player[11]);
		hitLeft = new Animation(4, texture.player[15], texture.player[16], texture.player[17]);
	}

	/**
	 * Runs 60 times a second
	 * 
	 * Where the bulk of the code is contained for the {@link Player} class
	 */
	public void tick(LinkedList<GameObject> object){
		x += velX;
		y += velY;

		if(x < 0){
			x = 0;
		}

		if((y + height) > Level1.level.getHeight() * 48){
			loseLife();
		}

		if(velX > MAX_SPEED) velX = MAX_SPEED;
		if(velY > MAX_SPEED) velY = MAX_SPEED;

		if(falling || jumping) velY += GRAVITY;

		if(velX > 0){
			direction = Direction.Right;
		}else if(velX < 0){
			direction = Direction.Left;
		}

		if(hit){
			count++;
			if(count == 18){
				hit = false;
				count = 0;
			}
		}

		walkRight.runAnimation(); //Runs the walkRight animation
		walkLeft.runAnimation(); //Runs the walkLeft animation

		hitRight.runAnimation(); //Runs the hitRight animation
		hitLeft.runAnimation(); //Runs the hitLeft animation

		shovel.setLocation((int) ((int) x + width), (int) (y + (height / 2)));
		
		collision();
	}

	/**
	 * Checks when the player collides with another {@link GameObject}
	 */
	private void collision(){
		for(int i = 0; i < handler.objectList.size(); i++){

			GameObject tempObject = handler.objectList.get(i);

			if(tempObject.getId() == ObjectId.Block){

				/**
				 * Top collision
				 */
				if(getBoundsTop().intersects(tempObject.getBoundsBottom())){
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
				}else if(!getBoundsBottom().intersects(tempObject.getBoundsTop())){
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
			}else if(tempObject.getId() == ObjectId.BasicEnemy){
				if(getBoundsRight().intersects(tempObject.getBoundsLeft()) || getBoundsLeft().intersects(tempObject.getBoundsRight())){
					if(hit && !jumping){
						handler.removeObject(tempObject);
						points += 100;
					}else if(!hit){
						loseLife();
					}
				}else if(shovel.intersects(tempObject.getBounds())){
					if(hit && !jumping){
						handler.removeObject(tempObject);
						points += 100;
					}
				}else if(getBoundsBottom().intersects(tempObject.getBoundsTop())){
					handler.removeObject(tempObject);
					points += 100;
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
		
		if(!hit){
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
		}else if(hit){
			if(velX > 0){
				hitRight.drawAnimation(g, (int) x, (int) y, (int) width, (int) height);
			}else if(velX < 0){
				hitLeft.drawAnimation(g, (int) x, (int) y, (int) width, (int) height);
			}else{
				if(direction == Direction.Right){
					g.drawImage(texture.player[10], (int) x, (int) y, (int) width, (int) height, null);
				}else if(direction == Direction.Left){
					g.drawImage(texture.player[14], (int) x, (int) y, (int) width, (int) height, null);
				}
			}
		}

		/*g2d.setColor(Color.BLUE);
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsBottom());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());*/

		/*g2d.setColor(Color.RED);
		g2d.fill(shovel);*/
		
		g.setColor(Color.WHITE);
	}

	private void loseLife(){
		if(lives <= 1){
			Game.state = STATES.GAMEOVER;
		}else{
			lives--;
			handler.clearLevel();
			Game.loadLevel(Game.level);
		}
	}

}