package com.ashbmk.cargame;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable {

	public static final int WIDTH = 400, HEIGHT = 600;
	private Thread thread;
	private boolean threadRunning = false;
	static boolean noEnemy ;
	private HUD hud;
	private ObjectHandler handler;
	private Random r;
	private Road road;
	@SuppressWarnings("unused")
	private BufferedImage pCar,eCar,bg,sCar1,sCar2;
	private long timer = System.currentTimeMillis();
	@SuppressWarnings("unused")
	private AnEnemy enemy;
	public static boolean lightsOn ;


	// Constructor
	public Game() {
		try{
			this.bg =ImageIO.read(Game.class.getResourceAsStream("road3.png"));
			this.pCar =ImageIO.read(Game.class.getResourceAsStream("car1.png"));
			this.eCar =ImageIO.read(Game.class.getResourceAsStream("car2.png"));
//			this.sCar1 = ImageIO.read(Game.class.getResourceAsStream("scar1.png"));
//			this.sCar2 = ImageIO.read(Game.class.getResourceAsStream("scar2.png"));
			//road = new Road(-2, -4, ID.Road);
		}catch (Exception e){e.printStackTrace();}
		//enemy = new AnEnemy(200, 200, ID.AnEnemy,handler,sCar1,sCar2);
		r = new Random();
		handler = new ObjectHandler();
		//handler.addObject(new BasicEnemy(200, -160, ID.BasicEnemy, handler,eCar));
		this.addKeyListener(new KeyInput(handler));
		new Window(WIDTH, HEIGHT, "My Game", this);

		road = new Road(-2, -4, ID.Road,bg);
		handler.addObject(new Player(WIDTH / 2 + 32, HEIGHT / 2 + 32, ID.Player, handler,pCar));


		hud = new HUD();

		// get current system time in milliseconds and divide by 1000 to get
		// time in seconds

		// for(int i=0; i<r.nextInt(100); i++){

			handler.addObject(new BasicEnemy(200, -160, ID.BasicEnemy, handler));
			//handler.addObject(new AnEnemy(200, 200, ID.AnEnemy,handler));
			noEnemy = false;
		//System.out.println("gfhgfhgfgdgfdgfdgfdgfdgfdgfdgfdfgdf"+handler.object.size());
		// }

		Game.lightsOn = false;


	}

	// Start Method
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		threadRunning = true;
	}

	// Stop
	public synchronized void stop() {
		try {
			thread.join();
			threadRunning = false;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Run Method
	public void run() {
		// Main Game Loop
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOFTicks = 60.0; // seconds
		double ns = 100000000 / amountOFTicks; // convert to nano seconds
		double delta = 0;
		long timer = System.currentTimeMillis();
		@SuppressWarnings("unused")
		int frames = 0;
		while (threadRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (threadRunning) {
				render();
				frames++;

				if (System.currentTimeMillis() - timer > 1000) {


					timer += 15;
					//System.out.println("FramesPerSecond: " + frames);
					frames = 0;

				}

			}

		}

	}

	private void tick() {
		if((road != null) && (hud != null) ){
			hud.tick();
			road.tick();
			//enemy.tick();
		}
		handler.tick();

		if(HUD.HEALTH ==0){
			//System.exit(0);

		}

		if(noOfEnemies() == 0) {
			if (System.currentTimeMillis() - timer > 8000) {
				timer+=8000;
				for(int i=0; i< r.nextInt(6);i++){
			handler.addObject(new BasicEnemy(r.nextInt(WIDTH-100), r.nextInt(HEIGHT-200), ID.BasicEnemy, handler));
				//System.out.println("asdasdasdsffffffffffffffffffffffffffffff");
			noEnemy=false;
				}
			}

		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		//background(g);

		if((road != null) && (hud != null) ){
		road.render(g);
		//enemy.render(g);
			handler.render(g);

			hud.render(g);

			//System.out.println(road.getBounds());

		//g.drawImage(img, 120, 0, this);

		}




		g.dispose();
		bs.show();
	}

	public static int clamp(int var, int min, int max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	public void background(Graphics g) {

//		g.setColor(Color.black);
//		g.fillRect(0, 0, WIDTH, HEIGHT);
//
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

	}

	// Main Method
	public static void main(String[] args) {
		new Game();


	}

	public int noOfEnemies(){
		int noOfEnemies=0;
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.BasicEnemy) {
				noOfEnemies++;
				//System.out.println(noOfEnemies);
			}

		}
		return noOfEnemies;

	}
}
