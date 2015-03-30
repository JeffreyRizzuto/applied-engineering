/* this is a termporary class (maybe) to define a user
 * eventually, users will be imported from MySQL
 */

package inventory;

public class User {
	String fullName;
	String email;
	public User(String name, String email) {
		this.fullName = name;
		this.email = email;
	}
	
	public String getName() {
		return fullName;
	}
	
	public String email() {
		return email;
	}
}
