package com.ashbmk.cargame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Road extends GameObject {
	private int  my;
	private BufferedImage bg;
	//	public Road() {
//		this.x = 30;
//		this.y = 0;
//		this.width = 20;
//		this.height = 120;
//		this.vx = 1;
//		this.vy = 1;
//
//	}
	public Road(int x, int y, ID id,BufferedImage bg){
		super(x,y,id);
		this.my =-1;
		
		this.bg = bg;
//		try {
//			this.bg = ImageIO.read(new File("road2.png"));
//			//bg.getGraphics().
//			this.my =-1;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	

	public void tick() {
		// x += velX;
		// y += velY;
		//
		// if(x<=0 || x>= Game.WIDTH -16) velX = Game.WIDTH;
		// if(y<=0 || y>= Game.HEIGHT -16) velY =Game.HEIGHT;
		// handler.addObject(new Trail(x,y,ID.Trail,Color.red,16,16,0.01f,
		// handler));

		// int c = y;
		/*
		 * System.out.println(y);
		 * 
		 * y+= vy; // int c; if (y > Game.HEIGHT) {
		 * 
		 * // c--; // while (y != 0) { // y=-y; // this.revalidate(); //
		 * System.out.println(y); // // }
		 * 
		 * // y = Game.clamp(this.y, 0,this.y--);
		 * 
		 * // // y = c - 300;
		 * 
		 * vy--; System.out.println("asfsfaffassaf"+y); } else if(y < 0){ vy++;
		 * 
		 * }
		 */
		
//		y++;
//		
//		
//		
//		if (y > Game.HEIGHT) {
//			y=0;
//		}
		
//		System.out.println(y);
		y-=my;
        if(y>=Game.HEIGHT){
            y=0;
        }

	}

	public void render(Graphics g) {
		//set Background Color
//		g.setColor(Color.darkGray);
//		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		background(g);
//		g.setColor(Color.lightGray);
//		// left
//		//g.fillRect(x, y - 400, width, height);
//		//g.fillRect(x, y - 200, width, height);
//		
//		g.fillRect(x, y, width, height);
//		if(y>100){
//			g.fillRect(x, y-200, width, height);
//		}
//		//g.fillRect(x, y + 200, width, height);
		//g.fillRect(x, y + 400, width, height);
		// Right
//		g.fillRect(x + 565, y - 400, width, height);
//		g.fillRect(x + 565, y - 200, width, height);
		//g.fillRect(x + 565, y, width, height);
//		g.fillRect(x + 565, y + 200, width, height);
//		g.fillRect(x + 565, y + 400, width, height);
//		g.drawString("Score: ", Game.WIDTH / 2, 450);
//		// Center Road Lines
		// g.fillRect(x + 320, y - 300, 20, 320);
//		 g.fillRect(x + 320, y + 150, 20, 320);
		//g.dispose();
	}

	public void background(Graphics g) {

		
//		// Green/Grass areas
//		g.setColor(Color.ORANGE);
//		g.fillRect(610, 0, 32, 600);
//		g.setColor(Color.ORANGE);
//		g.fillRect(0, 0, 32, 600);
//
//		// Black lines
//		g.setColor(Color.black);
//		g.fillRect(595, 0, 20, 600);
//		g.setColor(Color.black);
//		g.fillRect(30, 0, 20, 600);
		
		
		// Background Image
		
		Graphics2D g2 =(Graphics2D) g;
		if (bg != null) {
			g2.drawImage(bg, x, y, null);
			if(y>=0);
	        {
				g2.drawImage(bg, x, y-Game.HEIGHT+10, null );
	        }

		}

	}


	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,bg.getWidth(),bg.getHeight());
	}



	@Override
	public void fire() {
		// TODO Auto-generated method stub
		
	}

	

}
