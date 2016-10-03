package scib.game.levels;

import java.awt.image.BufferedImage;

import scib.game.framework.Handler;
import scib.game.framework.ImageLoader;

public abstract class Level{

	public static int time = 0;
	public int timeLeft;
	public static BufferedImage level;
	
	public Level(Handler handler){
	}
}
