package scib.game.framework;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {

	/**
	 * The amount of ticks in between every frame change
	 */
	private int speed;
	
	/**
	 * The amount of frames
	 */
	private int frames;

	private int index = 0;
	private int count = 0;

	/**
	 * The current image which is being rendered
	 */
	private BufferedImage currentImage;
	
	/**
	 * The array containing the images
	 */
	private BufferedImage[] images;

	/**
	 * Initiates the animation
	 */
	public Animation(int speed, BufferedImage... args){
		this.speed = speed;
		images = new BufferedImage[args.length];

		images = args.clone();

		frames = args.length;
	}

	/**
	 * Runs the animation
	 */
	public void runAnimation(){
		index++;

		if(index > speed){
			index = 0;
			nextFrame();
		}
	}

	/**
	 * Changes the image in the animation
	 */
	private void nextFrame(){
		for(int i = 0; i < frames; i++){
			if(count == i){
				currentImage = images[i];
			}
		}

		count++;

		if(count > frames){
			count = 0;
		}
	}

	/**
	 * Draws the animation onto the screen
	 */
	public void drawAnimation(Graphics g, int x, int y, int width, int height){
		g.drawImage(currentImage, x, y, width, height, null);
	}

}
