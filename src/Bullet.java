import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Bullet 
{
	private int X;
	private int Y;
	private double velocityX;
	private double velocityY;
	private BufferedImage image;
	public Bullet()
	{
		X = 0;
		Y = 0;
		velocityX = 0;
		velocityY = 0;
     	try {
     		image = ImageIO.read(new File("src/Graphics/bullet.png"));
        }
        catch (Exception ex) {
            System.out.println("No bullet.png!!");
        }
	}
	public Bullet(int XValue, int YValue, double VXValue, double VYValue)
	{
		X = XValue;
		Y = YValue;
		velocityX = VXValue;
		velocityY = VYValue;
     	try {
     		image = ImageIO.read(new File("src/Graphics/bullet.png"));
        }
        catch (Exception ex) {
            System.out.println("No bullet.png!!");
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

