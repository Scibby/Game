package scib.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import scib.game.framework.Handler;
import scib.game.framework.ImageLoader;
import scib.game.framework.KeyInput;
import scib.game.framework.MouseInput;
import scib.game.framework.ObjectId;
import scib.game.framework.Texture;
import scib.game.game.objects.Block;
import scib.game.game.objects.Finish;
import scib.game.game.objects.Player;

/**
 * @author Andrew Sciberras
 */
public class Game extends Canvas implements Runnable{

	/**
	 * Width of window
	 */
	public static final int WIDTH = 1600;

	/**
	 * Height of window
	 */
	public static final int HEIGHT = 960;

	/**
	 * Dimentions of window
	 */
	public static final Dimension dimension = new Dimension(WIDTH, HEIGHT);

	/**
	 * Title of window
	 */
	public static final String TITLE = "Scibby Platformer";

	private boolean running = false;
	private Thread thread;
	private int fps, ticks;

	private static Texture texture;
	
	private Handler handler;
	public static BufferedImage level1;
	static Camera cam;
	
	Menu menu;
	Pause pause;

	public enum STATES{
		MENU(),
		GAME(),
		PAUSE();
	}

	public static STATES state = STATES.MENU;

	/**
	 * Runs the first time as soon as the game opens
	 */
	private void init(){
		handler = new Handler();
		texture = new Texture();

		cam = new Camera(0, 0);

		ImageLoader loader = new ImageLoader();

		level1 = loader.loadImage("/Level.png");
		
		this.addKeyListener(new KeyInput(handler));

		this.addMouseListener(new MouseInput());
		
		menu = new Menu();
		pause = new Pause();
		
		loadLevel(level1);

	}

	/**
	 * Starts the game loop
	 */
	private synchronized void start(){
		if(running) return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Stops the program
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
	 * Runs when game starts
	 * Forces the game to run at 60 cycles per second
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
				ticks = updates;
				fps = frames;
				updates = 0;
				frames = 0;
			}
		}
	}

	/**
	 * Runs 60 times every second
	 */
	private void tick(){

		if(state == STATES.GAME){
			handler.tick();
			
			for(int i = 0; i < handler.objectList.size(); i++) {
				if(handler.objectList.get(i).getId() == ObjectId.Player){
					cam.tick(handler.objectList.get(i));
				}
			}
		}
	}

	/**
	 * Renders graphics onto screen
	 */
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		
		Graphics2D g2d = (Graphics2D) g;
		
		if(state == STATES.GAME){
			
			g2d.setColor(new Color(44, 175, 219));
			g2d.fillRect(0, 0, getWidth(), getHeight());
			
			if(cam.getX() > 0){
				handler.render(g);
			}else{

				g2d.translate(cam.getX(), cam.getY());

				handler.render(g);

				g2d.translate(-cam.getX(), -cam.getY());
			}
		}else if(state == STATES.MENU){
			
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			menu.render(g);
		}else if(state == STATES.PAUSE){
			pause.render(g);
		}
		
		/*g.setColor(Color.WHITE);
		g.drawString(ticks + " ticks, " + fps + " fps", 50, 50);*/

		g.dispose();
		bs.show();
	}

	/**
	 * Given an image, this method loads a level depending on certain colours in the .png file
	 * 
	 * @param level the image of the level which is being loaded
	 */
	public void loadLevel(BufferedImage level){
		for(int i = 0; i < level.getHeight(); i++){
			for(int j = 0; j < level.getWidth(); j++){
				Color c = new Color(level.getRGB(j, i));

				if(c.getRGB() == Color.WHITE.getRGB()){
					handler.addObject(new Block(j * 48, i * 48, 48, 48, 3, ObjectId.Block, handler));
				}

				if(c.getRGB() == new Color(128, 128, 128).getRGB()){
					handler.addObject(new Block(j * 48, i * 48, 48, 48, 1, ObjectId.Block, handler));
				}

				if(c.getRGB() == Color.RED.getRGB()){
					handler.addObject(new Player(j * 48, i * 48, 48, 48, ObjectId.Player, handler));
				}

				if(c.getRGB() == Color.BLUE.getRGB()){
					handler.addObject(new Finish(j * 48, i * 48, 64, 128, ObjectId.Finish, handler));
				}
			}
		}
	}

	/**
	 * @return the texture object
	 */
	public static Texture getTexture(){
		return texture;
	}

	/**
	 * @return the camera object
	 */
	public static Camera getCamera(){
		return cam;
	}

	/**
	 * 
	 * main method, creates the frame and starts the thread
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
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}
}
