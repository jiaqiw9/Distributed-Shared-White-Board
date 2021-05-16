import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public WhiteBoard localWhiteBoard = new WhiteBoard();
	public RemoteWhiteBoard remoteWhiteBoard;
	public ChatBox chatBox;
	
	private JComboBox<String> shapeSelection;
	private JComboBox<String> widthSelection;
	private JComboBox<String> heightSelection;
	private JComboBox<String> colorSelection;
	private JLabel shapeLbl;
	private JLabel widthLbl;
	private JLabel heightLbl;
	private JLabel colorLbl;
	private JTextField textInput;
	
	private JTextField chatInput;
	private JButton sendButton;
	
	public ClientWindow(RemoteWhiteBoard remoteWhiteBoard, int userID, String username) {
		super("WhiteBoard - " + username);
		this.remoteWhiteBoard = remoteWhiteBoard;
		
		
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints c;
		String[] shapes = {"Line", "Circle", "Oval", "Rectangle", "Text"};
		String[] sizes = {"Small", "Median", "Large"};
        String[] colors = {"Light Red", "Red", "Dark Red", "Light Blue", "Blue", "Dark Blue", "Light Green",
        		"Green", "Dark Green", "Light Yellow", "Yellow", "Dark Yellow", "Orange", "Grey", "Brown", "Purple", "Black"};
        
		
        shapeLbl = new JLabel("  Shape", SwingConstants.RIGHT);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 0;
        add(shapeLbl, c);
        

        shapeSelection = new JComboBox<String>(shapes);
        shapeSelection.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		for(MouseListener mouselistener: localWhiteBoard.getMouseListeners()) {
        			localWhiteBoard.removeMouseListener(mouselistener);
        		}

        		String shape = (String) shapeSelection.getSelectedItem();
        		if(shape.equals("Line") || shape.equals("Text")) {
        			widthSelection.setVisible(false);
        			widthLbl.setVisible(false);
        			heightLbl.setVisible(false);
        			heightSelection.setVisible(false);
        			if(shape.equals("Line")) {
        				textInput.setVisible(false);
        				localWhiteBoard.addMouseListener(new DrawLine());
        			} else {
        				textInput.setVisible(true);
        				localWhiteBoard.addMouseListener(new DrawShape());
        			}
        		} else {
        			if(shape.equals("Circle")) {
        				widthLbl.setText("Radius");
        				heightLbl.setVisible(false);
            			heightSelection.setVisible(false);
        			} else {
        				widthLbl.setText("Width");
            			heightLbl.setText("Height");
        				heightLbl.setVisible(true);
            			heightSelection.setVisible(true);
        			}
        			widthSelection.setVisible(true);
        			widthLbl.setVisible(true);
    				textInput.setVisible(false);
    				localWhiteBoard.addMouseListener(new DrawShape());
        		}
            }
        });
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 1;
    	c.gridy = 0;
        add(shapeSelection, c);
        
        widthLbl = new JLabel("Width", SwingConstants.RIGHT);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 2;
    	c.gridy = 0;
        add(widthLbl, c);
        
        widthSelection = new JComboBox<String>(sizes);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 3;
    	c.gridy = 0;
        add(widthSelection, c);
        
        heightLbl = new JLabel("Height", SwingConstants.RIGHT);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 4;
    	c.gridy = 0;
        add(heightLbl, c);
        
        heightSelection = new JComboBox<String>(sizes);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 5;
    	c.gridy = 0;
        add(heightSelection, c);
        
        colorLbl = new JLabel("Color", SwingConstants.RIGHT);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 6;
    	c.gridy = 0;
        add(colorLbl, c);
        
        colorSelection =  new JComboBox<String>(colors);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 7;
    	c.gridy = 0;
        add(colorSelection, c);
        
        textInput =  new JTextField();
        textInput.setVisible(false);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 8;
    	c.gridy = 0;
        add(textInput, c);
        
        c = new GridBagConstraints();
    	c.gridx = 0;
    	c.gridy = 1;
    	c.gridwidth = 9;
    	c.gridheight = 3;
    	add(localWhiteBoard, c);
    	
        chatBox = new ChatBox();
        c = new GridBagConstraints();
    	c.gridx = 10;
    	c.gridy = 1;
        add(chatBox, c);
        
        
        chatInput = new JTextField();
        chatInput.setPreferredSize(new Dimension(400, 20));
        c = new GridBagConstraints();
    	c.gridx = 10;
    	c.gridy = 2;
    	c.weighty = 1;
        add(chatInput, c);
        
        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
			@Override
        	public void actionPerformed(ActionEvent ae) {
        		try {
        			remoteWhiteBoard.addChat(username + ": " + chatInput.getText() + "\n");
        		} catch(Exception e) {
        			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Dialog",
					        JOptionPane.ERROR_MESSAGE);
        		}
        	}
		});
        c = new GridBagConstraints();
    	c.gridx = 11;
    	c.gridy = 2;
    	c.weighty = 1;
        add(sendButton, c);
        
        
        addWindowListener(new WindowAdapter() {
        	@Override
            public void windowClosing(WindowEvent we) {
        		try {
        			remoteWhiteBoard.removeUser(userID);
        		} catch(Exception e) {
        			e.printStackTrace();
        		}
        	}
        });
        
        localWhiteBoard.addMouseListener(new DrawLine());
	}
	
	public Color getColor(String colorString) {
		Color color = ShapeColor.BLACK;
		switch(colorString) {
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
		return color;
	}
	
	public double getSize(String sizeString) {
		double size = 0;
		switch(sizeString) {
			case "Small":
				size = 50;
				break;
			case "Median":
				size = 100;
				break;
			case "Large":
				size = 200;
				break;
		}
		return size;
	}
	
	public void sync() {
		try {
			localWhiteBoard.shapeList = remoteWhiteBoard.getShapeList();
			chatBox.setText(remoteWhiteBoard.getChatHistory());
			localWhiteBoard.repaint();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	class DrawLine extends MouseAdapter {
		private double x1, y1, x2, y2;
		
		@Override
    	public void mousePressed(MouseEvent me) {
			Point currentPoint = me.getPoint();
			x1 = currentPoint.getX();
			y1 = currentPoint.getY();
		}
		
		@Override
		public void mouseReleased(MouseEvent me) {
			Color color;
			
			Point currentPoint = me.getPoint();
			x2 = currentPoint.getX();
			y2 = currentPoint.getY();
			color = getColor((String) colorSelection.getSelectedItem());
			try {
				remoteWhiteBoard.addShape(new ShapeColor("Line", x1, y1, x2, y2, color));
			} catch(Exception e) {
				JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Dialog",
				        JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class DrawShape extends MouseAdapter {
		@Override
    	public void mousePressed(MouseEvent me) {
			String shapeString;
			double x1, y1, width, height;
			Color color;
			ShapeColor shapeColor;
			
			Point currentPoint = me.getPoint();
			shapeString = (String) shapeSelection.getSelectedItem();
			x1 = currentPoint.getX();
			y1 = currentPoint.getY();
			color = getColor((String) colorSelection.getSelectedItem());
			if(shapeString.equals("Text")) {
				shapeColor = new ShapeColor(shapeString, textInput.getText(), x1, y1, color);
			} else {
				width = getSize((String) widthSelection.getSelectedItem());
				if(shapeString.equals("Circle")) {
					height = width;
					shapeString = "Oval";
				} else {
					height = getSize((String) heightSelection.getSelectedItem());
				}
				shapeColor = new ShapeColor(shapeString, x1, y1, width, height, color);
			}
			try {
				remoteWhiteBoard.addShape(shapeColor);
			} catch(Exception e) {
				JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Dialog",
				        JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	
	class ChatBox extends JTextArea {
		private static final long serialVersionUID = 1L;

		public ChatBox() {
			super();
			setBackground(Color.GRAY);
			setEditable(false);
		}

		@Override
	    public Dimension getPreferredSize() {
	        return new Dimension(400, 600);
	    }
		
		public void sendMessage(String username, String message) {
			append(username + ": " + message + "\n");
		}
	}
}
