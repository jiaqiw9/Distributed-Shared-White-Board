import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class WhiteBoard extends JPanel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ArrayList<ShapeColor> shapeList = new ArrayList<ShapeColor>();
	public File file = null;
	
	
	public WhiteBoard() {
		super();
		setBackground(Color.WHITE);
		
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
                
            }
		});
	}
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 700);
    }
	
	@Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(ShapeColor shapeColor: shapeList) {
            g2.setPaint(shapeColor.color);
        	if(shapeColor.shapeString.equals("Line")) {
                g2.draw(new Line2D.Double(shapeColor.x1, shapeColor.y1, shapeColor.x2, shapeColor.y2));
        	} else if(shapeColor.shapeString.equals("Oval")) {
                g2.fill(new Ellipse2D.Double(shapeColor.x1, shapeColor.y1, shapeColor.x2, shapeColor.y2));
        	} else if(shapeColor.shapeString.equals("Rectangle")) {
                g2.fill(new Rectangle2D.Double(shapeColor.x1, shapeColor.y1, shapeColor.x2, shapeColor.y2));
        	} else if(shapeColor.shapeString.equals("Text")) {
        		g2.drawString(shapeColor.text, (float) shapeColor.x1, (float) shapeColor.y1);
        	}
        }
        g2.dispose();
    }
	
	
	public void draw(ShapeColor shapeColor){
		shapeList.add(shapeColor);
		repaint();
	}
	
	
	public void readWhiteBoard(){
		try {
			FileInputStream fileInput = new FileInputStream(file);
			ObjectInputStream objectInput = new ObjectInputStream(fileInput);
			shapeList = (ArrayList<ShapeColor>) objectInput.readObject();
			objectInput.close();
			repaint();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeWhiteBoard(){
		try {
			FileOutputStream fileOutput = new FileOutputStream(file);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(shapeList);
			objectOutput.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void emptyWhiteBoard(){ 
		shapeList = new ArrayList<ShapeColor>();
		file = null;
		repaint();
	}
	
}

class ShapeColor implements Serializable {
	private static final long serialVersionUID = 1L;
	
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

	public String shapeString;
	public String text;
	public double x1, y1, x2, y2;
	public Color color;
	
	public ShapeColor(String shapeString, double x1, double y1, double x2, double y2, Color color) {
		this.shapeString = shapeString;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
	}
	
	public ShapeColor(String shapeString, String text, double x1, double y1, Color color) {
		this.shapeString = shapeString;
		this.text = text;
		this.x1 = x1;
		this.y1 = y1;
		this.color = color;
	}
}


