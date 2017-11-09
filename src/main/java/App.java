import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by mpetuska on November 2017
 */
public class App
{
	private JPanel mainPanel;
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("App");
		frame.setContentPane(new App().mainPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
