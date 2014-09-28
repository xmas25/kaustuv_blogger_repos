/**
|
| Copyright (c) Kaustuv Maji , 2013
|
| Please do not use source code in production.
| Repos -  https://github.com/kaustuvmaji
| Blog  -  http://kaustuvmaji.blogspot.in
*/
package com.kaustuv.jaxrs.example.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.kaustuv.jaxrs.example.vo.Address;
import com.kaustuv.jaxrs.example.vo.Customer;

/**
 * This implementation class is to implement rest service interface.
 * Methods
 *     --   Add customer               {@link #addCustomer(Customer)}.
 *     --   Get customer by customerId {@link #getCustomerById(long)}.
 *     --   Update customer            {@link #updateCustomer(Customer)}.
 *     --   Delete customer            {@link #deleteCustomer(long)}.
 *     --   init                       {@link #init()}.
 *     --   destory                    {@link #destroy()}.
 *
 * @since   1.0
 * @version 1.0
 *
 * @author KMaji
 *
 */
public class DemoCustomerServiceImpl implements DemoCustomerService {

  @Context
  UriInfo uriInfo;

  private final static String ACCESS_CONTROL_ALLOW_ORIGIN = "*";

  private Map<Long, Customer> customers;

  public DemoCustomerServiceImpl() {
    customers = new HashMap<Long, Customer>();
  }

  /**
   *
   */
  public Response getCustomerById(long custId) {
   System.out.println("Executing getCustomerById operation");
    if (!customers.containsKey(custId)) {
      return Response.status(Response.Status.BAD_REQUEST)
                     .location(uriInfo.getAbsolutePath())
                     .entity("customer not found")
                     .header("Access-Control-Allow-Origin", ACCESS_CONTROL_ALLOW_ORIGIN)
                     .build();
    }
    return Response.ok(uriInfo.getAbsolutePath())
                   .entity(customers.get(custId))
                   .header("Access-Control-Allow-Origin", ACCESS_CONTROL_ALLOW_ORIGIN)
                   .build();
  }

  /**
   *
   */
  public Response addCustomer(Customer customer) {
    System.out.println("Executing Add Customer operation");
    if (customers.containsKey(customer.getCustomerId())) {
     return Response.status(Response.Status.BAD_REQUEST)
              .location(uriInfo.getAbsolutePath())
              .entity("Exsisting customer found with same id.")
              .header("Access-Control-Allow-Origin", ACCESS_CONTROL_ALLOW_ORIGIN)
              .build();
    }
    customers.put(customer.getCustomerId(), customer);
    System.out.println("Added new customer with customer id" + customer.getCustomerId());
    return Response.ok(uriInfo.getAbsolutePath())
                   .entity(customer)
                   .header("Access-Control-Allow-Origin", ACCESS_CONTROL_ALLOW_ORIGIN)
                   .build();
  }

  /**
   *
   */
  public Response deleteCustomer(long custId) {
    System.out.println("Executing Delete Customer operation");
    if (!customers.containsKey(custId)) {
     return  Response.status(Response.Status.BAD_REQUEST)
              .location(uriInfo.getAbsolutePath())
              .entity("customer not found")
              .header("Access-Control-Allow-Origin", ACCESS_CONTROL_ALLOW_ORIGIN)
              .build();
    }
    customers.remove(custId);
    System.out.println("Removed customer contains customerid " + custId);
    return Response.ok(uriInfo.getAbsolutePath())
                   .entity("Removed customer contains customerid " + custId)
                   .header("Access-Control-Allow-Origin", ACCESS_CONTROL_ALLOW_ORIGIN)
                   .build();
  }

  /**
   *
   *
   */
  public Response updateCustomer(Customer customer) {
    System.out.println("Executing update Customer operation");
    if (!customers.containsKey(customer.getCustomerId())) {
     return Response.status(Response.Status.BAD_REQUEST)
              .location(uriInfo.getAbsolutePath())
              .entity("customer not found")
              .header("Access-Control-Allow-Origin", ACCESS_CONTROL_ALLOW_ORIGIN)
              .build();
    }
    customers.put(customer.getCustomerId(), customer);
    return Response.ok(uriInfo.getAbsolutePath())
                   .entity(customer)
                   .header("Access-Control-Allow-Origin", ACCESS_CONTROL_ALLOW_ORIGIN)
                   .build();
  }

  /**
   * Init method is used to prepare data which we
   * will use for testing purpose.
   * usage of this method is defined in bean definition.
   */
  public void init() {
    Customer cus1 = new Customer();
    cus1.setCustomerId(1l);
    cus1.setCustomerName("Kaustuv Maji");
    cus1.setPhone(9830098300l);
    Address add = new Address();
    add.setStreet("saltlake");
    add.setCity("kolkata");
    add.setState("westbengal");
    add.setCountry("India");
    add.setPincode(700106);
    cus1.setAddress(add);
    customers.put(cus1.getCustomerId(), cus1);
    System.out.println("Added " + cus1.getCustomerName() + " into Customer info");
    Customer cus2 = new Customer();
    cus2.setCustomerId(2l);
    cus2.setCustomerName("customer 2");
    customers.put(cus2.getCustomerId(), cus2);
    System.out.println("Added " + cus2.getCustomerName() + " into Customer info");
    System.out.println("Customer info map contains " + customers.size() + " details");
  }

  /**
   * destroy method is also used after our testing purpose is done.
   * usage of this method is defined in bean definition.
   */
  public void destroy() {
    System.out.println("Cleaning up customers info map");
    customers.clear();
  }

}
