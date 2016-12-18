package angrybird.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import angrybird.model.*;

public class AngryBirds extends Panel implements Runnable, MouseListener, MouseMotionListener {

	private double gravity;
	private int score;
	private boolean gameOver;
	private boolean selecting;
	Bird b1;
	Pig p1;
	private int mouseX;
	private int mouseY;
	Image buffer;

	public AngryBirds() {

		gravity = 0.1;
		score = 0;
		addMouseListener(this);
		addMouseMotionListener(this);
		init();
		new Thread(this).start();
	}

	void init() {
		b1 = new Bird();
        p1 = new Pig();
        gameOver = false;
        selecting = true;
        b1.setX(100);
        b1.setY(400);
        b1.setVelocityX(0);
        b1.setVelocityY(0);
        p1.setX(Math.random() * 500 + 200); // position aléatoire pour le cochon
        p1.setY(480);
        //message = "Choisissez l'angle et la vitesse.";
    }

	
	static double distance(double x1, double y1, double x2, double y2) {
		double dx = x1 - x2;
		double dy = y1 - y2;
		return Math.sqrt(dx * dx + dy * dy);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
		selecting=true;
		repaint();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseY = (int) e.getX();
		mouseY = (int) e.getY();
		repaint();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
	

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (gameOver) {
			init();
		} else if (selecting) {
			b1.setVelocityX((b1.getX() - mouseX) / 20.0);
			b1.setVelocityY((b1.getY() - mouseY) / 20.0);
			selecting = false;
		}
		repaint();

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		while (true) {
			// un pas de simulation toutes les 10ms
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
			}

			if (!gameOver && !selecting) {

				// moteur physique
				b1.setX(b1.getX() + b1.getVelocityX());
				b1.setY(b1.getY() + b1.getVelocityY());
				b1.setVelocityY(b1.getVelocityY() + gravity);

				// conditions de victoire
				if (distance(b1.getX(), b1.getY(), p1.getX(), p1.getY()) < 35) {
					stop();

					score++;
				} else if (b1.getX() < 20 || b1.getX() > 780 || b1.getY() < 0 || b1.getY() > 480) {
					stop();

				}

				// redessine
				repaint();
			}
		}

	}

	void stop() {
		b1.setVelocityX(0);
		b1.setVelocityY(0);
		gameOver = true;
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g2) {
		if (buffer == null)
			buffer = createImage(800, 600);
		Graphics2D g = (Graphics2D) buffer.getGraphics();

		// fond
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		// décor
		g.setColor(Color.BLACK);
		g.drawLine(0, 500, 800, 500);
		g.drawLine(100, 500, 100, 400);
//
//		// oiseau
		g.setColor(Color.RED);
		if (selecting)
			g.drawLine((int) b1.getX(), (int) b1.getY(), mouseX, mouseY); // montre l'angle et la vitesse
		g.fillOval((int) b1.getX() - 20, (int) b1.getY() - 20, 40, 40);
//
//		// cochon
		g.setColor(Color.GREEN);
		g.fillOval((int) p1.getX() - 20, (int) p1.getY() - 20, 40, 40);
//
//		// messages
//		g.setColor(Color.BLACK);
//
//		g.drawString("score: " + score, 20, 20);
//
//		 affichage à l'écran sans scintillement
		g2.drawImage(buffer, 0, 0, null);
	}

	// taille de la fenêtre
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}

}
