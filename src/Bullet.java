import java.lang.Math;

public class Bullet 
{
	public static final double G = 9.8;
	public static double ForceCoeff = 0.2;
	
	private double X;
	private double Y;
	private double velocityX;
	private double velocityY;
	
	public Bullet()
	{
		X = 0;
		Y = 0;
		velocityX = 0;
		velocityY = 0;
	}
	public Bullet(double VXValue, double VYValue)
	{
		X = 0;
		Y = 0;
		velocityX = VXValue;
		velocityY = VYValue;
	}
	
	public void refresh()
	{
		X = X + velocityX;
		Y = Y + velocityY;
		velocityX = velocityX - ForceCoeff * Math.pow(Math.hypot(velocityX, velocityY), 3) * velocityX;
		velocityY = velocityY - G - ForceCoeff * Math.pow(Math.hypot(velocityX, velocityY), 3) * velocityY;
	}
}

