/**
*
* Copyright © Kaustuv Maji , 2014
* Repos - https://github.com/kaustuvmaji
* Blog -  http://kaustuvmaji.blogspot.in
*
*/
package spring.jdbc.template.example;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * This is custom row mapper for member class.
 * @author KMaji
 *
 */
public class MemberMapper implements RowMapper<Member> {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member();
		member.setId(rs.getInt("id"));
		member.setName(rs.getString("name"));
		member.setAge(rs.getInt("age"));
		return member;
	}
}