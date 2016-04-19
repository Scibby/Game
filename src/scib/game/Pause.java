package scib.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Pause {

	public Pause(){
	}
	
	Rectangle resumeButton = new Rectangle(Game.WIDTH / 2 - 75, 150, 150, 50);
	Rectangle quitButton = new Rectangle(Game.WIDTH / 2 - 75, 225, 150, 50);
	
	public void render(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;
		
		Font titleFont = new Font("Arial", Font.BOLD, 50);
		g2d.setFont(titleFont);
		g2d.setColor(Color.WHITE);

		FontMetrics fm = g2d.getFontMetrics(titleFont);

		g2d.drawString(Game.TITLE, (Game.WIDTH / 2) - (fm.stringWidth(Game.TITLE) / 2), 50);

		g2d.setColor(Color.RED);
		
		g2d.drawString("PAUSED", (Game.WIDTH / 2) - (fm.stringWidth("PAUSED") / 2), 100);
		
		g2d.setColor(Color.WHITE);
		g2d.draw(resumeButton);
		g2d.draw(quitButton);


		Font buttonFont = new Font("Arial", Font.BOLD, 30);
		fm = g2d.getFontMetrics(buttonFont);
		g2d.setFont(buttonFont);

		g2d.drawString("Resume", resumeButton.x + (resumeButton.width / 2) - (fm.stringWidth("Resume") / 2), resumeButton.y + (resumeButton.height / 2) + 12);
	
		g2d.drawString("Quit", quitButton.x + (quitButton.width / 2) - (fm.stringWidth("Quit") / 2), quitButton.y + (quitButton.height / 2) + 12);
	
	}
	
	
}
