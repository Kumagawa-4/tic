package tic;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class source implements Runnable {

	private JFrame frame;

	private Thread thread;

	private Painter painter;

	public static boolean confirm = false;
	public drawn d = new drawn();
	
	public client c;

	public source() {
		frame = new JFrame();
		c = new client(JOptionPane.showInputDialog(frame, "Entrez server Ip:"),
				JOptionPane.showInputDialog(frame, "Entrez un Port:"));
		socket.ip = c.getIp();
		int Port = Integer.parseInt(c.getPort());
		socket.port = Port;
		System.out.println("The port you entered was invalid, please input another port: ");

		d.loadImages();

		painter = new Painter();
		painter.setPreferredSize(new Dimension(drawn.WIDTH, drawn.HEIGHT));

		if (!socket.connect())
			socket.initializeServer();
			frame.setSize(drawn.WIDTH, drawn.HEIGHT);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.setContentPane(painter);
		    frame.setTitle("Jeu");
		
		frame.setVisible(true);

		thread = new Thread(this, "Jeu");
		thread.start();
	}

	public void run() {
		while (true) {
			fonction.tick();
			painter.repaint();

			if (!fonction.verif && !confirm) {
				socket.listenForServerRequest();
			}

		}
	}

	private class Painter extends JPanel implements MouseListener {
		private static final long serialVersionUID = 1L;

		public Painter() {
			setFocusable(true);
			requestFocus();
			setBackground(Color.WHITE);
			addMouseListener(this);
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			drawn.render(g);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (confirm) {
				if (fonction.switche && !fonction.opposite && !fonction.won
						&& !fonction.enemyWin) {
							int y = e.getY() / drawn.length;int x = e.getX() / drawn.length;y *= 3;int position = x + y;
					if (fonction.zone[position] == null) {
						if (!fonction.verif)
							fonction.zone[position] = "X";
						else
							fonction.zone[position] = "O";
						fonction.switche = false;
						repaint();
						Toolkit.getDefaultToolkit().sync();

						try {
							socket.dos.writeInt(position);
							socket.dos.flush();
						} catch (IOException e1) {
							fonction.errors++;
							e1.printStackTrace();
						}
						fonction.checkWin();
						fonction.checkForTie();

					}
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

	}

}
