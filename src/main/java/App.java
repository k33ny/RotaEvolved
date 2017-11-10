import frontEnd.Forms.MainForm;

import javax.swing.JFrame;

/**
 * Created by mpetuska on November 2017
 */
public class App
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("MainForm");
		frame.setContentPane(new MainForm().getMainPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
