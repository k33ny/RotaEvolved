package app.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Martynas Petuska on November 2017
 * E-MAIL: martynas.petuska@outlook.com
 */
@Repository
public abstract class DefaultDao
{
	@Resource
	protected JdbcTemplate jdbcTemplate;
}
