import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class ManagerWindow extends ClientWindow {
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	private JMenu manageMenu = new JMenu("Manage");
	private JMenuItem new_ = new JMenuItem("New");
	private JMenuItem open = new JMenuItem("Open");
	private JMenuItem save = new JMenuItem("Save");
	private JMenuItem saveAs = new JMenuItem("Save As");
	private JMenuItem close = new JMenuItem("Close");
	private JMenuItem kickout = new JMenuItem("Kick Out");
	
	public ManagerWindow(RemoteWhiteBoard remoteWhiteBoard, String username) {
		super(remoteWhiteBoard, 0, username);
		setTitle("WhiteBoard - Manager - " + username);
		
        new_.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		try {
        			localWhiteBoard.emptyWhiteBoard();
        			remoteWhiteBoard.setShapeList(localWhiteBoard.shapeList);
        		} catch(Exception e) {
        			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Dialog",
					        JOptionPane.ERROR_MESSAGE);
        		}
            }
        });
        open.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		try {
        			JFileChooser openFile = new JFileChooser();
                    int userSelection = openFile.showOpenDialog(null);
                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                    	localWhiteBoard.file = openFile.getSelectedFile();
                    	localWhiteBoard.readWhiteBoard();
                    	remoteWhiteBoard.setShapeList(localWhiteBoard.shapeList);
                    }
        		} catch(Exception e) {
        			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Dialog",
					        JOptionPane.ERROR_MESSAGE);
        		}
            }
        });
        save.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		try {
        			if(localWhiteBoard.file != null) {
        				localWhiteBoard.writeWhiteBoard();
                    } else {
                    	JFileChooser saveFile = new JFileChooser();
                        int userSelection = saveFile.showSaveDialog(null);
                        if (userSelection == JFileChooser.APPROVE_OPTION) {
                        	localWhiteBoard.file = saveFile.getSelectedFile();
                        	localWhiteBoard.writeWhiteBoard();
                        }
                    }
        		} catch(Exception e) {
        			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Dialog",
					        JOptionPane.ERROR_MESSAGE);
        		}
            }
        });
        saveAs.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		try {
        			JFileChooser saveFile = new JFileChooser();
                    int userSelection = saveFile.showSaveDialog(null);
                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                    	localWhiteBoard.file = saveFile.getSelectedFile();
                    	localWhiteBoard.writeWhiteBoard();
                    }
        		} catch(Exception e) {
        			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Dialog",
					        JOptionPane.ERROR_MESSAGE);
        		}
            }
        });
        close.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		System.exit(0);
            }
        });
        
        kickout.addActionListener(new ActionListener() {
			@Override
        	public void actionPerformed(ActionEvent ae) {
        		try {
        		String[] userList = getUserList();
        		String selectedUser = (String) JOptionPane.showInputDialog(null, "Please select the user to kick out.",
        				null, JOptionPane.QUESTION_MESSAGE, null, userList, null);
        		if(selectedUser != null) {
        			int userID = Integer.parseInt(selectedUser.split("(: )|(, )")[1]);
            		remoteWhiteBoard.removeUser(userID);
        		}
        		} catch(Exception e) {
        			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Dialog",
					        JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });

        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(manageMenu);
        fileMenu.add(new_);
        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.add(saveAs);
        fileMenu.add(close);
        manageMenu.add(kickout);
	}
	
	private String[] getUserList() throws Exception {
		ArrayList<String> userList = new ArrayList<String>();
		Map<Integer, String> userMap = remoteWhiteBoard.getUserMap();
		for(Map.Entry<Integer, String> user: userMap.entrySet()) {
			userList.add("userID: " + user.getKey() + ", " + "username: " + user.getValue());
		}
		return userList.toArray(new String[1]);
	}
}
