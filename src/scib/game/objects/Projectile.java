package scib.game.objects;

import java.awt.Color;
import java.awt.Graphics;

import scib.game.framework.GameObject;
import scib.game.framework.Handler;
import scib.game.framework.ObjectId;

public class Projectile extends GameObject{

	private int dir;

	public Projectile(float x, float y, float width, float height, ObjectId id, Handler handler, int dir){
		super(x, y, width, height, id, handler);
		this.dir = dir;
	}

	@Override
	/**
	 * Updates the position of the projectile
	 */
	public void tick(){

		if(dir == 0) x -= 10;
		if(dir == 1) x += 10;
		//y += 5;
		collision();
	}

	/**
	 * Where the code of the collision is contained.
	 */
	private void collision(){
		for(int i = 0; i < handler.objectList.size(); i++){

			GameObject tempObject = handler.objectList.get(i);

			if(tempObject.getId() == ObjectId.Block){

				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
				}

			}else if(tempObject.getId() == ObjectId.BasicEnemy){
				if(getBounds().intersects(tempObject.getBounds())){					
					handler.removeObject(this);
					handler.removeObject(tempObject);
					Player.points += 100;
				}
			}
		}
	}

	@Override
	/**
	 * Where the projectiles get rendered
	 */
	public void render(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect((int) x, (int) y, (int) width, (int) height);
	}

}
