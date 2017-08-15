import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

public class Enemy extends Object
{
	private int X;
	private int Y;
	private double velocityX;
	private double velocityY;
	private int type;
	private BufferedImage image;
	
	public Enemy(int type)
	{
		this.type = type;
		X = 800;
		Random rand = new Random();
		Y = (rand.nextInt(10) + 1) * 60;
		velocityX = 0;
		velocityY = 0;
     	try {
     		image = ImageIO.read(new File("src/Graphics/enemy_" + Integer.toString(type) + ".png"));
        }
        catch (Exception ex) {
            System.out.println("No enemy_" + Integer.toString(type) + ".png!!");
        }
				
	}
	
	public void refresh(int targetX, int targetY)
	{
		X += velocityX;
		Y += velocityY;
		velocityY = (targetY - (Y + image.getHeight() / 2)) / 30;
		velocityX = ((X + image.getWidth() / 2) - targetX) > 500 ? -2 : 0;
	}
	
	public void setX(int XValue)
	{
		X = XValue;
	}
	public void setY(int YValue)
	{
		Y = YValue;
	}
	public int getX()
	{
		return X;
	}
	public int getY()
	{
		return Y;
	}
	public BufferedImage getImage()
	{
		return image;
	}
	public boolean collision(int x, int y)
	{
		// according to the meteorite type and check the location.
		if (Math.hypot((double)(X - x + image.getWidth() / 2), (double)(Y - y + image.getHeight() / 2)) < 
				Math.hypot((double)image.getWidth() / 2, (double)image.getHeight() / 2))
			return true;
		return false;
	}
}
