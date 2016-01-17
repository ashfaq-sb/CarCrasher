package com.ashbmk.cargame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public class BasicEnemy extends GameObject {
	private ObjectHandler handler;
	private long timer2 = System.currentTimeMillis();
	private int dx = 1;
	private int dy = 1;
	int counter = 0;
	private boolean isSmashed;
	private BufferedImage smash,lights;
	private BufferedImage sCar2;

	public BasicEnemy(int x, int y, ID id, ObjectHandler handler) {
		super(x, y, id);
		velY = 1;
		
		this.handler = handler;
		new Random();
		// moving = false;
		try {
			this.sCar2 = ImageIO.read(Game.class.getResourceAsStream("truck.png"));
			this.smash = ImageIO.read(Game.class.getResourceAsStream("smash.png"));
			this.lights = ImageIO.read(Game.class.getResourceAsStream("lights.png"));
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, sCar2.getWidth(), sCar2.getHeight());
	}

	@Override
	public void tick() {
		// x += velX;
		// y += velY;
		// if(y<=0 || y>= Game.HEIGHT -32) velY *= -1;
		// if(x<=0 || x>= Game.WIDTH -16) velX *= -1;
		// handler.addObject(new Trail(x,y,ID.Trail,Color.red,16,16,0.01f,
		// handler));
		// y = (y + 2) / 2;

		// System.out.println(c);
		// counter++;

		// topDownMotion();
		collision();
		// choice= r.nextInt(2);
		if (System.currentTimeMillis() - timer2 > 2000) {
			timer2 += 35;
			// topDownMotion();
			Motion();
		}
	}

	public void topDownMotion() {

		y += dy;
		// System.out.println("asfsfaffassaf "+ counter +" "+ x + " Y " +
		// y);
		if (y > Game.HEIGHT + 100) {
			dy -= 2;
			// System.out.println("DY+=2 " + dy);

		} else if (y < -100) {
			dy++;
		}

	}

	public void Motion() {
		if ((x < 50)) {
			dx = 0;
			// x=50;
			dy = 2;
			y += -dy;
			//System.out.println("Up " + x + " " + y);
		} else if (y > Game.HEIGHT ) {
			dy = 0;
			dx = -2;
			x += dx;
			// System.out.println("Left " + x + " " + y);

		}
		if (x > Game.WIDTH - 130) {
			// dy=1;
			dx = 0;
			dy = -2;
			y -= dy;
			// System.out.println("Down" + x + " " + y);
		} else

		// x += dx;
			if ((y < Game.HEIGHT - 200) && (!(x < Game.WIDTH / 4)) || (y < -150)) {

			dy = 0;
			dx = -2;
			x -= dx;
			//System.out.println("Right " + x + " " + y);
		}

	}
	// }else{
	// x += dx;
	// }

	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.PlayerFire) {

				if (getBounds().intersects(tempObject.getBounds())) {

					handler.removeObject(this);
					Game.noEnemy = true;
					// handler.addObject(new BasicEnemy(200, 50, ID.BasicEnemy,
					// handler));

				}
			}
			if (tempObject.getId() == ID.Player) {

				if (getBounds().getX() == tempObject.getBounds().getX()) {// contains(,
																			// tempObject.getBounds().getY()))
																			// {

					// System.out.println("++++++++++++++++++++" +
					// tempObject.toString());
					//fire();
					

				}
				if (getBounds().intersects(tempObject.getBounds())) {
					//leftRightMotion();
					//topDownMotion();

					// collision();
					isSmashed = true;
					HUD.HEALTH -= 1 ;
					
					
				} else {
					isSmashed = false;
				}
			}

			if (tempObject.getId() == ID.BasicEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					// leftRightMotion();
					// topDownMotion();

					// collision();

				}
			}
		}

	}

	@Override
	public void render(Graphics g) {
		if (sCar2 != null) {
			g.drawImage(sCar2, x, y, null);
			
			if (isSmashed) {
				g.drawImage(smash,x, y+30, null);
				g.drawImage(lights,x+12, y-64, null);
				g.drawImage(lights,x+53, y-64, null);
			}

		}
	}

	public void fire() {

		handler.addObject(new EnemyFire(x - 5, y + 5, ID.EnemyFire, Color.orange, 16, 16, 0.001f, handler));

	}
	
	
}
