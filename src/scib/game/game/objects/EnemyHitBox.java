package scib.game.game.objects;

import java.awt.Graphics;
import java.util.LinkedList;

import scib.game.framework.GameObject;
import scib.game.framework.Handler;
import scib.game.framework.ObjectId;

public class EnemyHitBox extends GameObject{

	public EnemyHitBox(float x, float y, float width, float height, ObjectId id, Handler handler){
		super(x, y, width, height, id, handler);
	}

	@Override
	public void tick(LinkedList<GameObject> object){
		
	}

	@Override
	public void render(Graphics g){
		
	}

}
