
package tic;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.awt.Image;

public class drawn {
	private ImageIcon icoterrain;
	private static Image terrain;
	private ImageIcon icoX;
	private static Image X;
	private ImageIcon icoX2;
	private static Image X2;
	private ImageIcon icoO;
	private static Image O;
	private ImageIcon icoO2;
	private static Image O2;
	private static Font font = new Font("Verdana", Font.ITALIC, 30);
	private static String waitingString = "En attent";
	private static String communique = "En attent";
	private static String Win = "Tu a gagner!";
	private static String enemyWin = "AD a gagner!";
	private static String end = "End game.";
	public static final int WIDTH = 506;
	public static final int HEIGHT = 527;
	static int length = 160;

	static void render(Graphics g) {
		g.drawImage(terrain, 0, 0, null);
		if (fonction.opposite) {
			g.setColor(Color.RED);
			g.setFont(font);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			int stringWidth = g2.getFontMetrics().stringWidth(communique);
			g.drawString(communique, WIDTH / 2 - stringWidth / 2, HEIGHT / 2);
			return;
		}

		if (source.confirm) {
			for (int i = 0; i < fonction.zone.length; i++) {
				if (fonction.zone[i] != null) {
					if (fonction.zone[i].equals("X")) {
						if (fonction.verif) {
							g.drawImage(X, (i % 3) * length + 10 * (i % 3), (int) (i / 3) * length + 10 * (int) (i / 3),
									null);
						} else {
							g.drawImage(X2, (i % 3) * length + 10 * (i % 3),
									(int) (i / 3) * length + 10 * (int) (i / 3), null);
						}
					} else if (fonction.zone[i].equals("O")) {
						if (fonction.verif) {
							g.drawImage(O2, (i % 3) * length + 10 * (i % 3),
									(int) (i / 3) * length + 10 * (int) (i / 3), null);
						} else {
							g.drawImage(O, (i % 3) * length + 10 * (i % 3), (int) (i / 3) * length + 10 * (int) (i / 3),
									null);
						}
					}
				}
			}
			if (fonction.won || fonction.enemyWin) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(10));
				g.setColor(Color.BLACK);
				g.drawLine(fonction.etape1 % 3 * length + 10 *
						fonction.etape1 % 3 + length / 2,
						(int) (fonction.etape1 / 3) * length + 10 * (int) (fonction.etape1 / 3) + length / 2,
						fonction.etape2 % 3 * length + 10 *
								fonction.etape2 % 3 + length / 2,
						(int) (fonction.etape2 / 3) * length + 10 * (int) (fonction.etape2 / 3) + length / 2);

				g.setColor(Color.RED);
				g.setFont(font);
				if (fonction.won) {
					int stringWidth = g2.getFontMetrics().stringWidth(Win);
					g.drawString(Win, WIDTH / 2 - stringWidth / 2, HEIGHT / 2);
				} else if (fonction.enemyWin) {
					int stringWidth = g2.getFontMetrics().stringWidth(enemyWin);
					g.drawString(enemyWin, WIDTH / 2 - stringWidth / 2, HEIGHT / 2);
				}
			}
			if (fonction.tie) {
				Graphics2D g2 = (Graphics2D) g;
				g.setColor(Color.BLACK);
				g.setFont(font);
				int stringWidth = g2.getFontMetrics().stringWidth(end);
				g.drawString(end, WIDTH / 2 - stringWidth / 2, HEIGHT / 2);
			}
		} else {
			g.setColor(Color.RED);
			g.setFont(font);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			int stringWidth = g2.getFontMetrics().stringWidth(waitingString);
			g.drawString(waitingString, WIDTH / 2 - stringWidth / 2, HEIGHT / 2);
		}

	}

	public void loadImages() {
		icoterrain = new ImageIcon("./image/board2.jpg");
		terrain = icoterrain.getImage();
		icoX = new ImageIcon("./image/crane2.jpg");
		X = icoX.getImage();
		icoX2 = new ImageIcon("./image/crane2.jpg");
		X2 = icoX2.getImage();
		icoO = new ImageIcon("./image/red2.gif");
		O = icoO.getImage();
		icoO2 = new ImageIcon("./image/red2.gif");
		O2 = icoO2.getImage();
	}

}
