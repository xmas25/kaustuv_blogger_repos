/*---------------------------------------------------------------------------*\
|
| Copyright (c) Kaustuv Maji , 2013
|
| Please do not use source code in production.
| Repos - https://github.com/kaustuvmaji
| Blog -  http://kaustuvmaji.blogspot.in
\*---------------------------------------------------------------------------*/
package com.kaustuv.jaxrs.example.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.kaustuv.jaxrs.example.vo.Customer;

/**
 * This is rest services interface .
 * Methods
 *     i.   Add customer               {@link #addCustomer(Customer)}.
 *     ii.  Get customer by customerId {@link #getCustomerById(long)}.
 *     iii. Update customer            {@link #updateCustomer(Customer)}.
 *     iv.  Delete customer            {@link #deleteCustomer(long)}.
 *
 * @since   1.0
 * @version 1.0
 *
 * @author KMaji
 *
 */
public interface DemoCustomerService {

  /**
   * This method is used to demonstrate http method GET.
   *
   * @param custId will be accepted as Queryparam.
   * @return response
   */
  @GET
  @Path("/getCustomerById")
  public Response getCustomerById(@QueryParam("custId") long custId);

  /**
   * This method is used to demonstrate http method POST.
   * @param customer
   * @return response
   */
  @POST
  @Path("/addCustomer")
  public Response addCustomer(Customer customer);

  /**
   * This method is used to demonstrate Http method DELETE.
   * @param custId will be accepted as Queryparam.
   * @return response
   */
  @DELETE
  @Path("/deleteCustomer")
  public Response deleteCustomer(@QueryParam("custId") long custId);

  /**
   * This method is to demonstrate http method PUT.
   *
   * @param customer
   * @return response
   */
  @PUT
  @Path("/updateCustomer")
  public Response updateCustomer(Customer customer);

}
