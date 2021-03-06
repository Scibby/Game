package scib.game.framework;

import java.awt.image.BufferedImage;

public class Texture {

	private static SpriteSheet ps, bs, es;
	private static BufferedImage blockSheet = null;
	private static BufferedImage playerSheet = null;
	private static BufferedImage enemySheet = null;

	public static BufferedImage[] block = new BufferedImage[4];
	public static BufferedImage[] player = new BufferedImage[18];
	public static BufferedImage[] finish = new BufferedImage[2];
	public static BufferedImage[] enemy = new BufferedImage[2];
	public static BufferedImage[] instruct = new BufferedImage[3];
	public static BufferedImage moon;

	private Texture(){
	}
	
	/**
	 * Gets textures from the {@link SpriteSheet}
	 */
	public static void getTextures() {

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

		block[0] = bs.getImage(1, 1, 32, 32); // Grass Left
		block[1] = bs.getImage(2, 1, 32, 32); // Grass Mid
		block[2] = bs.getImage(3, 1, 32, 32); // Grass Right
		block[3] = bs.getImage(4, 1, 32, 32); // Dirt

		player[0] = ps.getImage(1, 1, 32, 32); // Player idle right
		player[1] = ps.getImage(1, 2, 32, 32); // Player walk right 1
		player[2] = ps.getImage(2, 2, 32, 32); // Player walk right 2
		player[3] = ps.getImage(3, 2, 32, 32); // Player walk right 3
		player[4] = ps.getImage(1, 3, 32, 32); // Player jump right

		player[5] = ps.getImage(8, 1, 32, 32); // Player idle left
		player[6] = ps.getImage(6, 2, 32, 32); // Player walk left 1
		player[7] = ps.getImage(7, 2, 32, 32); // Player walk left 2
		player[8] = ps.getImage(8, 2, 32, 32); // Player walk left 3
		player[9] = ps.getImage(8, 3, 32, 32); // Player jump left

		player[10] = ps.getImage(1, 4, 32, 32); // Player hit idle right
		player[11] = ps.getImage(1, 5, 32, 32); // Player hit walk right 1
		player[12] = ps.getImage(2, 5, 32, 32); // Player hit walk right 2
		player[13] = ps.getImage(3, 5, 32, 32); // Player hit walk right 3

		player[14] = ps.getImage(8, 4, 32, 32); /// Player hit idle left
		player[15] = ps.getImage(6, 5, 32, 32); // Player hit walk left 1
		player[16] = ps.getImage(7, 5, 32, 32); // Player hit walk left 2
		player[17] = ps.getImage(8, 5, 32, 32); // Player hit walk left 3

		enemy[0] = es.getImage(1, 1, 32, 32); // Enemy image 1
		enemy[1] = es.getImage(1, 2, 32, 32); // Enemy image 2

	}

}
