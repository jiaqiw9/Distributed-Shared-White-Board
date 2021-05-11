import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientWindow extends JFrame{
	private WhiteBoard whiteboard = null;
	private JComboBox shapesCombo;
	private JComboBox colorsCombo;
	private JLabel firstLbl;
	private JLabel secondLbl;
	private JLabel thirdLbl;
	private JLabel fourthLbl;
	private JTextField firstArg;
	private JTextField secondArg;
	private JTextField thirdArg;
	private JTextField fourthArg;
	
	public ClientWindow(WhiteBoard whiteboard) {
		super("WhiteBoard - User");
		this.whiteboard = whiteboard;
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
        String[] shapes = {"Line", "Circle", "Oval", "Rectangle"};
        shapesCombo = new JComboBox(shapes);
        shapesCombo.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		JComboBox cb = (JComboBox) ae.getSource();
        		String shape = (String) cb.getSelectedItem();
        		switch (shape) {
        			case "Line":
        				thirdLbl.setText("x1");
        				fourthLbl.setText("x2");
        				fourthLbl.setVisible(true);
        				fourthArg.setVisible(true);
        				break;
        			case "Circle":
        				thirdLbl.setText("r");
        				fourthLbl.setVisible(false);
        				fourthArg.setVisible(false);
        				break;
        			case "Oval":
        				thirdLbl.setText("width");
        				fourthLbl.setText("height");
        				fourthLbl.setVisible(true);
        				fourthArg.setVisible(true);
        				break;
        			case "Rectangle":
        				thirdLbl.setText("width");
        				fourthLbl.setText("height");
        				fourthLbl.setVisible(true);
        				fourthArg.setVisible(true);
        				break;
        		}
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
    	c.weightx = 0.5;
    	c.gridx = 0;
    	c.gridy = 0;
        add(shapesCombo, c);
        
        firstLbl = new JLabel("x", SwingConstants.RIGHT);
    	c.weightx = 0.1;
    	c.gridx = 1;
        add(firstLbl, c);
        
        firstArg = new JTextField();
    	c.weightx = 0.5;
    	c.gridx = 2;
        add(firstArg, c);
        
        secondLbl = new JLabel("y", SwingConstants.RIGHT);
    	c.weightx = 0.1;
    	c.gridx = 3;
        add(secondLbl, c);
        
        secondArg = new JTextField();
    	c.weightx = 0.5;
    	c.gridx = 4;
        add(secondArg, c);
        
        thirdLbl = new JLabel("x2", SwingConstants.RIGHT);
    	c.weightx = 0.1;
    	c.gridx = 5;
        add(thirdLbl, c);
        
        thirdArg = new JTextField();
    	c.weightx = 0.5;
    	c.gridx = 6;
        add(thirdArg, c);
        
        fourthLbl = new JLabel("y2", SwingConstants.RIGHT);
    	c.weightx = 0.1;
    	c.gridx = 7;
        add(fourthLbl, c);
        
        fourthArg = new JTextField();
    	c.weightx = 0.5;
    	c.gridx = 8;
        add(fourthArg, c);
        
        String[] colors = {"Light Red", "Red", "Dark Red", "Light Blue", "Blue", "Dark Blue", "Light Green",
        		"Green", "Dark Green", "Light Yellow", "Yellow", "Dark Yellow", "Orange", "Grey", "Brown", "Purple", "Black"};
        colorsCombo = new JComboBox(colors);
    	c.gridx = 9;
        add(colorsCombo, c);
        
        
		JButton draw = new JButton("draw");
		draw.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		try {
        			String shape = (String) shapesCombo.getSelectedItem();
        			String shape_;
        			double x1, y1, x2, y2;
        			Color color = ShapeColor.BLACK;

        			x1 = Double.parseDouble(firstArg.getText());
    				y1 = Double.parseDouble(secondArg.getText());
        			if(shape.equals("Circle")) {
        				shape = "Oval";
        				x2 = Double.parseDouble(thirdArg.getText());
        				y2 = Double.parseDouble(thirdArg.getText());
        			} else {
        				x2 = Double.parseDouble(thirdArg.getText());
        				y2 = Double.parseDouble(fourthArg.getText());
        			}
        			switch((String) colorsCombo.getSelectedItem()) {
        				case "Light Red":
        					color = ShapeColor.LIGHT_RED;
        					break;
        				case "Red":
        					color = ShapeColor.RED;
        					break;
        				case "Dark Red":
        					color = ShapeColor.DARK_RED;
        					break;
        				case "Light Blue":
        					color = ShapeColor.LIGHT_BLUE;
        					break;
        				case "Blue":
        					color = ShapeColor.BLUE;
        					break;
        				case "Dark Blue":
        					color = ShapeColor.DARK_BLUE;
        					break;
        				case "Light Green":
        					color = ShapeColor.LIGHT_GREEN;
        					break;
        				case "Green":
        					color = ShapeColor.GREEN;
        					break;
        				case "Dark Green":
        					color = ShapeColor.DARK_GREEN;
        					break;
        				case "Light Yellow":
        					color = ShapeColor.LIGHT_YELLOW;
        					break;
        				case "Yellow":
        					color = ShapeColor.YELLOW;
        					break;
        				case "Dark Yellow":
        					color = ShapeColor.DARK_YELLOW;
        					break;
        				case "Orange":
        					color = ShapeColor.ORANGE;
        					break;
        				case "Grey":
        					color = ShapeColor.GREY;
        					break;
        				case "Brown":
        					color = ShapeColor.BROWN;
        					break;
        				case "Purple":
        					color = ShapeColor.PURPLE;
        					break;
        				case "Black":
        					color = ShapeColor.BLACK;
        					break;
        			}
        			whiteboard.draw(new ShapeColor(shape, x1, y1, x2, y2, color));
        		} catch(Exception e) {
        			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Dialog",
					        JOptionPane.ERROR_MESSAGE);
        		}
        	}
		});
    	c.weightx = 0.5;
    	c.gridx = 10;
        add(draw, c);
        
        
        c.weightx = 0.0;
    	c.gridwidth = GridBagConstraints.REMAINDER;
    	c.gridx = 0;
    	c.gridy = 1;
        add(whiteboard, c);
	}
	
}
