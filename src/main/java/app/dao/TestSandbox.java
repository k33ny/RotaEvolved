package app.dao;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by marty on November 2017
 */
@Component
public class TestSandbox
{
	@Resource
	private DefaultDao defaultDao;
	
	public String test()
	{
		return defaultDao.getNameByID(1);
	}
}
