package com.ashbmk.cargame;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class EnemyFire extends GameObject {

	private ObjectHandler handler;
	private float alpha = 1.0f;
	private Color color;
	private int height;
	private int width;
	private float life;

	public EnemyFire(int x, int y, ID id, Color color, int width, int height, float life, ObjectHandler handler) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
	}

	public void tick() {
		
			y++;
		
		if (alpha > life) {
			alpha -= life;
		} else {
			handler.removeObject(this);
		}
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		g.setColor(color);
		// g.fillRect(x, y, width, height);
		g.fillOval(x, y, width, height);

		g2d.setComposite(makeTransparent(1));
	}

	AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return AlphaComposite.getInstance(type, alpha);
	}

	public Rectangle getBounds() {
		
		return new Rectangle(x,y,width,height);
	}

	@Override
	public void fire() {
		// TODO Auto-generated method stub
		
	}

	
}
