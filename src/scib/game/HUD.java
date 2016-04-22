package scib.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import scib.game.game.objects.Player;
import scib.game.levels.Level1;

public class HUD {
	
	public HUD(){
		
	}
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(new Color(10, 25, 112));
		g.fillRect(0, 0, Game.WIDTH - 128, 100);
		
		FontMetrics fm;
		Font hudFont = new Font("Georgia", Font.BOLD, 25);
		
		fm = g2d.getFontMetrics(hudFont);
		
		g2d.setFont(hudFont);
		g2d.setColor(Color.WHITE);

		g2d.drawString("Lives: " + Player.lives, 50, 50);
		g2d.drawString("Time Left: " + Level1.timeLeft, 200, 50);
		g2d.drawString("Points: " + Player.points, 450, 50);
		g2d.drawString("Level: " + Game.level, 640, 50);

	}
}
