package scib.game.framework;

import java.awt.image.BufferedImage;

public class Texture {

	SpriteSheet ps, bs, es;
	private BufferedImage blockSheet = null;
	private BufferedImage playerSheet = null;
	private BufferedImage enemySheet = null;
	
	public BufferedImage[] block = new BufferedImage[4];
	public BufferedImage[] player = new BufferedImage[10];
	public BufferedImage[] finish = new BufferedImage[2];
	public BufferedImage[] enemy = new BufferedImage[2];
	public BufferedImage moon;

	public Texture(){
		
		ImageLoader loader = new ImageLoader();
		
		blockSheet = loader.loadImage("/blockSpriteSheet.png");
		playerSheet = loader.loadImage("/playerSpriteSheet.png");
		enemySheet = loader.loadImage("/enemySpriteSheet.png");
		moon = loader.loadImage("/moon.png");
		
		finish[0] = loader.loadImage("/flagDown.png");
		finish[1] = loader.loadImage("/flagUp.png");
		
		bs = new SpriteSheet(blockSheet);
		ps = new SpriteSheet(playerSheet);
		es = new SpriteSheet(enemySheet);
		
		getTextures();
	}
	
	/**
	 * Gets textures from the {@link SpriteSheet}
	 */
	private void getTextures(){
		block[0] = bs.getImage(1, 1, 32, 32); //Grass Left
		block[1] = bs.getImage(2, 1, 32, 32); //Grass Mid
		block[2] = bs.getImage(3, 1, 32, 32); //Grass Right
		block[3] = bs.getImage(4, 1, 32, 32); //Dirt
		
		player[0] = ps.getImage(1, 1, 32, 32); //Player idle right
		player[1] = ps.getImage(1, 2, 32, 32); //Player walk right 1
		player[2] = ps.getImage(2, 2, 32, 32); //Player walk right 2
		player[3] = ps.getImage(3, 2, 32, 32); //Player walk right 3
		player[4] = ps.getImage(1, 3, 32, 32); //Player jump right
		
		player[5] = ps.getImage(8, 1, 32, 32); //Player idle left
		player[6] = ps.getImage(6, 2, 32, 32); //Player walk left 1
		player[7] = ps.getImage(7, 2, 32, 32); //Player walk left 2
		player[8] = ps.getImage(8, 2, 32, 32); //Player walk left 3
		player[9] = ps.getImage(8, 3, 32, 32); //Player jump left
		
		enemy[0] = es.getImage(1, 1, 32, 32); //Enemy image 1
		enemy[1] = es.getImage(1, 2, 32, 32); //Enemy image 2
		
	}
	
}
