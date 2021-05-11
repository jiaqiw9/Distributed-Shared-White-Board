import javax.swing.*;


public class Test {
	public static void main(String[] args) {
		WhiteBoard whiteboard = new WhiteBoard();
		
		ManagerWindow managerWindow = new ManagerWindow(whiteboard);
		managerWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		managerWindow.pack();
		managerWindow.setVisible(true);
	}
}