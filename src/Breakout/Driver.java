package Breakout;
import javax.swing.JFrame;
public class Driver {
	public static void main(String[] args) {
		JFrame frame = new JFrame ("BreakOut");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new Board());
		frame.pack();
		frame.setVisible(true);
	}

}
