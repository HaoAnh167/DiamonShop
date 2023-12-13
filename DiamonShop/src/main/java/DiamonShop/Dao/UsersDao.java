package DiamonShop.Dao;

import org.springframework.stereotype.Repository;

import DiamonShop.Entity.MapperUsers;
import DiamonShop.Entity.Users;
@Repository
public class UsersDao extends BaseDao{
	public int addAccount(Users user) {
		StringBuffer  sql = new StringBuffer();
		sql.append("insert ");
		sql.append("into users ");
		sql.append("( ");
		sql.append("    user, ");
		sql.append("    password, ");
		sql.append("    display_name, ");
		sql.append("    address ");
		sql.append(") ");
		sql.append("values ");
		sql.append("( ");
		sql.append("    '" + user.getUser() + "', ");
		sql.append("    '" + user.getPassword() + "', ");
		sql.append("    '" + user.getDisplay_name() + "', ");
		sql.append("    '" + user.getAddress() + "' ");
		sql.append(")");
		
		int insert = _jdbcTemplate.update(sql.toString());
		return insert;
	}
		
	public Users getUserByAccount(Users user) {
		String sql = "select * from users where user = '" + user.getUser() + "'";
		Users user1 = _jdbcTemplate.queryForObject(sql, new MapperUsers());
		return user1;	
	}
}
