package scib.game.framework;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image){
		this.image = image;
	}
	
	/**
	 * Gets the image from the spritesheet
	 * 
	 * @param col column of the image
	 * @param row row of the image
	 * @param width width of the image
	 * @param height height of the image
	 * @return the image inside the spritesheet
	 */
	public BufferedImage getImage(int col, int row, int width, int height){
		return image.getSubimage((col * width) - width, (row * height) - height, width, height);
	}
	
}
