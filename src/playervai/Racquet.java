package playervai;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;


public class Racquet {
	int x = 120;
	int xa = 0;
	@SuppressWarnings("deprecation")
	public Integer count = new Integer(0);
	int y = 0;
	private Game game;

	public Racquet(Game game, int ycor) {
		y = ycor;
		this.game= game;
	}
	boolean collisionracquet() {
		if(game.racquet.getBoundaries().intersects(game.ball.getBoundaries()))
		{
			return true;
		}
		return false;
	}
	public Rectangle getBoundaries()
	{
		return new Rectangle(x, y, 60, 10);
	}
	public void move() {
		if (x + xa > 0 && x + xa < game.getWidth()-60)
		{
			x = x + xa;
		}
			
	}

	public void paint(Graphics2D g) {
		g.fillRect(x, y, 60, 10);
	}

	public void keyReleased(KeyEvent e) {
		xa = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xa = -1;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = 1;
	}
}