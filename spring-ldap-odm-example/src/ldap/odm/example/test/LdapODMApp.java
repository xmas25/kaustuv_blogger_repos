/**
 *
 * Copyright © Kaustuv Maji , 2015
 * Repos - https://github.com/kaustuvmaji
 * Blog -  http://kaustuvmaji.blogspot.in
 *
 */
package ldap.odm.example.test;

import java.util.Date;

import ldap.odm.example.User;
import ldap.advance.example.UserRepositoryIntf;

import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author KMaji
 *
 */
public class LdapODMApp {

	private static Logger	log	= Logger.getLogger(LdapODMApp.class);

	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-ldap-odm.xml");
		log.info("Test started at " + new Date(context.getStartupDate()));
		@SuppressWarnings("unchecked")
		UserRepositoryIntf<User> ldapDao = (UserRepositoryIntf<User>) context.getBean("userReposImpl");
		final String success = "Success";
		final String failure = "Failure";
		final String username = "kaustuv";
		{
			// Create
			User user = new User();
			{
				user.setUid("spring_ldap_test");
				user.setCn("spring_ldap_test");
				user.setSn("spring_ldap_test");
				user.setPostalAddress("spring_ldap_test");
				user.setTelephoneNumber("9830098301");
			}

			log.info("\n Example of OdmManager create #=> " + (ldapDao.createUser(user) ? success : failure));

			// Read
			log.info("\n Example of OdmManager search all #=> " + ldapDao.getAllUsers());

			// find
			log.info("\n Example of OdmManager read #=> " + ldapDao.getUserDetail(username));

			// Update
			log.info("\n Example of OdmManager update #=> " + ldapDao.updateTelePhone("spring_ldap_test", "9831198311"));

			// Delete
			log.info("\n Example of OdmManager delete #=> " + (ldapDao.remove("spring_ldap_test") ? success : failure));
		}
		context.registerShutdownHook();
		context.close();
	}
}
