/**
*
* Copyright (c) Kaustuv Maji , 2014
* Repos - https://github.com/kaustuvmaji
* Blog -  http://kaustuvmaji.blogspot.in
*
*/
package com.kaustuv.poc.ws.security;

import java.io.Serializable;

public class SignatureInfoBean implements Serializable {

	/**
   *
   */
	private static final long serialVersionUID = -5749465533676763004L;

	public SignatureInfoBean() {
	}

	private String signatureServiceKey = null;
	private String signatureServiceKeyPassword = null;

	/**
	 * Getter for the property signatureServiceKey.
	 *
	 * @return Returns the value of signatureServiceKey.
	 */
	public String getSignatureServiceKey() {
		return signatureServiceKey;
	}

	/**
	 * Setter for the property signatureServiceKey.
	 *
	 * @param signatureServiceKey
	 *            The new value of signatureServiceKey.
	 */
	public void setSignatureServiceKey(String signatureServiceKey) {
		this.signatureServiceKey = signatureServiceKey;
	}

	/**
	 * Getter for the property signatureServiceKeyPassword.
	 *
	 * @return Returns the value of signatureServiceKeyPassword.
	 */
	public String getSignatureServiceKeyPassword() {
		return signatureServiceKeyPassword;
	}

	/**
	 * Setter for the property signatureServiceKeyPassword.
	 *
	 * @param signatureServiceKeyPassword
	 *            The new value of signatureServiceKeyPassword.
	 */
	public void setSignatureServiceKeyPassword(String signatureServiceKeyPassword) {
		this.signatureServiceKeyPassword = signatureServiceKeyPassword;
	}
}
