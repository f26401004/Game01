import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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
	// define the bullet number.
	private static Byte bulletNum;
	// define the array list to store the bullet.
	private ArrayList<Bullet> bulletList;
	// define the fighter image
	private static BufferedImage image;
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
    	bulletList = new ArrayList<Bullet>();
     	try {
     		image = ImageIO.read(new File("src/Graphics/fighter.png"));
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
        graphics.clearRect(0, 0, 800, 600);
		// clear all the screen.
		graphics.clearRect(0, 0, 800, 640);
		graphics.drawImage(image, X, Y, null);
        repaint();
    }
    // override key press function to move the fighter.
    @Override
    public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode())
		{
		case KeyEvent.VK_UP:
			forceY = -20.0;
			break;
		case KeyEvent.VK_DOWN:
			forceY = 20.0;
			break;
		case KeyEvent.VK_LEFT:
			
			forceX = -20.0;
			break;
		case KeyEvent.VK_RIGHT:
			forceX = 20.0;
			break;
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
					frameCount = 1;
				// update the movement.
				moveUpdate();
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
	private static void moveUpdate()
	{
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
	}
}
