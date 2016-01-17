package com.ashbmk.cargame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class KeyInput extends KeyAdapter {
	public ObjectHandler handler;

	// Constructor
	public KeyInput(ObjectHandler handler) {

		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				/// move this player // Key events for player 1
				if (key == KeyEvent.VK_W)
					tempObject.setVelY(-1);
				// System.out.println(key);
				if (key == KeyEvent.VK_S)
					tempObject.setVelY(1);
				if (key == KeyEvent.VK_D)
					tempObject.setVelX(1);
				if (key == KeyEvent.VK_A)
					tempObject.setVelX(-1);
				if (key == KeyEvent.VK_SPACE)
					tempObject.fire();
				if (key == KeyEvent.VK_H)
					horn();

				if (key == KeyEvent.VK_L) {
					if (Game.lightsOn) {
						Game.lightsOn = false;
					} else {
						Game.lightsOn = true;
					}
				}

			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				/// move this player // Key events for player 1
				if (key == KeyEvent.VK_W)
					tempObject.setVelY(0);
				if (key == KeyEvent.VK_S)
					tempObject.setVelY(0);
				if (key == KeyEvent.VK_D)
					tempObject.setVelX(0);
				if (key == KeyEvent.VK_A)
					tempObject.setVelX(0);
			}
		}
	}

	public void horn() {
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(Game.class.getResourceAsStream("horn.wav"));
			clip.open(inputStream);
			clip.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
