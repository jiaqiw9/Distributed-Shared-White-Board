import java.awt.*;
import java.io.*;

public class ShapeColor implements Serializable {
	public static final Color LIGHT_RED = new Color(255,51,51);
	public static final Color RED = new Color(255,0,0);
	public static final Color DARK_RED = new Color(204,0,0);
	public static final Color LIGHT_BLUE = new Color(51,153,255);
	public static final Color BLUE = new Color(0,0,255);
	public static final Color DARK_BLUE = new Color(0,0,204);
	public static final Color LIGHT_GREEN = new Color(0,255,51);
	public static final Color GREEN = new Color(0,204,0);
	public static final Color DARK_GREEN = new Color(0,153,0);
	public static final Color LIGHT_YELLOW = new Color(255,255,153);
	public static final Color YELLOW = new Color(255,255,0);
	public static final Color DARK_YELLOW = new Color(255,204,0);
	public static final Color ORANGE = new Color(255,102,0);
	public static final Color GREY = new Color(153,153,153);
	public static final Color BROWN = new Color(102,51,0);
	public static final Color PURPLE = new Color(102,0,153);
	public static final Color BLACK = new Color(0,0,0);

	public String shape;
	public double x1, y1, x2, y2;
	public Color color;
	
	public ShapeColor(String shape, double x1, double y1, double x2, double y2, Color color) {
		this.shape = shape;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
	}
}
