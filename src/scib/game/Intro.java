package scib.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import scib.game.framework.Texture;

public class Intro{
	
	Texture texture = Game.getTexture();
	
	public Intro(){
	}
	
	private Rectangle continueButton = new Rectangle(Game.WIDTH / 2 - 75, Game.HEIGHT - 100, 150, 50);

	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.BLACK);
		
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		FontMetrics fm;
		Font font = new Font("Georgia", Font.BOLD, 25);
		
		fm = g2d.getFontMetrics(font);
		
		g2d.setFont(font);
		g2d.setColor(Color.WHITE);
		
		g2d.drawString("You are Fred.", (Game.WIDTH / 2) - (fm.stringWidth("You are Fred,") / 2), 30);
		g2d.drawString("You are a gravedigger at the local cemertery.", (Game.WIDTH / 2) - (fm.stringWidth("You are a gravedigger at the local cemertery.") / 2), 60);
		g2d.drawString("Today was bring your daughter to work day, so you brought your 5-year-old daughter Mary to work.",
				(Game.WIDTH / 2) - (fm.stringWidth("Today was bring your daughter to work day, so you brought your 5-year-old daughter Mary to work.") / 2), 90);
		g2d.drawString("Late at night, when you were about to go home, one of the recently burried bodies rose from the dead",
				(Game.WIDTH / 2) - (fm.stringWidth("Late at night, when you were about to go home, one of the recently burried bodies rose from the dead") / 2), 120);
		g2d.drawString("and snached your poor daughter!", (Game.WIDTH / 2) - (fm.stringWidth("and snached your poor daughter!") / 2), 150);
		g2d.drawString("It is your mission to venture through the vast graveyard and bring back your daughter!", (Game.WIDTH / 2) - (fm.stringWidth("It is your mission to venture through the vast graveyard and bring back your daughter!") / 2), 180);
		g2d.drawString("It won't be easy however, as the one who took your daughter has fled to one of the badly kept parts of the cemerery.",
				(Game.WIDTH / 2) - (fm.stringWidth("It won't be easy however, as the one who took your daughter has fled to one of the badly kept parts of the cemerery.") / 2), 210);
		g2d.drawString("And he has summoned a bunch of skeletons to try to stop you.", (Game.WIDTH / 2) - (fm.stringWidth("And he has summoned a bunch of skeletons to try to stop you.") / 2), 240);
		g2d.drawString("But with your trusty spade! You can conquer anything!", (Game.WIDTH / 2) - (fm.stringWidth("But with your trusty spade! You can conquer anything!") / 2), 270);
		
		g.drawImage(texture.instruct[0], 150, Game.HEIGHT - 168 - 200, 243, 168, null);
		g.drawImage(texture.instruct[1], Game.WIDTH - 243 - 150, Game.HEIGHT - 168 - 200, 243, 168, null);
		g.drawImage(texture.instruct[2], 655 , Game.HEIGHT - 168 - 200 + 84, 318, 52, null);
		
		g2d.draw(continueButton);
		g2d.drawString("Continue", continueButton.x + (continueButton.width / 2) - (fm.stringWidth("Continue") / 2), continueButton.y + (continueButton.height / 2) + 11);


	}
}
