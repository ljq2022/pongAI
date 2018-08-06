package playervai;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class Ball {
	int x = 300;
	int y = 120;
	int xa = 1;
	int ya = 1;
	private Game game;
	public Ball(Game game) {
		this.game= game;
	}

	void move() {
		if (x + xa < 0)
			xa = 1;
		if (x + xa > game.getWidth() - 30)
			xa = -1;
		if (y + ya < 0)
			ya = 1;
		if (y + ya > game.getHeight() - 30)
			ya = -1;
		if(game.racquet.collisionracquet())
		{
			ya = -1;
		}
		if(game.ai.collisionAI())
		{
			ya = 1;
		}
		if(y == 0)
		{
			game.racquet.count++;
		}
		if(y == game.getHeight() - 30)
		{
			game.ai.count++;
			game.ai.scorePredict = true;
		}
		if(game.racquet.count == 5)
		{
			game.playerOneWins();
		}
		if(game.ai.count == 5)
		{
			game.playerTwoWins();
		}
		x = x + xa;
		y = y + ya;
	}
	public Rectangle getBoundaries()
	{
		return new Rectangle(x, y, 30, 30);
	}
	public void paint(Graphics2D g) {
		g.fillOval(x, y, 30, 30);
	}
}