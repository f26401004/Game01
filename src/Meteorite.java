import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Meteorite extends Object
{
	private int X;
	private int Y;
	private double velocityX;
	private double velocityY;
	private int type;
	private BufferedImage image;
	
	public Meteorite(int YValue, int type)
	{
		X = 800;
		Y = YValue;
		velocityX = -3;
		velocityY = 0;
		this.type = type;
     	try {
     		image = ImageIO.read(new File("src/Graphics/meteorite_" + Integer.toString(type) + ".png"));
        }
        catch (Exception ex) {
            System.out.println("No meteorite_" + Integer.toString(type) + ".png!!");
        }
	}
	public void refresh()
	{
		// refresh the properties.
		X = X + (int)velocityX;
		Y = Y + (int)velocityY;
		// deal the obstruct effect.
		if (velocityY > 0)
			velocityY = Math.max(velocityY - 0.1, 0.0);
		else
			velocityY = Math.min(velocityY + 0.1, 0.0);
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
