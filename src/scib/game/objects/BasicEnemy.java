package scib.game.objects;

import java.awt.Graphics;

import scib.game.framework.Animation;
import scib.game.framework.GameObject;
import scib.game.framework.Handler;
import scib.game.framework.ObjectId;
import scib.game.framework.Texture;

public class BasicEnemy extends GameObject{
	
	/**
	 * Animation for the enemy walking
	 */
	private Animation enemyWalk;
	
	private final float GRAVITY = 1.5f;
	private final int MAX_SPEED = 15;
	
	public BasicEnemy(float x, float y, float width, float height, ObjectId id, Handler handler){
		super(x, y, width, height, id, handler);
		enemyWalk = new Animation(10, Texture.enemy[0], Texture.enemy[1]);
		velX = -2;
	}

	public void tick(){
		
		x += velX;
		y += velY;
		
		if(velX > MAX_SPEED) velX = MAX_SPEED;
		if(velY > MAX_SPEED) velY = MAX_SPEED;
		
		if(falling || jumping) velY += GRAVITY;
		
		enemyWalk.runAnimation();
		collision();
	}
	
	/**
	 * Checks when the enemy collides with another {@link GameObject}
	 */
	private void collision(){
		for(int i = 0; i < handler.objectList.size(); i++){

			GameObject tempObject = handler.objectList.get(i);

			if(tempObject.getId() == ObjectId.Block || tempObject.getId() == ObjectId.EnemyHitBox){

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
					velX = -2;
				}

				/**
				 * Left collision
				 */
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					velX = 2;
				}
			}
		}
	}

	/**
	 * Where the enemy gets rendered
	 */
	public void render(Graphics g){
		
		enemyWalk.drawAnimation(g, (int) x, (int) y, (int) width, (int) height);		

		/*g2d.setColor(Color.BLUE);
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsBottom());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());*/
		
	}

}
