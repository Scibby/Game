package scib.game.framework;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	/**
	 * The image containing the whole spritesheet
	 */
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image){
		this.image = image;
	}
	
	/**
	 * Gets the image from the spritesheet
	 */
	public BufferedImage getImage(int col, int row, int width, int height){
		return image.getSubimage((col * width) - width, (row * height) - height, width, height);
	}
	
}
