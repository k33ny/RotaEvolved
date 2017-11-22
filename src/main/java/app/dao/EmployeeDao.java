package app.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Martynas Petuska on November 2017
 * E-MAIL: martynas.petuska@outlook.com
 */
@Repository
public class EmployeeDao extends DefaultDao
{
	public List<String> getEmployeeNames()
	{
		return jdbcTemplate.queryForList("SELECT NAME FROM USERS", String.class);
	}
	
	public boolean deleteEmployee(String employeeName)
	{
		return jdbcTemplate.update("DELETE FROM USERS WHERE NAME = ?", employeeName) > 0;
	}
	
	public boolean addEmployee(String employeeName)
	{
		return jdbcTemplate.update("INSERT INTO USERS VALUES (99, ?, ?)", employeeName,
				employeeName + "@mail.com") > 0;
	}
}