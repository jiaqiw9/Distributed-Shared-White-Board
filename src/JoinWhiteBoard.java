import java.rmi.*;
import java.rmi.registry.*;
import javax.swing.*;

public class JoinWhiteBoard {
	public static void main(String[] args) {
		try {
			String ip = args[0];
			int port = Integer.parseInt(args[1]);
			String username = args[2];
			
			Registry registry = LocateRegistry.getRegistry(ip, port);
			RemoteWhiteBoard remoteWhiteBoard = (RemoteWhiteBoard) registry.lookup("remoteWhiteBoard");
			
			int[] joinResult = remoteWhiteBoard.join(username);
			if(joinResult[0] == 0) {
				System.out.println("Join request is rejected by the manager.");
				System.exit(0);
			}
			int userID = joinResult[1];
			
			ClientWindow clientWindow = new ClientWindow(remoteWhiteBoard, userID, username);
			clientWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			clientWindow.pack();
			clientWindow.setVisible(true);
			
			try {
				while(true) {
					Thread.sleep(100);
					if(!remoteWhiteBoard.checkUser(userID)) {
						JOptionPane.showMessageDialog(null, "You are kicked out.");
						System.exit(0);
					}
					clientWindow.sync();
				}
			} catch(ConnectException ce) {
				JOptionPane.showMessageDialog(null, "The manager quits.");
				System.exit(0);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
