package scib.game;

import java.awt.Color;
import java.awt.image.BufferedImage;

import scib.game.framework.ImageLoader;
import scib.game.framework.ObjectId;
import scib.game.game.objects.Block;
import scib.game.game.objects.Finish;
import scib.game.game.objects.Player;

public class ParseLevelImage {

	static ImageLoader loader = new ImageLoader();

	static BufferedImage level = loader.loadImage("/Level1.png");

	public static void main(String[] args) {
		for(int i = 0; i < level.getHeight(); i++){
			for(int j = 0; j < level.getWidth(); j++){
				Color c = new Color(level.getRGB(j, i));

				if(c.getRGB() == Color.WHITE.getRGB()){
					System.out.println("handler.addObject(new Block(" + (j * 48) + ", " + (i * 48) + ", 48, 48, 3, ObjectId.Block, handler));");
				}

				if(c.getRGB() == new Color(128, 128, 128).getRGB()){
					System.out.println("handler.addObject(new Block(" + (j * 48) + ", " + (i * 48) + ", 48, 48, 1, ObjectId.Block, handler));");
				}

				if(c.getRGB() == Color.RED.getRGB()){
					System.out.println("handler.addObject(new Player(" + (j * 48) + ", " + (i * 48) + ", 48, 48, ObjectId.Player, handler));");
				}

				if(c.getRGB() == Color.BLUE.getRGB()){
					System.out.println("handler.addObject(new Finish(" + (j * 48) + ", " + (i * 48) + ", 144, 576, ObjectId.Finish, handler));");
				}
				
				if(c.getRGB() == Color.GREEN.getRGB()){
					System.out.println("handler.addObject(new BasicEnemy(" + (j * 48) + ", " + (i * 48) + ", 48, 48, ObjectId.BasicEnemy, handler));");
				}
				
				if(c.getRGB() == new Color(173,  173,  173).getRGB()){
					System.out.println("handler.addObject(new EnemyHitBox(" + (j * 48) + ", " + (i * 48) + ", 48, 48, ObjectId.EnemyHitBox, handler));");
				}
			}
		}
	}
}
