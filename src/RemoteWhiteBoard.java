import java.rmi.*;
import java.util.*;

public interface RemoteWhiteBoard extends Remote {
	
	public int[] join(String username) throws RemoteException;
	
	public boolean checkUser(int userID) throws RemoteException;
	
	public void removeUser(int userID) throws RemoteException;
	
	public Map<Integer, String> getUserMap() throws RemoteException;
	
	public ArrayList<ShapeColor> getShapeList() throws RemoteException;
	
	public void addShape(ShapeColor shapeColor) throws RemoteException;
	
	public void setShapeList(ArrayList<ShapeColor> shapeList) throws RemoteException;
	
	public String getChatHistory() throws RemoteException;
	
	public void addChat(String chat) throws RemoteException;
}
