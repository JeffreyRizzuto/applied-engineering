package inventory;

import java.util.HashMap;
import java.util.Map;

public class Authenticator {
	
	static User Tom = new User("Tom Jones", "tj@cab.net");
	static User Sue = new User("Sue Smith", "ss@cab.net");
	static User Ragnar = new User("Ragnar Nelson", "rn@cab.net");
	
	static Map<String, String> usersLogins = new HashMap<String, String>() {
		{
			put("tj@cab.net", "hunter1");
			put("ss@cab.net", "hunter1");
			put("rn@cab.net", "hunter1");
		}
	};
	static Map<String, User> users = new HashMap<String, User>() {
		{
			put("tj@cab.net", Tom);
			put("ss@cab.net", Sue);
			put("rn@cab.net", Ragnar);
		}
	};
	static Map<String, String> roles = new HashMap<String, String>() {
		{
			put("tj@cab.net", "Production Manager");
			put("ss@cab.net", "Inventory Manager");
			put("rn@cab.net", "Admin");
		}
	};
	
	public static Session authenticate(String email, String password) {
		if(usersLogins.get(email) != null && usersLogins.get(email).equals(password)) {
			 return new Session(users.get(email), roles.get(email));
		}
		return  new Session(new User("",""),"");		
	}
}
