/**
 *
 * Copyright © Kaustuv Maji , 2014
 * Repos - https://github.com/kaustuvmaji
 * Blog -  http://kaustuvmaji.blogspot.in
 *
 */
package spring.jdbc.template.example;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class is used to show-case example of SCRUD functions. This class will
 * also show example of use spring jdbc batch functions.
 *
 * @author KMaji
 *
 */
@Component
public class MemberDAOImpl implements MemberDAOIntf {

	private static Logger log = Logger.getLogger(MemberDAOImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private MemberMapper memberMapper;

	/**
	 *
	 */
	public MemberDAOImpl() {

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see spring.jdbc.template.example.MemberDAOIntf#create(java.lang.String,
	 *      java.lang.Integer)
	 */
	@Override
	@Transactional
	public void create(String name, Integer age) {
		final String SQL = "insert into Member (name, age) values (?, ?)";
		// Issue a single SQL update operation (such as an insert, update or
		// delete statement) via a prepared statement, binding the given
		// arguments.
		jdbcTemplate.update(SQL, name, age);
		log.info("Created Record Name = " + name + " Age = " + age);
		return;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see spring.jdbc.template.example.MemberDAOIntf#getMember(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public Member getMember(Integer id) {
		String SQL = "select * from Member where id = ?";
		// Query given SQL to create a prepared statement from SQL and a list of
		// arguments to bind to the query, expecting a result object.
		Member member = jdbcTemplate.queryForObject(SQL, new Object[]{id}, memberMapper);

		return member;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see spring.jdbc.template.example.MemberDAOIntf#listMembers()
	 */
	@Override
	@Transactional
	public List<Member> listMembers() {
		String SQL = "select * from Member";
		// perfect example for BeanPropertyRowMapper <>
		List<Member> members = jdbcTemplate.query(SQL, new BeanPropertyRowMapper<Member>(Member.class));
		return members;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see spring.jdbc.template.example.MemberDAOIntf#delete(java.lang.Integer)
	 */
	@Override
	@Transactional
	public void delete(Integer id) {
		String SQL = "delete from Member where id = ?";
		// Issue a single SQL update operation (such as an insert, update or
		// delete statement) via a prepared statement, binding the given
		// arguments.
		jdbcTemplate.update(SQL, id);
		log.info("Deleted Record with ID = " + id);
		return;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see spring.jdbc.template.example.MemberDAOIntf#update(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@Override
	@Transactional
	public void update(Integer id, Integer age) {
		String SQL = "update Member set age = ? where id = ?";
		// Issue a single SQL update operation (such as an insert, update or
		// delete statement) via a prepared statement, binding the given
		// arguments.
		jdbcTemplate.update(SQL, age, id);
		log.info("Updated Record with ID = " + id);
		return;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see spring.jdbc.template.example.MemberDAOIntf#getMemberAsString(java.lang.
	 *      Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public void getMemberAsString(Integer id) {
		String SQL = "select NAME from Member where id = ?";
		log.info(jdbcTemplate.queryForObject(SQL, new Object[]{id}, String.class));
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see spring.jdbc.template.example.MemberDAOIntf#insertBatch(java.util.List)
	 */
	@Override
	public void insertBatch(final List<Member> members) {
		String sql = "INSERT INTO Member " + "( NAME, AGE) VALUES ( ?, ?)";
		// Issue multiple update statements on a single PreparedStatement, using
		// batch updates and a BatchPreparedStatementSetter to set values.
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int arg1) throws SQLException {
				Member member = members.get(arg1);
				ps.setString(1, member.getName());
				ps.setInt(2, member.getAge());
			}

			@Override
			public int getBatchSize() {
				return members.size();
			}
		});

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see spring.jdbc.template.example.MemberDAOIntf#batchSql(java.lang.String)
	 */
	@Override
	public void batchSql(final String sql) {
		// Issue multiple SQL updates on a single JDBC Statement using batching.
		jdbcTemplate.batchUpdate(new String[]{sql});
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see spring.jdbc.template.example.MemberDAOIntf#getRowSetSize(java.lang.String
	 *      )
	 */
	@Override
	public int getRowSetSize(String sql) {
		@SuppressWarnings("deprecation")
		// Query given SQL to create a prepared statement from SQL and a list of
		// arguments to bind to the query, resulting in an int value.
		int total = jdbcTemplate.queryForInt(sql);
		return total;
	}
}