package scib.game.framework;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {

	private int speed;
	private int frames;

	private int index = 0;
	private int count = 0;

	private BufferedImage currentImage;
	private BufferedImage[] images;

	/**
	 * Initiates the animation
	 * 
	 * @param speed speed of the animation
	 * @param args images to use in the animation
	 */
	public Animation(int speed, BufferedImage... args){
		this.speed = speed;
		images = new BufferedImage[args.length];

		images = args.clone();

		/*for(int i = 0; i < args.length; i++){
			images[i] = args[i];
		}*/

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
	 * 
	 * @param g graphics object to draw the animation
	 * @param x x co-ordiante to draw the animation
	 * @param y y co-ordinate to draw the animation
	 * @param width width of the animation
	 * @param height height of the animation
	 */
	public void drawAnimation(Graphics g, int x, int y, int width, int height){
		g.drawImage(currentImage, x, y, width, height, null);
	}

}
