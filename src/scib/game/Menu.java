package scib.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {

	public Menu(){
	}

	Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 75, 150, 150, 50);
	Rectangle quitButton = new Rectangle(Game.WIDTH / 2 - 75, 225, 150, 50);

	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;

		Font titleFont = new Font("Georgia", Font.BOLD, 50);
		g2d.setFont(titleFont);
		g2d.setColor(Color.WHITE);

		FontMetrics fm = g2d.getFontMetrics(titleFont);

		g2d.drawString(Game.TITLE, (Game.WIDTH / 2) - (fm.stringWidth(Game.TITLE) / 2), 50);

		g2d.draw(playButton);
		g2d.draw(quitButton);

		Font buttonFont = new Font("Georgia", Font.BOLD, 30);
		fm = g2d.getFontMetrics(buttonFont);
		g2d.setFont(buttonFont);

		g2d.drawString("Play", playButton.x + (playButton.width / 2) - (fm.stringWidth("Play") / 2), playButton.y + (playButton.height / 2) + 12);
	
		g2d.drawString("Quit", quitButton.x + (quitButton.width / 2) - (fm.stringWidth("Quit") / 2), quitButton.y + (quitButton.height / 2) + 12);
	
	
	}

}
