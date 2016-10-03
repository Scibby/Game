package scib.game.framework;

import java.awt.event.MouseListener;

import scib.game.Game;
import scib.game.Game.STATES;
import scib.game.objects.Player;

public class MouseInput implements MouseListener{

	public void mouseClicked(java.awt.event.MouseEvent e){
	}

	public void mouseEntered(java.awt.event.MouseEvent e){
	}

	public void mouseExited(java.awt.event.MouseEvent e){
	}

	/*
	 * 	Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 75, 150, 150, 50);
	 * 	Rectangle quitButton = new Rectangle(Game.WIDTH / 2 - 75, 225, 150, 50);
	 * 
	 *  Rectangle continueButton = new Rectangle(Game.WIDTH / 2 - 75, Game.HEIGHT - 100, 150, 50);
	 *  
	 *  Rectangle replayButton = new Rectangle(Game.WIDTH / 2 - 75, Game.HEIGHT - 200, 150, 50);
	 *	Rectangle quitButton = new Rectangle(Game.WIDTH / 2 - 75, Game.HEIGHT - 100, 150, 50);
	 *  
	 *  Rectangle replayButton = new Rectangle(Game.WIDTH / 2 - 75, Game.HEIGHT - 100, 150, 50);
	 */

	public void mousePressed(java.awt.event.MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		if(Game.state == STATES.MENU || Game.state == STATES.PAUSE){
			if(mx > Game.WIDTH / 2 - 75 && mx < Game.WIDTH / 2 - 75 + 150){
				if(my > 150 && my < 200){
					Game.state = STATES.GAME; 
				}
			}

			if(mx > Game.WIDTH / 2 - 75 && mx < Game.WIDTH / 2 - 75 + 150){
				if(my > 225 && my < 275){
					System.exit(1);
				}
			}
		}else if(Game.state == STATES.WIN){
			if(mx > Game.WIDTH / 2 - 75 && mx < Game.WIDTH / 2 - 75 + 150){
				if(my > Game.HEIGHT - 200 && my < Game.HEIGHT - 150){
					Game.state = STATES.GAME;
					Player.lives = 3;
					Player.points = 0;
					Game.level = 1;
				}
			}

			if(mx > Game.WIDTH / 2 - 75 && mx < Game.WIDTH / 2 - 75 + 150){
				if(my > Game.HEIGHT - 100 && my < Game.HEIGHT - 50){
					System.exit(1);
				}
			}
		}else if(Game.state == STATES.GAMEOVER){
			if(mx > Game.WIDTH / 2 - 75 && mx < Game.WIDTH / 2 - 75 + 150){
				if(my > Game.HEIGHT - 100 && my < Game.HEIGHT - 50){
					Game.state = STATES.GAME;
					Player.lives = 4;
					Player.points = 0;
					Game.level = 1;
				}
			}
		}
	}

	public void mouseReleased(java.awt.event.MouseEvent e){
	}

}
