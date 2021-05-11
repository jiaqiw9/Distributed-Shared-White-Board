import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.*;

public class WhiteBoard extends JPanel {
	public ArrayList<ShapeColor> shapeList = new ArrayList<ShapeColor>();
	public File file = null;
	
	
	public WhiteBoard() {
		super();
		setBackground(Color.WHITE);
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
        	if(shapeColor.shape.equals("Line")) {
                g2.draw(new Line2D.Double(shapeColor.x1, shapeColor.y1, shapeColor.x2, shapeColor.y2));
        	} else if(shapeColor.shape.equals("Oval")) {
                g2.fill(new Ellipse2D.Double(shapeColor.x1, shapeColor.y1, shapeColor.x2, shapeColor.y2));
        	} else if(shapeColor.shape.equals("Rectangle")) {
                g2.fill(new Rectangle2D.Double(shapeColor.x1, shapeColor.y1, shapeColor.x2, shapeColor.y2));
        	}
        }
        g2.dispose();
    }
	
	public void draw(ShapeColor shapeColor) {
		shapeList.add(shapeColor);
		repaint();
	}
	
	
	public void readWhiteBoard() {
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
	
	public void writeWhiteBoard() {
		try {
			FileOutputStream fileOutput = new FileOutputStream(file);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(shapeList);
			objectOutput.close();
			repaint();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void emptyWhiteBoard() {
		shapeList = new ArrayList<ShapeColor>();
		file = null;
		repaint();
	}
}
