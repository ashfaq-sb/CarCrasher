package com.ashbmk.cargame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Player extends GameObject{

	@SuppressWarnings("unused")
	private BufferedImage smash,lights;
	private ObjectHandler handler;
	private BufferedImage img;
	public Player(int x, int y, ID id, ObjectHandler handler, BufferedImage img) {
		super(x, y, id);
		velX = 0;
		velY = 0;
		this.handler = handler;
		this.img =img;
		try {
			this.smash = ImageIO.read(Game.class.getResourceAsStream("smash.png"));
			this.lights = ImageIO.read(Game.class.getResourceAsStream("lights.png"));
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, img.getWidth(), img.getHeight());
	}

	public void tick() {
		x += velX;
		y += velY;

		x = Game.clamp(x, 50, Game.WIDTH - 130);
		y = Game.clamp(y, 0, Game.HEIGHT - 100);

		 collision();
	}

	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.EnemyFire) {
				
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH-=1;
				}
			}
			

			
		}

	}

	@Override
	public void render(Graphics g) {

//		g.setColor(Color.BLUE);
//		// g.fillRect(x, y, 32, 32);
//		g.fill3DRect(x, y, 40, 40, true);
		
		if(img != null){
			g.drawImage(img, x, y, null);
		
		if (Game.lightsOn) {
			
			g.drawImage(lights,x+12, y-64, null);
			g.drawImage(lights,x+53, y-64, null);
		}
		}
		//paintComponent(g);
	}

	public void fire() {

		handler.addObject(new PlayerFire(x + 5, y - 5, ID.PlayerFire, Color.red, 16, 16, 0.001f, handler));

	}
	
	
}
