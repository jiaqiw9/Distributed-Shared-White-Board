import javax.swing.*;


public class Test {
	public static void main(String[] args) {
		String string1 = "userID: 1, username: qq";
		String[] splitted = string1.split(":|,");
		System.out.println(splitted[1]);
	}
}