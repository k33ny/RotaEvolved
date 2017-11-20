package app;

import app.frontEnd.forms.MainForm;

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
		frame.setTitle("Rota Evolved");
		frame.pack();
		frame.setVisible(true);
	}
}
