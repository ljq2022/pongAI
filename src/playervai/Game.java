package playervai;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {
	static boolean commence = false;
	Ball ball = new Ball(this);
	Racquet racquet = new Racquet(this, 350);
	AI ai = new AI(this, 5);

	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
			}
		});
		setFocusable(true);
		requestFocus();
	}
	
	private void move() {
		ball.move();
		racquet.move();
		ai.move();
	}
	public void playerOneWins() {
		JOptionPane.showMessageDialog(this, "Victory Player", "Player Wins!", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}
	public void playerTwoWins() {
		JOptionPane.showMessageDialog(this, "The robots are coming!", "Victory AI", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if(racquet.count >= 0 && ai.count >= 0 )
		{
			g.drawString(racquet.count.toString(), 5, getHeight() - 10);
			g.drawString(ai.count.toString(), 5, 10);
		}
		ball.paint(g2d);
		racquet.paint(g2d);
		ai.paint(g2d);
	}
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Mini Tennis");
		Game game = new Game();
		frame.setSize(300, 400);
		frame.setResizable(false);
		frame.add(game);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
	}
}