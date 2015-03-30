package inventory;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AuthenticatorWithWindow extends JFrame implements ActionListener {

	JTextField user;
	JPasswordField password;
	JButton login;
	int attempts;
	User authenticatedUser;

	// hard coding 3 users in(just for this assignment, as stated)
	User Tom = new User("Tom Jones", "tj@cab.net");
	User Sue = new User("Sue Smith", "ss@cab.net");
	User Ragnar = new User("Ragnar Nelson", "rn@cab.net");
	char[] passwd = { 'h', 'u', 'n', 't', 'e', 'r', '1' };

	Map<String, char[]> usersLogins = new HashMap<String, char[]>() {
		{
			put("tj@cab.net", passwd);
			put("ss@cab.net", passwd);
			put("rn@cab.net", passwd);
		}
	};
	Map<String, User> users = new HashMap<String, User>() {
		{
			put("tj@cab.net", Tom);
			put("cab", Sue);
			put("rn@cab.net", Ragnar);
		}
	};

	public AuthenticatorWithWindow() {
		super("Login");

		setTitle("Login");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		user = new JTextField("Username");
		password = new JPasswordField("Password");
		login = new JButton("login");

		login.setActionCommand("login");
		login.addActionListener(this);

		this.add(user, BorderLayout.NORTH);
		this.add(password, BorderLayout.CENTER);
		this.add(login, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {

		String sLogin = user.getText();
		char[] sPass = password.getPassword();// get text is depreciated

		if (e.getActionCommand().equals("login")) {

			/*
			 * check credentials(according to java themselves this is how to do
			 * it, wtf)
			 */
			if (usersLogins.get(sLogin) != null
					&& Arrays.equals(usersLogins.get(sLogin), sPass)) {
				authenticatedUser = users.get(sLogin);
				Arrays.fill(sPass, '\0');// java docs say correct usage is to
											// clear
				this.dispose();
			} else {
				attempts++;
				JOptionPane.showMessageDialog(new JFrame(), "Incorrect Login: "
						+ attempts);
				password.setText("");
			}
		}
	}

	public User authenticateduser() {
		return this.authenticatedUser;
	}
}
