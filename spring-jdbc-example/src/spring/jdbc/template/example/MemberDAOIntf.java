/**
*
* Copyright © Kaustuv Maji , 2014
* Repos - https://github.com/kaustuvmaji
* Blog -  http://kaustuvmaji.blogspot.in
*
*/
package spring.jdbc.template.example;

import java.util.List;


/**
 * This interface is used to show-case example of SCRUD.
 *
 * @author KMaji
 *
 */
public interface MemberDAOIntf {

	/**
	 * This method is used to create a record in the Member table.
	 */
	public void create(String name, Integer age);

	/**
	 * This method is used to list down a record from the Member
	 * table corresponding to a passed member id.
	 *
	 * @return {@link Member}
	 */
	public Member getMember(Integer id);

	/**
	 * This method is used to list down a record from the Member
	 * table corresponding to a passed member id. print record as string
	 *
	 */
	public void getMemberAsString(Integer id);

	/**
	 * This method is used to retrieve row set size.
	 * @param sql
	 * @return size of row set.
	 */
	public int getRowSetSize(String sql);

	/**
	 * This method is used to list down all the records from the
	 * Member table.
	 *
	 * @return List of {@link Member}
	 */
	public List<Member> listMembers();

	/**
	 * This method is used to delete a record from the Member table
	 * corresponding to a passed member id.
	 */
	public void delete(Integer id);

	/**
	 * This method is used to update a record into the Member table.
	 */
	public void update(Integer id, Integer age);

	/**
	 * This method is example of inserting mass data in table.
	 *
	 * @param members
	 */
	public void insertBatch(final List<Member> members);

	/**
	 * This method is example of inserting mass data in table using sql.
	 *
	 * @param sql
	 */
	public void batchSql(final String sql);
}