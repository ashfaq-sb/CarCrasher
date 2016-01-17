package com.ashbmk.cargame;

import java.awt.Dimension;

import javax.swing.*;


@SuppressWarnings("serial")
public class Window extends JApplet {

	public Window(int width, int height, String title, Game game) {

		JFrame frame = new JFrame(title);

		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();// method in Game Class to be created
	}

}
