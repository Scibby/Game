package scib.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import scib.game.Game.STATES;

public class Win {

	public Win(){
		
	}

	Rectangle replayButton = new Rectangle(Game.WIDTH / 2 - 75, Game.HEIGHT - 200, 150, 50);
	Rectangle quitButton = new Rectangle(Game.WIDTH / 2 - 75, Game.HEIGHT - 100, 150, 50);
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(new Color(10, 25, 112));
		g.fillRect(0, 0, Game.WIDTH + 100, Game.HEIGHT + 100);
		
		FontMetrics fm;
		Font font = new Font("Georgia", Font.BOLD, 50);
		
		fm = g2d.getFontMetrics(font);
		
		g2d.setFont(font);
		g2d.setColor(Color.ORANGE);
		
		
		g2d.drawString("You win!!!", (Game.WIDTH / 2) - (fm.stringWidth("You win!!!") / 2), (Game.HEIGHT / 2) - fm.getHeight());

		Font buttonFont = new Font("Georgia", Font.BOLD, 30);
		fm = g2d.getFontMetrics(buttonFont);
		g2d.setFont(buttonFont);
		
		g2d.setColor(Color.WHITE);
		
		g2d.draw(replayButton);
		g2d.draw(quitButton);
		
		g2d.drawString("Replay", replayButton.x + (replayButton.width / 2) - (fm.stringWidth("Replay") / 2), replayButton.y + (replayButton.height / 2) + 12);
		
		g2d.drawString("Quit", quitButton.x + (quitButton.width / 2) - (fm.stringWidth("Quit") / 2), quitButton.y + (quitButton.height / 2) + 12);
	
		
	}
	
}
