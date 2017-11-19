package app;

import app.dao.TestSandbox;
import app.frontEnd.forms.MainForm;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.JFrame;

/**
 * Created by mpetuska on November 2017
 */
public class App
{
	public static void main(String[] args)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-config.xml");
		
		TestSandbox testSandbox = (TestSandbox) context.getBean("testSandbox");
		
		System.out.println(testSandbox.test());
		
		JFrame frame = new JFrame("MainForm");
		frame.setContentPane(new MainForm().getMainPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Rota Evolved");
		frame.pack();
		frame.setVisible(true);
	}
}
