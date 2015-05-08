/**
 *
 * Copyright © Kaustuv Maji , 2014
 * Repos - https://github.com/kaustuvmaji
 * Blog -  http://kaustuvmaji.blogspot.in
 *
 */
package ldap.odm.example;

import java.util.List;

import javax.naming.directory.SearchControls;

import ldap.advance.example.UserRepositoryIntf;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.odm.core.OdmManager;
import org.springframework.stereotype.Component;

/**
 * This class implements the @see {@link UserRepositoryIntf}.
 *
 * @author KMaji
 *
 */
@Component
public class UserRepositoryImpl implements UserRepositoryIntf<User> {

	private static Logger		log				= Logger.getLogger(UserRepositoryImpl.class);
	private static final String	basedn			= "ou=users";
	private static final String	queryDelimeter	= ",";

	@SuppressWarnings("deprecation")
	@Autowired
	private OdmManager			odmManager;

	@Autowired(required = true)
	@Qualifier(value = "ldapTemplate")
	private LdapTemplate		ldapTemplate;

	/*
	 * (non-Javadoc)
	 *
	 * @see ldap.advance.example.UserRepositoryIntf#getAllUserNames()
	 */
	@Override
	public List<String> getAllUserNames() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ldap.advance.example.UserRepositoryIntf#getAllUsers()
	 */
	@SuppressWarnings("deprecation")
	@Override
	public List<User> getAllUsers() {
		SearchControls controls = new SearchControls();
		// controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		return odmManager.search(User.class, new DistinguishedName(basedn), "(uid=*)", controls);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * ldap.advance.example.UserRepositoryIntf#getUserDetails(java.lang.String)
	 */
	@Override
	public User getUserDetails(String userName) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * ldap.advance.example.UserRepositoryIntf#getUserDetail(java.lang.String)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public User getUserDetail(String userName) {
		log.info("executing {getUserDetail}");
		log.info(bindDN(userName));
		User rUser = odmManager.read(User.class, bindDN(userName));
		return rUser;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * ldap.advance.example.UserRepositoryIntf#authenticate(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean authenticate(String base, String userName, String password) {
		log.info("executing {authenticate}");
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * ldap.advance.example.UserRepositoryIntf#updateTelePhone(java.lang.String)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public User updateTelePhone(String userName, String newNumber) {
		log.info("executing {updateTelePhone}");
		User mUser = odmManager.read(User.class, bindDN(userName));
		mUser.setTelephoneNumber(newNumber);
		odmManager.update(mUser);
		return getUserDetails(userName);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * ldap.advance.example.UserRepositoryIntf#createUser(ldap.advance.example
	 * .User)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public boolean createUser(User user) {
		log.info("executing {createUser}");
		user.setDistinguisedName(bindDN(user.getUid()));
		odmManager.create(user);
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ldap.advance.example.UserRepositoryIntf#remove(java.lang.String)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public boolean remove(String uid) {
		// ldapTemplate.unbind(bindDN(uid));
		odmManager.delete(getUserDetail(uid));
		return true;
	}

	@SuppressWarnings("deprecation")
	public static javax.naming.Name bindDN(String userName) {
		javax.naming.Name name = new DistinguishedName("uid=".concat(userName).concat(queryDelimeter.concat(basedn)));
		return name;
	}
}
