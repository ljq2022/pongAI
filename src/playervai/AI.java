package playervai;

import java.awt.Graphics2D;
import java.awt.Rectangle;


public class AI {
	int x = 120;
	int impact = 200;
	int xa = 0;
	boolean scorePredict = false;
	boolean prevPositive = false;
	@SuppressWarnings("deprecation")
	public Integer count = new Integer(0);
	int y = 0;
	private Game game;

	public AI(Game game, int ycor) {
		y = ycor;
		this.game= game;
	}
	boolean collisionAI() {
		if(game.ai.getBoundaries().intersects(game.ball.getBoundaries()))
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
		if (x + xa >= game.getWidth()-60)
		{
			x = 224;
		}
		if (x + xa <= 0)
		{
			x = 2;
		}
		if (x + xa > 0 && x + xa < game.getWidth()-60)
		{
			if (game.ball.ya == 1 && x < (game.getWidth() / 2) - 30)
			{
				xa = 1;
				x = x + xa;
			}
			if(game.ball.ya == 1 && x > (game.getWidth() / 2) - 30)
			{
				xa = -1;
				x = x + xa;
			}
			if(game.ball.ya == 1 && x == (game.getWidth() / 2) - 30)
			{
				xa = 0;
			}
			if(game.ball.ya == 1 && game.ball.xa > 0)
			{
				prevPositive = true;
			}
			if(game.ball.ya == 1 && game.ball.xa < 0)
			{
				prevPositive = false;
			}
			if(game.racquet.getBoundaries().intersects(game.ball.getBoundaries()) || scorePredict == true)
			{
				impact = calculateImpact(game.ball.x, prevPositive);
				System.out.println("\nCalculate:\t" + impact + "\t" + game.getHeight());
			}
			if(game.ball.y == 16)
			{
				System.out.print("\tActual:\t" + game.ball.x);
			}
			if(game.ball.ya == -1 && x < impact)
			{
				xa = 1;
				x = x + xa;
				//System.out.println("Return Positive +1\t" + x + "\t" + game.ball.ya + "\t" + impact);
			}
			if(game.ball.ya == -1 && game.ai.x > impact)
			{
				xa = -1;
				x = x + xa;
				//System.out.println("Return Positive -1\t" + x + "\t" + game.ball.ya + "\t" + impact);
			}
			if(game.ball.ya == -1 && game.ball.xa == impact)
			{
				xa = 0;
				//System.out.println("Return Exact\t" + x + "\t" + game.ball.ya + "\t" + impact);
			}
		}
	}
			
	public int calculateImpact(int impact, boolean previouslyPositive)
	{
		impact = game.ball.x;
		boolean leftCollision = false;
		boolean rightCollision = false;
		int horozontialLoc = impact;
		if(scorePredict == false)
		{
			for(int i = 0; i < 303; i++)
			{
				if(horozontialLoc == 0)
				{
					leftCollision = true;
					rightCollision = false;
				}
				if(horozontialLoc == game.getWidth() - 30)
				{
					leftCollision = false;
					rightCollision = true;
				}
				if(rightCollision == false && leftCollision == false && previouslyPositive)
				{
					horozontialLoc++;
				}
				if(rightCollision == false && leftCollision == false && previouslyPositive == false)
				{
					horozontialLoc--;
				}
				if(rightCollision)
				{
					horozontialLoc--;
				}
				if(leftCollision)
				{
					horozontialLoc++;
				}
			}
		}
		else
		{
			for(int i1 = 0; i1 < 313; i1++)
			{
				if(horozontialLoc == 0)
				{
					leftCollision = true;
					rightCollision = false;
				}
				if(horozontialLoc == game.getWidth() - 30)
				{
					leftCollision = false;
					rightCollision = true;
				}
				if(rightCollision == false && leftCollision == false && previouslyPositive)
				{
					horozontialLoc++;
				}
				if(rightCollision == false && leftCollision == false && previouslyPositive == false)
				{
					horozontialLoc--;
				}
				if(rightCollision)
				{
					horozontialLoc--;
				}
				if(leftCollision)
				{
					horozontialLoc++;
				}
			}
			scorePredict = false;
		}
		return horozontialLoc;
	}
		
	public void paint(Graphics2D g) {
		g.fillRect(x, y, 60, 10);
	}
}

