import org.hsqldb.util.DatabaseManagerSwing;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by marty on November 2017
 */
public class HSQLDB_ManagerStudio
{
	private static String url;
	private static String user;
	private static String password;
	
	public static void main(String[] args)
	{
		loadProperties();
		DatabaseManagerSwing
				.main(new String[] { "--url", url, "--user", user, "--password", password });
	}
	
	private static void loadProperties()
	{
		Properties prop = new Properties();
		InputStream input = null;
		
		try
		{
			
			input = HSQLDB_ManagerStudio.class.getClassLoader()
					.getResourceAsStream("database" + ".properties");
			
			// load a properties file
			prop.load(input);
			
			// get the property value and print it out
			url = prop.getProperty("jdbc.url");
			user = prop.getProperty("jdbc.user");
			password = prop.getProperty("jdbc.password");
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
