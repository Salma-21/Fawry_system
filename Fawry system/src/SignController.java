import java.util.Vector;

public class SignController {
	Sign sign = new Sign();
	//Vector<User>users = new ;
	public User signIn(String userName,String password) {
		User user;
		user =search(userName,password);
		return user;

	}

	public User search(String userName,String password) {
		
		if(sign.users != null ) 
		{
			for(int i=0;i< sign.users.size();i++)
			{
			
				if(sign.users.get(i).getUserName().equals(userName) && sign.users.get(i).getPassword().equals(password))
				{
					//System.out.println(sign.users.get(i).getUserName() + sign.users.get(i).getPassword());
					return sign.users.get(i);
				}
			}
		}
		return null;
	}
    public User signUp(String userName,String password,String email ) {
		User newUser = new User(userName,password,email);
		sign.users.addElement(newUser);
		//users.addElement(newUser);
		return newUser;
	}
}
