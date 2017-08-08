import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Meteorite 
{
	private int X;
	private int Y;
	private double velocityX;
	private double velocityY;
	private BufferedImage image;
	
	public Meteorite(int YValue, int type)
	{
		X = 800;
		Y = YValue;
		velocityX = -3;
		velocityY = 0;
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
}
