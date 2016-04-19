package scib.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class HUD {
	
	public HUD(){
		
	}
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		FontMetrics fm;
		Font hudFont = new Font("Georgia", Font.BOLD, 25);
		
		fm = g2d.getFontMetrics(hudFont);
		
		g2d.setFont(hudFont);
		g2d.setColor(Color.WHITE);

		g2d.drawString("Lives: ", 50, 50);
		g2d.drawString("Time Left: ", 200, 50);
		g2d.drawString("Points: " , 450, 50);
		g2d.drawString("Level: " , 640, 50);
		g2d.drawString("Coins: " , 800, 50);

	}
}
