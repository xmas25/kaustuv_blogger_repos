/**
*
* Copyright © Kaustuv Maji , 2014
* Repos - https://github.com/kaustuvmaji
* Blog -  http://kaustuvmaji.blogspot.in
*
*/
package spring.jdbc.template.example;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

/**
 * This class is used to show-case example of calling stored procedure.
 *
 * @author kmaji
 *
 */
public class MemberStoredProcedureDAOImpl {

	private DataSource dataSource;

	private SimpleJdbcCall jdbcCall;

	/**
	 * Example of calling stored procedure.
	 *
	 * @param id
	 * @return
	 */
	public Member getMembers(Integer id) {
	      SqlParameterSource in = new MapSqlParameterSource().
	                              addValue("in_id", id);

	      Map<String, Object> out = jdbcCall.execute(in);

	      Member member = new Member();
	      member.setId(id);
	      member.setName((String) out.get("out_name"));
	      member.setAge((Integer) out.get("out_age"));

	      return member;
	   }

	/**
	 * Setter method for datasource and {@link SimpleJdbcCall}.
	 *
	 * @param dataSource
	 */
	  public void setDataSource(DataSource dataSource) {
	      this.jdbcCall =  new SimpleJdbcCall(dataSource).
	                       withProcedureName("fetchRecord");
	   }
}
