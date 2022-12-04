import java.util.Vector;

public class SignController {
	Sign sign = new Sign();
	Vector<User>users = sign.getAllUsers();
	public Boolean signIn(String userName,String password) {
		int index;
		index=search(userName,password);
		if(index!=-1)//found
		{
			users.elementAt(index).setVerified(true);
			return true;
			//welcome user
		}
		else//not found
		{
			return false;
			//ask to sign up
		}

	}


	public int search(String userName,String password) {
		for(int i=0;i< users.size();i++)
		{
			if(users.elementAt(i).getUserName()==userName && users.elementAt(i).getPassword()==password)
			{
				return (i);
			}
		}
		return -1;
	}
public void signUp(String userName,String password,String email) {
		
		User newUser=new User(userName,password,email,true);
		users.addElement(newUser);
	}
}
