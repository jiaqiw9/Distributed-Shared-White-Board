import java.awt.event.*;
import javax.swing.*;

public class ManagerWindow extends ClientWindow {
	public JMenuBar menuBar = new JMenuBar();
	public JMenu fileMenu = new JMenu("File");
    public JMenuItem new_ = new JMenuItem("New");
    public JMenuItem open = new JMenuItem("Open");
    public JMenuItem save = new JMenuItem("Save");
    public JMenuItem saveAs = new JMenuItem("Save As");
    public JMenuItem close = new JMenuItem("Close");
	
	public ManagerWindow(WhiteBoard whiteboard) {
		super(whiteboard);
		setTitle("WhiteBoard - Manager");
		
        new_.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		whiteboard.emptyWhiteBoard();
            }
        });
        open.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		JFileChooser openFile = new JFileChooser();
                int userSelection = openFile.showOpenDialog(null);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                	whiteboard.file = openFile.getSelectedFile();
                	whiteboard.readWhiteBoard();
                }
            }
        });
        save.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
                if(whiteboard.file != null) {
                	whiteboard.writeWhiteBoard();
                } else {
                	JFileChooser saveFile = new JFileChooser();
                    int userSelection = saveFile.showSaveDialog(null);
                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                    	whiteboard.file = saveFile.getSelectedFile();
                    	whiteboard.writeWhiteBoard();
                    }
                }
            }
        });
        saveAs.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		JFileChooser saveFile = new JFileChooser();
                int userSelection = saveFile.showSaveDialog(null);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                	whiteboard.file = saveFile.getSelectedFile();
                	whiteboard.writeWhiteBoard();
                }
            }
        });
        close.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		System.exit(0);
            }
        });
        

        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        fileMenu.add(new_);
        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.add(saveAs);
        fileMenu.add(close);
	}
}
