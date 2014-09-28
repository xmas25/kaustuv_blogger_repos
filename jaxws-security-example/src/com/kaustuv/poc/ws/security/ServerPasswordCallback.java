/**
*
* Copyright (c) Kaustuv Maji , 2014
* Repos - https://github.com/kaustuvmaji
* Blog -  http://kaustuvmaji.blogspot.in
*
*/
package com.kaustuv.poc.ws.security;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

/**
 * @author KMaji
 *
 */
public class ServerPasswordCallback implements CallbackHandler {

	private SignatureInfoBean signatureInfo;

	/**
	 * Getter for the property signatureInfo.
	 *
	 * @return Returns the value of signatureInfo.
	 */
	public SignatureInfoBean getSignatureInfo() {
		return signatureInfo;
	}

	/**
	 * Setter for the property signatureInfo.
	 *
	 * @param signatureInfo
	 *            The new value of signatureInfo.
	 */
	public void setSignatureInfo(SignatureInfoBean signatureInfo) {
		this.signatureInfo = signatureInfo;
	}

	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		WSPasswordCallback wsPasswordCallback = null;
		String password = null;
		for (int count = 0; count < callbacks.length; count++) {
			wsPasswordCallback = (WSPasswordCallback) callbacks[count];
			// String identifier = wsPasswordCallback.getIdentifier();
			switch (wsPasswordCallback.getUsage()) {
				case WSPasswordCallback.USERNAME_TOKEN :
					try {
						password = "password";
					} catch (Exception exp) {
						exp.printStackTrace();
					}
					wsPasswordCallback.setPassword(password);
					break;
				case WSPasswordCallback.DECRYPT :
					wsPasswordCallback.setPassword(signatureInfo.getSignatureServiceKeyPassword());
					break;
				case WSPasswordCallback.SIGNATURE :
					wsPasswordCallback.setPassword(signatureInfo.getSignatureServiceKeyPassword());
					break;
			}
		}
	}
}
