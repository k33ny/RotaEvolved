import javax.swing.JFrame;
import javax.swing.JLabel;

public class App
{
	public static void main(String[] args)
	{
		JFrame f = new JFrame("Hello World");
		f.add(new JLabel("Hello"));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}
}