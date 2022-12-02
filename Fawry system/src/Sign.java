import java.util.*;

public class Sign {
	Vector<User>users;
	
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
		for(int i=0;i<users.size();i++)
		{
			if(users.elementAt(i).getUserName()==userName&&users.elementAt(i).getPassword()==password)
			{
				return (i);
			}
		}
		return -1;
	}
}
