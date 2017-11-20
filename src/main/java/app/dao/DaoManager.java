package app.dao;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Martynas Petuska on November 2017
 * E-MAIL: martynas.petuska@outlook.com
 */
@Component
public class DaoManager
{
	private static DaoManager instance = (DaoManager) (new ClassPathXmlApplicationContext(
			"spring/spring.xml").getBean("daoManager"));
	@Resource
	private EmployeeDao employeeDao;
	
	public static EmployeeDao getEmployeeDao()
	{
		return instance.employeeDao;
	}
}
