/**
*
* Copyright © Kaustuv Maji , 2014
* Repos - https://github.com/kaustuvmaji
* Blog -  http://kaustuvmaji.blogspot.in
*
*/
package spring.jdbc.template.example;

import java.io.Serializable;

/**
 * @author KMaji
 *
 */
public class Member implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8944832256293484687L;


	private String name;
	private Integer id;
	private Integer age;

	/**
	 * Default constructor
	 */
	public Member() {

	}

	/**
	 * Constructor with argument
	 * @param name
	 * @param age
	 */
	public Member(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public final Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public final void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the age
	 */
	public final Integer getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public final void setAge(Integer age) {
		this.age = age;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Member [");
		if (name != null)
			builder.append("name=").append(name).append(", ");
		if (id != null)
			builder.append("id=").append(id).append(", ");
		if (age != null)
			builder.append("age=").append(age);
		builder.append("]");
		return builder.toString();
	}
}
