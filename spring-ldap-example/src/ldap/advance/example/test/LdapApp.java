/**
 *
 * Copyright © Kaustuv Maji , 2014
 * Repos - https://github.com/kaustuvmaji
 * Blog -  http://kaustuvmaji.blogspot.in
 *
 */
package ldap.advance.example.test;

import java.util.Date;

import ldap.advance.example.User;
import ldap.advance.example.UserRepositoryIntf;

import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author KMaji
 *
 */
public class LdapApp {

	private static Logger log = Logger.getLogger(LdapApp.class);

	static String username = "kaustuv";

	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-ldap-example.xml");
		log.info("Test started at "+new Date(context.getStartupDate()));
		UserRepositoryIntf ldapDao = (UserRepositoryIntf) context.getBean("userReposImpl");
		{
			// Create
			User user = new User();
			{
				user.setCn("spring_ldap_test");
				user.setSn("spring_ldap_test");
				user.setUid("spring_ldap_test");
				user.setPostalAddress("spring_ldap_test");
				user.setTelephoneNumber("9830098301");
				user.setUserPassword("spring_ldap_test");
			}
			log.info("\n =>" + ldapDao.createUser(user));

			// Read
			log.info("\n =>" + ldapDao.getAllUsers());
			log.info("\n =>" + ldapDao.getAllUserNames());

			context.refresh();

			log.info("\n =>" + ldapDao.getUserDetails("spring_ldap_test"));
			log.info("\n =>" + ldapDao.getUserDetail("spring_ldap_test"));

			// Update
			log.info("\n =>" + ldapDao.updateTelePhone("kaustuv", "9831198311"));

			// Delete
			log.info("\n =>" + ldapDao.remove("spring_ldap_test"));
		}
		context.registerShutdownHook();
		context.close();
	}
}
