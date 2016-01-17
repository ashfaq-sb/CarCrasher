package com.ashbmk.cargame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class AnEnemy extends BasicEnemy {

	boolean check = false;
	private long timer = System.currentTimeMillis();
	private BufferedImage img, img1;
	@SuppressWarnings("unused")
	private int w, h;

	public AnEnemy(int x, int y, ID id, ObjectHandler handler) {
		super(x, y, id, handler);
		try {
			this.img = ImageIO.read(Game.class.getResourceAsStream("scar1.png"));
			this.img1 = ImageIO.read(Game.class.getResourceAsStream("scar2.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	public void tick() {

		if (System.currentTimeMillis() - timer > 2000) {
			timer += 35;
			if (check) {
				check = false;
			} else {
				check = true;
			}
		}
	}

	public void render(Graphics g2d) {
		//Graphics2D g2d = (Graphics2D) g;

		if (check) {
			g2d.drawImage(img, x + 2, y, null);

		} else {
			g2d.drawImage(img1, x, y, null);
		}
	}


	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return super.getBounds();
	}


	@Override
	public void Motion() {
		// TODO Auto-generated method stub
		super.Motion();
	}


	@Override
	public void collision() {
		// TODO Auto-generated method stub
		super.collision();
	}
}
