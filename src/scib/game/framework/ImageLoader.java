package scib.game.framework;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageLoader {

	private BufferedImage image;
	
	/**
	 * Loads the image
	 * 
	 * @param path path to the image used
	 * @return the image object
	 */
	public BufferedImage loadImage(String path){
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
