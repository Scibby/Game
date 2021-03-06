package scib.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import scib.game.framework.Handler;
import scib.game.framework.KeyInput;
import scib.game.framework.MouseInput;
import scib.game.framework.ObjectId;
import scib.game.framework.Texture;
import scib.game.levels.Level;
import scib.game.levels.Level1;
import scib.game.levels.Level2;
import scib.game.objects.Player;

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
	 * Dimensions of window
	 */
	public static final Dimension dimension = new Dimension(WIDTH, HEIGHT);

	/**
	 * Title of the game
	 */
	public static final String TITLE = "Platformer";

	/**
	 * If the program is running or not
	 */
	private boolean running = false;
	private Thread thread;
	private int fps, ticks;

	private int tickCount;
	
	/**
	 * The current level of the Game
	 */
	public static int level;

	private static Handler handler;
	private static Camera cam;

	/*
	 * Instances of the various states
	 */
	private Menu menu;
	private Pause pause;
	private HUD hud;
	private Gameover gameover;
	private Win win;

	/**
	 * Array of the levels
	 */
	public static Level[] levels = new Level[2];

	/**
	 * Enum of the states
	 */
	public enum STATES{
		MENU(),
		GAME(),
		PAUSE(),
		WIN(),
		GAMEOVER();
	}

	/**
	 * The current state
	 */
	public static STATES state = STATES.MENU;

	/**
	 * Runs the first time as soon as the game opens
	 */
	private void init(){
		handler = new Handler();

		Texture.getTextures();
		
		cam = new Camera(0, 0);

		this.addKeyListener(new KeyInput(handler));

		this.addMouseListener(new MouseInput());

		level = 1;
		loadLevel(level);

		menu = new Menu();
		pause = new Pause();
		hud = new HUD();
		gameover = new Gameover();
		win = new Win();
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

			tickCount++;
			if(tickCount >= 60){
				tickCount = 0;
				Level.time++;
			}
			
			if(Level.time >= 200){
				Player.loseLife();
			}

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

			g2d.setColor(new Color(10, 25, 112));
			g2d.fillRect(0, 0, getWidth(), getHeight());

			g2d.setColor(Color.BLACK);

			if(cam.x > 0){
				handler.render(g);
				hud.render(g);
				//g.drawImage(Texture.moon, getWidth() - Texture.moon.getWidth() * 2, 0, Texture.moon.getWidth() * 2, Texture.moon.getHeight() * 2, null);
			}else{

				hud.render(g);
				//g.drawImage(Texture.moon, getWidth() - Texture.moon.getWidth() * 2, 0, Texture.moon.getWidth() * 2, Texture.moon.getHeight() * 2, null);

				g2d.translate(cam.x, cam.y);

				handler.render(g);

				g2d.translate(-cam.x, -cam.y);
			}
		}else if(state == STATES.MENU){

			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());

			menu.render(g);
		}else if(state == STATES.PAUSE){
			pause.render(g);
		}else if(state == STATES.GAMEOVER){
			gameover.render(g);
		}else if(state == STATES.WIN){
			win.render(g);
		}

		/*g.setColor(Color.WHITE);
		g.drawString(ticks + " ticks, " + fps + " fps", 50, 50);*/

		g.dispose();
		bs.show();
	}

	/**
	 * Loads the level from a seperate class
	 * 
	 * @param level the level which is being loaded
	 */
	public static void loadLevel(int level){
		switch(level){
		case 1:
			levels[1 - 1] = new Level1(handler);
			return;
		case 2:
			handler.clearLevel();
			levels[2 - 1] = new Level2(handler);
			return;
		default:
			state = STATES.WIN;
		}

	}

	/**
	 * @return the camera object
	 */
	public static Camera getCamera(){
		return cam;
	}

	/**
	 * The main method, creates the frame and starts the thread
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
