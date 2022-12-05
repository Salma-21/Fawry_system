import java.util.Vector;

public class SignController {
	Sign sign = new Sign();
	Vector<User>users = sign.getAllUsers();
	public User signIn(String userName,String password) {
		User user;
		user=search(userName,password);
		return user;

	}


	public User search(String userName,String password) {
		for(int i=0;i< users.size();i++)
		{
			if(users.elementAt(i).getUserName()==userName && users.elementAt(i).getPassword()==password)
			{
				return users.elementAt(i);
			}
		}
		return null;
	}
public User signUp(String userName,String password,String email ) {
		
		User newUser=new User(userName,password,email);
		users.addElement(newUser);
		return newUser;
	}
}
