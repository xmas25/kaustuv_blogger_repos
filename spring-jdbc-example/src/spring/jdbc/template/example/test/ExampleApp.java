/**
 *
 * Copyright © Kaustuv Maji , 2014
 * Repos - https://github.com/kaustuvmaji
 * Blog -  http://kaustuvmaji.blogspot.in
 *
 */
package spring.jdbc.template.example.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.jdbc.template.example.Member;
import spring.jdbc.template.example.MemberDAOIntf;
import spring.jdbc.template.example.MemberStoredProcedureDAOImpl;

/**
 * @author KMaji
 *
 */
public class ExampleApp {

	private static Logger log = Logger.getLogger(ExampleApp.class);

	/**
	 *
	 */
	static {
		// System.out.println(System.getenv());
	}

	/**
	 * @param args
	 */
	public static void main(String... args) {

		@SuppressWarnings("resource")
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

		MemberDAOIntf memberDaoImpl = null;

		if (context.containsBean("memberJDBCTemplate")) {
			memberDaoImpl = (MemberDAOIntf) context.getBean("memberJDBCTemplate");
		}

		MemberStoredProcedureDAOImpl sspDao = null;

		if (context.containsBean("memberJDBCImpl")) {
			sspDao = (MemberStoredProcedureDAOImpl) context.getBean("memberJDBCImpl");
		}

		log.info(memberDaoImpl.getRowSetSize("select COUNT(*) from Member"));

		{
			// SCRUD Example.
			{
				// Create
				log.info("{Executing} ------Records Creation--------");
				memberDaoImpl.create("member_1", 11);
				memberDaoImpl.create("member_2", 2);
				memberDaoImpl.create("member_3", 15);
			}

			{
				// Retrieve
				log.info("{Executing} ------Listing Multiple Records--------");
				List<Member> members = memberDaoImpl.listMembers();
				for (Member record : members) {
					log.info("ID : " + record.getId() + " , Name : " + record.getName() + " , Age : " + record.getAge());
				}

				log.info("{Executing} ----Listing Record with ID = 3 -----");
				Member member = memberDaoImpl.getMember(3);
				log.info("ID : " + member.getId() + " , Name : " + member.getName() + " , Age : " + member.getAge());

				memberDaoImpl.getMemberAsString(3);
			}

			{
				// Update
				log.info("{Executing} ---- Updating Record with ID = 3 -----");
				memberDaoImpl.update(3, 20);
			}

			{
				// Delete
				log.info("{Executing} ---- Deleting Record with ID = 2 -----");
				memberDaoImpl.delete(2);
				log.info("Deleted member with " + "ID : ");
			}
		}

		{
			// Batch Example
			{
				// Insert
				log.info("{Executing} ---- Records Creation as a batch process -----");
				List<Member> members = new ArrayList<>();
				for (int i = 0; i < 10; i++) {
					members.add(new Member("member_batch_" + i, i * 5));
				}
				memberDaoImpl.insertBatch(members);
				log.info(memberDaoImpl.listMembers());
			}

			{
				// Update
				log.info("{Executing} ---- Records update as a batch process -----");
				memberDaoImpl.batchSql("UPDATE Member SET NAME ='Mary' where AGE > 35");
				log.info(memberDaoImpl.listMembers());

			}
		}

		{
			// calling stored procedure
			log.info("{Executing} ---- Records according to stored procedure -----");
			log.info(sspDao.getMembers(3));
		}

		// register shutdownhook
		context.registerShutdownHook();
	}

}
