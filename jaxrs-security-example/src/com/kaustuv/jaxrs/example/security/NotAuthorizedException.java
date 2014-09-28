/**
|
| Copyright (c) Kaustuv Maji , 2013
| 
| Please do not use source code in production.
| Repos -  https://github.com/kaustuvmaji
| Blog  -  http://kaustuvmaji.blogspot.in 
*/
package com.kaustuv.jaxrs.example.security;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebFault;

/**
 * @author KMaji
 *
 */
@WebFault
public class NotAuthorizedException extends WebApplicationException{

  private static final long serialVersionUID = -1203116970226591712L;


  public NotAuthorizedException(String faultString) {
    super(Response.status(Response.Status.UNAUTHORIZED)
          .entity(faultString).type(MediaType.TEXT_PLAIN).build());
    System.out.println("################NotAuthorizedException(String faultString)######################");
  }

}
