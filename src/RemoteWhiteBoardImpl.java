import java.util.*;
import java.util.concurrent.*;
import javax.swing.*;
import java.rmi.*;
import java.rmi.server.*;

public class RemoteWhiteBoardImpl extends UnicastRemoteObject implements RemoteWhiteBoard {
	private static final long serialVersionUID = 1L;
	
	// Note that the manager always has count 0 since he is the first user starts the RemoteWhiteBoard.
	private int userCount = 0;
	private ConcurrentHashMap<Integer, String> userMap = new ConcurrentHashMap<Integer, String>();
	private ArrayList<ShapeColor> shapeList = new ArrayList<ShapeColor>();
	private String chatHistory = "";
	
	public RemoteWhiteBoardImpl() throws RemoteException {
		
	}
	
	public int createUser(String username) {
		int userID = userCount;
		userMap.put(userID, username);
		userCount++;
		return userID;
	}
	
	public int[] join(String username) throws RemoteException {
		int n = JOptionPane.showOptionDialog(null, username + " wants to share your whiteboard.", null,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		
		int[] result = new int[2];
		if(n == 0) {
			int userID = createUser(username);
			result[0] = 1;
			result[1] = userID;
		} else {
			result[0] = 0;
			result[0] = 0;
		}
		return result;
	}
	
	public boolean checkUser(int userID) throws RemoteException {
		if(userMap.containsKey(userID)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void removeUser(int userID) throws RemoteException {
		userMap.remove(userID);
	}
	
	public Map<Integer, String> getUserMap() throws RemoteException {
		return userMap;
	}
	
	public synchronized ArrayList<ShapeColor> getShapeList() throws RemoteException {
		return shapeList;
	}
	
	public synchronized void addShape(ShapeColor shapeColor) throws RemoteException {
		shapeList.add(shapeColor);
	}
	
	
	public synchronized void setShapeList(ArrayList<ShapeColor> shapeList) throws RemoteException {
		this.shapeList = shapeList;
	}
	
	public synchronized void addChat(String chat) throws RemoteException {
		chatHistory += chat;
	}
	
	public synchronized String getChatHistory() throws RemoteException {
		return chatHistory;
	}
}
