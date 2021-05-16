import javax.swing.*;
import java.rmi.registry.*;

public class CreateWhiteBoard {
	public static void main(String[] args) {
		try {
			String username = args[2];
			
			RemoteWhiteBoardImpl remoteWhiteBoard = new RemoteWhiteBoardImpl();
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("remoteWhiteBoard", remoteWhiteBoard);
			System.out.println("Whiteboard server ready");
			
			remoteWhiteBoard.createUser(username);
			
			ManagerWindow managerWindow = new ManagerWindow(remoteWhiteBoard, username);
			managerWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			managerWindow.pack();
			managerWindow.setVisible(true);
			
			while(true) {
				Thread.sleep(100);
				
				if(!remoteWhiteBoard.checkUser(0)) {
					JOptionPane.showMessageDialog(null, "You are kicked out.");
					System.exit(0);
				}
				managerWindow.sync();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}