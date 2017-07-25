import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;

public class mainScene extends Canvas
{
	// define the frame per second properties.
	public static Byte frameCount = 0;
	public static final Byte frameRate = 60;
	
	// define the angle and velocity.
	private Double angle;
	private Double velocity;
	// define the array list to store the bullet.
	private ArrayList<Bullet> bulletList;
	// initializer of mainScene
	public mainScene()
	{
		// initialize the properties.
		angle = 0.0;
		velocity = 0.0;
		bulletList = new ArrayList<Bullet>();
	}
	// main process
	public static void main(String argv[])
	{
		// use AWT to draw the picture.
		Frame frame  = new Frame("shoot");
		frame.addWindowListener(new Adapter());
		frame.setSize(800, 600);
		// create a canvas.
		mainScene canvas = new mainScene();
		// add the canvas to the frame.
		frame.add(canvas, BorderLayout.CENTER);
		frame.setVisible(true);
		// create a timer task to set the refresh function
		TimerTask refresh = new TimerTask() {
		    public void run() {
		    	// deal the frame per second calculation.
				++frameCount;
				if (frameCount > frameRate)
					frameCount = 1;
				// repaint the screen.
				frame.repaint();
				canvas.repaint();
		    }
		};
		// create a timer to count the FPS.
		Timer frameCounter = new Timer();
		// call the refresh function at fixed rate.
		frameCounter.scheduleAtFixedRate(refresh, 1000 / frameRate, 1000 / frameRate);
	}

	// override paint function to draw the picture 
	public void paint(Graphics graphics)
	{
		graphics.clearRect(0, 0, 800, 600);
		graphics.fillRect(10, 10, frameCount * 4, 10);
	}
}

//create a class inherit the WindowAdapter to override some specific function.
class Adapter extends WindowAdapter
{
	public void windowClosing(WindowEvent event)
	{
		System.exit(0);
	}
}