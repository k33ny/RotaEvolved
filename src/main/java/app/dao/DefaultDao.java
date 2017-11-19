package app.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by marty on November 2017
 */
@Repository
public class DefaultDao
{
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	public String getNameByID(int ID)
	{
		String name = jdbcTemplate
				.queryForObject("SELECT name FROM users WHERE id = ?", new Object[] { ID },
						String.class);
		return name;
	}
}
