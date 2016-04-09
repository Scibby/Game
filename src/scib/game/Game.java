package scib.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import scib.game.framework.Handler;
import scib.game.framework.ImageLoader;
import scib.game.framework.KeyInput;
import scib.game.framework.ObjectId;
import scib.game.game.objects.Block;
import scib.game.game.objects.Player;

/**
 * 
 * @author Andrew Sciberras
 *
 */
public class Game extends Canvas implements Runnable{

	public static final int WIDTH = 1024;
	public static final int HEIGHT = 1024;
	public static final int SCALE = 2;
	public static final Dimension dimension = new Dimension(WIDTH, HEIGHT);
	public final String TITLE = "2D Platformer";

	private boolean running = false;
	private Thread thread;

	Handler handler;
	BufferedImage image;

	private void init(){

		handler = new Handler();

		/*handler.addObject(new Player(100, 100, 32, 64, ObjectId.Player, handler));
		handler.addObject(new Block(100, 300, 32, 32, ObjectId.Block, handler));
		handler.addObject(new Block(132, 268, 32, 32, ObjectId.Block, handler));
		*/
		
		//handler.createLevel(handler);
		
		ImageLoader loader = new ImageLoader();
		
		image = loader.loadImage("/Level.png");
		
		loadLevel(image);


		this.addKeyListener(new KeyInput(handler));
	}


	/**
	 * 
	 * starts the program
	 */
	private synchronized void start(){
		if(running) return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * 
	 * stops the program
	 */
	private synchronized void stop(){
		if(!running) return;

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	/**
	 * 
	 * runs when program starts
	 */
	public void run() {

		init();

		long initialNanoTime = System.nanoTime();
		double ticksPerSecond = 1000000000 / 60;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long milli = System.currentTimeMillis();
		while(running){
			long now = System.nanoTime();
			delta += (now - initialNanoTime) / ticksPerSecond;
			initialNanoTime = now;
			if(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if(System.currentTimeMillis() - milli > 1000){
				milli += 1000;
				System.out.println(updates + " ticks, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}

	}

	/**
	 * 
	 * runs every tick
	 */
	private void tick(){
		handler.tick();
	}

	/**
	 * 
	 * renders on screen
	 */
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		handler.render(g);

		g.setColor(Color.RED);
		g.drawString("Hello World", 50, 50);

		g.dispose();
		bs.show();
	}
	
	public void loadLevel(BufferedImage image){
		
		for(int i = 0; i < image.getHeight(); i++){
			for(int j = 0; j < image.getWidth(); j++){
				Color c = new Color(image.getRGB(j, i));
				
				if(c.getRGB() == Color.WHITE.getRGB()){
					handler.addObject(new Block(j * 32, i * 32, 32, 32, ObjectId.Block, handler));
				}
				
				if(c.getRGB() == Color.RED.getRGB()){
					handler.addObject(new Player(j * 32, i * 32, 32, 64, ObjectId.Player, handler));
				}
			}
		}
		
		
	}
	
	
	

	/**
	 * 
	 * main method
	 */
	public static void main(String[] args) {
		Game game = new Game();

		game.setPreferredSize(dimension);
		game.setMaximumSize(dimension);
		game.setMinimumSize(dimension);

		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}
}
