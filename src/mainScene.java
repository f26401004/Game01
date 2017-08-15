import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.Timer;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.io.*;

public class mainScene extends JComponent implements MouseListener, KeyListener
{
	// define the frame per second properties.
	public static Byte frameCount = 0;
	public static final Byte frameRate = 60;
	public static final Double obstruct = 1.2;
	// define the X and Y variable.
	private static Integer X;
	private static Integer Y;
	private static Double forceX;
	private static Double forceY;
	private static boolean shoot;
	private static Random rand;
	// define the HP
	private static Double HP;
	// define the bullet number.
	private static Byte bulletNum;
	// define the array list to store the bullet.
	private static ArrayList<Bullet> bulletList;
	// define the array list to store the meteorite.
	private static ArrayList<Meteorite> meteoriteList;
	// define the array list to store the enemy.
	private static Enemy enemy;
	// define the fighter image
	private static BufferedImage image;
	// define the animate back picture.
	private static Image aniBack;
	// define the white back picture.
	private static Image whiteBack;
	private static float effectCounter;
	// define timer
	private static int time;
	// define the phase.
	private Byte phase;
	// constructor of mainScene
    public mainScene(int x, int y)
    {
        super();
        this.setLocation(x, y);
        this.setSize(800, 640);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        // initialize the properties.
     	X = 0;
     	Y = 0;
     	forceX = 0.0;
     	forceY = 0.0;
    	bulletNum = 50;
    	shoot = false;
    	effectCounter = 8;
    	time = 0;
    	HP = 100.0;
    	bulletList = new ArrayList<Bullet>();
    	meteoriteList = new ArrayList<Meteorite>();
    	enemy = new Enemy(1);
    	rand = new Random();
     	try {
     		image = ImageIO.read(new File("src/Graphics/fighter.png"));
     		aniBack = Toolkit.getDefaultToolkit().createImage("src/Graphics/animateBack.gif");
     		whiteBack = ImageIO.read(new File("src/Graphics/whiteBack.png"));
        }
        catch (Exception ex) {
            System.out.println("No fighter.png!!");
        }
    }
    // override paint function to draw the picture 
    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
		// clear all the screen.
		graphics.clearRect(0, 0, 800, 640);
		// draw animate back.
		graphics.drawImage(aniBack, 0, 0, null);
		// set the font and color.
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        graphics.setColor(new Color(255, 255, 255));
		graphics.drawImage(image, X, Y, null);
		// draw the bullet.
		for (int i = 0 ; i < bulletList.size() ; ++i)
			graphics.drawImage(bulletList.get(i).getImage(), bulletList.get(i).getX(), bulletList.get(i).getY(), null);
		// draw the meteorite
		for (int i = 0 ; i < meteoriteList.size() ; ++i)
			graphics.drawImage(meteoriteList.get(i).getImage(), meteoriteList.get(i).getX(), meteoriteList.get(i).getY(), null);
		// draw the enemy
		if (enemy != null)
			graphics.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), null);
		// draw the HP bar
		graphics.drawString("HP                " + Integer.toString(HP.intValue()) + "/100", 20, 30);
		graphics.fillRect(20, 40, (int)(200 * HP / 100.0), 10);
		// draw the rest number of bullet.
		graphics.drawString("¤l¼u¼Æ¶q   " + Integer.toString(bulletNum), 650, 30);
		// draw the time
		int hour = (time / 3600);
		int min = (time % 3600) / 60;
		int sec = (time % 3600) % 60;
		graphics.drawString("time "+ Integer.toString(hour) + ":" + Integer.toString(min) + ":" + Integer.toString(sec), 650, 60);
		// deal the shoot effect.
		if (shoot && effectCounter > 0)
		{
			Graphics2D graphics2d = (Graphics2D) graphics;
			// set the opacity.
			graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, effectCounter / 60));
			effectCounter -= 0.8;
			graphics2d.drawImage(whiteBack, 0, 0, null);
			// if reach the end then turn off the flag and reset the properties.
			if (effectCounter == 0)
			{
				effectCounter = 8;
				shoot = false;
			}
		}
        repaint();
    }
    // override key press function to move the fighter.
    @Override
    public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode())
		{
		case KeyEvent.VK_UP:
			if (Y > 20)
				forceY = -20.0;
			break;
		case KeyEvent.VK_DOWN:
			if (Y < 530)
				forceY = 20.0;
			break;
		case KeyEvent.VK_LEFT:
			if (X > 20)
				forceX = -20.0;
			break;
		case KeyEvent.VK_RIGHT:
			if (X < 700)
				forceX = 20.0;
			break;
		case KeyEvent.VK_SPACE:
			if (bulletNum > 0)
			{
				bulletList.add(new Bullet(X + 20, Y + 12, 10.0, forceY / 5));
				--bulletNum;
				shoot = true;
				effectCounter = 30;
			}
		}
    }
    
	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	// main process
	public static void main(String argv[])
	{
		// create a frame.
        JFrame frame = new JFrame("fighter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
		// create the main scene
		mainScene scene = new mainScene(0, 0);
        // set the content of frame
        frame.getContentPane().add(scene);
        frame.pack();
        frame.setSize(800, 640);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
		// create a timer task to set the refresh function
		TimerTask refresh = new TimerTask() {
		    public void run() {
		    	// deal the frame per second calculation.
				++frameCount;
				if (frameCount > frameRate)
				{
					frameCount = 1;
					++time;
					// generate the enemy
					if (enemy.equals(null))
						enemy = new Enemy(1);
					// randomly read the map data
					try {
						readMap(rand.nextInt(26));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				// update the movement.
				moveUpdate();
				// check collision
				checkCollision();
				// repaint the screen.
				scene.repaint();
		    }
		};
		// create a timer to count the FPS.
		Timer frameCounter = new Timer();
		// call the refresh function at fixed rate.
		frameCounter.scheduleAtFixedRate(refresh, 1000 / frameRate, 1000 / frameRate);
	}
	// use Newton's second law of motion to simulate the movement of fighter.
	// update the bullet movement.
	private static void moveUpdate()
	{
		// update the movement of fighter
		X = X + (int)((forceX > 0 ? (forceX - obstruct) / 3 : (forceX + obstruct) / 3));
		Y = Y + (int)((forceY > 0 ? (forceY - obstruct) / 3 : (forceY + obstruct) / 3));
		if (forceX > 0)
			forceX = Math.max(forceX - obstruct, 0.0);
		else
			forceX = Math.min(forceX + obstruct, 0.0);
		if (forceY > 0)
			forceY = Math.max(forceY - obstruct, 0.0);
		else
			forceY = Math.min(forceY + obstruct, 0.0);
		for (int i = 0; i < bulletList.size(); ++i)
			bulletList.get(i).refresh();
		for (int i = 0; i < meteoriteList.size(); ++i)
			meteoriteList.get(i).refresh();		
		enemy.refresh(X + 25, Y + 25);		
		// lock the bulletList and check the bullet reach the boundary.
		synchronized (bulletList)
		{
			if (bulletList.size() > 0 && bulletList.get(0).getX() > 800)
				bulletList.remove(0);
		}
		// lock the meteoriteList and check the meteorite reach the boundary.
		synchronized (meteoriteList)
		{
			for (int i = 0; i < meteoriteList.size(); ++i)
			if (meteoriteList.get(i).getX() < -250)
				meteoriteList.remove(i);
		}
	}
	// read the map through the text file
	private static void readMap(int index) throws IOException
	{
		try {
		    int n = 0;
		    // use scanner to read the txt.
		    Scanner scan = new Scanner(System.in);
		    File file = new File("src/map.txt");
		    scan = new Scanner(file);
		    // jump to the target line
			while (scan.hasNextLine() && n < index)
			{
				scan.nextLine();
				++n;
			}
			n = 0;
		    while (scan.hasNextLine() && n < 10){
		    	String s = scan.nextLine();
		        for(int i = 0 ; i < s.length() ; i += 2)
		        {
		        	String ss;
		        	ss = s.substring(i, i + 1);	
		        	// if the map character is equal to M, then add the meteorite in random type.
		            if(ss.equals("M"))
		                meteoriteList.add(new Meteorite(n * 60, rand.nextInt(3) + 1));
		            ++n;
		        }
		    }
		  }
		  catch(Exception e){
		  }		
	}
	private static void checkCollision()
	{
		// use for loop to check all meteorite collision.
		for (int i = 0 ; i < meteoriteList.size() ; ++i)
		{
			if (meteoriteList.get(i).collision(X + 25, Y + 25))
				HP -= 0.5;
			for (int j = 0 ; j < bulletList.size() ; ++j)
			{
				if (meteoriteList.get(i).collision(bulletList.get(j).getX() + 12, bulletList.get(j).getY() + 12))
				{
					// lock the meteoriteList an remove the target.
					synchronized (meteoriteList)
					{
						meteoriteList.remove(i);
					}
				}
				if (enemy.collision(bulletList.get(j).getX() + 12 , bulletList.get(j).getY() + 12))
				{
					enemy.setX(800);
					enemy.setY((rand.nextInt(10) + 1) * 60);
				}
			}
		}
		
	}
}
