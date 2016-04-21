package scib.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Gameover {

	public Gameover(){
		
	}
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH + 100, Game.HEIGHT + 100);
		
		Font gameOverFont = new Font("Georgia", Font.BOLD, 50);
		g.setFont(gameOverFont);
		g.setColor(Color.RED);
		
		FontMetrics fm = g2d.getFontMetrics(gameOverFont);

		g2d.drawString("GAME OVER", (Game.WIDTH / 2) - (fm.stringWidth("GAME OVER") / 2), (Game.HEIGHT / 2) - fm.getHeight());
		
		Font creditsFont = new Font("Georgia", Font.BOLD, 20);
		g.setFont(creditsFont);
		g.setColor(Color.RED);
		
		fm = g2d.getFontMetrics(creditsFont);
		
		g2d.drawString("Ideas by: Andrew Sciberras", (Game.WIDTH / 2) - (fm.stringWidth("deas by: Andrew Sciberras") / 2), (Game.HEIGHT / 2) + 20);
		g2d.drawString("Programming by: Andrew Sciberras", (Game.WIDTH / 2) - (fm.stringWidth("Programming by: Andrew Sciberras") / 2), (Game.HEIGHT / 2) + fm.getHeight() + 20);
		g2d.drawString("Graphics by: Andrew Sciberras with help by Adam Mifsud", 
				(Game.WIDTH / 2) - (fm.stringWidth("Graphics by: Andrew Sciberras with help by Adam Mifsud") / 2), 
				 (Game.HEIGHT / 2) + fm.getHeight() + fm.getHeight() + 20);
	}
}
